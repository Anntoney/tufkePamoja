package com.tufike.taxi.driver.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.afollestad.materialdialogs.MaterialDialog;
import com.tufike.taxi.common.components.BaseActivity;
import com.tufike.taxi.common.events.BackgroundServiceStartedEvent;
import com.tufike.taxi.common.events.ConnectEvent;
import com.tufike.taxi.common.events.ConnectResultEvent;
import com.tufike.taxi.common.utils.MyPreferenceManager;
import com.tufike.taxi.driver.R;
import com.tufike.taxi.driver.services.DriverService;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DriverBaseActivity extends BaseActivity {
    MyPreferenceManager SP;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SP = MyPreferenceManager.getInstance(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean isServiceRunning = isMyServiceRunning(DriverService.class);
        if (!isServiceRunning)
            startService(new Intent(this, DriverService.class));
    }

    @Subscribe
    public void onServiceStarted(BackgroundServiceStartedEvent event) {
        tryConnect();
    }

    public void tryConnect() {
        String token = SP.getString("driver_token", null);
        if (token != null && !token.isEmpty()) {
            eventBus.post(new ConnectEvent(token));
            if (connectionProgressDialog == null) {
                connectionProgressDialog = new MaterialDialog.Builder(this)
                        .title(getString(com.tufike.taxi.common.R.string.connection_dialog_title))
                        .content(R.string.event_reconnecting)
                        .progress(true, 0)
                        .cancelable(false)
                        .show();
            } else {
                connectionProgressDialog.setContent(R.string.event_reconnecting);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onConnectedResult(ConnectResultEvent event) {
        if (connectionProgressDialog != null)
            connectionProgressDialog.dismiss();
        onReconnected();
    }

    public void onReconnected(){

    }
}