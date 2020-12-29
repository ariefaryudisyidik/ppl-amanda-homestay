package com.excode.amandahomestay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.excode.amandahomestay.adapter.ListBookingAdapter;
import com.excode.amandahomestay.database.BookingRepository;
import com.excode.amandahomestay.model.Booking;
import com.excode.amandahomestay.model.Bookkeeping;

import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView rvBookings;
    private ArrayList<Booking> list = new ArrayList<>();
    private BookingRepository mBookingRepository;

    private ImageView ivBgNotFound;
    private Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        // Database
        /*
        database = Room.databaseBuilder(getApplicationContext(),
                BookkeepingDatabase.class, "dbbookkeeping").allowMainThreadQueries().build();
         */

        // RecyclerView
        rvBookings = findViewById(R.id.rv_bookings);
        rvBookings.setHasFixedSize(true);

        // BookingsData
        mBookingRepository = new BookingRepository(this);
        mBookingRepository.retrieveNotesTask().observe(this, new Observer<List<Booking>>() {
            @Override
            public void onChanged(List<Booking> bookings) {
                if (list.size() > 0) {
                    list.clear();
                }
                if (bookings != null) {
                    list.addAll(bookings);
                }

                if (list.isEmpty()) {
                    ivBgNotFound.setVisibility(View.VISIBLE);
                    btnAddData.setVisibility(View.VISIBLE);
                    rvBookings.setVisibility(View.GONE);
                } else {
                    ivBgNotFound.setVisibility(View.GONE);
                    btnAddData.setVisibility(View.GONE);
                    rvBookings.setVisibility(View.VISIBLE);
                }
            }
        });
        showRecyclerList();

        // Cast Component
        ivBgNotFound = findViewById(R.id.iv_bg_not_found);
        btnAddData = findViewById(R.id.btn_add_data);
        btnAddData.setOnClickListener(this);
    }

    private void showRecyclerList() {
        rvBookings.setLayoutManager(new LinearLayoutManager(this));
        ListBookingAdapter listBookingAdapter = new ListBookingAdapter(list, this);
        rvBookings.setAdapter(listBookingAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_data:
                startActivity(new Intent(BookingActivity.this, BookkeepingActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(BookingActivity.this, MainActivity.class));
        finish();
        super.onBackPressed();
    }
}