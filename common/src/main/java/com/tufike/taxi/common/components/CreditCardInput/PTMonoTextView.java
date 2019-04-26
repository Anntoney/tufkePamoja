package com.tufike.taxi.common.components.CreditCardInput;

import android.content.Context;
import android.graphics.Typeface;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

public class PTMonoTextView extends AppCompatTextView {

    public PTMonoTextView(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public PTMonoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public PTMonoTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/PTM55FT.ttf");
        setTypeface(typeface);
    }
}