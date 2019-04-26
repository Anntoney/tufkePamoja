package com.tufike.taxi.rider.databinding;
import com.tufike.taxi.rider.R;
import com.tufike.taxi.rider.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityEditProfileBindingImpl extends ActivityEditProfileBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.app_bar_layout, 8);
        sViewsWithIds.put(R.id.collapse_toolbar, 9);
        sViewsWithIds.put(R.id.media, 10);
        sViewsWithIds.put(R.id.toolbar, 11);
        sViewsWithIds.put(R.id.mobile_text_layout, 12);
        sViewsWithIds.put(R.id.email_text_layout, 13);
        sViewsWithIds.put(R.id.first_name_text_layout, 14);
        sViewsWithIds.put(R.id.last_name_text_layout, 15);
        sViewsWithIds.put(R.id.address_text_layout, 16);
    }
    // views
    @NonNull
    private final androidx.coordinatorlayout.widget.CoordinatorLayout mboundView0;
    @NonNull
    private final android.widget.EditText mboundView2;
    @NonNull
    private final android.widget.EditText mboundView3;
    @NonNull
    private final android.widget.EditText mboundView4;
    @NonNull
    private final android.widget.EditText mboundView5;
    @NonNull
    private final android.widget.EditText mboundView7;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener mboundView3androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of user.email
            //         is user.setEmail((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mboundView3);
            // localize variables for thread safety
            // user.email
            java.lang.String userEmail = null;
            // user != null
            boolean userJavaLangObjectNull = false;
            // user
            com.tufike.taxi.common.models.Rider user = mUser;



            userJavaLangObjectNull = (user) != (null);
            if (userJavaLangObjectNull) {




                user.setEmail(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener mboundView4androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of user.firstName
            //         is user.setFirstName((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mboundView4);
            // localize variables for thread safety
            // user != null
            boolean userJavaLangObjectNull = false;
            // user.firstName
            java.lang.String userFirstName = null;
            // user
            com.tufike.taxi.common.models.Rider user = mUser;



            userJavaLangObjectNull = (user) != (null);
            if (userJavaLangObjectNull) {




                user.setFirstName(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener mboundView5androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of user.lastName
            //         is user.setLastName((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mboundView5);
            // localize variables for thread safety
            // user != null
            boolean userJavaLangObjectNull = false;
            // user
            com.tufike.taxi.common.models.Rider user = mUser;
            // user.lastName
            java.lang.String userLastName = null;



            userJavaLangObjectNull = (user) != (null);
            if (userJavaLangObjectNull) {




                user.setLastName(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener mboundView7androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of user.address
            //         is user.setAddress((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mboundView7);
            // localize variables for thread safety
            // user.address
            java.lang.String userAddress = null;
            // user != null
            boolean userJavaLangObjectNull = false;
            // user
            com.tufike.taxi.common.models.Rider user = mUser;



            userJavaLangObjectNull = (user) != (null);
            if (userJavaLangObjectNull) {




                user.setAddress(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener spinnerGendergenderAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of user.gender
            //         is user.setGender((com.tufike.taxi.common.models.Gender) callbackArg_0)
            com.tufike.taxi.common.models.Gender callbackArg_0 = com.tufike.taxi.common.utils.DataBinder.getGender(spinnerGender);
            // localize variables for thread safety
            // user != null
            boolean userJavaLangObjectNull = false;
            // user
            com.tufike.taxi.common.models.Rider user = mUser;
            // user.gender
            com.tufike.taxi.common.models.Gender userGender = null;



            userJavaLangObjectNull = (user) != (null);
            if (userJavaLangObjectNull) {




                user.setGender(((com.tufike.taxi.common.models.Gender) (callbackArg_0)));
            }
        }
    };

    public ActivityEditProfileBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds));
    }
    private ActivityEditProfileBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (com.google.android.material.textfield.TextInputLayout) bindings[16]
            , (com.google.android.material.appbar.AppBarLayout) bindings[8]
            , (com.google.android.material.appbar.CollapsingToolbarLayout) bindings[9]
            , (com.google.android.material.textfield.TextInputLayout) bindings[13]
            , (com.google.android.material.textfield.TextInputLayout) bindings[14]
            , (com.google.android.material.textfield.TextInputLayout) bindings[15]
            , (android.widget.ImageView) bindings[10]
            , (com.google.android.material.textfield.TextInputLayout) bindings[12]
            , (de.hdodenhof.circleimageview.CircleImageView) bindings[1]
            , (com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner) bindings[6]
            , (androidx.appcompat.widget.Toolbar) bindings[11]
            );
        this.mboundView0 = (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView2 = (android.widget.EditText) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.EditText) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (android.widget.EditText) bindings[4];
        this.mboundView4.setTag(null);
        this.mboundView5 = (android.widget.EditText) bindings[5];
        this.mboundView5.setTag(null);
        this.mboundView7 = (android.widget.EditText) bindings[7];
        this.mboundView7.setTag(null);
        this.profileImage.setTag(null);
        this.spinnerGender.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x200L;
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
        if (BR.converter == variableId) {
            setConverter((com.tufike.taxi.common.utils.Converters) variable);
        }
        else if (BR.user == variableId) {
            setUser((com.tufike.taxi.common.models.Rider) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setConverter(@Nullable com.tufike.taxi.common.utils.Converters Converter) {
        this.mConverter = Converter;
    }
    public void setUser(@Nullable com.tufike.taxi.common.models.Rider User) {
        updateRegistration(0, User);
        this.mUser = User;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.user);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeUser((com.tufike.taxi.common.models.Rider) object, fieldId);
        }
        return false;
    }
    private boolean onChangeUser(com.tufike.taxi.common.models.Rider User, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        else if (fieldId == BR.media) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        else if (fieldId == BR.mobileNumber) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        else if (fieldId == BR.email) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        else if (fieldId == BR.firstName) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        else if (fieldId == BR.lastName) {
            synchronized(this) {
                    mDirtyFlags |= 0x40L;
            }
            return true;
        }
        else if (fieldId == BR.gender) {
            synchronized(this) {
                    mDirtyFlags |= 0x80L;
            }
            return true;
        }
        else if (fieldId == BR.address) {
            synchronized(this) {
                    mDirtyFlags |= 0x100L;
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
        java.lang.String stringValueOfUserMobileNumber = null;
        com.tufike.taxi.common.models.Gender userGender = null;
        java.lang.String userEmail = null;
        com.tufike.taxi.common.models.Media userMedia = null;
        java.lang.String userFirstName = null;
        long userMobileNumber = 0L;
        com.tufike.taxi.common.models.Rider user = mUser;
        java.lang.String userAddress = null;
        java.lang.String userLastName = null;

        if ((dirtyFlags & 0x3fdL) != 0) {


            if ((dirtyFlags & 0x281L) != 0) {

                    if (user != null) {
                        // read user.gender
                        userGender = user.getGender();
                    }
            }
            if ((dirtyFlags & 0x211L) != 0) {

                    if (user != null) {
                        // read user.email
                        userEmail = user.getEmail();
                    }
            }
            if ((dirtyFlags & 0x205L) != 0) {

                    if (user != null) {
                        // read user.media
                        userMedia = user.getMedia();
                    }
            }
            if ((dirtyFlags & 0x221L) != 0) {

                    if (user != null) {
                        // read user.firstName
                        userFirstName = user.getFirstName();
                    }
            }
            if ((dirtyFlags & 0x209L) != 0) {

                    if (user != null) {
                        // read user.mobileNumber
                        userMobileNumber = user.getMobileNumber();
                    }


                    // read String.valueOf(user.mobileNumber)
                    stringValueOfUserMobileNumber = java.lang.String.valueOf(userMobileNumber);
            }
            if ((dirtyFlags & 0x301L) != 0) {

                    if (user != null) {
                        // read user.address
                        userAddress = user.getAddress();
                    }
            }
            if ((dirtyFlags & 0x241L) != 0) {

                    if (user != null) {
                        // read user.lastName
                        userLastName = user.getLastName();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x209L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, stringValueOfUserMobileNumber);
        }
        if ((dirtyFlags & 0x211L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, userEmail);
        }
        if ((dirtyFlags & 0x200L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mboundView3, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mboundView3androidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mboundView4, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mboundView4androidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mboundView5, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mboundView5androidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mboundView7, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mboundView7androidTextAttrChanged);
            com.tufike.taxi.common.utils.DataBinder.bindGenderChanged(this.spinnerGender, spinnerGendergenderAttrChanged);
        }
        if ((dirtyFlags & 0x221L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, userFirstName);
        }
        if ((dirtyFlags & 0x241L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView5, userLastName);
        }
        if ((dirtyFlags & 0x301L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView7, userAddress);
        }
        if ((dirtyFlags & 0x205L) != 0) {
            // api target 1

            com.tufike.taxi.common.utils.DataBinder.setMedia(this.profileImage, userMedia);
        }
        if ((dirtyFlags & 0x281L) != 0) {
            // api target 1

            com.tufike.taxi.common.utils.DataBinder.setGender(this.spinnerGender, userGender);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): user
        flag 1 (0x2L): converter
        flag 2 (0x3L): user.media
        flag 3 (0x4L): user.mobileNumber
        flag 4 (0x5L): user.email
        flag 5 (0x6L): user.firstName
        flag 6 (0x7L): user.lastName
        flag 7 (0x8L): user.gender
        flag 8 (0x9L): user.address
        flag 9 (0xaL): null
    flag mapping end*/
    //end
}