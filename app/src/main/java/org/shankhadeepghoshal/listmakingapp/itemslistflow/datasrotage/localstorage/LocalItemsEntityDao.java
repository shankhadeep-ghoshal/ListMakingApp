package org.shankhadeepghoshal.listmakingapp.itemslistflow.datasrotage.localstorage;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import org.shankhadeepghoshal.listmakingapp.itemslistflow.model.ItemEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface LocalItemsEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long> insertSingleItem(ItemEntity itemEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<List<Long>> insertListOfItems(List<ItemEntity> listOfItems);

    @Delete
    Single<Integer> deleteSelectedItem(ItemEntity itemEntity);

    @Query("DELETE FROM ItemEntity")
    Single<Integer> deleteAllItems();

    @Query("SELECT * FROM ItemEntity")
    Flowable<List<ItemEntity>> getAllItems();

    @Query("SELECT * FROM ItemEntity WHERE itemName LIKE :itemName LIMIT 1")
    Single<ItemEntity> getSingleItemByName(String itemName);

    @Query("SELECT * FROM ItemEntity WHERE unitPrice BETWEEN (:lowerLimit) AND (:upperLimit)")
    Flowable<List<ItemEntity>> getListOfItemsWithUnitPriceInRange(double lowerLimit,
                                                                  double upperLimit);

    @Query("SELECT * FROM ItemEntity WHERE categoryId = :categoryId")
    Flowable<List<ItemEntity>> getListOfItemsOfGivenCategoryId(int categoryId);

    @Query("SELECT * FROM ItemEntity WHERE itemType = :itemType")
    Flowable<List<ItemEntity>> getListOfItemsOfGivenType(int itemType);
}