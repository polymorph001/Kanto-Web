package com.polymorph.hildajoubert.helena20.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jakewharton.rxbinding.view.RxView;
import com.polymorph.hildajoubert.helena20.KnowYourCompanyLauncherActivity;
import com.polymorph.hildajoubert.helena20.MainApplication;
import com.polymorph.hildajoubert.helena20.R;
import com.polymorph.hildajoubert.helena20.util.storage.Storage;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class SigninActivity extends BaseActivity {

    private static final String TAG = "EmailPassword";

    @BindView(R.id.status)
    TextView mStatusTextView;
    @BindView(R.id.detail)
    TextView mDetailTextView;
    @BindView(R.id.field_email)
    EditText mEmailField;
    @BindView(R.id.field_password)
    EditText mPasswordField;
    @BindView(R.id.email_sign_in_button)
    View btnSignIn;

    Dialog dialog;


    @Inject
    Storage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        ButterKnife.bind(this);

        // Dagger 2 inject
        ((MainApplication) getApplication()).getAppComponent().inject(this);

    }

    @Override
    public void onStart() {
        super.onStart();

        Subscription subAuthState = storage.observeAuthState()
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<FirebaseUser, Boolean>() {
                    @Override
                    public Boolean call(FirebaseUser firebaseUser) {
                        return firebaseUser != null;
                    }
                })
                .subscribe(new Action1<FirebaseUser>() {
                    @Override
                    public void call(FirebaseUser firebaseUser) {
                        // User is signed in
                        showProfileActivity();
                    }
                });
        subs.add(subAuthState);

        Subscription subBtn = RxView.clicks(btnSignIn)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<Void, Boolean>() {
                    @Override
                    public Boolean call(Void aVoid) {
                        return validateForm();
                    }
                })
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        doLogin();
                    }
                });
        subs.add(subBtn);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void doLogin() {
        final String email = mEmailField.getText().toString();
        final String password = mPasswordField.getText().toString();

        showProgressDialog();

        Subscription sub = storage.signInWithEmailAndPassword(email, password)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideProgressDialog();
                    }
                })
                .doOnNext(new Action1<AuthResult>() {
                    @Override
                    public void call(AuthResult authResult) {
                        hideProgressDialog();
                    }
                })
                .subscribe(new Action1<AuthResult>() {
                    @Override
                    public void call(AuthResult authResult) {
                        // onNext
                        if (authResult.getUser() == null) {
                            mStatusTextView.setText(R.string.auth_failed);
                        } else {
                            showProfileActivity();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        // onError
                        mStatusTextView.setText(R.string.auth_failed);
                    }
                });
        subs.add(sub);
    }

    private void showProfileActivity() {
        startActivity(new Intent(this, KnowYourCompanyLauncherActivity.class));

        finish();
    }

    private void showProgressDialog() {
        dialog = new Dialog(this);
        dialog.show();
    }

    private void hideProgressDialog() {
        dialog.hide();
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }

}