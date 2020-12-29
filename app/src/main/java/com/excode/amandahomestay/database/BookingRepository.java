package com.excode.amandahomestay.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.excode.amandahomestay.model.Booking;

import java.util.List;

public class BookingRepository {

    private BookingDatabase mBookingDatabase;

    public BookingRepository(Context context) {
        mBookingDatabase = BookingDatabase.getInstance(context);
    }
    public LiveData<List<Booking>> retrieveNotesTask() {
        return mBookingDatabase.bookingDao().selectAllBookings();
    }
}
