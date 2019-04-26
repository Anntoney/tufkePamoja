package com.tufike.taxi.rider.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public abstract class ActivityMainBinding extends ViewDataBinding {
  @NonNull
  public final CardView bottomSheet;

  @NonNull
  public final AppCompatButton buttonConfirmDestination;

  @NonNull
  public final AppCompatButton buttonConfirmPickup;

  @NonNull
  public final AppCompatButton buttonRequest;

  @NonNull
  public final DrawerLayout drawerLayout;

  @NonNull
  public final ImageView imageDestination;

  @NonNull
  public final ImageView imagePickup;

  @NonNull
  public final LinearLayout mapLayout;

  @NonNull
  public final NavigationView navigationView;

  @NonNull
  public final FloatingSearchView searchPlace;

  @NonNull
  public final TextView searchText;

  @NonNull
  public final ViewPager serviceTypesViewPager;

  @NonNull
  public final TabLayout tabCategories;

  protected ActivityMainBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, CardView bottomSheet, AppCompatButton buttonConfirmDestination,
      AppCompatButton buttonConfirmPickup, AppCompatButton buttonRequest, DrawerLayout drawerLayout,
      ImageView imageDestination, ImageView imagePickup, LinearLayout mapLayout,
      NavigationView navigationView, FloatingSearchView searchPlace, TextView searchText,
      ViewPager serviceTypesViewPager, TabLayout tabCategories) {
    super(_bindingComponent, _root, _localFieldCount);
    this.bottomSheet = bottomSheet;
    this.buttonConfirmDestination = buttonConfirmDestination;
    this.buttonConfirmPickup = buttonConfirmPickup;
    this.buttonRequest = buttonRequest;
    this.drawerLayout = drawerLayout;
    this.imageDestination = imageDestination;
    this.imagePickup = imagePickup;
    this.mapLayout = mapLayout;
    this.navigationView = navigationView;
    this.searchPlace = searchPlace;
    this.searchText = searchText;
    this.serviceTypesViewPager = serviceTypesViewPager;
    this.tabCategories = tabCategories;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityMainBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.activity_main, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityMainBinding>inflate(inflater, com.tufike.taxi.rider.R.layout.activity_main, null, false, component);
  }

  public static ActivityMainBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityMainBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityMainBinding)bind(component, view, com.tufike.taxi.rider.R.layout.activity_main);
  }
}
