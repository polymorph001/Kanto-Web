package com.polymorph.hildajoubert.helena20.util.storage;

import android.support.annotation.NonNull;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.Query;

import java.util.List;

import rx.Observable;

public interface Storage {

    Observable<AuthResult> signInWithEmailAndPassword(@NonNull final String email,
                                                      @NonNull final String password);

    Observable<FirebaseUser> observeAuthState();

    <T> Observable<List<T>> observeValuesList(@NonNull final Query query,
                                              @NonNull final Class<T> clazz);
}
