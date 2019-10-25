package org.shankhadeepghoshal.listmakingapp.itemslistflow.model;

import androidx.annotation.Nullable;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Comparator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(indices = {@Index(value = {"itemImageUrl"}, unique = true)})
public class ItemEntity {
    public static Comparator<ItemEntity> ORDER_BY_NAME_ASCENDING =
            (o1, o2) -> o1.getItemName().compareToIgnoreCase(o2.getItemName());
    public static Comparator<ItemEntity> ORDER_BY_NAME_DESCENDING =
            (o1, o2) -> o2.getItemName().compareToIgnoreCase(o1.getItemName());
    public static Comparator<ItemEntity> ORDER_BY_UNIT_PRICE_ASCENDING =
            (o1, o2) -> Double.compare(o1.getUnitPrice(), o2.getUnitPrice());
    public static Comparator<ItemEntity> ORDER_BY_UNIT_PRICE_DESCENDING =
            (o1, o2) -> Double.compare(o2.getUnitPrice(), o1.getUnitPrice());

    @PrimaryKey(autoGenerate = true) private int itemNumber;
    @NonNull private String itemName;
    @Nullable private String itemImageUrl;
    private int itemType;
    private int unitSystem;
    private int unit;
    private double quantity;
    private double unitPrice;
    @Embedded private CategoryEntity categoryEntity;
    private boolean isItemBought;
    private boolean isDeleted;

    @Override
    public boolean equals(Object o) {
        if(o instanceof ItemEntity) {
            ItemEntity newObj = (ItemEntity) o;

            return this.getItemName().equalsIgnoreCase(newObj.getItemName())
                    && this.getItemType() == newObj.getItemType()
                    && this.getQuantity() == newObj.getQuantity()
                    && this.getUnitPrice() == newObj.getUnitPrice()
                    && this.getCategoryEntity().equals(newObj.getCategoryEntity());
        }

        return false;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemImageUrl(@Nullable String itemImageUrl) {
        this.itemImageUrl = itemImageUrl;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public void setUnitSystem(int unitSystem) {
        this.unitSystem = unitSystem;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    public void setItemBought(boolean itemBought) {
        isItemBought = itemBought;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public String getItemName() {
        return itemName;
    }

    @Nullable
    public String getItemImageUrl() {
        return itemImageUrl;
    }

    public int getItemType() {
        return itemType;
    }

    public int getUnitSystem() {
        return unitSystem;
    }

    public int getUnit() {
        return unit;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public boolean isItemBought() {
        return isItemBought;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}