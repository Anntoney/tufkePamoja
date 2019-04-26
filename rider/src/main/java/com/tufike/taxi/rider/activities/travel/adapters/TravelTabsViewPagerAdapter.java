package com.tufike.taxi.rider.activities.travel.adapters;


import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.tufike.taxi.common.models.Travel;
import com.tufike.taxi.rider.activities.travel.fragments.TabDriverInfoFragment;
import com.tufike.taxi.rider.activities.travel.fragments.TabReviewFragment;
import com.tufike.taxi.rider.activities.travel.fragments.TabStatisticsFragment;

import java.util.ArrayList;
import java.util.Arrays;

public class TravelTabsViewPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private ArrayList<Integer> arrayIndexes = new ArrayList<>(Arrays.asList(0,1,2));
    private Travel travel;
    public TabStatisticsFragment statisticsFragment;

    public TravelTabsViewPagerAdapter(FragmentManager manager, Context context, Travel travel) {
        super(manager);
        this.context = context;
        this.travel = travel;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case (0):
                return TabDriverInfoFragment.newInstance(travel);
            case (1):
                statisticsFragment = TabStatisticsFragment.newInstance(travel);
                return statisticsFragment;
            case (2):
                return new TabReviewFragment();
            default:
                return TabDriverInfoFragment.newInstance(travel);
        }
    }

    @Override
    public int getCount() {
        return arrayIndexes.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case (0):
                return context.getString(com.tufike.taxi.common.R.string.tab_driver_info);
            case (1):
                return context.getString(com.tufike.taxi.common.R.string.tab_statistics);
            case (2):
                return context.getString(com.tufike.taxi.common.R.string.tab_review);
            default:
                return context.getString(com.tufike.taxi.common.R.string.tab_driver_info);
        }
    }
    public void deletePage(int position)
    {
        // Remove the corresponding item in the data set
        arrayIndexes.remove(position);
        // Notify the adapter that the data set is changed
        notifyDataSetChanged();
    }
}