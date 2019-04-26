package com.tufike.taxi.rider.events;

import com.tufike.taxi.common.events.BaseRequestEvent;
import com.tufike.taxi.common.models.Review;
import com.tufike.taxi.common.utils.ServerResponse;

public class ReviewDriverEvent extends BaseRequestEvent {
    public Review review;
    public ReviewDriverEvent(Review review){
        super(new ReviewDriverResultEvent(ServerResponse.REQUEST_TIMEOUT.getValue()));
        this.review = review;
    }
}
