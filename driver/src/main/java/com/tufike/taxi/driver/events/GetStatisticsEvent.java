package com.tufike.taxi.driver.events;

import com.tufike.taxi.common.events.BaseRequestEvent;
import com.tufike.taxi.common.utils.ServerResponse;

public class GetStatisticsEvent extends BaseRequestEvent {
    public enum QueryTime {
        DAILY(1),
        WEEKLY(2),
        MONTHLY(3);
        private int value;
        QueryTime(int value) {
            this.value = value;
        }
        public static QueryTime get(int code) {
            for(QueryTime s : values()) {
                if(s.value == code) return s;
            }
            return null;
        }
        public int getValue() {
            return value;
        }
    }
    public QueryTime queryTime;
    public GetStatisticsEvent(QueryTime queryTime){
        super(new GetStatisticsResultEvent(ServerResponse.REQUEST_TIMEOUT.getValue(),null,null));
        this.queryTime = queryTime;
    }

}
