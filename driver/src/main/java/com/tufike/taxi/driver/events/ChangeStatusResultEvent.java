package com.tufike.taxi.driver.events;

import com.tufike.taxi.common.events.BaseResultEvent;

public class ChangeStatusResultEvent extends BaseResultEvent {
    public ChangeStatusResultEvent(int code) {
        super(code);
    }
}
