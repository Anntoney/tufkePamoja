package com.tufike.taxi.rider.events;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tufike.taxi.common.events.BaseResultEvent;
import com.tufike.taxi.common.models.ServiceCategory;
import com.tufike.taxi.common.utils.ServerResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CalculateFareResultEvent extends BaseResultEvent {
    public ArrayList<ServiceCategory> serviceCategories;
    public CalculateFareResultEvent() {
        super(ServerResponse.REQUEST_TIMEOUT);
    }
    public CalculateFareResultEvent(Object... args) {
        super(args);
        if(hasError())
            return;
        Type type = new TypeToken<List<ServiceCategory>>() {}.getType();
        this.serviceCategories = new Gson().fromJson(args[1].toString(), type);
    }
    public CalculateFareResultEvent(int code, String error) {
        super(code,error);
    }
}
