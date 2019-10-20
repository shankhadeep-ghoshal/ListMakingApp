package org.shankhadeepghoshal.listmakingapp;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class GlobalConstants {
    public static final int DATABASE_VERSION = 1;

    public static final int METRIC = 1;
    public static final int IMPERIAL = 2;

    public static final String DATABASE_NAME = "ListAppLocalDB.db";

    public static final Map<Integer, String> UNITS_MAP_METRIC =
            new ConcurrentHashMap<Integer, String>() {{
                put(1, "mg");
                put(2,"g");
                put(3,"kg");
                put(4,"ml");
                put(5,"l");
                put(6,"cc");
                put(7,"unit/s");
            }};

    public static final Map<Integer, String> UNITS_MAP_IMPERIAL = new ConcurrentHashMap<Integer,
            String>() {{
                put(1,"oz");
                put(2,"lb");
                put(3,"pt");
                put(4,"qt");
                put(5,"gal");
                put(6,"unit/s");
    }};
}