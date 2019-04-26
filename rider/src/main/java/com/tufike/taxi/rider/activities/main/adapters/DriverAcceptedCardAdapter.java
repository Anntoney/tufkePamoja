package com.tufike.taxi.rider.activities.main.adapters;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tufike.taxi.common.models.Driver;
import com.tufike.taxi.common.models.DriverInfo;
import com.tufike.taxi.common.utils.DataBinder;
import com.tufike.taxi.rider.R;
import com.tufike.taxi.rider.databinding.CardDriverAcceptedBinding;
import com.tufike.taxi.rider.events.AcceptDriverUIEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class DriverAcceptedCardAdapter extends BaseAdapter {

    private List<DriverInfo> mData;
    private LayoutInflater layoutInflater;

    public DriverAcceptedCardAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        this.mData = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    public void addDriver(DriverInfo driver) {
        mData.add(driver);
    }

    @Override
    public DriverInfo getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final DriverInfo info = mData.get(position);
        final Driver driver = info.driver;

        CardDriverAcceptedBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.card_driver_accepted, parent, false);
        binding.setInfo(info);
        binding.executePendingBindings();
        if(binding.getRoot().getResources().getBoolean(R.bool.use_miles))
            binding.textDistance.setText(binding.getRoot().getContext().getString(R.string.unit_distance_miles,info.distance / 1.609344f));
        if (driver.getMedia() != null)
            DataBinder.setMedia(binding.imageDriver,driver.getMedia());
        if (driver.getCarMedia() != null)
            DataBinder.setMedia(binding.imageHeader,driver.getCarMedia());
        binding.buttonAccept.setTag(info);
        binding.buttonAccept.setOnClickListener(view -> {
            DriverInfo driverInfo = (DriverInfo) view.getTag();
            EventBus.getDefault().post(new AcceptDriverUIEvent(driverInfo));
        });
        return binding.getRoot();
    }
}
