package com.tufike.taxi.rider.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.airbnb.lottie.LottieAnimationView;

public abstract class ActivityLookingBinding extends ViewDataBinding {
  @NonNull
  public final AppCompatButton buttonCancel;

  @NonNull
  public final LottieAnimationView loadingIndicator;

  protected ActivityLookingBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, AppCompatButton buttonCancel, LottieAnimationView loadingIndicator) {
    super(_bindingComponent, _root, _localFieldCount);
    this.buttonCancel = buttonCancel;
    this.loadingIndicator = loadingIndicator;
  }

  @NonNull
  public static ActivityLookingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityLookingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityLookingBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.activity_looking, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityLookingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityLookingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityLookingBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.activity_looking, null, false, component);
  }

  public static ActivityLookingBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityLookingBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityLookingBinding)bind(component, view, com.tufike.taxi.rider.R.layout.activity_looking);
  }
}
