package org.shankhadeepghoshal.listmakingapp.di;

import org.shankhadeepghoshal.listmakingapp.itemslistflow.FragmentListItemsScreen;
import org.shankhadeepghoshal.listmakingapp.itemslistflow.di.ListItemScreenDiModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersDiModule {
    @ContributesAndroidInjector(modules = {ListItemScreenDiModule.class})
    public abstract FragmentListItemsScreen contributesFragmentListItemsScreen();
}