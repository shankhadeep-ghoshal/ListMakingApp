package org.shankhadeepghoshal.listmakingapp.itemslistflow.datasrotage;

import org.shankhadeepghoshal.listmakingapp.itemslistflow.ItemEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface IItemsDataStore {
    Completable insertOrUpdateSingleItem(ItemEntity itemEntity);
    Completable insertOrUpdateListOfItems(List<ItemEntity> listOfItems);
    Completable deleteSelectedItem(ItemEntity itemEntity);
    Completable deleteAllItems();
    Flowable<List<ItemEntity>> getAllItems();
    Single<ItemEntity> getSingleItemByName(String itemName);
    Flowable<List<ItemEntity>> getListOfItemsWithUnitPriceInRange(double lowerLimit,
                                                                  double upperLimit);
    Flowable<List<ItemEntity>> getListOfItemsOfGivenCategoryId(int categoryId);
    Flowable<List<ItemEntity>> getListOfItemsOfGivenType(int itemType);
}