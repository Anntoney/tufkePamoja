package com.tufike.taxi.rider.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.tufike.taxi.common.models.Coupon;

public abstract class ItemCouponBinding extends ViewDataBinding {
  @NonNull
  public final AppCompatButton buttonSelect;

  @NonNull
  public final CardView cardTransaction;

  @NonNull
  public final ConstraintLayout constraintHeader;

  @NonNull
  public final TextView textDay;

  @NonNull
  public final TextView textLeft;

  @NonNull
  public final TextView textTime;

  @NonNull
  public final TextView textTitle;

  @Bindable
  protected Coupon mItem;

  protected ItemCouponBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, AppCompatButton buttonSelect, CardView cardTransaction,
      ConstraintLayout constraintHeader, TextView textDay, TextView textLeft, TextView textTime,
      TextView textTitle) {
    super(_bindingComponent, _root, _localFieldCount);
    this.buttonSelect = buttonSelect;
    this.cardTransaction = cardTransaction;
    this.constraintHeader = constraintHeader;
    this.textDay = textDay;
    this.textLeft = textLeft;
    this.textTime = textTime;
    this.textTitle = textTitle;
  }

  public abstract void setItem(@Nullable Coupon item);

  @Nullable
  public Coupon getItem() {
    return mItem;
  }

  @NonNull
  public static ItemCouponBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ItemCouponBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ItemCouponBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.item_coupon, root, attachToRoot, component);
  }

  @NonNull
  public static ItemCouponBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ItemCouponBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ItemCouponBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.item_coupon, null, false, component);
  }

  public static ItemCouponBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ItemCouponBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ItemCouponBinding)bind(component, view, com.tufike.taxi.rider.R.layout.item_coupon);
  }
}
