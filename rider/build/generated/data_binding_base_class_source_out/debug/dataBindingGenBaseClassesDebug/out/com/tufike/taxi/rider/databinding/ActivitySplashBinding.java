package com.tufike.taxi.rider.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class ActivitySplashBinding extends ViewDataBinding {
  @NonNull
  public final Button loginButton;

  @NonNull
  public final ImageView logoImage;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final ConstraintLayout relativeLayout;

  @NonNull
  public final TextView textView;

  protected ActivitySplashBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, Button loginButton, ImageView logoImage, ProgressBar progressBar,
      ConstraintLayout relativeLayout, TextView textView) {
    super(_bindingComponent, _root, _localFieldCount);
    this.loginButton = loginButton;
    this.logoImage = logoImage;
    this.progressBar = progressBar;
    this.relativeLayout = relativeLayout;
    this.textView = textView;
  }

  @NonNull
  public static ActivitySplashBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivitySplashBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivitySplashBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.activity_splash, root, attachToRoot, component);
  }

  @NonNull
  public static ActivitySplashBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivitySplashBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivitySplashBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.activity_splash, null, false, component);
  }

  public static ActivitySplashBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivitySplashBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivitySplashBinding)bind(component, view, com.tufike.taxi.rider.R.layout.activity_splash);
  }
}
