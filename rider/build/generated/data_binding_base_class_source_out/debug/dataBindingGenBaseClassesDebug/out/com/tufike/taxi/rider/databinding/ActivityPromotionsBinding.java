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

public abstract class ActivityPromotionsBinding extends ViewDataBinding {
  @NonNull
  public final EmptyStateRecyclerView recyclerView;

  protected ActivityPromotionsBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, EmptyStateRecyclerView recyclerView) {
    super(_bindingComponent, _root, _localFieldCount);
    this.recyclerView = recyclerView;
  }

  @NonNull
  public static ActivityPromotionsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityPromotionsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityPromotionsBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.activity_promotions, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityPromotionsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityPromotionsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityPromotionsBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.activity_promotions, null, false, component);
  }

  public static ActivityPromotionsBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityPromotionsBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityPromotionsBinding)bind(component, view, com.tufike.taxi.rider.R.layout.activity_promotions);
  }
}
