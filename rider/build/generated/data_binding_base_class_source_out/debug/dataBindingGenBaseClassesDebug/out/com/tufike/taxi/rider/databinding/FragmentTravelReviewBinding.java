package com.tufike.taxi.rider.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.textfield.TextInputLayout;

public abstract class FragmentTravelReviewBinding extends ViewDataBinding {
  @NonNull
  public final Button buttonSaveReview;

  @NonNull
  public final RatingBar ratingBar;

  @NonNull
  public final EditText reviewText;

  @NonNull
  public final TextInputLayout reviewTextLayout;

  protected FragmentTravelReviewBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, Button buttonSaveReview, RatingBar ratingBar, EditText reviewText,
      TextInputLayout reviewTextLayout) {
    super(_bindingComponent, _root, _localFieldCount);
    this.buttonSaveReview = buttonSaveReview;
    this.ratingBar = ratingBar;
    this.reviewText = reviewText;
    this.reviewTextLayout = reviewTextLayout;
  }

  @NonNull
  public static FragmentTravelReviewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentTravelReviewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentTravelReviewBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.fragment_travel_review, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentTravelReviewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentTravelReviewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentTravelReviewBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.fragment_travel_review, null, false, component);
  }

  public static FragmentTravelReviewBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static FragmentTravelReviewBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (FragmentTravelReviewBinding)bind(component, view, com.tufike.taxi.rider.R.layout.fragment_travel_review);
  }
}
