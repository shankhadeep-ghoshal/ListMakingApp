package org.shankhadeepghoshal.listmakingapp.itemslistflow.datasrotage.localstorage;

import org.shankhadeepghoshal.listmakingapp.itemslistflow.ItemEntity;
import org.shankhadeepghoshal.listmakingapp.itemslistflow.datasrotage.IItemsDataStore;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class LocalDataStore implements IItemsDataStore {
    private final LocalItemsEntityDao localItemsEntityDao;

    public LocalDataStore(LocalItemsEntityDao localItemsEntityDao) {
        this.localItemsEntityDao = localItemsEntityDao;
    }

    @Override
    public Completable insertOrUpdateSingleItem(ItemEntity itemEntity) {
        return localItemsEntityDao.insertSingleItem(itemEntity);
    }

    @Override
    public Completable insertOrUpdateListOfItems(List<ItemEntity> listOfItems) {
        return localItemsEntityDao.insertListOfItems(listOfItems);
    }

    @Override
    public Completable deleteSelectedItem(ItemEntity itemEntity) {
        return localItemsEntityDao.deleteSelectedItem(itemEntity);
    }

    @Override
    public Completable deleteAllItems() {
        return localItemsEntityDao.deleteAllItems();
    }

    @Override
    public Flowable<List<ItemEntity>> getAllItems() {
        return localItemsEntityDao.getAllItems();
    }

    @Override
    public Single<ItemEntity> getSingleItemByName(String itemName) {
        return localItemsEntityDao.getSingleItemByName(itemName);
    }

    @Override
    public Flowable<List<ItemEntity>>
    getListOfItemsWithUnitPriceInRange(double lowerLimit, double upperLimit) {
        return localItemsEntityDao.getListOfItemsWithUnitPriceInRange(lowerLimit, upperLimit);
    }

    @Override
    public Flowable<List<ItemEntity>> getListOfItemsOfGivenCategoryId(int categoryId) {
        return localItemsEntityDao.getListOfItemsOfGivenCategoryId(categoryId);
    }

    @Override
    public Flowable<List<ItemEntity>> getListOfItemsOfGivenType(int itemType) {
        return localItemsEntityDao.getListOfItemsOfGivenType(itemType);
    }
}