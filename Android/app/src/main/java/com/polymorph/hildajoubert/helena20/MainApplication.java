package com.polymorph.hildajoubert.helena20;

import android.app.Application;
import android.content.Context;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.polymorph.hildajoubert.helena20.util.components.AppComponent;
import com.polymorph.hildajoubert.helena20.util.components.DaggerAppComponent;
import com.polymorph.hildajoubert.helena20.util.modules.StorageModule;
import com.polymorph.hildajoubert.helena20.util.modules.GeneralModule;

import net.danlew.android.joda.JodaTimeAndroid;

import timber.log.Timber;

import static timber.log.Timber.DebugTree;

public class MainApplication extends Application {

    private static final String LOG_TAG = MainApplication.class.getSimpleName();

    private static Context appContext;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Iconify.with(new FontAwesomeModule());

        JodaTimeAndroid.init(this);

        Iconify.with(new FontAwesomeModule());

        if (BuildConfig.DEBUG) {
            Timber.plant(new DebugTree());
        }

        // Dagger 2 app component
        appComponent = createAppComponent();
    }

    private AppComponent createAppComponent() {
        return DaggerAppComponent.builder()
                .generalModule(new GeneralModule(this))
                .storageModule(new StorageModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
