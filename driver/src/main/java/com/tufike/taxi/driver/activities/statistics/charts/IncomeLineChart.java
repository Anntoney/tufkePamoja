package com.tufike.taxi.driver.activities.statistics.charts;

import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.db.chart.animation.Animation;
import com.db.chart.model.LineSet;
import com.db.chart.renderer.AxisRenderer;
import com.db.chart.tooltip.Tooltip;
import com.db.chart.util.Tools;
import com.db.chart.view.LineChartView;
import com.tufike.taxi.driver.R;

public class IncomeLineChart extends ChartBase {


    private final LineChartView mChart;



    private Tooltip mTip;
    private Context mContext;
    private Runnable mBaseAction;


    public IncomeLineChart(LineChartView chart, Context context) {
        super();
        mChart = chart;
        mContext = context;
    }


    @Override
    public void show(Runnable action) {
        // Tooltip
        mTip = new Tooltip(mContext, R.layout.tooltip_income, R.id.value);
        mTip.setVerticalAlignment(Tooltip.Alignment.BOTTOM_TOP);
        mTip.setDimensions((int) Tools.fromDpToPx(58), (int) Tools.fromDpToPx(25));
        mTip.setEnterAnimation(PropertyValuesHolder.ofFloat(View.ALPHA, 1),
                PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f),
                PropertyValuesHolder.ofFloat(View.SCALE_X, 1f)).setDuration(200);
        mTip.setExitAnimation(PropertyValuesHolder.ofFloat(View.ALPHA, 0),
                PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f),
                PropertyValuesHolder.ofFloat(View.SCALE_X, 0f)).setDuration(200);
        mTip.setPivotX(Tools.fromDpToPx(65) / 2);
        mTip.setPivotY(Tools.fromDpToPx(25));
        mChart.setTooltips(mTip);

        LineSet dataSet = new LineSet(mLabels, mValues);
        dataSet.setColor(Color.parseColor("#b3b5bb"))
                .setFill(Color.parseColor("#2d374c"))
                .setDotsColor(Color.parseColor("#ffc755"))
                .setThickness(4);
        mChart.addData(dataSet);
        // Chart
        mChart.setBorderSpacing((int)Tools.fromDpToPx(15))
                //.setAxisBorderValues(0, 20)
                .setYLabels(AxisRenderer.LabelPosition.NONE)
                .setLabelsColor(Color.parseColor("#6a84c3"))
                .setXAxis(false)
                .setYAxis(false);

        mBaseAction = action;
        Runnable chartAction = new Runnable() {
            @Override
            public void run() {
                mBaseAction.run();
                //mTip.prepare(mChart.getEntriesArea(0).get(3), mValues[3]);
                //mChart.showTooltip(mTip, true);
            }
        };
        /*LineSet dataSet = new LineSet(mLabels, mValues[0]);
        dataSet.setColor(Color.parseColor("#53c1bd"))
                .setFill(Color.parseColor("#3d6c73"))
                .setGradientFill(new int[] {Color.parseColor("#364d5a"), Color.parseColor("#3f7178")},
                        null);
        mChart.addData(dataSet);

        mChart.setBorderSpacing(1)
                .setXLabels(AxisRenderer.LabelPosition.NONE)
                .setYLabels(AxisRenderer.LabelPosition.NONE)
                .setXAxis(false)
                .setYAxis(false)
                .setBorderSpacing(Tools.fromDpToPx(5));*/

        Animation anim = new Animation().setInterpolator(new BounceInterpolator()).withEndAction(chartAction);

        mChart.show(anim);
    }

    @Override
    public void update(String[] labels, float[] values) {
        super.update(labels,values);
        mChart.dismissAllTooltips();
        mChart.updateValues(0, mValues);
        //mChart.getChartAnimation().setEndAction(mBaseAction);
        mChart.notifyDataUpdate();
    }


    @Override
    public void dismiss(Runnable action) {
        super.dismiss(action);
        mChart.dismissAllTooltips();
        mChart.dismiss(new Animation().setInterpolator(new BounceInterpolator()).withEndAction(action));
    }
}