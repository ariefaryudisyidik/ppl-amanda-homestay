package com.excode.amandahomestay.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.excode.amandahomestay.model.Bookkeeping;

import java.util.List;

@Dao
public interface BookkeepingDao {
    @Query("SELECT * FROM bookkeeping")
    LiveData<List<Bookkeeping>> selectAllBookkeepings();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertBookkeeping(Bookkeeping bookkeeping);

    @Update
    int updateBookkeeping(Bookkeeping bookkeeping);

    @Delete
    int deleteBookkeeping(Bookkeeping bookkeeping);
}
