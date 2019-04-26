package com.tufike.taxi.driver.models;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class Stats {
    @Expose
    Float amount;
    @Expose
    Integer services;
    @Expose
    Float rating;

    public Stats() {

    }

    public Stats fromJson(String json) {
        return (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create().fromJson(json, Stats.class);
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Integer getServices() {
        return services;
    }

    public void setServices(Integer services) {
        this.services = services;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
