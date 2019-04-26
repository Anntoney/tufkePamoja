package com.tufike.taxi.driver.events;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tufike.taxi.common.events.BaseResultEvent;
import com.tufike.taxi.common.models.Request;
import com.tufike.taxi.common.utils.ServerResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetRequestsResultEvent extends BaseResultEvent {
    public ArrayList<Request> requests;

    public GetRequestsResultEvent(){
        super(ServerResponse.REQUEST_TIMEOUT);
    }

    public GetRequestsResultEvent(Object... args) {
        super(args);
        if(response != ServerResponse.OK)
            return;
        Type type = new TypeToken<List<Request>>() {}.getType();
        GsonBuilder gsonBuilder = new GsonBuilder();
        //gsonBuilder.registerTypeAdapter(LatLng.class, new LatLngDeserializer());

        Gson customGson = gsonBuilder.create();
        this.requests = customGson.fromJson(args[1].toString(),type);
    }
}
