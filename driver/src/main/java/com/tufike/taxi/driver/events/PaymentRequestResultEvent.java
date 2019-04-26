package com.tufike.taxi.driver.events;

import com.tufike.taxi.common.events.BaseResultEvent;

public class PaymentRequestResultEvent extends BaseResultEvent {
    public PaymentRequestResultEvent(int code) {
        super(code);
    }
}
