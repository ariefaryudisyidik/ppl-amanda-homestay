package com.excode.amandahomestay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.GridLayout;

import com.excode.amandahomestay.adapter.GridRoomStatusAdapter;
import com.excode.amandahomestay.database.BookkeepingRepository;
import com.excode.amandahomestay.model.Bookkeeping;

import java.util.ArrayList;
import java.util.List;


public class RoomStatusActivity extends AppCompatActivity {

    private RecyclerView rvRoomStatus;
    private ArrayList<Bookkeeping> list = new ArrayList<>();
    private BookkeepingRepository mBookkeepingRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_status);
        rvRoomStatus = findViewById(R.id.rv_room_status);
        rvRoomStatus.setHasFixedSize(true);

        mBookkeepingRepository = new BookkeepingRepository(this);
        mBookkeepingRepository.retrieveNotesTask().observe(this, new Observer<List<Bookkeeping>>() {
            @Override
            public void onChanged(List<Bookkeeping> bookkeepings) {
                if (bookkeepings != null) {
                    list.addAll(bookkeepings);
                }
            }
        });

        showRecyclerGrid();
    }

    private void showRecyclerGrid() {
        rvRoomStatus.setLayoutManager(new GridLayoutManager(this, 5));
        GridRoomStatusAdapter gridRoomStatusAdapter = new GridRoomStatusAdapter(list, this);
        rvRoomStatus.setAdapter(gridRoomStatusAdapter);
    }

}
