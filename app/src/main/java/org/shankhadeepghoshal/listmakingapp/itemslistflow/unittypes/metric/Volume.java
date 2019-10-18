package org.shankhadeepghoshal.listmakingapp.itemslistflow.unittypes.metric;

import org.shankhadeepghoshal.listmakingapp.itemslistflow.unittypes.ItemType;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Volume implements ItemType {
    public static final int CLASS_INDEX = 1;
    public static final Map<Integer, String> UNIT_MAP = new HashMap<Integer, String>() {{
        put(1, "mL");
        put(2, "L");
    }};

    @Override
    public String getItemTypeValue(int key) {
        return UNIT_MAP.get(key);
    }
}