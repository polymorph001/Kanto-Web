package com.polymorph.hildajoubert.helena20.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.jakewharton.rxbinding.view.RxView;
import com.polymorph.hildajoubert.helena20.MainApplication;
import com.polymorph.hildajoubert.helena20.R;
import com.polymorph.hildajoubert.helena20.util.storage.Storage;

import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class ProfileActivity extends BaseActivity {

    private static final String TAG = ProfileActivity.class.getSimpleName();
    private final static int PICK_FROM_GALLERY = 1001;

    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.fab)
    View btnFab;
    @BindView(R.id.profileImage)
    CircleImageView profileImage;

    Dialog dialog;

    @Inject
    Storage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);

        // Dagger 2 inject
        ((MainApplication) getApplication()).getAppComponent().inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            userName.setText(user.getDisplayName());
            email.setText(user.getEmail());

            final Uri photoUrl = user.getPhotoUrl();
            if (photoUrl != null) {
                Glide.with(this)
                        .load(photoUrl)
                        .fitCenter()
                        .into(profileImage);
            }

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }
    }

    private void updateProfile() {
        showProgressDialog();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(userName.getText().toString())
//                .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                .build();


        profileImage.setDrawingCacheEnabled(true);
        profileImage.buildDrawingCache();
        Bitmap bitmap = profileImage.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();


        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                            Toast.makeText(ProfileActivity.this, "Profile Updated", Toast.LENGTH_LONG).show();
                        }

                        hideProgressDialog();
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();

        Subscription subFab = RxView.clicks(btnFab)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        updateProfile();
                    }
                });
        subs.add(subFab);

        Subscription subAvatar = RxView.clicks(profileImage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Avatar"), PICK_FROM_GALLERY);
                    }
                });
        subs.add(subAvatar);
    }

    private void showProgressDialog() {
        dialog = new Dialog(this);
        dialog.setTitle("Waiting ....");
        dialog.show();
    }

    private void hideProgressDialog() {
        dialog.hide();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Request to gallery
        if (requestCode == PICK_FROM_GALLERY) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    Glide.with(this)
                            .load(data.getData())
                            .fitCenter()
                            .into(profileImage);

//                    Bitmap bitmap = null;
//                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
//
//                    int height = bitmap.getHeight();
//                    int width = bitmap.getWidth();
//
//                    // Compress Bitmap
//                    bitmap = Bitmap.createScaledBitmap(bitmap, 640, height / (width / 640), true);
//
//                    profileImage.setImageBitmap(bitmap);
//                } catch (IOException e) {
//                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
