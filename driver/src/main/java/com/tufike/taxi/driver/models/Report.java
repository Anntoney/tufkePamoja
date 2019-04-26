package com.tufike.taxi.driver.models;

import com.google.gson.annotations.Expose;

public class Report {
    @Expose
    String date;
    @Expose
    Float amount;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
