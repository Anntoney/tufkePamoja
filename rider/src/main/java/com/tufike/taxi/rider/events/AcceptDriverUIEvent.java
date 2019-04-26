package com.tufike.taxi.rider.events;

import com.tufike.taxi.common.models.DriverInfo;

public class AcceptDriverUIEvent {
    public DriverInfo driverInfo;
    public AcceptDriverUIEvent(DriverInfo driverInfo){
        this.driverInfo = driverInfo;
    }
}
