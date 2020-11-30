package com.excode.amandahomestay.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.excode.amandahomestay.model.Bookkeeping;

@Database(entities = {Bookkeeping.class}, version = 1)
public abstract class BookkeepingDatabase extends RoomDatabase {
    public abstract BookkeepingDao bookkeepingDao();
}
