package com.tufike.taxi.rider.events;

import com.tufike.taxi.common.events.BaseRequestEvent;

public class ApplyCouponRequestEvent extends BaseRequestEvent {
    public String code;
    public ApplyCouponRequestEvent(String code) {
        super(new ApplyCouponResultEvent());
        this.code = code;
    }
}
