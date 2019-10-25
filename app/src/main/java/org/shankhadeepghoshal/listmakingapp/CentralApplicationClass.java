package org.shankhadeepghoshal.listmakingapp;

import org.shankhadeepghoshal.listmakingapp.di.DaggerCentralApplicationDiComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class CentralApplicationClass extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerCentralApplicationDiComponent.builder().application(this).build();
    }
}
