package com.tufike.taxi.rider.events;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tufike.taxi.common.events.BaseResultEvent;
import com.tufike.taxi.common.models.Coupon;
import com.tufike.taxi.common.utils.ServerResponse;

import java.lang.reflect.Type;
import java.util.List;

import static com.tufike.taxi.common.utils.ServerResponse.OK;

public class GetCouponsResultEvent extends BaseResultEvent {
    public List<Coupon> coupons;
    public GetCouponsResultEvent() {
        super(ServerResponse.REQUEST_TIMEOUT);
    }
    public GetCouponsResultEvent(Object... args) {
        super(args);
        if(response != OK)
            return;
        Type type = new TypeToken<List<Coupon>>() {}.getType();
        GsonBuilder gsonBuilder = new GsonBuilder();
        //gsonBuilder.registerTypeAdapter(LatLng.class, new LatLngDeserializer());

        Gson customGson = gsonBuilder.create();
        this.coupons = customGson.fromJson(args[1].toString(),type);
    }
}
