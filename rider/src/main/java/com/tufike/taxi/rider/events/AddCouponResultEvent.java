package com.tufike.taxi.rider.events;

import com.tufike.taxi.common.events.BaseResultEvent;
import com.tufike.taxi.common.utils.ServerResponse;

public class AddCouponResultEvent extends BaseResultEvent {
    public AddCouponResultEvent(){
        super(ServerResponse.REQUEST_TIMEOUT);
    }
    public AddCouponResultEvent(Object... args) {
        super(args);
    }
}
