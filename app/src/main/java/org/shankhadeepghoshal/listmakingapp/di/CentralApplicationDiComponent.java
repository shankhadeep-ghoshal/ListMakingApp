package org.shankhadeepghoshal.listmakingapp.di;

import android.app.Application;

import org.shankhadeepghoshal.listmakingapp.CentralApplicationClass;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {
        AndroidSupportInjectionModule.class,
        CentralApplicationDiModule.class,
        ActivityBuildersDiModule.class,
        FragmentBuildersDiModule.class
})
public interface CentralApplicationDiComponent extends AndroidInjector<CentralApplicationClass> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        CentralApplicationDiComponent build();
    }
}