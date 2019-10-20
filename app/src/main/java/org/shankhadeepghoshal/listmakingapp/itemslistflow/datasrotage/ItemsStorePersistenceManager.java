package org.shankhadeepghoshal.listmakingapp.itemslistflow.datasrotage;

import org.shankhadeepghoshal.listmakingapp.itemslistflow.ItemEntity;
import org.shankhadeepghoshal.listmakingapp.itemslistflow.datasrotage.localstorage.LocalItemsEntityDao;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class ItemsStorePersistenceManager {
    private final LocalItemsEntityDao localItemsEntityDao;

    public ItemsStorePersistenceManager(LocalItemsEntityDao localItemsEntityDao) {
        this.localItemsEntityDao = localItemsEntityDao;
    }

    public Completable insertOrUpdateSingleItem(ItemEntity itemEntity) {
        return localItemsEntityDao.insertSingleItem(itemEntity);
    }

    public Completable insertOrUpdateListOfItems(List<ItemEntity> listOfItems) {
        return localItemsEntityDao.insertListOfItems(listOfItems);
    }

    public Completable deleteSelectedItem(ItemEntity itemEntity) {
        return localItemsEntityDao.deleteSelectedItem(itemEntity);
    }

    public Completable deleteAllItems() {
        return localItemsEntityDao.deleteAllItems();
    }

    public Flowable<List<ItemEntity>> getAllItems() {
        return localItemsEntityDao.getAllItems();
    }

    public Single<ItemEntity> getSingleItemByName(String itemName) {
        return localItemsEntityDao.getSingleItemByName(itemName);
    }

    public Flowable<List<ItemEntity>>
    getListOfItemsWithUnitPriceInRange(double lowerLimit, double upperLimit) {
        return localItemsEntityDao.getListOfItemsWithUnitPriceInRange(lowerLimit, upperLimit);
    }

    public Flowable<List<ItemEntity>> getListOfItemsOfGivenCategoryId(int categoryId) {
        return localItemsEntityDao.getListOfItemsOfGivenCategoryId(categoryId);
    }

    public Flowable<List<ItemEntity>> getListOfItemsOfGivenType(int itemType) {
        return localItemsEntityDao.getListOfItemsOfGivenType(itemType);
    }
}