package com.tufike.taxi.rider.databinding;
import com.tufike.taxi.rider.R;
import com.tufike.taxi.rider.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class CardDriverAcceptedBindingImpl extends CardDriverAcceptedBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.image_header, 7);
        sViewsWithIds.put(R.id.image_driver, 8);
        sViewsWithIds.put(R.id.label_distance, 9);
        sViewsWithIds.put(R.id.label_duration, 10);
        sViewsWithIds.put(R.id.label_cost, 11);
        sViewsWithIds.put(R.id.label_rating, 12);
        sViewsWithIds.put(R.id.button_accept, 13);
    }
    // views
    @NonNull
    private final androidx.cardview.widget.CardView mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public CardDriverAcceptedBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }
    private CardDriverAcceptedBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (androidx.appcompat.widget.AppCompatButton) bindings[13]
            , (de.hdodenhof.circleimageview.CircleImageView) bindings[8]
            , (android.widget.ImageView) bindings[7]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[6]
            );
        this.mboundView0 = (androidx.cardview.widget.CardView) bindings[0];
        this.mboundView0.setTag(null);
        this.textCarName.setTag(null);
        this.textCost.setTag(null);
        this.textDistance.setTag(null);
        this.textDriverName.setTag(null);
        this.textDuration.setTag(null);
        this.textRating.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
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
        if (BR.info == variableId) {
            setInfo((com.tufike.taxi.common.models.DriverInfo) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setInfo(@Nullable com.tufike.taxi.common.models.DriverInfo Info) {
        this.mInfo = Info;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.info);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeInfoDriver((com.tufike.taxi.common.models.Driver) object, fieldId);
        }
        return false;
    }
    private boolean onChangeInfoDriver(com.tufike.taxi.common.models.Driver InfoDriver, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
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
        java.lang.String textDistanceAndroidStringUnitDistanceInfoDistance = null;
        com.tufike.taxi.common.models.Car infoDriverCar = null;
        java.lang.String androidxDatabindingViewDataBindingSafeUnboxInfoDriverRatingInt0StringValueOfAndroidxDatabindingViewDataBindingSafeUnboxInfoDriverRatingJavaLangString = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxInfoDriverRatingInt0 = false;
        com.tufike.taxi.common.models.Driver infoDriver = null;
        java.lang.String infoDriverFirstName = null;
        java.lang.String infoDriverCarTitle = null;
        java.lang.Integer infoDuration = null;
        java.lang.String infoDriverFirstNameCharInfoDriverLastName = null;
        java.lang.String stringValueOfAndroidxDatabindingViewDataBindingSafeUnboxInfoDriverRating = null;
        java.lang.String infoDriverFirstNameChar = null;
        java.lang.Integer infoDriverRating = null;
        int androidxDatabindingViewDataBindingSafeUnboxInfoDriverRating = 0;
        com.tufike.taxi.common.models.DriverInfo info = mInfo;
        java.lang.String infoDriverLastName = null;
        java.lang.String textDurationAndroidStringUnitMinuteInfoDuration = null;
        java.lang.Double infoDistance = null;
        java.lang.Double infoCost = null;
        java.lang.String textCostAndroidStringUnitMoneyInfoCost = null;

        if ((dirtyFlags & 0x7L) != 0) {



                if (info != null) {
                    // read info.driver
                    infoDriver = info.driver;
                }
                updateRegistration(0, infoDriver);


                if (infoDriver != null) {
                    // read info.driver.car
                    infoDriverCar = infoDriver.getCar();
                    // read info.driver.firstName
                    infoDriverFirstName = infoDriver.getFirstName();
                    // read info.driver.rating
                    infoDriverRating = infoDriver.getRating();
                    // read info.driver.lastName
                    infoDriverLastName = infoDriver.getLastName();
                }


                if (infoDriverCar != null) {
                    // read info.driver.car.title
                    infoDriverCarTitle = infoDriverCar.getTitle();
                }
                // read (info.driver.firstName) + (' ')
                infoDriverFirstNameChar = (infoDriverFirstName) + (' ');
                // read androidx.databinding.ViewDataBinding.safeUnbox(info.driver.rating)
                androidxDatabindingViewDataBindingSafeUnboxInfoDriverRating = androidx.databinding.ViewDataBinding.safeUnbox(infoDriverRating);


                // read ((info.driver.firstName) + (' ')) + (info.driver.lastName)
                infoDriverFirstNameCharInfoDriverLastName = (infoDriverFirstNameChar) + (infoDriverLastName);
                // read androidx.databinding.ViewDataBinding.safeUnbox(info.driver.rating) != 0
                androidxDatabindingViewDataBindingSafeUnboxInfoDriverRatingInt0 = (androidxDatabindingViewDataBindingSafeUnboxInfoDriverRating) != (0);
            if((dirtyFlags & 0x7L) != 0) {
                if(androidxDatabindingViewDataBindingSafeUnboxInfoDriverRatingInt0) {
                        dirtyFlags |= 0x10L;
                }
                else {
                        dirtyFlags |= 0x8L;
                }
            }
            if ((dirtyFlags & 0x6L) != 0) {

                    if (info != null) {
                        // read info.duration
                        infoDuration = info.duration;
                        // read info.distance
                        infoDistance = info.distance;
                        // read info.cost
                        infoCost = info.cost;
                    }


                    // read @android:string/unit_minute
                    textDurationAndroidStringUnitMinuteInfoDuration = textDuration.getResources().getString(R.string.unit_minute, infoDuration);
                    // read @android:string/unit_distance
                    textDistanceAndroidStringUnitDistanceInfoDistance = textDistance.getResources().getString(R.string.unit_distance, infoDistance);
                    // read @android:string/unit_money
                    textCostAndroidStringUnitMoneyInfoCost = textCost.getResources().getString(R.string.unit_money, infoCost);
            }
        }
        // batch finished

        if ((dirtyFlags & 0x10L) != 0) {

                // read String.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(info.driver.rating))
                stringValueOfAndroidxDatabindingViewDataBindingSafeUnboxInfoDriverRating = java.lang.String.valueOf(androidxDatabindingViewDataBindingSafeUnboxInfoDriverRating);
        }

        if ((dirtyFlags & 0x7L) != 0) {

                // read androidx.databinding.ViewDataBinding.safeUnbox(info.driver.rating) != 0 ? String.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(info.driver.rating)) : "-"
                androidxDatabindingViewDataBindingSafeUnboxInfoDriverRatingInt0StringValueOfAndroidxDatabindingViewDataBindingSafeUnboxInfoDriverRatingJavaLangString = ((androidxDatabindingViewDataBindingSafeUnboxInfoDriverRatingInt0) ? (stringValueOfAndroidxDatabindingViewDataBindingSafeUnboxInfoDriverRating) : ("-"));
        }
        // batch finished
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textCarName, infoDriverCarTitle);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textDriverName, infoDriverFirstNameCharInfoDriverLastName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textRating, androidxDatabindingViewDataBindingSafeUnboxInfoDriverRatingInt0StringValueOfAndroidxDatabindingViewDataBindingSafeUnboxInfoDriverRatingJavaLangString);
        }
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textCost, textCostAndroidStringUnitMoneyInfoCost);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textDistance, textDistanceAndroidStringUnitDistanceInfoDistance);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textDuration, textDurationAndroidStringUnitMinuteInfoDuration);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): info.driver
        flag 1 (0x2L): info
        flag 2 (0x3L): null
        flag 3 (0x4L): androidx.databinding.ViewDataBinding.safeUnbox(info.driver.rating) != 0 ? String.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(info.driver.rating)) : "-"
        flag 4 (0x5L): androidx.databinding.ViewDataBinding.safeUnbox(info.driver.rating) != 0 ? String.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(info.driver.rating)) : "-"
    flag mapping end*/
    //end
}