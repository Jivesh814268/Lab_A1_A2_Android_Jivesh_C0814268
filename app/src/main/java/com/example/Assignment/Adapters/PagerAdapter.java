package com.example.Assignment.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.Assignment.Fragments.Products;
import com.example.Assignment.Fragments.Providers;

public class PagerAdapter extends FragmentPagerAdapter {
    private int numberofTabs;
    public PagerAdapter(FragmentManager fm, int numberofTabs){
        super(fm);
        this.numberofTabs=numberofTabs;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Products();
            case 1:
                return new Providers();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberofTabs;
    }
}