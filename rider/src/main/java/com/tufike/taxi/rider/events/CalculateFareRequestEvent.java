package com.tufike.taxi.rider.events;

import com.google.android.gms.maps.model.LatLng;
import com.tufike.taxi.common.events.BaseRequestEvent;

import org.json.JSONException;
import org.json.JSONObject;

public class CalculateFareRequestEvent extends BaseRequestEvent {
    public JSONObject pickUpPoint;
    public JSONObject destinationPoint;
    public CalculateFareRequestEvent(LatLng pickUpPoint, LatLng destinationPoint) {
        super(new CalculateFareResultEvent());
        try {
            JSONObject arrayPickup = new JSONObject();
            arrayPickup.put("x",pickUpPoint.longitude);
            arrayPickup.put("y",pickUpPoint.latitude);
            this.pickUpPoint = arrayPickup;
            JSONObject arrayDestination = new JSONObject();
            arrayDestination.put("x",destinationPoint.longitude);
            arrayDestination.put("y",destinationPoint.latitude);
            this.destinationPoint = arrayDestination;
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
