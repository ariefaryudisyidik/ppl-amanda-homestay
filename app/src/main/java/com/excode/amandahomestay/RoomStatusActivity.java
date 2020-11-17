package com.excode.amandahomestay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class RoomStatusActivity extends AppCompatActivity {
    private RecyclerView rvRoomStatus;
    private ArrayList<GetterSetter> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_status);
        rvRoomStatus = findViewById(R.id.rv_room_status);
        rvRoomStatus.setHasFixedSize(true);

        list.addAll(Dataset.getListDataRoomStatus());
        showRecyclerGrid();
    }

    private void showRecyclerGrid() {
        rvRoomStatus.setLayoutManager(new GridLayoutManager(this, 5));
        GridRoomStatusAdapter gridRoomStatusAdapter = new GridRoomStatusAdapter(list);
        rvRoomStatus.setAdapter(gridRoomStatusAdapter);
    }

}