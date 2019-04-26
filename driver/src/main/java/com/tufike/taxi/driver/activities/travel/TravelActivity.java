package com.tufike.taxi.driver.activities.travel;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import androidx.transition.TransitionManager;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.PolyUtil;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.tufike.taxi.common.components.LoadingDialog;
import com.tufike.taxi.common.events.ServiceCallRequestEvent;
import com.tufike.taxi.common.events.ServiceCallRequestResultEvent;
import com.tufike.taxi.common.events.ServiceCancelEvent;
import com.tufike.taxi.common.events.ServiceCancelResultEvent;
import com.tufike.taxi.common.location.MapHelper;
import com.tufike.taxi.common.models.Travel;
import com.tufike.taxi.common.utils.AlertDialogBuilder;
import com.tufike.taxi.common.utils.DirectionsJSONParser;
import com.tufike.taxi.common.utils.LocationHelper;
import com.tufike.taxi.driver.R;
import com.tufike.taxi.driver.databinding.ActivityTravelBinding;
import com.tufike.taxi.driver.events.LocationChangedEvent;
import com.tufike.taxi.driver.events.SendTravelInfoEvent;
import com.tufike.taxi.driver.events.ServiceFinishEvent;
import com.tufike.taxi.driver.events.ServiceFinishResultEvent;
import com.tufike.taxi.driver.events.ServiceInLocationEvent;
import com.tufike.taxi.driver.events.ServiceStartEvent;
import com.tufike.taxi.driver.ui.DriverBaseActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TravelActivity extends DriverBaseActivity implements OnMapReadyCallback {
    Travel travel;
    GoogleMap gMap;
    boolean endTravel = false;
    LatLng currentLocation;
    ActivityTravelBinding binding;
    Marker currentMarker;
    Marker destinationMarker;
    DirectionsJSONParser directionToPassengerRouter;
    List<LatLng> geoLog = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_travel);
        travel = Travel.fromJson(getIntent().getStringExtra("travel"));
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        LocationHelper locationHelper = new LocationHelper(this);
        locationHelper.loadGps(mLocationListener);
        binding.slideStart.setOnSlideCompleteListener(slideView -> startTravel(true));
        binding.slideFinish.setOnSlideCompleteListener(slideView -> finishTravel());
        binding.slideCancel.setOnSlideCompleteListener(slideView -> eventBus.post(new ServiceCancelEvent()));
        binding.costText.setText(getString(R.string.unit_money, travel.getCostBest()));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        gMap.setTrafficEnabled(true);
        LatLng dLocation = new LatLng(getIntent().getDoubleExtra("driverLat", -1), getIntent().getDoubleExtra("driverLng", -1));
        currentLocation = dLocation;
        currentMarker = googleMap.addMarker(new MarkerOptions()
                .position(dLocation)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_taxi)));
        destinationMarker = googleMap.addMarker(new MarkerOptions()
                .position(travel.getPickupPoint())
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_destination)));
        if(travel.getStartTimestamp()!=null)
            startTravel(false);
        googleMap.setOnMapLoadedCallback(() -> {
            List<LatLng> locations = new ArrayList<>();
            locations.add(currentMarker.getPosition());
            locations.add(destinationMarker.getPosition());
            MapHelper.centerLatLngsInMap(gMap, locations, true);
            directionToPassengerRouter = new DirectionsJSONParser(gMap, currentMarker.getPosition(), destinationMarker.getPosition());
            directionToPassengerRouter.run();
        });
    }

    private String computeTime(int time) {
        if (time == 0)
            return "00:00";
        int sec = time % 60;
        int min = time / 60;
        return String.format(Locale.getDefault(), "%02d:%02d", min, sec);
    }


    private void Timer() {
        Thread th = new Thread(() -> {
            while (!endTravel) {
                runOnUiThread(() -> {
                    travel.setDurationReal(travel.getDurationReal() + 1);
                    if(binding.getRoot().getResources().getBoolean(R.bool.use_miles))
                        binding.distanceText.setText(binding.getRoot().getContext().getString(R.string.unit_distance_miles,travel.getDistanceReal() / 1609.344f));
                    else
                        binding.distanceText.setText(getString(R.string.unit_distance, (travel.getDistanceReal() / 1000f)));
                    binding.timeText.setText(computeTime(travel.getDurationReal()));
                    binding.costText.setText(getString(R.string.unit_money, travel.getCostBest()));
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        th.start();
    }


    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            travel.setDistanceReal(travel.getDurationReal() + LocationHelper.distFrom(currentLocation.latitude, currentLocation.longitude, location.getLatitude(), location.getLongitude()));
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            eventBus.post(new LocationChangedEvent(latLng));
            eventBus.post(new SendTravelInfoEvent(travel));
            geoLog.add(new LatLng(location.getLatitude(), location.getLongitude()));
            currentLocation = new LatLng(location.getLatitude(),location.getLongitude());
            currentMarker.setPosition(currentLocation);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(currentMarker.getPosition(), gMap.getCameraPosition().zoom);
            gMap.animateCamera(cameraUpdate);
        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
    };

    void startTravel(boolean fromScratch) {
        if(fromScratch)
            eventBus.post(new ServiceStartEvent());
        TransitionManager.beginDelayedTransition((ViewGroup) (binding.getRoot()));
        binding.slideFinish.setVisibility(View.VISIBLE);
        binding.slideCancel.setVisibility(View.GONE);
        binding.slideStart.setVisibility(View.GONE);
        TransitionManager.beginDelayedTransition(binding.layoutActions);
        binding.inLocationButton.setVisibility(View.GONE);
        binding.callButton.setVisibility(View.GONE);
        destinationMarker.setPosition(travel.getDestinationPoint());
        if(fromScratch) {
            List<LatLng> locations = new ArrayList<>();
            locations.add(currentMarker.getPosition());
            locations.add(destinationMarker.getPosition());
            MapHelper.centerLatLngsInMap(gMap, locations, true);
            if (directionToPassengerRouter != null)
                directionToPassengerRouter.removeLine();
            DirectionsJSONParser directionsJSONParser = new DirectionsJSONParser(gMap, currentMarker.getPosition(), destinationMarker.getPosition());
            directionsJSONParser.run();
        }
        Timer();

    }

    void finishTravel(){
        if(getResources().getBoolean(R.bool.use_custom_fee)){
            new MaterialDialog.Builder(this)
                    .title(R.string.travel_fee_dialog_title)
                    .content(R.string.travel_fee_dialog_content)
                    .inputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER)
                    .input(getString(R.string.travel_fee_dialog_hint), "", (dialog, input) -> finishTravel(Double.valueOf(input.toString()))).show();
        } else {
            finishTravel(travel.getCostBest());
        }
    }

    void finishTravel(Double cost) {
        endTravel = true;
        String encodedPoly = "";
        if (geoLog.size() > 0)
            encodedPoly = PolyUtil.encode(PolyUtil.simplify(geoLog, 10));
        eventBus.post(new ServiceFinishEvent(cost, travel.getDurationReal(), travel.getDistanceReal(), encodedPoly));
    }

    public void onInLocationButtonClicked(View v) {
        eventBus.post(new ServiceInLocationEvent());
        TransitionManager.beginDelayedTransition(binding.layoutActions);
        binding.inLocationButton.setVisibility(View.GONE);

    }

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

    PermissionListener callPermissionListener = new PermissionListener() {
        @SuppressLint("MissingPermission")
        @Override
        public void onPermissionGranted() {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:+" + travel.getRider().getMobileNumber()));
            startActivity(intent);
        }

        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {

        }

    };

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
    public void onServicedFinished(ServiceFinishResultEvent event) {
        //LoadingDialog.dismiss();
        if (event.hasError()) {
            event.showError(TravelActivity.this, result -> {
                if (result == AlertDialogBuilder.DialogResult.RETRY)
                    finishTravel();
            });
            return;
        }
        if (event.isCreditUsed) {
            new MaterialDialog.Builder(this)
                    .title(R.string.message_default_title)
                    .content(R.string.service_finished_credit)
                    .positiveText(R.string.alert_ok)
                    .onPositive((dialog, which) -> finish())
                    .show();
        } else {
            new MaterialDialog.Builder(this)
                    .title(R.string.message_default_title)
                    .content(R.string.service_finished_cash)
                    .positiveText(R.string.alert_ok)
                    .onPositive((dialog, which) -> finish())
                    .show();
        }
    }

    public void onCallDriverClicked(View view) {
        boolean isCallRequestEnabled = getResources().getBoolean(R.bool.is_call_request_enabled_driver);
        boolean isDirectCallEnabled = getResources().getBoolean(R.bool.is_direct_call_enabled_driver);
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
}
