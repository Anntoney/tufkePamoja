package com.tufike.taxi.rider.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.tufike.taxi.common.models.DriverInfo;
import de.hdodenhof.circleimageview.CircleImageView;

public abstract class CardDriverAcceptedBinding extends ViewDataBinding {
  @NonNull
  public final AppCompatButton buttonAccept;

  @NonNull
  public final CircleImageView imageDriver;

  @NonNull
  public final ImageView imageHeader;

  @NonNull
  public final TextView labelCost;

  @NonNull
  public final TextView labelDistance;

  @NonNull
  public final TextView labelDuration;

  @NonNull
  public final TextView labelRating;

  @NonNull
  public final TextView textCarName;

  @NonNull
  public final TextView textCost;

  @NonNull
  public final TextView textDistance;

  @NonNull
  public final TextView textDriverName;

  @NonNull
  public final TextView textDuration;

  @NonNull
  public final TextView textRating;

  @Bindable
  protected DriverInfo mInfo;

  protected CardDriverAcceptedBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, AppCompatButton buttonAccept, CircleImageView imageDriver,
      ImageView imageHeader, TextView labelCost, TextView labelDistance, TextView labelDuration,
      TextView labelRating, TextView textCarName, TextView textCost, TextView textDistance,
      TextView textDriverName, TextView textDuration, TextView textRating) {
    super(_bindingComponent, _root, _localFieldCount);
    this.buttonAccept = buttonAccept;
    this.imageDriver = imageDriver;
    this.imageHeader = imageHeader;
    this.labelCost = labelCost;
    this.labelDistance = labelDistance;
    this.labelDuration = labelDuration;
    this.labelRating = labelRating;
    this.textCarName = textCarName;
    this.textCost = textCost;
    this.textDistance = textDistance;
    this.textDriverName = textDriverName;
    this.textDuration = textDuration;
    this.textRating = textRating;
  }

  public abstract void setInfo(@Nullable DriverInfo info);

  @Nullable
  public DriverInfo getInfo() {
    return mInfo;
  }

  @NonNull
  public static CardDriverAcceptedBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static CardDriverAcceptedBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<CardDriverAcceptedBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.card_driver_accepted, root, attachToRoot, component);
  }

  @NonNull
  public static CardDriverAcceptedBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static CardDriverAcceptedBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<CardDriverAcceptedBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.card_driver_accepted, null, false, component);
  }

  public static CardDriverAcceptedBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static CardDriverAcceptedBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (CardDriverAcceptedBinding)bind(component, view, com.tufike.taxi.rider.R.layout.card_driver_accepted);
  }
}
