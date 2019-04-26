package com.tufike.taxi.rider.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.textfield.TextInputLayout;
import com.tufike.taxi.common.models.Rider;
import com.tufike.taxi.common.utils.Converters;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;
import de.hdodenhof.circleimageview.CircleImageView;

public abstract class ActivityEditProfileBinding extends ViewDataBinding {
  @NonNull
  public final TextInputLayout addressTextLayout;

  @NonNull
  public final AppBarLayout appBarLayout;

  @NonNull
  public final CollapsingToolbarLayout collapseToolbar;

  @NonNull
  public final TextInputLayout emailTextLayout;

  @NonNull
  public final TextInputLayout firstNameTextLayout;

  @NonNull
  public final TextInputLayout lastNameTextLayout;

  @NonNull
  public final ImageView media;

  @NonNull
  public final TextInputLayout mobileTextLayout;

  @NonNull
  public final CircleImageView profileImage;

  @NonNull
  public final MaterialBetterSpinner spinnerGender;

  @NonNull
  public final Toolbar toolbar;

  @Bindable
  protected Rider mUser;

  @Bindable
  protected Converters mConverter;

  protected ActivityEditProfileBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, TextInputLayout addressTextLayout, AppBarLayout appBarLayout,
      CollapsingToolbarLayout collapseToolbar, TextInputLayout emailTextLayout,
      TextInputLayout firstNameTextLayout, TextInputLayout lastNameTextLayout, ImageView media,
      TextInputLayout mobileTextLayout, CircleImageView profileImage,
      MaterialBetterSpinner spinnerGender, Toolbar toolbar) {
    super(_bindingComponent, _root, _localFieldCount);
    this.addressTextLayout = addressTextLayout;
    this.appBarLayout = appBarLayout;
    this.collapseToolbar = collapseToolbar;
    this.emailTextLayout = emailTextLayout;
    this.firstNameTextLayout = firstNameTextLayout;
    this.lastNameTextLayout = lastNameTextLayout;
    this.media = media;
    this.mobileTextLayout = mobileTextLayout;
    this.profileImage = profileImage;
    this.spinnerGender = spinnerGender;
    this.toolbar = toolbar;
  }

  public abstract void setUser(@Nullable Rider user);

  @Nullable
  public Rider getUser() {
    return mUser;
  }

  public abstract void setConverter(@Nullable Converters converter);

  @Nullable
  public Converters getConverter() {
    return mConverter;
  }

  @NonNull
  public static ActivityEditProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityEditProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityEditProfileBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.activity_edit_profile, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityEditProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityEditProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityEditProfileBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.activity_edit_profile, null, false, component);
  }

  public static ActivityEditProfileBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityEditProfileBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityEditProfileBinding)bind(component, view, com.tufike.taxi.rider.R.layout.activity_edit_profile);
  }
}
