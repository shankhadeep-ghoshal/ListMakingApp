package org.shankhadeepghoshal.listmakingapp.algotests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shankhadeepghoshal.listmakingapp.GlobalConstants;
import org.shankhadeepghoshal.listmakingapp.itemslistflow.model.CategoryEntity;
import org.shankhadeepghoshal.listmakingapp.itemslistflow.model.ItemEntity;
import org.shankhadeepghoshal.listmakingapp.utility.algosnds.GroupingAndSortingItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

final class TestGroupingAndSortingItems {
    private static List<ItemEntity> itemEntitiesList;

    @BeforeEach
    void fillItemsList() {
        itemEntitiesList = new ArrayList<>();
        int categoryOne = 1;
        String categoryOneName = "Books";

        for (int i = 1; i < 7; i++) {
            if (i % 2 == 0) {
                itemEntitiesList.add(new ItemEntity(i, i+"",i+"",
                        1, GlobalConstants.METRIC, 2, 0.0, 1.0,
                        new CategoryEntity(categoryOneName,categoryOne),
                        true,
                        false));
            }
        }

        int categoryTwo = 2;
        String categoryTwoName = "Sugars";

        for (int i = 1; i < 7; i++) {
            if (i % 2 == 1){
                itemEntitiesList.add(new ItemEntity(i, i+"", i+"",
                        2, GlobalConstants.IMPERIAL, 4,0.0, 0.2,
                        new CategoryEntity(categoryTwoName, categoryTwo),
                        false,
                        true));
            }
        }
    }

    @Test
    void testGroupingItemsBasedOnCategory() {
        Map<Integer, ConcurrentSkipListSet<ItemEntity>> groupedMap =
                GroupingAndSortingItems.groupByCategories(itemEntitiesList,
                        (o1,o2) -> o1.getItemNumber() - o2.getItemNumber());

        Assertions.assertIterableEquals(new ArrayList<Integer>()
                                        {{add(2);add(4);add(6);add(1);add(3);add(5);}},
                GroupingAndSortingItems.convertMapToList(groupedMap)
                        .stream()
                        .map(itemEntity -> Integer.parseInt(itemEntity.getItemName()))
                        .collect(Collectors.toList()));
    }

    @Test
    void testSortItemsBasedOnItemName() {
        Assertions.assertIterableEquals(new ArrayList<Integer>()
                                        {{add(1);add(2);add(3);add(4);add(5);add(6);}},
                GroupingAndSortingItems.sortListItems(itemEntitiesList,
                ((o1, o2) -> o1.getItemName().compareToIgnoreCase(o2.getItemName())))
                .stream()
                .map(itemEntity -> Integer.parseInt(itemEntity.getItemName()))
                    .collect(Collectors.toList()));
    }
}