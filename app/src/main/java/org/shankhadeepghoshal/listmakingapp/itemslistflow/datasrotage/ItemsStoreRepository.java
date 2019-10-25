package org.shankhadeepghoshal.listmakingapp.itemslistflow.datasrotage;

import org.shankhadeepghoshal.listmakingapp.itemslistflow.datasrotage.cloudstorage.ItemsStoreApi;
import org.shankhadeepghoshal.listmakingapp.itemslistflow.datasrotage.localstorage.LocalDataStoreItemsList;
import org.shankhadeepghoshal.listmakingapp.itemslistflow.model.ItemEntity;
import org.shankhadeepghoshal.listmakingapp.utility.NullResolverClass;
import org.shankhadeepghoshal.listmakingapp.utility.Resource;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@SuppressWarnings("ALL")
public class ItemsStoreRepository {
    private final LocalDataStoreItemsList localItemsEntityDao;
    private final ItemsStoreApi itemsStoreApi;

    public ItemsStoreRepository(LocalDataStoreItemsList localItemsEntityDao,
                                ItemsStoreApi itemsStoreApi) {
        this.localItemsEntityDao = localItemsEntityDao;
        this.itemsStoreApi = itemsStoreApi;
    }

    public Maybe<Resource<Boolean>> insertOrUpdateSingleItem(ItemEntity itemEntity) {
        return Maybe.merge(localItemsEntityDao
                .insertOrUpdateSingleItem(itemEntity)
                .subscribeOn(Schedulers.io()),
                itemsStoreApi.insertOrUpdateItem(itemEntity).subscribeOn(Schedulers.io()))
                .reduce((aBoolean, aBoolean2) -> aBoolean && aBoolean2)
                .map(o -> o != null && o ? Resource.success(true) : Resource.error(false));
    }

    public Maybe<Resource<Boolean>> insertOrUpdateListOfItems(List<ItemEntity> listOfItems) {
        return Maybe.merge(localItemsEntityDao
                        .insertOrUpdateListOfItems(listOfItems)
                        .subscribeOn(Schedulers.io()),
                itemsStoreApi.insertOrUpdateListOfItems(listOfItems).subscribeOn(Schedulers.io()))
                .reduce((aBoolean, aBoolean2) -> aBoolean && aBoolean2)
                .map(o -> o != null && o ? Resource.success(true) : Resource.error(false));
    }

    public Maybe<Resource<Boolean>> deleteSelectedItem(ItemEntity itemEntity) {
        return Maybe.merge(localItemsEntityDao
                        .deleteSelectedItem(itemEntity)
                        .subscribeOn(Schedulers.io()),
                itemsStoreApi.deleteSingleItem(itemEntity.getItemNumber())
                        .subscribeOn(Schedulers.io()))
                .reduce((aBoolean, aBoolean2) -> aBoolean && aBoolean2)
                .map(o -> o != null && o ? Resource.success(true) : Resource.error(false));
    }

    public Maybe<Resource<Boolean>> deleteAllItems() {
        return Maybe.merge(localItemsEntityDao
                        .deleteAllItems()
                        .subscribeOn(Schedulers.io()),
                itemsStoreApi.deleteAllItems()
                        .subscribeOn(Schedulers.io()))
                .reduce((aBoolean, aBoolean2) -> aBoolean && aBoolean2)
                .map(o -> o != null && o ? Resource.success(true) : Resource.error(false));
    }

    public Flowable<Resource<List<ItemEntity>>> getAllItems() {
        return localItemsEntityDao.getAllItems()
                .subscribeOn(Schedulers.io())
                .filter(itemEntities -> NullResolverClass.checkNullOrEmptyCollection(itemEntities))
                .map(itemEntities -> Resource.success(itemEntities))
                .onErrorResumeNext(Flowable.generate(resourceEmitter ->
                        itemsStoreApi.getAllItemsOfUser()
                                .subscribeOn(Schedulers.io())
                                .filter(itemEntities ->
                                        NullResolverClass
                                                .checkNullOrEmptyCollection(itemEntities))
                                .map(itemEntities -> Resource.success(itemEntities))
                                .doOnSuccess(listResource ->
                                        localItemsEntityDao
                                                .insertOrUpdateListOfItems(listResource.data)
                                                .subscribeOn(Schedulers.io()))));
    }

    public Single<Resource<ItemEntity>> getSingleItemByName(String itemName) {
        return localItemsEntityDao.getSingleItemByName(itemName)
                .subscribeOn(Schedulers.io())
                .filter(itemEntity -> itemEntity != null)
                .toSingle()
                .map(itemEntity -> Resource.success(itemEntity))
                .onErrorResumeNext(itemsStoreApi.getSingleItemEntityByName(itemName)
                        .subscribeOn(Schedulers.io())
                        .filter(itemEntity -> itemEntity != null)
                        .toSingle()
                        .map(itemEntity -> Resource.success(itemEntity))
                        .doOnSuccess(itemEntityResource ->
                                localItemsEntityDao
                                        .insertOrUpdateSingleItem(itemEntityResource.data)
                                        .subscribeOn(Schedulers.io()))
                        .onErrorResumeNext(throwable ->
                                Single.just(Resource.error(new ItemEntity()))));

    }

    public Flowable<Resource<List<ItemEntity>>>
    getListOfItemsWithUnitPriceInRange(double lowerLimit, double upperLimit) {
        return localItemsEntityDao.getListOfItemsWithUnitPriceInRange(lowerLimit, upperLimit)
                .subscribeOn(Schedulers.io())
                .filter(itemEntities -> NullResolverClass.checkNullOrEmptyCollection(itemEntities))
                .map(itemEntities -> Resource.success(itemEntities))
                .onErrorResumeNext(Flowable.generate(resourceEmitter ->
                        itemsStoreApi.getItemsInPriceRange(lowerLimit, upperLimit)
                                .subscribeOn(Schedulers.io())
                                .filter(itemEntities ->
                                        NullResolverClass
                                                .checkNullOrEmptyCollection(itemEntities))
                                .map(itemEntities -> Resource.success(itemEntities))
                                .doOnSuccess(listResource ->
                                        localItemsEntityDao
                                                .insertOrUpdateListOfItems(listResource.data)
                                                .subscribeOn(Schedulers.io()))));
    }

    public Flowable<Resource<List<ItemEntity>>> getListOfItemsOfGivenCategoryId(int categoryId) {
        return localItemsEntityDao.getListOfItemsOfGivenCategoryId(categoryId)
                .subscribeOn(Schedulers.io())
                .filter(itemEntities -> NullResolverClass.checkNullOrEmptyCollection(itemEntities))
                .map(itemEntities -> Resource.success(itemEntities))
                .onErrorResumeNext(Flowable.generate(resourceEmitter ->
                        itemsStoreApi.getItemsOfGivenCategory(categoryId)
                                .subscribeOn(Schedulers.io())
                                .filter(itemEntities ->
                                        NullResolverClass
                                                .checkNullOrEmptyCollection(itemEntities))
                                .map(itemEntities -> Resource.success(itemEntities))
                                .doOnSuccess(listResource ->
                                        localItemsEntityDao
                                                .insertOrUpdateListOfItems(listResource.data)
                                                .subscribeOn(Schedulers.io()))));
    }

    public Flowable<Resource<List<ItemEntity>>> getListOfItemsOfGivenType(int itemType) {
        return localItemsEntityDao.getListOfItemsOfGivenType(itemType)
                .subscribeOn(Schedulers.io())
                .filter(itemEntities -> NullResolverClass.checkNullOrEmptyCollection(itemEntities))
                .map(itemEntities -> Resource.success(itemEntities))
                .onErrorResumeNext(Flowable.generate(resourceEmitter ->
                        itemsStoreApi.getItemsOfGivenType(itemType)
                                .subscribeOn(Schedulers.io())
                                .filter(itemEntities -> NullResolverClass
                                .checkNullOrEmptyCollection(itemEntities))
                                .map(itemEntities -> Resource.success(itemEntities))
                                .doOnSuccess(listResource ->
                                        localItemsEntityDao
                                                .insertOrUpdateListOfItems(listResource.data)
                                                .subscribeOn(Schedulers.io()))));
    }
}