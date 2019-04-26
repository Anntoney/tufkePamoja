package com.tufike.taxi.rider.activities.main.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.tufike.taxi.common.models.ServiceCategory;
import com.tufike.taxi.rider.activities.main.fragments.ServiceCarousalFragment;

import java.util.ArrayList;

public class ServiceCategoryViewPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<ServiceCategory> list;

    public ServiceCategoryViewPagerAdapter(FragmentManager manager, ArrayList<ServiceCategory> list) {
        super(manager);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return ServiceCarousalFragment.newInstance(list.get(position).getServices());
    }

    public void setItems(ArrayList<ServiceCategory> list){
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getCatTitle();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}