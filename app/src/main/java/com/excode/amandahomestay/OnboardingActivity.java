package com.excode.amandahomestay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class OnboardingActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager slideViewPager;
    private TabLayout tabIndicator;
    private SliderAdapter sliderAdapter;
    private Button btnNext, btnBack, btnGetStarted;
    private Animation btnAnim;
    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        // make activity fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        // when this activity is about to be launch we need to check if its opened before or not
        if (restorePrefData()) {
            Intent loginActivityIntent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginActivityIntent);
            finish();
        }
        slideViewPager = findViewById(R.id.vp_slide);
        tabIndicator = findViewById(R.id.tab_indicator);

        sliderAdapter = new SliderAdapter(this);
        slideViewPager.setAdapter(sliderAdapter);
        slideViewPager.addOnPageChangeListener(viewListener);

        tabIndicator.setupWithViewPager(slideViewPager);

        btnNext = findViewById(R.id.btn_next);
        btnBack = findViewById(R.id.btn_back);
        btnGetStarted = findViewById(R.id.btn_get_started);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.btn_animation);

        btnNext.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnGetStarted.setOnClickListener(this);
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
                loadLastScreen();
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
            case R.id.btn_get_started:
                // open MainActivity
                Intent loginActivityIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginActivityIntent);
                // check Onboarding complete or not
                savePrefData();
                finish();
                break;
        }
    }

    private void savePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("onboardPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("onboardingComplete", true);
        editor.commit();
    }

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("onboardPrefs", MODE_PRIVATE);
        Boolean onboardingComplete = pref.getBoolean("onboardingComplete", false);
        return onboardingComplete;
    }

    private void loadLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnBack.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        // setup animation
        btnGetStarted.setAnimation(btnAnim);
    }
}