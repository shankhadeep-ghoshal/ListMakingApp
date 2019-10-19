package org.shankhadeepghoshal.listmakingapp.itemslistflow;

import androidx.annotation.Nullable;

import org.shankhadeepghoshal.listmakingapp.itemslistflow.unittypes.ItemType;

import java.util.Comparator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {
    public static Comparator<ItemEntity> ORDER_BY_NAME_ASCENDING =
            (o1, o2) -> o1.getItemName().compareToIgnoreCase(o2.getItemName());
    public static Comparator<ItemEntity> ORDER_BY_NAME_DESCENDING =
            (o1, o2) -> o2.getItemName().compareToIgnoreCase(o1.getItemName());
    public static Comparator<ItemEntity> ORDER_BY_UNIT_PRICE_ASCENDING =
            (o1, o2) -> Double.compare(o1.getUnitPrice(), o2.getUnitPrice());
    public static Comparator<ItemEntity> ORDER_BY_UNIT_PRICE_DESCENDING =
            (o1, o2) -> Double.compare(o2.getUnitPrice(), o1.getUnitPrice());

    private int itemNumber;
    @NonNull private String itemName;
    @Nullable private String itemImageUrl;
    @NonNull private ItemType itemType;
    private int unitKey;
    private double quantity;
    private double unitPrice;
    private CategoryEntity categoryEntity;
    private boolean isItemBought;

    @Override
    public boolean equals(Object o) {
        if(o instanceof ItemEntity) {
            ItemEntity newObj = (ItemEntity) o;

            return this.getItemName().equalsIgnoreCase(newObj.getItemName())
                    && this.getItemType().getItemTypeValue(this.getUnitKey())
                    .equalsIgnoreCase(newObj.getItemType().getItemTypeValue(newObj.getUnitKey()))
                    && this.getQuantity() == newObj.getQuantity()
                    && this.getUnitPrice() == newObj.getUnitPrice()
                    && this.getCategoryEntity().equals(newObj.getCategoryEntity());
        }

        return false;
    }
}