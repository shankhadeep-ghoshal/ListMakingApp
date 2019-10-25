package org.shankhadeepghoshal.listmakingapp.itemslistflow.di;

import org.shankhadeepghoshal.listmakingapp.LocalDatabase;
import org.shankhadeepghoshal.listmakingapp.itemslistflow.datasrotage.ItemsStoreRepository;
import org.shankhadeepghoshal.listmakingapp.itemslistflow.datasrotage.cloudstorage.ItemsStoreApi;
import org.shankhadeepghoshal.listmakingapp.itemslistflow.datasrotage.localstorage.LocalDataStoreItemsList;
import org.shankhadeepghoshal.listmakingapp.itemslistflow.datasrotage.localstorage.LocalItemsEntityDao;
import org.shankhadeepghoshal.listmakingapp.itemslistflow.viewhandling.ListItemsRecyclerViewAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ListItemScreenDiModule {

    @Provides
    public ListItemsRecyclerViewAdapter provideListItemsRecyclerViewAdapter() {
        return new ListItemsRecyclerViewAdapter();
    }

    @Provides
    public LocalItemsEntityDao providesLocalItemsEntityDao(LocalDatabase localDatabase) {
        return localDatabase.getLocalItemsEntityDao();
    }

    @Provides
    public LocalDataStoreItemsList
    provideLocalDataStoreItemsList(LocalItemsEntityDao localItemsEntityDao) {
        return new LocalDataStoreItemsList(localItemsEntityDao);
    }

    @Provides
    public ItemsStoreApi provideItemsStoreApi(Retrofit retrofit) {
        return retrofit.create(ItemsStoreApi.class);
    }

    @Provides
    public ItemsStoreRepository
    provideItemsStoreRepository(LocalDataStoreItemsList localDataStoreItemsList,
                                ItemsStoreApi itemsStoreApi) {
        return new ItemsStoreRepository(localDataStoreItemsList, itemsStoreApi);
    }
}