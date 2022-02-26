package com.example.tds.outils;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;

public class PageAdapter extends PagerAdapter {
    public PageAdapter(FragmentManager supportFragmentManager, int[] intArray) {
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page "+position;
    }
}
