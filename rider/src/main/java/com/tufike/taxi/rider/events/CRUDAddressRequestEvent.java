package com.tufike.taxi.rider.events;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tufike.taxi.common.events.BaseRequestEvent;
import com.tufike.taxi.common.models.Address;
import com.tufike.taxi.common.models.CRUD;
import com.tufike.taxi.common.utils.LatLngDeserializer;

import org.json.JSONException;
import org.json.JSONObject;

public class CRUDAddressRequestEvent extends BaseRequestEvent {
    public CRUD crud;
    public JSONObject address = null;

    public CRUDAddressRequestEvent(CRUD crud, Address address) {
        super(new CRUDAddressResultEvent());
        this.crud = crud;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LatLng.class, new LatLngDeserializer());
        Gson customGson = gsonBuilder.create();
        String jsonString = customGson.toJson(address);
        try {
            this.address = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public CRUDAddressRequestEvent(CRUD crud) {
        super(new CRUDAddressResultEvent());
        this.crud = crud;
    }
}
