package com.tufike.taxi.driver.activities.splash;

import android.Manifest;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.crashlytics.android.Crashlytics;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.gson.Gson;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.tufike.taxi.common.activities.login.LoginActivity;
import com.tufike.taxi.common.components.BaseActivity;
import com.tufike.taxi.common.events.BackgroundServiceStartedEvent;
import com.tufike.taxi.common.events.ConnectEvent;
import com.tufike.taxi.common.events.ConnectResultEvent;
import com.tufike.taxi.common.events.LoginEvent;
import com.tufike.taxi.common.models.Driver;
import com.tufike.taxi.common.utils.AlertDialogBuilder;
import com.tufike.taxi.common.utils.AlerterHelper;
import com.tufike.taxi.common.utils.CommonUtils;
import com.tufike.taxi.common.utils.MyPreferenceManager;
import com.tufike.taxi.driver.BuildConfig;
import com.tufike.taxi.driver.DriverEventBusIndex;
import com.tufike.taxi.driver.R;
import com.tufike.taxi.driver.activities.main.MainActivity;
import com.tufike.taxi.driver.databinding.ActivitySplashBinding;
import com.tufike.taxi.driver.events.LoginResultEvent;
import com.tufike.taxi.driver.services.DriverService;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Collections;
import java.util.List;

import io.fabric.sdk.android.Fabric;

public class SplashActivity extends BaseActivity {
    MyPreferenceManager SP;
    ActivitySplashBinding binding;
    int RC_SIGN_IN = 123;
    String lastPhoneNumber = "";

    private PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {

        }

        @Override
        public void onPermissionGranted() {
            try {
                if (!isMyServiceRunning(DriverService.class))
                    startService(new Intent(SplashActivity.this, DriverService.class));
            } catch (Exception c) {
                c.printStackTrace();
            }
        }
    };
    private View.OnClickListener onLoginClicked = v -> {
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
            EventBus.builder().addIndex(new DriverEventBusIndex()).installDefaultEventBus();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        if (!getString(R.string.fabric_key).equals("")) {
            Fabric.with(this, new Crashlytics());
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        binding.loginButton.setOnClickListener(onLoginClicked);
        SP = MyPreferenceManager.getInstance(getApplicationContext());
        checkPermissions();
    }

    private void checkPermissions() {
        if (!CommonUtils.isGPSEnabled(this)) {
            AlertDialogBuilder.show(this, getString(R.string.message_enable_gps), AlertDialogBuilder.DialogButton.CANCEL_RETRY, result -> {
                if (result == AlertDialogBuilder.DialogResult.RETRY) {
                    checkPermissions();
                } else {
                    finishAffinity();
                }
            });
            return;
        }
        if (CommonUtils.isInternetDisabled(this)) {
            AlertDialogBuilder.show(this, getString(R.string.message_internet_connection), AlertDialogBuilder.DialogButton.CANCEL_RETRY, result -> {
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

    public void tryConnect() {
        try {
            String token = SP.getString("driver_token", null);
            if (token != null && !token.isEmpty()) {
                eventBus.post(new ConnectEvent(token));
                goToLoadingMode();
            } else {
                goToLoginMode();
            }
        } catch (Exception c) {
            c.printStackTrace();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onConnectedResult(ConnectResultEvent event) {
        if (event.hasError()) {
            goToLoginMode();
            event.showError(SplashActivity.this, result -> {
                if (result == AlertDialogBuilder.DialogResult.RETRY)
                    tryConnect();
            });
            return;
        }

        CommonUtils.driver = new Gson().fromJson(SP.getString("driver_user", "{}"), Driver.class);
        startMainActivity();
    }

    @Subscribe
    public void onServiceStarted(BackgroundServiceStartedEvent event) {
        tryConnect();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginResultEvent(LoginResultEvent event) {
        if (event.hasError()) {
            event.showError(SplashActivity.this, result -> {
                if (result == AlertDialogBuilder.DialogResult.RETRY)
                    tryLogin(lastPhoneNumber);
                else
                    finish();
            });
            return;
        }
        CommonUtils.driver = event.driver;
        SP.putString("driver_user", event.driverJson);
        SP.putString("driver_token", event.jwtToken);
        tryConnect();

    }

    @Override
    protected void onResume() {
        super.onResume();
        tryConnect();
    }

    private void startMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void tryLogin(String phone) {
        lastPhoneNumber = phone;
        goToLoadingMode();
        if (phone.substring(0, 1).equals("+"))
            phone = phone.substring(1);
        eventBus.post(new LoginEvent(Long.valueOf(phone), BuildConfig.VERSION_CODE));
    }

    private void goToLoadingMode() {
        binding.loginButton.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void goToLoginMode() {
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
                    }
                }
                return;
            }
            AlerterHelper.showError(SplashActivity.this, getString(R.string.login_failed));
            goToLoginMode();
        }
    }
}
