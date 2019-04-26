package com.tufike.taxi.rider.events;

import com.tufike.taxi.common.events.BaseRequestEvent;

public class AddCouponRequestEvent extends BaseRequestEvent {
    public String code;
    public AddCouponRequestEvent(String code) {
        super(new AddCouponResultEvent());
        this.code = code;
    }
}
