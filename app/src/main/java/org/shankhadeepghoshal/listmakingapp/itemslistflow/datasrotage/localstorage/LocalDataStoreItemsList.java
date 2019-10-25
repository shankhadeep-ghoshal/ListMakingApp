package org.shankhadeepghoshal.listmakingapp.itemslistflow.datasrotage.localstorage;

import org.shankhadeepghoshal.listmakingapp.itemslistflow.datasrotage.IItemsDataStore;
import org.shankhadeepghoshal.listmakingapp.itemslistflow.model.ItemEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public class LocalDataStoreItemsList implements IItemsDataStore {
    private final LocalItemsEntityDao localItemsEntityDao;

    public LocalDataStoreItemsList(LocalItemsEntityDao localItemsEntityDao) {
        this.localItemsEntityDao = localItemsEntityDao;
    }

    @Override
    public Maybe<Boolean> insertOrUpdateSingleItem(ItemEntity itemEntity) {
        return localItemsEntityDao.insertSingleItem(itemEntity)
                .map(aLong -> aLong > 0L)
                .toMaybe();
    }

    @Override
    public Maybe<Boolean> insertOrUpdateListOfItems(List<ItemEntity> listOfItems) {
        return localItemsEntityDao.insertListOfItems(listOfItems)
                .map(longList -> longList.size() > 0)
                .toMaybe();
    }

    @Override
    public Maybe<Boolean> deleteSelectedItem(ItemEntity itemEntity) {
        return localItemsEntityDao.deleteSelectedItem(itemEntity)
                .map(integer -> integer > 0)
                .toMaybe();
    }

    @Override
    public Maybe<Boolean> deleteAllItems() {
        return localItemsEntityDao.deleteAllItems()
                .map(integer -> integer > 0)
                .toMaybe();
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