package com.tufike.taxi.rider.events;

import com.google.android.gms.maps.model.LatLng;
import com.tufike.taxi.common.events.BaseResultEvent;

public class AcceptDriverResultEvent extends BaseResultEvent {
    public LatLng startPoint;
    public LatLng destinationPoint;
    public AcceptDriverResultEvent(int response,float startLat,float startLng,float destinationLat,float destinationLng) {
        super(response);
        startPoint = new LatLng(startLat,startLng);
        destinationPoint = new LatLng(destinationLat,destinationLng);
    }
}
