package com.tufike.taxi.rider.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.tufike.taxi.common.models.Address;

public abstract class FragmentEditAddressBinding extends ViewDataBinding {
  @NonNull
  public final TextInputEditText textAddress;

  @NonNull
  public final TextInputLayout textLayoutAddress;

  @NonNull
  public final TextInputLayout textLayoutTitle;

  @NonNull
  public final TextInputEditText textTitle;

  @Bindable
  protected Address mAddress;

  protected FragmentEditAddressBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, TextInputEditText textAddress, TextInputLayout textLayoutAddress,
      TextInputLayout textLayoutTitle, TextInputEditText textTitle) {
    super(_bindingComponent, _root, _localFieldCount);
    this.textAddress = textAddress;
    this.textLayoutAddress = textLayoutAddress;
    this.textLayoutTitle = textLayoutTitle;
    this.textTitle = textTitle;
  }

  public abstract void setAddress(@Nullable Address address);

  @Nullable
  public Address getAddress() {
    return mAddress;
  }

  @NonNull
  public static FragmentEditAddressBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentEditAddressBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentEditAddressBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.fragment_edit_address, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentEditAddressBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentEditAddressBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentEditAddressBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.fragment_edit_address, null, false, component);
  }

  public static FragmentEditAddressBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static FragmentEditAddressBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (FragmentEditAddressBinding)bind(component, view, com.tufike.taxi.rider.R.layout.fragment_edit_address);
  }
}
