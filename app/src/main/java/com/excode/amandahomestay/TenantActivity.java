package com.excode.amandahomestay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.excode.amandahomestay.adapter.ListTenantAdapter;
import com.excode.amandahomestay.database.BookkeepingDatabase;
import com.excode.amandahomestay.model.Bookkeeping;

import java.util.ArrayList;
import java.util.Arrays;

public class TenantActivity extends AppCompatActivity {
    private BookkeepingDatabase database;
    private RecyclerView rvTenants;
    private ArrayList<Bookkeeping> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant);

        database = Room.databaseBuilder(getApplicationContext(),
                BookkeepingDatabase.class, "dbbookkeeping").allowMainThreadQueries().build();

        rvTenants = findViewById(R.id.rv_tenants);
        rvTenants.setHasFixedSize(true);

        // TenantsData
        list.addAll(Arrays.asList(database.bookkeepingDao().selectAllBookkeepings()));
        showRecyclerList();

    }

    private void showRecyclerList() {
        rvTenants.setLayoutManager(new LinearLayoutManager(this));
        ListTenantAdapter listTenantAdapter = new ListTenantAdapter(list, this);
        rvTenants.setAdapter(listTenantAdapter);
    }
}