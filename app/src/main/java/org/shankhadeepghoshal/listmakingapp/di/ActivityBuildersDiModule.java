package org.shankhadeepghoshal.listmakingapp.di;

import org.shankhadeepghoshal.listmakingapp.loginandsignupflow.ActivityLoginSignUpScreen;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersDiModule {
    @ContributesAndroidInjector
    public abstract ActivityLoginSignUpScreen contributeActivityLoginSignUpScreen();
}