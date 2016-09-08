package com.polymorph.hildajoubert.helena20.util.modules;

import android.app.Application;
import android.support.annotation.NonNull;

import com.polymorph.hildajoubert.helena20.util.storage.FirebaseStorage;
import com.polymorph.hildajoubert.helena20.util.storage.Storage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = GeneralModule.class)
public class StorageModule {

    @Provides
    @Singleton
    Storage provideStorage(@NonNull final Application context) {
        return new FirebaseStorage(context);
    }

}
