package com.tufike.taxi.rider.activities.travel.fragments;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;
import com.tufike.taxi.common.components.BaseFragment;
import com.tufike.taxi.common.models.Travel;
import com.tufike.taxi.common.utils.CommonUtils;
import com.tufike.taxi.rider.R;
import com.tufike.taxi.rider.databinding.FragmentTravelStatsBinding;
import com.tufike.taxi.rider.events.GetTravelInfoResultEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Locale;

public class TabStatisticsFragment extends BaseFragment {
    Travel travel = new Travel();
    private static final String ARG_TRAVEL = "travel";
    FragmentTravelStatsBinding binding;
    onTravelInfoReceived listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setRegisterEventBus(true);
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
            travel = Travel.fromJson(getArguments().getString(ARG_TRAVEL));
        else{
            throw new RuntimeException("Travel activity must have a travel passed into it.");

        }

    }

    public static TabStatisticsFragment newInstance(Travel travel) {
        TabStatisticsFragment tabStatisticsFragment = new TabStatisticsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TRAVEL, travel.toJson());
        tabStatisticsFragment.setArguments(args);
        return tabStatisticsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_travel_stats,container,false);
        binding.costText.setText(getString(R.string.unit_money, travel.getCostBest()));
        binding.balanceText.setText(getString(R.string.unit_money, CommonUtils.rider.getBalance()));
        return binding.getRoot();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTravelInfoReceived(GetTravelInfoResultEvent event) {
        if(listener != null)
            listener.onReceived(event.location, event.cost);
        binding.timeText.setText(String.format(Locale.getDefault(),"%02d:%02d", event.time / 60, event.time % 60));
        if(binding.getRoot().getResources().getBoolean(R.bool.use_miles))
            binding.distanceText.setText(binding.getRoot().getContext().getString(R.string.unit_distance_miles,event.distance / 1609.344f));
        else
            binding.distanceText.setText(getString(R.string.unit_distance, event.distance / 1000f));
    }

    public void onUpdatePrice(Double price) {
        binding.costText.setText(getString(R.string.unit_money, price));
    }

    public interface onTravelInfoReceived{
        void onReceived(LatLng driverLocation,float cost);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ReviewDialog.onReviewFragmentInteractionListener) {
            listener = (onTravelInfoReceived) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement onEditAddressInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
