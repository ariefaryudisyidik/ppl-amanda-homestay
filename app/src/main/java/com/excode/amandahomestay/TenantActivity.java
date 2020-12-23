package com.excode.amandahomestay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.excode.amandahomestay.adapter.ListTenantAdapter;
import com.excode.amandahomestay.database.BookkeepingDatabase;
import com.excode.amandahomestay.database.BookkeepingRepository;
import com.excode.amandahomestay.model.Bookkeeping;
import com.excode.amandahomestay.note.persistence.NoteRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TenantActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView rvTenants;
    private ArrayList<Bookkeeping> list = new ArrayList<>();
    private BookkeepingRepository mBookkeepingRepository;

    private ImageView ivBgNotFound;
    private Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant);

        // Database
        /*
        database = Room.databaseBuilder(getApplicationContext(),
                BookkeepingDatabase.class, "dbbookkeeping").allowMainThreadQueries().build();
         */

        // RecyclerView
        rvTenants = findViewById(R.id.rv_tenants);
        rvTenants.setHasFixedSize(true);

        // TenantsData
        mBookkeepingRepository = new BookkeepingRepository(this);
        mBookkeepingRepository.retrieveNotesTask().observe(this, new Observer<List<Bookkeeping>>() {
            @Override
            public void onChanged(List<Bookkeeping> bookkeepings) {
                if (list.size() > 0) {
                    list.clear();
                }
                if (bookkeepings != null) {
                    list.addAll(bookkeepings);
                }

                if (list.isEmpty()) {
                    ivBgNotFound.setVisibility(View.VISIBLE);
                    btnAddData.setVisibility(View.VISIBLE);
                    rvTenants.setVisibility(View.GONE);
                } else {
                    ivBgNotFound.setVisibility(View.GONE);
                    btnAddData.setVisibility(View.GONE);
                    rvTenants.setVisibility(View.VISIBLE);
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
        rvTenants.setLayoutManager(new LinearLayoutManager(this));
        ListTenantAdapter listTenantAdapter = new ListTenantAdapter(list, this);
        rvTenants.setAdapter(listTenantAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_data:
                startActivity(new Intent(TenantActivity.this, BookkeepingActivity.class));
                finish();
                break;
        }
    }
}