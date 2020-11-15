package com.excode.amandahomestay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;


public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    ImageView ivSlideImage;
    TextView tvSlideHeading, tvSlideDesc;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    // Arrays
    public int[] slideImages = {
            R.drawable.ic_bookkeeping,
            R.drawable.ic_room_status,
            R.drawable.ic_notification
    };

    public String[] slideHeadings = {
            "Pembukuan",
            "Data Penyewa & Kamar",
            "Notifikasi"
    };

    public String[] slideDescs = {
            "Mencatat data dengan mudah dan teratur",
            "Semua data tentang penyewa kos dan\nInformasi kamar",
            "Info tentang aktifitas serta tunggakan\ntagihan bulanan"
    };

    @Override
    public int getCount() {
        return slideHeadings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);
        ivSlideImage = view.findViewById(R.id.iv_slide_image);
        tvSlideHeading = view.findViewById(R.id.tv_slide_title);
        tvSlideDesc = view.findViewById(R.id.tv_slide_desc);

        ivSlideImage.setImageResource(slideImages[position]);
        tvSlideHeading.setText(slideHeadings[position]);
        tvSlideDesc.setText(slideDescs[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }

}
