package com.excode.amandahomestay;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun;
import androidx.room.Room;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.cottacush.android.currencyedittext.CurrencyEditText;
import com.excode.amandahomestay.database.BookkeepingDatabase;
import com.excode.amandahomestay.model.Bookkeeping;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Currency;
import java.util.Locale;

public class BookkeepingActivity extends AppCompatActivity implements View.OnClickListener {
    private BookkeepingDatabase database;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormat;
    private EditText edtTenantName, edtRoomNumber, edtPhoneNumber, edtEntryDate, edtOutDate;
    private CurrencyEditText edtCost;
    private Button btnSave, btnTenant, btnBooking, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookkeeping);

        database = Room.databaseBuilder(getApplicationContext(),
                BookkeepingDatabase.class, "bookkeeping_db").build();


        edtTenantName = findViewById(R.id.edt_tenant_name);
        edtRoomNumber = findViewById(R.id.edt_room_number);
        edtPhoneNumber = findViewById(R.id.edt_phone_number);
        edtEntryDate = findViewById(R.id.edt_entry_date);
        edtOutDate = findViewById(R.id.edt_out_date);
        edtCost = findViewById(R.id.edt_cost);
        btnSave = findViewById(R.id.btn_save);
        btnTenant = findViewById(R.id.btn_tenant);
        btnBooking = findViewById(R.id.btn_booking);
        btnCancel = findViewById(R.id.btn_cancel);

        edtEntryDate.setOnClickListener(this);
        edtOutDate.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        final Bookkeeping bookkeeping = (Bookkeeping) getIntent().getSerializableExtra("DATA");
        //Edit
        if (bookkeeping != null) {
            edtTenantName.setText(bookkeeping.getNamaPenyewa());
            edtRoomNumber.setText(bookkeeping.getNomorKamar());
            edtPhoneNumber.setText(bookkeeping.getNomorTelepon());
            edtEntryDate.setText(bookkeeping.getTanggalMasuk());
            edtOutDate.setText(bookkeeping.getTanggalKeluar());
            edtCost.setText(bookkeeping.getBiaya());
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bookkeeping.setNamaPenyewa(edtTenantName.getText().toString());
                    bookkeeping.setNomorKamar(edtRoomNumber.getText().toString());
                    bookkeeping.setNomorTelepon(edtPhoneNumber.getText().toString());
                    bookkeeping.setTanggalMasuk(edtEntryDate.getText().toString());
                    bookkeeping.setTanggalKeluar(edtOutDate.getText().toString());
                    bookkeeping.setBiaya(edtCost.getText().toString());
                    updateTenant(bookkeeping);
                    getParentActivityIntent();
                    finish();
                }
            });
        } //Create
        else {
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnSave.setVisibility(View.GONE);
                    btnTenant.setVisibility(View.VISIBLE);
                    btnBooking.setVisibility(View.VISIBLE);

                    btnTenant.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bookkeeping bookkeeping = new Bookkeeping();
                            bookkeeping.setNamaPenyewa(edtTenantName.getText().toString());
                            bookkeeping.setNomorKamar(edtRoomNumber.getText().toString());
                            bookkeeping.setNomorTelepon(edtPhoneNumber.getText().toString());
                            bookkeeping.setTanggalMasuk(edtEntryDate.getText().toString());
                            bookkeeping.setTanggalKeluar(edtOutDate.getText().toString());
                            bookkeeping.setBiaya(edtCost.getText().toString());
                            insertBookkeeping(bookkeeping);
                            clear();
                        }
                    });
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edt_entry_date:
                edShowDateDialog();
                break;
            case R.id.edt_out_date:
                odShowDateDialog();
                break;
            case R.id.btn_cancel:
                getParentActivityIntent();
                finish();
                break;
        }

    }

    private void edShowDateDialog() {
        Calendar newCalendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                edtEntryDate.setText("" + dateFormat.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void odShowDateDialog() {
        Calendar newCalendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                edtOutDate.setText("" + dateFormat.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }


    private void insertBookkeeping(Bookkeeping bookkeeping) {
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                long status = database.bookkeepingDao().insertBookkeeping(bookkeeping);
                return status;
            }

            @Override
            protected void onPostExecute(Long aLong) {
                Toast.makeText(BookkeepingActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    private void updateTenant(Bookkeeping bookkeeping) {
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                long status = database.bookkeepingDao().updateBookkeeping(bookkeeping);
                return status;
            }

            @Override
            protected void onPostExecute(Long aLong) {
                Toast.makeText(BookkeepingActivity.this, "Data berhasil diubah", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    private void clear() {
        edtTenantName.setText("");
        edtRoomNumber.setText("");
        edtPhoneNumber.setText("");
        edtEntryDate.setText("");
        edtOutDate.setText("");
        edtCost.setText("");
        edtCost.clearFocus();
    }
}