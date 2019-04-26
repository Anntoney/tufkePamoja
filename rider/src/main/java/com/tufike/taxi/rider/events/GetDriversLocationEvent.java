package com.tufike.taxi.rider.events;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

public class GetDriversLocationEvent {
    public JSONObject point;

    public GetDriversLocationEvent(LatLng point) {
        try {

            JSONObject jsonArray = new JSONObject();
            jsonArray.put("x",point.longitude);
            jsonArray.put("y",point.latitude);
            this.point = jsonArray;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
