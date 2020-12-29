package com.excode.amandahomestay.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.excode.amandahomestay.model.Booking;

@Database(entities = {Booking.class}, version = 1)
public abstract class BookingDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "booking_db";
    public static BookingDatabase instance;

    static BookingDatabase getInstance(final Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    BookingDatabase.class,
                    DATABASE_NAME
            ).fallbackToDestructiveMigration().build();
        }
        return instance;
    }
    public abstract BookingDao bookingDao();
}
