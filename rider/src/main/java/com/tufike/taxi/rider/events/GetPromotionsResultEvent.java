package com.tufike.taxi.rider.events;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tufike.taxi.common.events.BaseResultEvent;
import com.tufike.taxi.common.models.Promotion;
import com.tufike.taxi.common.utils.ServerResponse;

import java.lang.reflect.Type;
import java.util.List;

import static com.tufike.taxi.common.utils.ServerResponse.OK;

public class GetPromotionsResultEvent extends BaseResultEvent {
    public List<Promotion> promotions;
    public GetPromotionsResultEvent(){
        super(ServerResponse.REQUEST_TIMEOUT);
    }
    public GetPromotionsResultEvent(Object... args) {
        super(args);
        if (response != OK)
            return;
        Type type = new TypeToken<List<Promotion>>() {
        }.getType();
        GsonBuilder gsonBuilder = new GsonBuilder();
        //gsonBuilder.registerTypeAdapter(LatLng.class, new LatLngDeserializer());

        Gson customGson = gsonBuilder.create();
        this.promotions = customGson.fromJson(args[1].toString(), type);
    }
}
