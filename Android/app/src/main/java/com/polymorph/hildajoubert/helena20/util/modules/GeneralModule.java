package com.polymorph.hildajoubert.helena20.util.modules;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.polymorph.hildajoubert.helena20.util.preferences.AppPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class GeneralModule {
    Application app;

    public GeneralModule(@NonNull final Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return app;
    }

    @Provides
    @Singleton
    public AppPreferences provideAppPreferences(@NonNull final Application app) {
        return new AppPreferences(app);
    }
}
