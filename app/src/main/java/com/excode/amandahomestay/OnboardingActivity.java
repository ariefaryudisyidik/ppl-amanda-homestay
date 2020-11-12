package com.excode.amandahomestay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.LinearLayout;

public class OnboardingActivity extends AppCompatActivity {
    private ViewPager vpSlideView;
    private LinearLayout linearDotLayout;
    private SliderAdapter sliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        vpSlideView = findViewById(R.id.vp_slide_view);
        linearDotLayout = findViewById(R.id.linear_dots_layout);
        sliderAdapter = new SliderAdapter(this);
        vpSlideView.setAdapter(sliderAdapter);
    }
}