package com.excode.amandahomestay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;

import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.excode.amandahomestay.note.NotesListActivity;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    CardView cvBookkeeping, cvRoomStatus, cvTenantData, cvNotification, cvBooking, cvNote;

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        if (restorePrefData()) {
            Intent loginActivityIntent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginActivityIntent);
        }
         */

        // Cast
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        cvBookkeeping = findViewById(R.id.cv_bookkeeping);
        cvRoomStatus = findViewById(R.id.cv_room_status);
        cvTenantData = findViewById(R.id.cv_tenant_data);
        cvNotification = findViewById(R.id.cv_notification);
        cvBooking = findViewById(R.id.cv_booking);
        cvNote = findViewById(R.id.cv_note);

        // CardView setOnClickListener
        cvBookkeeping.setOnClickListener(this);
        cvRoomStatus.setOnClickListener(this);
        cvTenantData.setOnClickListener(this);
        cvNotification.setOnClickListener(this);
        cvBooking.setOnClickListener(this);
        cvNote.setOnClickListener(this);

        // Toolbar
        setSupportActionBar(toolbar);

        // Navigation Drawer
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setCheckedItem(R.id.nav_home);

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finishActivity(0);
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.nav_about:
                Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
                finish();
                break;
            case R.id.nav_logout:
                savePrefData();
                Intent logoutIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(logoutIntent);
                finish();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void savePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("loginPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("alreadyLogin", false);
        editor.commit();
    }

    // CardView setOnClickListener
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_bookkeeping:
                Intent bookkeepingIntent = new Intent(MainActivity.this, BookkeepingActivity.class);
                startActivity(bookkeepingIntent);
                break;
            case R.id.cv_room_status:
                Intent roomStatusIntent = new Intent(MainActivity.this, RoomStatusActivity.class);
                startActivity(roomStatusIntent);
                break;
            case R.id.cv_tenant_data:
                Intent tenantDataIntent = new Intent(MainActivity.this, TenantActivity.class);
                startActivity(tenantDataIntent);
                break;
            case R.id.cv_notification:
                break;
            case R.id.cv_booking:
                break;
            case R.id.cv_note:
                startActivity(new Intent(MainActivity.this, NotesListActivity.class));
                break;
        }
    }
}
