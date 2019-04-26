package com.tufike.taxi.driver.activities.profile;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.ReturnMode;
import com.esafirm.imagepicker.model.Image;
import com.google.gson.Gson;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.tufike.taxi.common.components.BaseActivity;
import com.tufike.taxi.common.components.LoadingDialog;
import com.tufike.taxi.common.events.ChangeProfileImageEvent;
import com.tufike.taxi.common.events.ChangeProfileImageResultEvent;
import com.tufike.taxi.common.events.EditProfileInfoEvent;
import com.tufike.taxi.common.events.EditProfileInfoResultEvent;
import com.tufike.taxi.common.models.Driver;
import com.tufike.taxi.common.utils.AlertDialogBuilder;
import com.tufike.taxi.common.utils.AlerterHelper;
import com.tufike.taxi.common.utils.CommonUtils;
import com.tufike.taxi.common.utils.MyPreferenceManager;
import com.tufike.taxi.common.utils.Validators;
import com.tufike.taxi.driver.R;
import com.tufike.taxi.driver.databinding.ActivityEditProfileBinding;
import com.tufike.taxi.driver.events.ChangeHeaderImageEvent;
import com.tufike.taxi.driver.events.ChangeHeaderImageResultEvent;
import com.yalantis.ucrop.UCrop;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.List;

public class ProfileActivity extends BaseActivity {
    ActivityEditProfileBinding binding;
    Driver driver = new Driver();
    boolean isProfileRequested = true;
    MyPreferenceManager SP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SP = MyPreferenceManager.getInstance(getApplicationContext());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.genders));
        binding.spinnerGender.setAdapter(adapter);
        driver = new Gson().fromJson(new Gson().toJson(CommonUtils.driver), Driver.class);
        binding.setUser(driver);
        binding.profileImage.setOnClickListener(onProfileImageClicked);
        initializeToolbar("");
        if (getResources().getBoolean(R.bool.read_only_driver_profile))
            for (int i = 0; i < binding.fieldsLayout.getChildCount(); i++)
                binding.fieldsLayout.getChildAt(i).setEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (driver.getEmail() != null && !driver.getEmail().equals("") && !Validators.validateEmailAddress(driver.getEmail())) {
            AlerterHelper.showError(ProfileActivity.this, getString(R.string.error_invalid_email));
            return false;
        }
        eventBus.post(new EditProfileInfoEvent(driver.toJson()));
        LoadingDialog.show(this, getString(R.string.saving_profile));
        return super.onOptionsItemSelected(item);
    }

    View.OnClickListener onProfileImageClicked = new View.OnClickListener() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                ImagePicker.create(ProfileActivity.this)
                        .returnMode(ReturnMode.ALL) // set whether pick and / or camera action should return immediate result or not.
                        .folderMode(true) // folder mode (false by default)
                        .toolbarFolderTitle(getString(R.string.picker_folder)) // folder selection title
                        .toolbarImageTitle(getString(R.string.picker_tap_select)) // image selection title
                        .toolbarArrowColor(Color.WHITE) // Toolbar 'up' arrow color
                        .single() // single mode
                        .limit(10) // max images can be selected (99 by default)
                        .showCamera(true) // show camera or not (true by default)
                        .imageDirectory("Camera") // directory name for captured image  ("Camera" folder by default)
                        .theme(R.style.ImagePickerTheme) // must inherit ef_BaseTheme. please refer to sample
                        .start();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {

            }
        };

        @Override
        public void onClick(View v) {
            isProfileRequested = true;
            TedPermission.with(ProfileActivity.this)
                    .setPermissionListener(permissionlistener)
                    .setDeniedMessage(getString(R.string.message_permission_denied))
                    .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .check();

        }
    };

    void saveDriverInfo() {
        SP.putString("driver_user", new Gson().toJson(CommonUtils.driver));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onProfileInfoChanged(EditProfileInfoResultEvent event) {
        LoadingDialog.dismiss();
        if (event.hasError()) {
            event.showAlert(ProfileActivity.this);
            return;
        }
        setResult(RESULT_OK);
        finish();
        CommonUtils.driver = driver;
        saveDriverInfo();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onProfileImageChanged(ChangeProfileImageResultEvent event) {
        binding.getUser().setMedia(event.media);
        saveDriverInfo();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHeaderImageChanged(ChangeHeaderImageResultEvent event) {
        binding.getUser().setCarMedia(event.media);
        saveDriverInfo();
    }

    public void onProfileImageClicked(View view) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CommonUtils.MY_PERMISSIONS_REQUEST_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 1
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    if (isProfileRequested)
                        onProfileImageClicked(binding.profileImage);
                    else
                        onHeaderImageClicked(binding.cameraHeaderImage);
                } else {
                    AlertDialogBuilder.show(ProfileActivity.this, getString(R.string.prompt_storage_rw));
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            // or get a single image only
            Image image = ImagePicker.getFirstImageOrNull(data);
            Uri destinationUri = Uri.fromFile(new File(getCacheDir(), "p.jpg"));
            UCrop.of(Uri.fromFile(new File(image.getPath())), destinationUri)
                    .withAspectRatio(1, 1)
                    .withMaxResultSize(200, 200)
                    .start(ProfileActivity.this);
        }
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            if (resultUri == null)
                return;
            if (isProfileRequested)
                EventBus.getDefault().post(new ChangeProfileImageEvent(resultUri.getPath()));
            else
                EventBus.getDefault().post(new ChangeHeaderImageEvent(resultUri.getPath()));
        } else if (resultCode == UCrop.RESULT_ERROR)
            try {
                throw UCrop.getError(data);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
    }

    public void onHeaderImageClicked(View view) {
        isProfileRequested = false;
        TedPermission.with(ProfileActivity.this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage(getString(R.string.message_permission_denied))
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    private PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            ImagePicker.create(ProfileActivity.this)
                    .returnMode(ReturnMode.ALL) // set whether pick and / or camera action should return immediate result or not.
                    .folderMode(true) // folder mode (false by default)
                    .toolbarFolderTitle(getString(R.string.picker_folder)) // folder selection title
                    .toolbarImageTitle(getString(R.string.picker_tap_select)) // image selection title
                    .toolbarArrowColor(Color.WHITE) // Toolbar 'up' arrow color
                    .single() // single mode
                    .limit(10) // max images can be selected (99 by default)
                    .showCamera(true) // show camera or not (true by default)
                    .imageDirectory("Camera") // directory name for captured image  ("Camera" folder by default)
                    .theme(R.style.AppTheme) // must inherit ef_BaseTheme. please refer to sample
                    .start();
        }

        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {

        }
    };
}
