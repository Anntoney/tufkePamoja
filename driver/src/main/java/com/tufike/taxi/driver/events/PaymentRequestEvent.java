package com.tufike.taxi.driver.events;

import com.tufike.taxi.common.events.BaseRequestEvent;
import com.tufike.taxi.common.utils.ServerResponse;

public class PaymentRequestEvent extends BaseRequestEvent {
    public PaymentRequestEvent() {
        super(new PaymentRequestResultEvent(ServerResponse.REQUEST_TIMEOUT.getValue()));
    }
}
