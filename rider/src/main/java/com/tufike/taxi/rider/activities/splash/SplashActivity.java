package com.tufike.taxi.rider.activities.splash;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.crashlytics.android.Crashlytics;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.tufike.taxi.common.activities.login.LoginActivity;
import com.tufike.taxi.common.components.BaseActivity;
import com.tufike.taxi.common.events.BackgroundServiceStartedEvent;
import com.tufike.taxi.common.events.ConnectEvent;
import com.tufike.taxi.common.events.ConnectResultEvent;
import com.tufike.taxi.common.events.LoginEvent;
import com.tufike.taxi.common.models.Rider;
import com.tufike.taxi.common.utils.AlertDialogBuilder;
import com.tufike.taxi.common.utils.AlerterHelper;
import com.tufike.taxi.common.utils.CommonUtils;
import com.tufike.taxi.common.utils.LocationHelper;
import com.tufike.taxi.common.utils.MyPreferenceManager;
import com.tufike.taxi.rider.BuildConfig;
import com.tufike.taxi.rider.R;
import com.tufike.taxi.rider.RiderEventBusIndex;
import com.tufike.taxi.rider.activities.main.MainActivity;
import com.tufike.taxi.rider.databinding.ActivitySplashBinding;
import com.tufike.taxi.rider.events.LoginResultEvent;
import com.tufike.taxi.rider.services.RiderService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Collections;
import java.util.List;

import io.fabric.sdk.android.Fabric;

public class SplashActivity extends BaseActivity implements LocationListener {
    MyPreferenceManager SP;
    ActivitySplashBinding binding;
    int RC_SIGN_IN = 123;
    Handler locationTimeoutHandler;
    LocationManager locationManager;
    LatLng currentLocation;
    boolean isErrored = false;

    private PermissionListener permissionlistener = new PermissionListener() {
        @SuppressLint("MissingPermission")
        @Override
        public void onPermissionGranted() {
            FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(SplashActivity.this);
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(SplashActivity.this, location -> {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            currentLocation = new LatLng(location.getLatitude(),location.getLongitude());
                        }
                    });
        boolean isServiceRunning = isMyServiceRunning(RiderService.class);
            if (!isServiceRunning)
                startService(new Intent(SplashActivity.this, RiderService.class));
        }

        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {
            boolean isServiceRunning = isMyServiceRunning(RiderService.class);
            if (!isServiceRunning)
                startService(new Intent(SplashActivity.this, RiderService.class));
        }
    };
    private View.OnClickListener onLoginButtonClicked = v -> {
        String resourceName = "testMode";
        int testExists = SplashActivity.this.getResources().getIdentifier(resourceName, "string", SplashActivity.this.getPackageName());
        if (testExists > 0) {
            tryLogin(getString(testExists));
            return;
        }
        if (getResources().getBoolean(R.bool.use_custom_login)) {
            startActivityForResult(new Intent(SplashActivity.this, LoginActivity.class), RC_SIGN_IN);
        } else
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(
                                    Collections.singletonList(new AuthUI.IdpConfig.PhoneBuilder().build()))
                            .setTheme(getCurrentTheme())
                            .build(),
                    RC_SIGN_IN);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setImmersive(true);
        showConnectionDialog = false;
        try {
            EventBus.builder().addIndex(new RiderEventBusIndex()).installDefaultEventBus();
        } catch (Exception ignored) {
        }
        super.onCreate(savedInstanceState);
        // Initialize Places.
        Places.initialize(getApplicationContext(), getString(R.string.google_maps_key));

// Create a new Places client instance.
        PlacesClient placesClient = Places.createClient(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (!getString(R.string.fabric_key).equals("")) {
            Fabric.with(this, new Crashlytics());
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(SplashActivity.this, R.layout.activity_splash);
        binding.loginButton.setOnClickListener(onLoginButtonClicked);
        SP = MyPreferenceManager.getInstance(getApplicationContext());
        checkPermissions();

    }

    private void checkPermissions() {
        if (CommonUtils.isInternetDisabled(this)) {
            AlertDialogBuilder.show(this, getString(R.string.message_enable_wifi), AlertDialogBuilder.DialogButton.CANCEL_RETRY, result -> {
                if (result == AlertDialogBuilder.DialogResult.RETRY) {
                    checkPermissions();
                } else {
                    finishAffinity();
                }
            });
            return;
        }
        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage(getString(R.string.message_permission_denied))
                .setPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                .check();
    }

    @SuppressLint("MissingPermission")
    private void searchCurrentLocation() {
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        assert locationManager != null;
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 1, this);
    }

    private void startMainActivity(LatLng latLng) {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        double[] array = LocationHelper.LatLngToDoubleArray(latLng);
        intent.putExtra("currentLocation", array);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginResultEvent(LoginResultEvent event) {
        if (event.hasError()) {
            event.showError(SplashActivity.this, result -> {
                if (result == AlertDialogBuilder.DialogResult.RETRY)
                    binding.loginButton.callOnClick();
                else
                    finish();
            });
            return;
        }
        CommonUtils.rider = event.rider;
        SP.putString("rider_user", event.riderJson);
        SP.putString("rider_token", event.jwtToken);
        tryConnect();
    }

    public void tryConnect() {
        String token = SP.getString("rider_token", null);
        if (token != null && !token.isEmpty()) {
            eventBus.post(new ConnectEvent(token));
            goToLoadingMode();
        } else {
            goToLoginMode();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onConnectedResult(ConnectResultEvent event) {

        if (event.hasError()) {
            isErrored = true;
            binding.progressBar.setVisibility(View.GONE);
            event.showError(SplashActivity.this, result -> {
                if (result == AlertDialogBuilder.DialogResult.RETRY) {
                    tryConnect();
                } else {
                    goToLoginMode();
                }
            });
            return;
        }
        locationTimeoutHandler = new Handler();
        locationTimeoutHandler.postDelayed(() -> {
            locationManager.removeUpdates(SplashActivity.this);
            if (currentLocation == null) {
                String[] location = getString(R.string.defaultLocation).split(",");
                double lat = Double.parseDouble(location[0]);
                double lng = Double.parseDouble(location[1]);
                currentLocation = new LatLng(lat, lng);
            }
            if(isErrored)
                return;
            startMainActivity(currentLocation);

        }, 5000);
        searchCurrentLocation();
        CommonUtils.rider = Rider.fromJson(SP.getString("rider_user", "{}"));
    }

    @Subscribe
    public void onServiceStarted(BackgroundServiceStartedEvent event) {
        tryConnect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tryConnect();
    }

    private void tryLogin(String phone) {
        isErrored = false;
        goToLoadingMode();
        if (phone.substring(0, 1).equals("+"))
            phone = phone.substring(1);
        eventBus.post(new LoginEvent(Long.valueOf(phone), BuildConfig.VERSION_CODE));
    }

    private void goToLoadingMode(){
        binding.loginButton.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void goToLoginMode(){
        binding.loginButton.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                if (getResources().getBoolean(R.bool.use_custom_login)) {
                    tryLogin(data.getStringExtra("mobile"));
                } else {
                    IdpResponse idpResponse = IdpResponse.fromResultIntent(data);
                    String phone;
                    if (idpResponse != null) {
                        phone = idpResponse.getPhoneNumber();
                        tryLogin(phone);
                        return;
                    }
                }
                return;
            }
        }
        AlerterHelper.showError(SplashActivity.this, getString(R.string.login_failed));
        goToLoginMode();
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

}
