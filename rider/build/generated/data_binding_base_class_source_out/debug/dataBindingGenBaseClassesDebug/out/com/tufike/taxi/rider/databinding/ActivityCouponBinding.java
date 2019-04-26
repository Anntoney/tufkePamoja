package com.tufike.taxi.rider.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.tylersuehr.esr.EmptyStateRecyclerView;

public abstract class ActivityCouponBinding extends ViewDataBinding {
  @NonNull
  public final EmptyStateRecyclerView recyclerView;

  protected ActivityCouponBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, EmptyStateRecyclerView recyclerView) {
    super(_bindingComponent, _root, _localFieldCount);
    this.recyclerView = recyclerView;
  }

  @NonNull
  public static ActivityCouponBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityCouponBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityCouponBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.activity_coupon, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityCouponBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityCouponBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityCouponBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.activity_coupon, null, false, component);
  }

  public static ActivityCouponBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityCouponBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityCouponBinding)bind(component, view, com.tufike.taxi.rider.R.layout.activity_coupon);
  }
}
