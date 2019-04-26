package com.tufike.taxi.rider.events;

import com.google.android.gms.maps.model.LatLng;

public class GetTravelInfoResultEvent {
    public LatLng location;
    public int distance;
    public int time;
    public float cost;
    public GetTravelInfoResultEvent(int distance,int time,float cost,float lat, float lng){
        this.location = new LatLng(lat,lng);
        this.distance = distance;
        this.time = time;
        this.cost = cost;
    }
}
