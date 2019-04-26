package com.tufike.taxi.rider.activities.travel;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.FragmentManager;
import androidx.transition.TransitionManager;

import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.tufike.taxi.common.activities.chargeAccount.ChargeAccountActivity;
import com.tufike.taxi.common.components.BaseActivity;
import com.tufike.taxi.common.components.LoadingDialog;
import com.tufike.taxi.common.events.ServiceCallRequestEvent;
import com.tufike.taxi.common.events.ServiceCallRequestResultEvent;
import com.tufike.taxi.common.events.ServiceCancelEvent;
import com.tufike.taxi.common.events.ServiceCancelResultEvent;
import com.tufike.taxi.common.location.MapHelper;
import com.tufike.taxi.common.models.Coupon;
import com.tufike.taxi.common.models.Review;
import com.tufike.taxi.common.models.Travel;
import com.tufike.taxi.common.utils.AlertDialogBuilder;
import com.tufike.taxi.common.utils.AlerterHelper;
import com.tufike.taxi.common.utils.CommonUtils;
import com.tufike.taxi.common.utils.ServerResponse;
import com.tufike.taxi.rider.R;
import com.tufike.taxi.rider.activities.coupon.CouponActivity;
import com.tufike.taxi.rider.activities.travel.adapters.TravelTabsViewPagerAdapter;
import com.tufike.taxi.rider.activities.travel.fragments.ReviewDialog;
import com.tufike.taxi.rider.activities.travel.fragments.TabStatisticsFragment;
import com.tufike.taxi.rider.databinding.ActivityTravelBinding;
import com.tufike.taxi.rider.events.ReviewDriverEvent;
import com.tufike.taxi.rider.events.ReviewDriverResultEvent;
import com.tufike.taxi.rider.events.ServiceFinishedEvent;
import com.tufike.taxi.rider.events.ServiceStartedEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TravelActivity extends BaseActivity implements OnMapReadyCallback, ReviewDialog.onReviewFragmentInteractionListener, TabStatisticsFragment.onTravelInfoReceived {
    private static final int ACTIVITY_COUPON = 700;
    ActivityTravelBinding binding;
    Travel travel;
    Marker pickupMarker;
    Marker driverMarker;
    Marker destinationMarker;
    LatLng driverLocation;
    GoogleMap gMap;
    TravelTabsViewPagerAdapter travelTabsViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_travel);
        travel = Travel.fromJson(getIntent().getStringExtra("travel"));
        binding.slideCancel.setOnSlideCompleteListener(slideView -> eventBus.post(new ServiceCancelEvent()));
        binding.slideCall.setOnSlideCompleteListener(slideView -> TravelActivity.this.onCallDriverClicked(null));
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        travelTabsViewPagerAdapter = new TravelTabsViewPagerAdapter(getSupportFragmentManager(), TravelActivity.this, travel);
        binding.viewpager.setAdapter(travelTabsViewPagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewpager);
        if(travel.getRating() != null) {
            travelTabsViewPagerAdapter.deletePage(2);
            TabLayout.Tab tab = binding.tabLayout.getTabAt(0);
            if (tab != null)
                tab.select();
        }
        if(travel.getStartTimestamp() != null) {
            TransitionManager.beginDelayedTransition((ViewGroup) (binding.getRoot()));
            binding.slideCall.setVisibility(View.GONE);
            binding.slideCancel.setVisibility(View.GONE);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onServiceFinished(ServiceFinishedEvent event) {
        String message;
        travel.setFinishTimestamp(new Timestamp(System.currentTimeMillis()));
        if (event.isCreditUsed)
            message = getString(R.string.travel_finished_taken_from_balance, event.amount);
        else
            message = getString(R.string.travel_finished_not_sufficient_balance, event.amount);
        new MaterialDialog.Builder(this)
                .title(R.string.message_default_title)
                .content(message)
                .positiveText(R.string.alert_ok)
                .onPositive((dialog, which) -> {
                    if (travelTabsViewPagerAdapter.getCount() == 2) {
                        finish();
                        return;
                    }
                    FragmentManager fm = getSupportFragmentManager();
                    ReviewDialog reviewDialog = ReviewDialog.newInstance();
                    reviewDialog.show(fm, "fragment_review_travel");
                }).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onServiceCanceled(ServiceCancelResultEvent event) {
        if (event.hasError()) {
            event.showError(TravelActivity.this, result -> {
                if (result == AlertDialogBuilder.DialogResult.RETRY)
                    eventBus.post(new ServiceCancelEvent());
            });
            return;
        }
        AlertDialogBuilder.show(TravelActivity.this, getString(R.string.service_canceled), AlertDialogBuilder.DialogButton.OK, result -> finish());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReviewResult(ReviewDriverResultEvent event) {
        if (event.response == ServerResponse.OK) {
            if (travel.getFinishTimestamp() != null) {
                finish();
                return;
            }
            AlerterHelper.showInfo(TravelActivity.this, getString(R.string.message_review_sent));
            travelTabsViewPagerAdapter.deletePage(2);
            TabLayout.Tab tab = binding.tabLayout.getTabAt(0);
            if (tab != null)
                tab.select();

        } else
            event.showAlert(TravelActivity.this);
    }

    public void onChargeAccountClicked(View view) {
        Intent intent = new Intent(TravelActivity.this, ChargeAccountActivity.class);
        if (travel.getCostBest() - CommonUtils.rider.getBalance() > 0)
            intent.putExtra("defaultAmount", travel.getCostBest() - CommonUtils.rider.getBalance());
        startActivity(intent);
    }

    public void onApplyCouponClicked(View view) {
        Intent intent = new Intent(TravelActivity.this, CouponActivity.class);
        intent.putExtra("select_mode", true);
        startActivityForResult(intent,ACTIVITY_COUPON);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTravelStarted(ServiceStartedEvent event) {
        travel.setStartTimestamp(new Timestamp(System.currentTimeMillis()));
        AlerterHelper.showInfo(TravelActivity.this, getString(R.string.message_travel_started));
        updateMarkers();
        TransitionManager.beginDelayedTransition((ViewGroup) (binding.getRoot()));
        binding.slideCall.setVisibility(View.GONE);
        binding.slideCancel.setVisibility(View.GONE);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        gMap = googleMap;
        gMap.setTrafficEnabled(true);
        updateMarkers();
    }

    void updateMarkers() {
        List<LatLng> locations = new ArrayList<>();
        if (pickupMarker == null)
            pickupMarker = gMap.addMarker(new MarkerOptions()
                    .position(travel.getPickupPoint())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_pickup)));
        else
            pickupMarker.setPosition(travel.getPickupPoint());
        if (destinationMarker == null)
            destinationMarker = gMap.addMarker(new MarkerOptions()
                    .position(travel.getDestinationPoint())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_destination)));
        else
            destinationMarker.setPosition(travel.getDestinationPoint());
        if (driverLocation != null) {
            locations.add(driverLocation);
            if (travel.getStartTimestamp() != null)
                locations.add(travel.getDestinationPoint());
            else
                locations.add(travel.getPickupPoint());
            if (driverMarker == null)
                driverMarker = gMap.addMarker(new MarkerOptions()
                        .position(driverLocation)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_taxi)));
            else
                driverMarker.setPosition(driverLocation);
        } else {
            locations.add(travel.getPickupPoint());
            locations.add(travel.getDestinationPoint());
        }
        boolean allEqual = true;
        for(int i = 0; i < locations.size() - 1; i++) {
            if(locations.get(i).latitude != locations.get(i + 1).latitude || locations.get(i).longitude != locations.get(i + 1).longitude) {
                allEqual = false;
                break;
            }
        }
        if(!allEqual)
            MapHelper.centerLatLngsInMap(gMap, locations, true);
        else {
            gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(locations.get(0), 16));
        }

    }

    PermissionListener callPermissionListener = new PermissionListener() {
        @SuppressLint("MissingPermission")
        @Override
        public void onPermissionGranted() {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:+" + travel.getDriver().getMobileNumber()));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {

        }

    };


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCallRequested(ServiceCallRequestResultEvent event) {
        LoadingDialog.dismiss();
        if (event.hasError()) {
            event.showError(TravelActivity.this, result -> {
                if (result == AlertDialogBuilder.DialogResult.RETRY)
                    onCallDriverClicked(null);
            });
            return;
        }
        AlertDialogBuilder.show(TravelActivity.this, getString(R.string.call_request_sent));
    }

    @Override
    public void onReviewTravelClicked(Review review) {
        eventBus.post(new ReviewDriverEvent(review));
    }

    public void onCallDriverClicked(View view) {
        boolean isCallRequestEnabled = getResources().getBoolean(R.bool.is_call_request_enabled_rider);
        boolean isDirectCallEnabled = getResources().getBoolean(R.bool.is_direct_call_enabled_rider);
        if (isCallRequestEnabled && !isDirectCallEnabled)
            eventBus.post(new ServiceCallRequestEvent());
        if (!isCallRequestEnabled && isDirectCallEnabled)
            TedPermission.with(this)
                    .setPermissionListener(callPermissionListener)
                    .setDeniedMessage(R.string.message_permission_denied)
                    .setPermissions(Manifest.permission.CALL_PHONE)
                    .check();
        new MaterialDialog.Builder(this)
                .title(R.string.select_contact_approach)
                .items(new String[]{getString(R.string.direct_call), getString(R.string.operator_call)})
                .itemsCallback((dialog, view1, which, text) -> {
                    if (which == 0)
                        TedPermission.with(TravelActivity.this)
                                .setPermissionListener(callPermissionListener)
                                .setDeniedMessage(R.string.message_permission_denied)
                                .setPermissions(Manifest.permission.CALL_PHONE)
                                .check();
                    if (which == 1)
                        eventBus.post(new ServiceCallRequestEvent());
                })
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ACTIVITY_COUPON){
            if(resultCode == RESULT_OK){
                Coupon coupon = (Coupon) data.getSerializableExtra("coupon");
                String message = "";
                if(coupon.getFlatDiscount() == 0 && coupon.getDiscountPercent() != 0)
                    message = "Coupon with discount of " + coupon.getDiscountPercent() + "% has been applied.";
                if(coupon.getFlatDiscount() != 0 && coupon.getDiscountPercent() == 0)
                    message = "Coupon with discount of " + getString(R.string.unit_money,coupon.getFlatDiscount()) + " has been applied.";
                if(coupon.getFlatDiscount() != 0 && coupon.getDiscountPercent() != 0)
                    message = "Coupon with discount of " + getString(R.string.unit_money,coupon.getFlatDiscount()) + " and " + coupon.getDiscountPercent() + "% has been applied.";
                if(message.equals(""))
                    return;
                AlerterHelper.showInfo(TravelActivity.this,message);
                travelTabsViewPagerAdapter.statisticsFragment.onUpdatePrice(data.getDoubleExtra("costAfterCoupon",travel.getCostBest()));

            }
        }
    }

    @Override
    public void onReceived(LatLng driverLocation, float cost) {
        this.driverLocation = driverLocation;
        updateMarkers();
    }
}
