package com.tufike.taxi.rider.databinding;
import com.tufike.taxi.rider.R;
import com.tufike.taxi.rider.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemPromotionBindingImpl extends ItemPromotionBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.constraint_header, 5);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemPromotionBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }
    private ItemPromotionBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.cardview.widget.CardView) bindings[0]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[5]
            , (android.widget.ImageView) bindings[1]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[2]
            );
        this.cardTransaction.setTag(null);
        this.imgThumb.setTag(null);
        this.textLeft.setTag(null);
        this.textTime.setTag(null);
        this.textTitle.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.item == variableId) {
            setItem((com.tufike.taxi.common.models.Promotion) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable com.tufike.taxi.common.models.Promotion Item) {
        this.mItem = Item;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        com.tufike.taxi.common.models.Promotion item = mItem;
        int itemDaysLeft = 0;
        java.lang.String itemTitle = null;
        java.lang.String textLeftAndroidStringCouponDaysLeftItemDaysLeft = null;
        java.lang.String itemDescription = null;
        com.tufike.taxi.common.models.Media itemMedia = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (item != null) {
                    // read item.daysLeft
                    itemDaysLeft = item.getDaysLeft();
                    // read item.title
                    itemTitle = item.getTitle();
                    // read item.description
                    itemDescription = item.getDescription();
                    // read item.media
                    itemMedia = item.getMedia();
                }


                // read @android:string/coupon_days_left
                textLeftAndroidStringCouponDaysLeftItemDaysLeft = textLeft.getResources().getString(R.string.coupon_days_left, itemDaysLeft);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            com.tufike.taxi.common.utils.DataBinder.setMedia(this.imgThumb, itemMedia);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textLeft, textLeftAndroidStringCouponDaysLeftItemDaysLeft);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textTime, itemDescription);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textTitle, itemTitle);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): item
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}