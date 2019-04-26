package com.tufike.taxi.driver.activities.main.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.tufike.taxi.common.models.Request;
import com.tufike.taxi.driver.activities.main.fragments.RequestCardFragment;
import com.tufike.taxi.common.ui.ArrayFragmentPagerAdapter;

import java.util.ArrayList;

public class RequestsFragmentPagerAdapter extends ArrayFragmentPagerAdapter<Request> {
    public RequestsFragmentPagerAdapter(FragmentManager fm, ArrayList<Request> requests) {
        super(fm, requests);
    }

    public int getPositionWithTravelId(int travelId) {
        for (int i = 0; i < getCount(); i++) {
            if (getItem(i).travel.getId() == travelId) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Fragment getFragment(Request item, int position) {
        return RequestCardFragment.newInstance(item);
    }
}