package org.shankhadeepghoshal.listmakingapp.itemslistflow.unittypes;

public interface ItemType {
    int VOLUMETRIC = 1;
    int QUANTITATIVE = 2;

    String getItemTypeValue(int key);
}