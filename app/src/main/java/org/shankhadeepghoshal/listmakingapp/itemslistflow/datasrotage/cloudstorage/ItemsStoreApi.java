package org.shankhadeepghoshal.listmakingapp.itemslistflow.datasrotage.cloudstorage;

import org.shankhadeepghoshal.listmakingapp.itemslistflow.model.ItemEntity;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ItemsStoreApi {
    @POST(value = "addOne")
    Maybe<Boolean> insertOrUpdateItem(ItemEntity itemEntity);

    @POST(value = "addMany")
    Maybe<Boolean> insertOrUpdateListOfItems(List<ItemEntity> listOfItems);

    @DELETE(value = "deleteOne/{itemId}")
    Maybe<Boolean> deleteSingleItem(@Path("itemId")int itemId);

    @POST(value = "deleteAll")
    Maybe<Boolean> deleteAllItems();

    @GET(value = "allItems")
    Single<List<ItemEntity>> getAllItemsOfUser();

    @GET(value = "singleItem/{itemName}")
    Single<ItemEntity>
    getSingleItemEntityByName(@Path("itemName")String itemName);

    @GET(value = "allItemsInPriceRange/{lowerLimit}/{upperLimit}")
    Single<List<ItemEntity>>
    getItemsInPriceRange(@Path("lowerLimit")double lowerLimit,
                         @Path("upperLimit")double upperLimit);

    @GET(value = "getItemsOfCategory/{categoryId}")
    Single<List<ItemEntity>>
    getItemsOfGivenCategory(@Path("categoryId")int categoryId);

    @GET(value = "getItemsOfCategory/{typeId}")
    Single<List<ItemEntity>>
    getItemsOfGivenType(@Path("typeId")int typeId);
}