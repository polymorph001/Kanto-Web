package com.polymorph.hildajoubert.helena20.util.storage;

import android.content.Context;
import android.support.annotation.NonNull;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import rx.Observable;
import rx.Subscriber;

public class FirebaseStorage implements Storage {

    private FirebaseAuth mAuth;

    public FirebaseStorage(@NonNull final Context context) {
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public Observable<AuthResult> signInWithEmailAndPassword(@NonNull final String email,
                                                             @NonNull final String password) {
        return Observable.create(new Observable.OnSubscribe<AuthResult>() {
            @Override
            public void call(final Subscriber<? super AuthResult> subscriber) {
                execute(subscriber, mAuth.signInWithEmailAndPassword(email, password));
            }
        });
    }

    private <T> void execute(@NonNull final Subscriber<? super T> subscriber, @NonNull final Task<T> task) {
        task
                .addOnSuccessListener(new OnSuccessListener<T>() {
                    @Override
                    public void onSuccess(T task) {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(task);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onError(e);
                        }
                    }
                })
                .addOnCompleteListener(new OnCompleteListener<T>() {
                    @Override
                    public void onComplete(@NonNull Task<T> task) {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onCompleted();
                        }
                    }
                });
    }
}
