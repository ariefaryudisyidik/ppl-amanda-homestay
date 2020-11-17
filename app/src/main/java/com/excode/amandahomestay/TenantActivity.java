package com.excode.amandahomestay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class TenantActivity extends AppCompatActivity {
    private RecyclerView rvTenants;
    private ArrayList<GetterSetter> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant);

        rvTenants = findViewById(R.id.rv_tenants);
        rvTenants.setHasFixedSize(true);

        // TenantsData
        list.addAll(Dataset.getListDataTenant());
        showRecyclerList();
    }

    private void showRecyclerList() {
        rvTenants.setLayoutManager(new LinearLayoutManager(this));
        ListTenantAdapter listTenantAdapter = new ListTenantAdapter(list);
        rvTenants.setAdapter(listTenantAdapter);
    }
}