package org.shankhadeepghoshal.listmakingapp.itemslistflow.datasrotage.localstorage;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import org.shankhadeepghoshal.listmakingapp.itemslistflow.ItemEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface LocalItemsEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertSingleItem(ItemEntity itemEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertListOfItems(List<ItemEntity> listOfItems);

    @Delete
    Completable deleteSelectedItem(ItemEntity itemEntity);

    @Query("DELETE FROM ItemEntity")
    Completable deleteAllItems();

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