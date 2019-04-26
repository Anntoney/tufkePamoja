package com.tufike.taxi.rider.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.textfield.TextInputLayout;

public abstract class FragmentReviewBinding extends ViewDataBinding {
  @NonNull
  public final RatingBar ratingBar;

  @NonNull
  public final EditText reviewText;

  @NonNull
  public final TextInputLayout reviewTextLayout;

  protected FragmentReviewBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, RatingBar ratingBar, EditText reviewText,
      TextInputLayout reviewTextLayout) {
    super(_bindingComponent, _root, _localFieldCount);
    this.ratingBar = ratingBar;
    this.reviewText = reviewText;
    this.reviewTextLayout = reviewTextLayout;
  }

  @NonNull
  public static FragmentReviewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentReviewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentReviewBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.fragment_review, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentReviewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentReviewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentReviewBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.fragment_review, null, false, component);
  }

  public static FragmentReviewBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static FragmentReviewBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (FragmentReviewBinding)bind(component, view, com.tufike.taxi.rider.R.layout.fragment_review);
  }
}
