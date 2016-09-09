package com.polymorph.hildajoubert.helena20.util.components;

import android.support.annotation.NonNull;

import com.polymorph.hildajoubert.helena20.ui.activity.ProfileActivity;
import com.polymorph.hildajoubert.helena20.ui.activity.SigninActivity;
import com.polymorph.hildajoubert.helena20.util.modules.GeneralModule;
import com.polymorph.hildajoubert.helena20.util.modules.StorageModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {GeneralModule.class, StorageModule.class})
public interface AppComponent {

    void inject(@NonNull final SigninActivity component);

    void inject(@NonNull final ProfileActivity component);

}
