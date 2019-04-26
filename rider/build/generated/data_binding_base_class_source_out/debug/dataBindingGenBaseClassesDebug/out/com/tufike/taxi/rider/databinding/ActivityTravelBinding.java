package com.tufike.taxi.rider.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import ng.max.slideview.SlideView;

public abstract class ActivityTravelBinding extends ViewDataBinding {
  @NonNull
  public final SlideView slideCall;

  @NonNull
  public final SlideView slideCancel;

  @NonNull
  public final TabLayout tabLayout;

  @NonNull
  public final ViewPager viewpager;

  protected ActivityTravelBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, SlideView slideCall, SlideView slideCancel, TabLayout tabLayout,
      ViewPager viewpager) {
    super(_bindingComponent, _root, _localFieldCount);
    this.slideCall = slideCall;
    this.slideCancel = slideCancel;
    this.tabLayout = tabLayout;
    this.viewpager = viewpager;
  }

  @NonNull
  public static ActivityTravelBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityTravelBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityTravelBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.activity_travel, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityTravelBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityTravelBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityTravelBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.activity_travel, null, false, component);
  }

  public static ActivityTravelBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityTravelBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityTravelBinding)bind(component, view, com.tufike.taxi.rider.R.layout.activity_travel);
  }
}
