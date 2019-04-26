package com.tufike.taxi.rider.events;

import com.tufike.taxi.common.events.BaseResultEvent;

public class ReviewDriverResultEvent extends BaseResultEvent {
    public ReviewDriverResultEvent(int response) {
        super(response);
    }
}
