package com.tufike.taxi.rider.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.tufike.taxi.common.models.Travel;

public abstract class FragmentTravelStatsBinding extends ViewDataBinding {
  @NonNull
  public final ImageView applyCouponButton;

  @NonNull
  public final TextView balanceLabel;

  @NonNull
  public final TextView balanceText;

  @NonNull
  public final ImageView chargeAccountButton;

  @NonNull
  public final TextView costLabel;

  @NonNull
  public final TextView costText;

  @NonNull
  public final TextView distanceLabel;

  @NonNull
  public final TextView distanceText;

  @NonNull
  public final TextView timeLabel;

  @NonNull
  public final TextView timeText;

  @Bindable
  protected Travel mTravel;

  protected FragmentTravelStatsBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, ImageView applyCouponButton, TextView balanceLabel,
      TextView balanceText, ImageView chargeAccountButton, TextView costLabel, TextView costText,
      TextView distanceLabel, TextView distanceText, TextView timeLabel, TextView timeText) {
    super(_bindingComponent, _root, _localFieldCount);
    this.applyCouponButton = applyCouponButton;
    this.balanceLabel = balanceLabel;
    this.balanceText = balanceText;
    this.chargeAccountButton = chargeAccountButton;
    this.costLabel = costLabel;
    this.costText = costText;
    this.distanceLabel = distanceLabel;
    this.distanceText = distanceText;
    this.timeLabel = timeLabel;
    this.timeText = timeText;
  }

  public abstract void setTravel(@Nullable Travel travel);

  @Nullable
  public Travel getTravel() {
    return mTravel;
  }

  @NonNull
  public static FragmentTravelStatsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentTravelStatsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentTravelStatsBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.fragment_travel_stats, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentTravelStatsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentTravelStatsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentTravelStatsBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.fragment_travel_stats, null, false, component);
  }

  public static FragmentTravelStatsBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static FragmentTravelStatsBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (FragmentTravelStatsBinding)bind(component, view, com.tufike.taxi.rider.R.layout.fragment_travel_stats);
  }
}
