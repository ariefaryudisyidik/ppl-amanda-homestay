package com.excode.amandahomestay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import com.google.android.material.slider.Slider;
import com.google.android.material.tabs.TabLayout;

public class OnboardingActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager slideViewPager;
    private TabLayout tabIndicator;
    private SliderAdapter sliderAdapter;
    private Button btnNext, btnBack, btnGetStarted;
    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        slideViewPager = findViewById(R.id.vp_slide);
        tabIndicator = findViewById(R.id.tab_indicator);

        sliderAdapter = new SliderAdapter(this);
        slideViewPager.setAdapter(sliderAdapter);
        slideViewPager.addOnPageChangeListener(viewListener);

        tabIndicator.setupWithViewPager(slideViewPager);

        btnNext = findViewById(R.id.btn_next);
        btnBack = findViewById(R.id.btn_back);
        btnGetStarted = findViewById(R.id.btn_get_started);

        btnNext.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            currentPage = position;

            if (position == 0) {
                btnNext.setEnabled(true);
                btnBack.setEnabled(false);

                btnNext.setVisibility(View.VISIBLE);
                btnBack.setVisibility(View.INVISIBLE);

                btnGetStarted.setVisibility(View.INVISIBLE);
                btnNext.setText("Selanjutnya");
                btnBack.setText("");
            } else if (position < sliderAdapter.slideHeadings.length - 1) {
                btnNext.setEnabled(true);
                btnBack.setEnabled(true);

                btnNext.setVisibility(View.VISIBLE);
                btnBack.setVisibility(View.VISIBLE);

                btnGetStarted.setVisibility(View.INVISIBLE);
                btnNext.setText("Selanjutnya");
                btnBack.setText("Kembali");
            } else {
                btnNext.setEnabled(true);
                btnBack.setEnabled(true);
                btnNext.setVisibility(View.INVISIBLE);
                btnBack.setVisibility(View.INVISIBLE);
                btnGetStarted.setVisibility(View.VISIBLE);
                btnNext.setText("Selesai");
                btnBack.setText("Kembali");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                slideViewPager.setCurrentItem(currentPage + 1);
                if (currentPage == sliderAdapter.slideHeadings.length - 1) {
                    loadLastScreen();
                }
                break;
            case R.id.btn_back:
                slideViewPager.setCurrentItem(currentPage - 1);
                break;
        }
    }

    private void loadLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnBack.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);

    }
}