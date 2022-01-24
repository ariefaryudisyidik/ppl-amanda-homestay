package com.excode.amandahomestay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.excode.amandahomestay.adapter.ListTenantAdapter;
import com.excode.amandahomestay.database.BookkeepingRepository;
import com.excode.amandahomestay.model.Bookkeeping;

import java.util.ArrayList;
import java.util.List;

public class TenantActivity extends AppCompatActivity implements View.OnClickListener {
    private ListTenantAdapter listTenantAdapter;
    public RecyclerView rvTenants;
    private ArrayList<Bookkeeping> list = new ArrayList<>();
    private BookkeepingRepository mBookkeepingRepository;

    private ImageView ivBgNotFound;
    private Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant);

        // Cast Component
        ivBgNotFound = findViewById(R.id.iv_bg_not_found);
        btnAddData = findViewById(R.id.btn_add_data);
        rvTenants = findViewById(R.id.rv_bookings);

        //Event
        btnAddData.setOnClickListener(this);
        retrieveTenant();
        showRecyclerList();
    }

    private void retrieveTenant() {
        // TenantsData
        mBookkeepingRepository = new BookkeepingRepository(this);
        mBookkeepingRepository.retrieveNotesTask().observe(this, new Observer<List<Bookkeeping>>() {
            @Override
            public void onChanged(@Nullable List<Bookkeeping> bookkeepings) {
                if (bookkeepings.isEmpty()) {
                    rvTenants.setVisibility(View.GONE);
                    ivBgNotFound.setVisibility(View.VISIBLE);
                    btnAddData.setVisibility(View.VISIBLE);
                } else {
                    list.addAll(bookkeepings);
                    rvTenants.setVisibility(View.VISIBLE);
                    ivBgNotFound.setVisibility(View.GONE);
                    btnAddData.setVisibility(View.GONE);
                }
                listTenantAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                        if (list.size() == 0) {
                            rvTenants.setVisibility(View.GONE);
                            ivBgNotFound.setVisibility(View.VISIBLE);
                            btnAddData.setVisibility(View.VISIBLE);
                        } else {
                            rvTenants.setVisibility(View.VISIBLE);
                            ivBgNotFound.setVisibility(View.GONE);
                            btnAddData.setVisibility(View.GONE);
                        }
                    }
                });


            }
        });
    }

    private void showRecyclerList() {
        rvTenants.setHasFixedSize(true);
        rvTenants.setLayoutManager(new LinearLayoutManager(this));
        listTenantAdapter = new ListTenantAdapter(list, this);
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
