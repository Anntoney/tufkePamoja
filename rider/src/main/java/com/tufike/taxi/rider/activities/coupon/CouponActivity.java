package com.tufike.taxi.rider.activities.coupon;

import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialdialogs.MaterialDialog;
import com.tufike.taxi.common.components.BaseActivity;
import com.tufike.taxi.common.models.Coupon;
import com.tufike.taxi.common.utils.ServerResponse;
import com.tufike.taxi.rider.R;
import com.tufike.taxi.rider.activities.coupon.adapters.CouponsRecyclerViewAdapter;
import com.tufike.taxi.rider.databinding.ActivityCouponBinding;
import com.tufike.taxi.rider.events.AddCouponRequestEvent;
import com.tufike.taxi.rider.events.AddCouponResultEvent;
import com.tufike.taxi.rider.events.ApplyCouponRequestEvent;
import com.tufike.taxi.rider.events.ApplyCouponResultEvent;
import com.tufike.taxi.rider.events.GetCouponsRequestEvent;
import com.tufike.taxi.rider.events.GetCouponsResultEvent;
import com.tylersuehr.esr.ContentItemLoadingStateFactory;
import com.tylersuehr.esr.EmptyStateRecyclerView;
import com.tylersuehr.esr.ImageTextStateDisplay;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class CouponActivity extends BaseActivity {
    ActivityCouponBinding binding;
    Coupon coupon;
    MaterialDialog loadingDialog;
    boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isEditMode =  getIntent().getBooleanExtra("select_mode",false);
        binding = DataBindingUtil.setContentView(CouponActivity.this,R.layout.activity_coupon);
        initializeToolbar("Coupons");
        binding.recyclerView.setStateDisplay(EmptyStateRecyclerView.STATE_LOADING, ContentItemLoadingStateFactory.newListLoadingState(this));
        binding.recyclerView.setStateDisplay(EmptyStateRecyclerView.STATE_EMPTY, new ImageTextStateDisplay(this, com.tufike.taxi.common.R.drawable.empty_state, "Oops!", "Nothing to show here :("));
        binding.recyclerView.setStateDisplay(EmptyStateRecyclerView.STATE_ERROR, new ImageTextStateDisplay(this, com.tufike.taxi.common.R.drawable.empty_state, "SORRY...!", "Something went wrong :("));
        binding.recyclerView.invokeState(EmptyStateRecyclerView.STATE_LOADING);
        eventBus.post(new GetCouponsRequestEvent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        new MaterialDialog.Builder(this).input("Enter your code here", null, false, (dialog, input) -> {
            eventBus.post(new AddCouponRequestEvent(input.toString()));
            loadingDialog = new MaterialDialog.Builder(this)
                    .title("Adding Coupon")
                    .content("Please wait...")
                    .progress(true, 0)
                    .cancelable(false)
                    .show();
        }).title("Coupon Code").positiveText("OK").show();
        return super.onOptionsItemSelected(item);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCouponsResultEvent(GetCouponsResultEvent event) {
        if(event.hasError()){
            binding.recyclerView.invokeState(EmptyStateRecyclerView.STATE_ERROR);
            return;
        }
        if(event.coupons.size() == 0) {
            binding.recyclerView.invokeState(EmptyStateRecyclerView.STATE_EMPTY);
            return;
        }
        binding.recyclerView.invokeState(EmptyStateRecyclerView.STATE_OK);
        CouponsRecyclerViewAdapter couponsRecyclerViewAdapter = new CouponsRecyclerViewAdapter(event.coupons, coupon -> {
            this.coupon = coupon;
            loadingDialog = new MaterialDialog.Builder(this)
                    .title("Applying Coupon")
                    .content("Please wait...")
                    .progress(true, 0)
                    .cancelable(false)
                    .show();
            eventBus.post(new ApplyCouponRequestEvent(coupon.getCode()));
        },isEditMode);
        LinearLayoutManager llm = new LinearLayoutManager(CouponActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(llm);
        binding.recyclerView.setAdapter(couponsRecyclerViewAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onApplyCouponResultEvent(ApplyCouponResultEvent event) {
        loadingDialog.dismiss();
        Intent intent = new Intent();
        intent.putExtra("coupon",coupon);
        intent.putExtra("costAfterCoupon",event.finalPrice);
        setResult(RESULT_OK,intent);
        this.finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAddCouponResultEvent(AddCouponResultEvent event) {
        loadingDialog.dismiss();
        if(event.response != ServerResponse.OK){
            event.showAlert(CouponActivity.this);
            return;
        }
        eventBus.post(new GetCouponsRequestEvent());
    }
}
