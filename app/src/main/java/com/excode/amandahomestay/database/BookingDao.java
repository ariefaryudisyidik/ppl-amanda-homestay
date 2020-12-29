package com.excode.amandahomestay.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.excode.amandahomestay.model.Booking;

import java.util.List;

@Dao
public interface BookingDao {
    @Query("SELECT * FROM booking")
    LiveData<List<Booking>> selectAllBookings();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertBooking(Booking booking);

    @Update
    int updateBooking(Booking booking);

    @Delete
    int deleteBooking(Booking booking);
}
