package com.tufike.taxi.rider.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.tufike.taxi.common.models.Promotion;

public abstract class ItemPromotionBinding extends ViewDataBinding {
  @NonNull
  public final CardView cardTransaction;

  @NonNull
  public final ConstraintLayout constraintHeader;

  @NonNull
  public final ImageView imgThumb;

  @NonNull
  public final TextView textLeft;

  @NonNull
  public final TextView textTime;

  @NonNull
  public final TextView textTitle;

  @Bindable
  protected Promotion mItem;

  protected ItemPromotionBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, CardView cardTransaction, ConstraintLayout constraintHeader,
      ImageView imgThumb, TextView textLeft, TextView textTime, TextView textTitle) {
    super(_bindingComponent, _root, _localFieldCount);
    this.cardTransaction = cardTransaction;
    this.constraintHeader = constraintHeader;
    this.imgThumb = imgThumb;
    this.textLeft = textLeft;
    this.textTime = textTime;
    this.textTitle = textTitle;
  }

  public abstract void setItem(@Nullable Promotion item);

  @Nullable
  public Promotion getItem() {
    return mItem;
  }

  @NonNull
  public static ItemPromotionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ItemPromotionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ItemPromotionBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.item_promotion, root, attachToRoot, component);
  }

  @NonNull
  public static ItemPromotionBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ItemPromotionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ItemPromotionBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.item_promotion, null, false, component);
  }

  public static ItemPromotionBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ItemPromotionBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ItemPromotionBinding)bind(component, view, com.tufike.taxi.rider.R.layout.item_promotion);
  }
}
