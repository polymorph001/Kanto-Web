package com.polymorph.hildajoubert.helena20.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import rx.subscriptions.CompositeSubscription;

public class BaseActivity extends AppCompatActivity {

    protected CompositeSubscription subs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        subs = new CompositeSubscription();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (!subs.isUnsubscribed()) {
            subs.clear();
        }
    }
}
