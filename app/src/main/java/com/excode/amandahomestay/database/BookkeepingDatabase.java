package com.excode.amandahomestay.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.excode.amandahomestay.model.Bookkeeping;

@Database(entities = {Bookkeeping.class}, version = 1)
public abstract class BookkeepingDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "bookkeeping_db";
    public static BookkeepingDatabase instance;

    static BookkeepingDatabase getInstance(final Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    BookkeepingDatabase.class,
                    DATABASE_NAME
            ).fallbackToDestructiveMigration().build();
        }
        return instance;
    }
    public abstract BookkeepingDao bookkeepingDao();
}
