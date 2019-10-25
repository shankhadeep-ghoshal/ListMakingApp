package org.shankhadeepghoshal.listmakingapp.itemslistflow.datasrotage;

import org.shankhadeepghoshal.listmakingapp.itemslistflow.model.ItemEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public interface IItemsDataStore {
    Maybe<Boolean> insertOrUpdateSingleItem(ItemEntity itemEntity);
    Maybe<Boolean> insertOrUpdateListOfItems(List<ItemEntity> listOfItems);
    Maybe<Boolean> deleteSelectedItem(ItemEntity itemEntity);
    Maybe<Boolean> deleteAllItems();
    Flowable<List<ItemEntity>> getAllItems();
    Single<ItemEntity> getSingleItemByName(String itemName);
    Flowable<List<ItemEntity>> getListOfItemsWithUnitPriceInRange(double lowerLimit,
                                                                  double upperLimit);
    Flowable<List<ItemEntity>> getListOfItemsOfGivenCategoryId(int categoryId);
    Flowable<List<ItemEntity>> getListOfItemsOfGivenType(int itemType);
}