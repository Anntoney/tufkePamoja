package com.tufike.taxi.rider.events;

import com.google.android.gms.maps.model.LatLng;
import com.tufike.taxi.common.events.BaseResultEvent;
import com.tufike.taxi.common.models.DriverLocation;
import com.tufike.taxi.common.utils.ServerResponse;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class GetDriversLocationResultEvent extends BaseResultEvent {
    public List<DriverLocation> driverLocations;

    public GetDriversLocationResultEvent() {
        super(ServerResponse.REQUEST_TIMEOUT);
    }
    public GetDriversLocationResultEvent(int code, JSONArray driverLocations) {
        super(code);
        this.driverLocations = new ArrayList<>();
        try {
            for(int i = 0; i < driverLocations.length(); i++){
                JSONArray driverLocation = driverLocations.getJSONArray(i);
                LatLng latLng = new LatLng(driverLocation.getJSONArray(2).getDouble(1),driverLocation.getJSONArray(2).getDouble(0));
                DriverLocation dLocation = new DriverLocation(driverLocation.getInt(0),driverLocation.getDouble(1),latLng);
                this.driverLocations.add(dLocation);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}