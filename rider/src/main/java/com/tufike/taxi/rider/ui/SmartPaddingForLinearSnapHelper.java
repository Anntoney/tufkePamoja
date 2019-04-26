package com.tufike.taxi.rider.ui;

import android.content.Context;
import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

public class SmartPaddingForLinearSnapHelper extends RecyclerView.ItemDecoration {
    private int PADDING_IN_DIPS = 8;
    private final int mPadding;

    public SmartPaddingForLinearSnapHelper(@NonNull Context context,int screenWidth) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        PADDING_IN_DIPS = screenWidth / 2;
        mPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, PADDING_IN_DIPS, metrics);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        final int itemPosition = parent.getChildAdapterPosition(view);
        if (itemPosition == RecyclerView.NO_POSITION) {
            return;
        }
        if (itemPosition == 0) {
            outRect.left = mPadding;
        }

        final RecyclerView.Adapter adapter = parent.getAdapter();
        if ((adapter != null) && (itemPosition == adapter.getItemCount() - 1)) {
            outRect.right = mPadding;
        }
    }

}
