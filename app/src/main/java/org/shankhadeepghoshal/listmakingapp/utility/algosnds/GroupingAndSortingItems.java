package org.shankhadeepghoshal.listmakingapp.utility.algosnds;

import org.shankhadeepghoshal.listmakingapp.itemslistflow.ItemEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class GroupingAndSortingItems {

    public static <T extends ItemEntity> Map<Integer, ConcurrentSkipListSet<T>>
    groupByCategories(List<T> itemsList, Comparator<T> sortingSpecifier) {
        Map<Integer, ConcurrentSkipListSet<T>> groupedMap = new ConcurrentHashMap<>();

        for (T item : itemsList) {
            if (!groupedMap.containsKey(item.getCategoryEntity().getCategoryId())) {
                groupedMap.put(item.getCategoryEntity().getCategoryId(),
                        new ConcurrentSkipListSet<>(sortingSpecifier));
            }

            groupedMap.get(item.getCategoryEntity().getCategoryId()).add(item);
        }

        return groupedMap;
    }

    public static <T extends ItemEntity> Set<T> sortListItems(List<T> itemsList,
                                                                        Comparator<T> comparator) {
        final ConcurrentSkipListSet<T> sortedConcurrentSet =
                new ConcurrentSkipListSet<>(comparator);

        sortedConcurrentSet.addAll(itemsList);

        return sortedConcurrentSet;
    }

    public static <T extends ItemEntity, U extends Set<T>> List<T>
    convertMapToList(Map<Integer, U> listMap) {
        List<T> returnList = new ArrayList<>();

        for (Map.Entry<Integer, U> entry : listMap.entrySet()) {
            returnList.addAll(entry.getValue());
        }

        return returnList;
    }
}