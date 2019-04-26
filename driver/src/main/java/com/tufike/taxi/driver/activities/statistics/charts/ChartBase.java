package com.tufike.taxi.driver.activities.statistics.charts;

import android.os.Handler;

public class ChartBase {
    String[] mLabels = new String[0];

    float[] mValues = new float[0];

    private final Runnable unlockAction = () -> new Handler().postDelayed(() -> unlock(), 500);

    private final Runnable showAction = () -> new Handler().postDelayed(() -> show(unlockAction), 500);


    ChartBase() {
        super();
    }


    public void init(String[] labels, float[] values) {
        mLabels = labels;
        mValues = values;
        show(unlockAction);
    }

    protected void show(Runnable action) {
        lock();
    }

    protected void update(String[] labels, float[] values) {
        mLabels = labels;
        mValues = values;
        lock();
    }

    protected void dismiss(Runnable action) {
        lock();
    }

    private void lock() {
    }

    private void unlock() {
    }
}
