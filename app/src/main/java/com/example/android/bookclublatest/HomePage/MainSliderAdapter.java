package com.example.android.bookclublatest.HomePage;

import com.example.android.bookclublatest.R;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainSliderAdapter extends SliderAdapter {

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {
        switch (position) {
            case 0:
                viewHolder.bindImageSlide(R.mipmap.slider1);
                break;
            case 1:
                viewHolder.bindImageSlide(R.mipmap.slider2);
                break;
            case 2:
                viewHolder.bindImageSlide(R.mipmap.slider3);
                break;
        }
    }
}