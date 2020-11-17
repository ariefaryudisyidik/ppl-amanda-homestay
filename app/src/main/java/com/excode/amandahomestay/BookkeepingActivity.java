package com.excode.amandahomestay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BookkeepingActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookkeeping);

        btnSave = findViewById(R.id.btn_save);
        btnCancel = findViewById(R.id.btn_cancel);

        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                break;
            case R.id.btn_cancel:
                Intent cancelIntent = new Intent(BookkeepingActivity.this, MainActivity.class);
                startActivity(cancelIntent);
                break;
        }
    }
}