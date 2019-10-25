package org.shankhadeepghoshal.listmakingapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.shankhadeepghoshal.listmakingapp.itemslistflow.model.ItemEntity;
import org.shankhadeepghoshal.listmakingapp.itemslistflow.datasrotage.localstorage.LocalItemsEntityDao;

@Database(entities = {ItemEntity.class}, exportSchema = false, version =
        GlobalConstants.DATABASE_VERSION)
public abstract class LocalDatabase extends RoomDatabase {
    private static volatile LocalDatabase INSTANCE;

    public abstract LocalItemsEntityDao getLocalItemsEntityDao();

    public static LocalDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (LocalDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LocalDatabase.class,
                            GlobalConstants.DATABASE_NAME)
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}