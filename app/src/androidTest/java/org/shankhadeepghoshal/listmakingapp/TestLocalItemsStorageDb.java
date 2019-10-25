package org.shankhadeepghoshal.listmakingapp;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.shankhadeepghoshal.listmakingapp.itemslistflow.model.CategoryEntity;
import org.shankhadeepghoshal.listmakingapp.itemslistflow.model.ItemEntity;
import org.shankhadeepghoshal.listmakingapp.itemslistflow.datasrotage.localstorage.LocalDataStoreItemsList;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class TestLocalItemsStorageDb {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private static final List<ItemEntity> listOfGroceryItems = new ArrayList<ItemEntity>(){{
        for (int i = 1; i < 10; i++) {
            add(new ItemEntity(i, i+"",i+"",
                    1, GlobalConstants.METRIC, 2, 0.0,
                    1.0 + 2.3 + ((double) i),
                    new CategoryEntity("Grocery",1),
                    true,
                    false));
        }
    }};

    private static final List<ItemEntity> listOfClothing = new ArrayList<ItemEntity>(){{
        for (int i = 10; i < 20; i++) {
            add(new ItemEntity(i, i+"",i+"",
                    2, GlobalConstants.IMPERIAL, 3, 0.0,1.0 + 1.6,
                    new CategoryEntity("Clothing",2),
                    false,
                    true));
        }
    }};

    private static final ItemEntity rice = new ItemEntity(55,
            55+"",55+"",
            3, GlobalConstants.METRIC, 2,
            0.0,1.0,
            new CategoryEntity("Food",3),
            true,
            false);

    private static final ItemEntity detergent = new ItemEntity(65,
            65+"",65+"",
            4, GlobalConstants.IMPERIAL, 4,
            0.0,1.0,
            new CategoryEntity("Washing",4),
            true,
            true);

    private LocalDatabase localDatabase;
    private LocalDataStoreItemsList localDataStoreItemsList;

    @Before
    public void initDb() {
        localDatabase = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
                LocalDatabase.class)
                .allowMainThreadQueries()
                .build();

        localDataStoreItemsList = new LocalDataStoreItemsList(localDatabase.getLocalItemsEntityDao());
    }

    @After
    public void closeDb() {
        localDatabase.close();
    }

    @Test
    public void testInsertAndGetSingleItem() {
        localDataStoreItemsList.insertOrUpdateSingleItem(rice).blockingGet();
        localDataStoreItemsList.getSingleItemByName("55")
                .test()
                .assertValue(itemEntity ->
                        itemEntity != null && itemEntity.equals(rice));
        localDatabase.clearAllTables();
    }

    @Test
    public void testInsertAndGetListOfItems() {
        localDataStoreItemsList.insertOrUpdateListOfItems(listOfClothing).blockingGet();
        localDataStoreItemsList.getAllItems()
                .test()
                .assertValue(itemEntities ->
                        itemEntities != null && itemEntities.containsAll(listOfClothing));
        localDatabase.clearAllTables();
    }

    @Test
    public void testGetSelectedItemByName() {
        localDataStoreItemsList.insertOrUpdateSingleItem(detergent).blockingGet();
        localDataStoreItemsList.getSingleItemByName(detergent.getItemName())
                .test()
                .assertValue(itemEntity ->
                        itemEntity != null && itemEntity.getItemName()
                                .equals(detergent.getItemName()));
        localDatabase.clearAllTables();
    }

    @Test
    public void testGetListOfItemsInPriceRange() {
        localDataStoreItemsList.insertOrUpdateListOfItems(listOfGroceryItems).blockingGet();
        localDataStoreItemsList.getListOfItemsWithUnitPriceInRange(1.0,6.0)
                .test()
                .assertValue(itemEntities ->
                        itemEntities != null
                                && itemEntities.size() == 2
                                && itemEntities.get(0).equals(listOfGroceryItems.get(0))
                                && itemEntities.get(1).equals(listOfGroceryItems.get(1)));
        localDatabase.clearAllTables();
    }

    @Test
    public void testGetListOfItemsByCategory() {
        localDataStoreItemsList.insertOrUpdateListOfItems(listOfClothing).blockingGet();
        localDataStoreItemsList.getListOfItemsOfGivenCategoryId(2)
                .test()
                .assertValue(itemEntities ->
                        itemEntities != null && itemEntities.containsAll(listOfClothing));
        localDatabase.clearAllTables();
    }
}