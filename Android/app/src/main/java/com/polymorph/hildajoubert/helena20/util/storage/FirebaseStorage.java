package com.polymorph.hildajoubert.helena20.util.storage;

import android.content.Context;
import android.support.annotation.NonNull;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

public class FirebaseStorage implements Storage {

    private FirebaseAuth firebaseAuth;

    public FirebaseStorage(@NonNull final Context context) {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public Observable<AuthResult> signInWithEmailAndPassword(@NonNull final String email,
                                                             @NonNull final String password) {
        return Observable.create(new Observable.OnSubscribe<AuthResult>() {
            @Override
            public void call(final Subscriber<? super AuthResult> subscriber) {
                execute(subscriber, firebaseAuth.signInWithEmailAndPassword(email, password));
            }
        });
    }

    @Override
    public Observable<FirebaseUser> observeAuthState() {
        return Observable.create(new Observable.OnSubscribe<FirebaseUser>() {
            @Override
            public void call(final Subscriber<? super FirebaseUser> subscriber) {
                final FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(firebaseAuth.getCurrentUser());
                        }
                    }
                };
                firebaseAuth.addAuthStateListener(authStateListener);

                subscriber.add(Subscriptions.create(new Action0() {
                    @Override
                    public void call() {
                        firebaseAuth.removeAuthStateListener(authStateListener);
                    }
                }));
            }
        });
    }

    @NonNull
    public <T> Observable<List<T>> observeValuesList(@NonNull final Query query, @NonNull final Class<T> clazz) {
        return Observable.create(new Observable.OnSubscribe<List<T>>() {
            @Override
            public void call(final Subscriber<? super List<T>> subscriber) {
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<T> items = new ArrayList<T>();
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            T value = childSnapshot.getValue(clazz);
                            if (value == null) {
                                if (!subscriber.isUnsubscribed()) {
                                    subscriber.onError(new Exception("unable to cast firebase data response to " + clazz.getSimpleName()));
                                }
                            } else {
                                items.add(value);
                            }
                        }

                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(items);
                            subscriber.onCompleted();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onError(new Exception(error.toException()));
                        }
                    }
                });
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
