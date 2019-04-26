package com.tufike.taxi.driver.events;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tufike.taxi.driver.models.Report;
import com.tufike.taxi.driver.models.Stats;
import com.tufike.taxi.common.events.BaseResultEvent;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetStatisticsResultEvent extends BaseResultEvent {
    public ArrayList<Report> reports;
    public Stats stats;
    public GetStatisticsResultEvent(int response, String statsJson,String reportsJson) {
        super(response);
        Type type = new TypeToken<List<Report>>() {
        }.getType();
        if(reportsJson!=null)
            this.reports = new Gson().fromJson(reportsJson, type);
        if(statsJson!=null)
            this.stats = new Stats().fromJson(statsJson);
    }
}
