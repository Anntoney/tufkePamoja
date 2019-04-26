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
import com.tufike.taxi.common.models.Driver;
import de.hdodenhof.circleimageview.CircleImageView;

public abstract class FragmentTravelDriverBinding extends ViewDataBinding {
  @NonNull
  public final CircleImageView imageDriver;

  @NonNull
  public final ImageView imageLocation;

  @NonNull
  public final TextView textCarName;

  @NonNull
  public final TextView textDestination;

  @NonNull
  public final TextView textDriverName;

  @NonNull
  public final TextView textPickup;

  @Bindable
  protected Driver mDriver;

  protected FragmentTravelDriverBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, CircleImageView imageDriver, ImageView imageLocation,
      TextView textCarName, TextView textDestination, TextView textDriverName,
      TextView textPickup) {
    super(_bindingComponent, _root, _localFieldCount);
    this.imageDriver = imageDriver;
    this.imageLocation = imageLocation;
    this.textCarName = textCarName;
    this.textDestination = textDestination;
    this.textDriverName = textDriverName;
    this.textPickup = textPickup;
  }

  public abstract void setDriver(@Nullable Driver driver);

  @Nullable
  public Driver getDriver() {
    return mDriver;
  }

  @NonNull
  public static FragmentTravelDriverBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentTravelDriverBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentTravelDriverBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.fragment_travel_driver, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentTravelDriverBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentTravelDriverBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentTravelDriverBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.fragment_travel_driver, null, false, component);
  }

  public static FragmentTravelDriverBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static FragmentTravelDriverBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (FragmentTravelDriverBinding)bind(component, view, com.tufike.taxi.rider.R.layout.fragment_travel_driver);
  }
}
