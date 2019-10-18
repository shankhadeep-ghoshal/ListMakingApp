package org.shankhadeepghoshal.listmakingapp.itemslistflow;

import androidx.annotation.Nullable;

import org.shankhadeepghoshal.listmakingapp.itemslistflow.unittypes.ItemType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {
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