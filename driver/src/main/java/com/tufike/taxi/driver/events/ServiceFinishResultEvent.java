package com.tufike.taxi.driver.events;

import com.tufike.taxi.common.events.BaseResultEvent;

public class ServiceFinishResultEvent extends BaseResultEvent {
    public boolean isCreditUsed;
    public float amount;
    public ServiceFinishResultEvent(int status, boolean isCreditUsed, float amount){
        super(status);
        this.isCreditUsed = isCreditUsed;
        this.amount = amount;
    }
}
