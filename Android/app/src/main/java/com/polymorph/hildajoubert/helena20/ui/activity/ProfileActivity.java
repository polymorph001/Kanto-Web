package com.polymorph.hildajoubert.helena20.ui.activity;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.polymorph.hildajoubert.helena20.R;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = ProfileActivity.class.getSimpleName();

    EditText userName;
    EditText email;
    FloatingActionButton fab;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userName = (EditText) findViewById(R.id.userName);
        email = (EditText) findViewById(R.id.email);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            userName.setText(user.getDisplayName());

            email.setText(user.getEmail());
            Uri photoUrl = user.getPhotoUrl();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }
    }

    private void updateProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(userName.getText().toString())
//                .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                .build();

        showProgressDialog();

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
    public void onClick(View view) {
        updateProfile();
    }

    private void showProgressDialog() {
        dialog = new Dialog(this);
        dialog.setTitle("Waiting ....");
        dialog.show();
    }

    private void hideProgressDialog() {
        dialog.hide();
    }
}
