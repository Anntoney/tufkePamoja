package com.tufike.taxi.rider.activities.addresses;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.model.LatLng;
import com.tufike.taxi.common.components.BaseActivity;
import com.tufike.taxi.common.models.Address;
import com.tufike.taxi.common.models.CRUD;
import com.tufike.taxi.common.utils.AlertDialogBuilder;
import com.tufike.taxi.common.utils.LocationHelper;
import com.tufike.taxi.rider.R;
import com.tufike.taxi.rider.activities.addresses.adapters.AddressesRecyclerViewAdapter;
import com.tufike.taxi.rider.activities.addresses.fragments.EditAddressDialog;
import com.tufike.taxi.rider.databinding.ActivityAddressesBinding;
import com.tufike.taxi.rider.events.CRUDAddressRequestEvent;
import com.tufike.taxi.rider.events.CRUDAddressResultEvent;
import com.tylersuehr.esr.ContentItemLoadingStateFactory;
import com.tylersuehr.esr.EmptyStateRecyclerView;
import com.tylersuehr.esr.ImageTextStateDisplay;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class AddressesActivity extends BaseActivity implements EditAddressDialog.onEditAddressInteractionListener {
    ActivityAddressesBinding binding;
    LatLng currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(AddressesActivity.this, R.layout.activity_addresses);
        currentLocation = LocationHelper.DoubleArrayToLatLng(getIntent().getDoubleArrayExtra("currentLocation"));
        initializeToolbar(getString(R.string.activity_address_title));
        binding.recyclerView.setStateDisplay(EmptyStateRecyclerView.STATE_LOADING, ContentItemLoadingStateFactory.newListLoadingState(this));
        binding.recyclerView.setStateDisplay(EmptyStateRecyclerView.STATE_EMPTY, new ImageTextStateDisplay(this, com.tufike.taxi.common.R.drawable.empty_state, "Oops!", "Nothing to show here :("));
        binding.recyclerView.setStateDisplay(EmptyStateRecyclerView.STATE_ERROR, new ImageTextStateDisplay(this, com.tufike.taxi.common.R.drawable.empty_state, "SORRY...!", "Something went wrong :("));
        binding.recyclerView.invokeState(EmptyStateRecyclerView.STATE_LOADING);
        eventBus.post(new CRUDAddressRequestEvent(CRUD.READ));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Address address = new Address();
        address.setLocation(currentLocation);
        showEditAddressDialog(address);
        return super.onOptionsItemSelected(item);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCRUDResultReceived(CRUDAddressResultEvent event) {
        if(event.hasError()){
            binding.recyclerView.invokeState(EmptyStateRecyclerView.STATE_ERROR);
            return;
        }
        if (event.addresses != null) {
            if(event.addresses.size() == 0){
                binding.recyclerView.invokeState(EmptyStateRecyclerView.STATE_EMPTY);
                return;
            }
            binding.recyclerView.invokeState(EmptyStateRecyclerView.STATE_OK);
            AddressesRecyclerViewAdapter addressesRecyclerViewAdapter = new AddressesRecyclerViewAdapter(event.addresses, new AddressesRecyclerViewAdapter.OnAddressItemInteractionListener() {
                @Override
                public void onEdit(Address address) {
                    showEditAddressDialog(address);
                }

                @Override
                public void onDelete(Address address) {
                    AlertDialogBuilder.show(AddressesActivity.this, getString(R.string.question_delete_address), AlertDialogBuilder.DialogButton.OK_CANCEL, result -> {
                        if(result == AlertDialogBuilder.DialogResult.OK)
                            eventBus.post(new CRUDAddressRequestEvent(CRUD.DELETE, address));
                    });
                }
            });
            LinearLayoutManager llm = new LinearLayoutManager(AddressesActivity.this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            llm.setAutoMeasureEnabled(false);
            binding.recyclerView.setHasFixedSize(true);
            binding.recyclerView.setLayoutManager(llm);
            binding.recyclerView.setAdapter(addressesRecyclerViewAdapter);
        }
        if(event.crud == CRUD.DELETE || event.crud == CRUD.UPDATE || event.crud == CRUD.CREATE) {
            eventBus.post(new CRUDAddressRequestEvent(CRUD.READ));
        }
    }

    public void showEditAddressDialog(Address address) {
        FragmentManager fm = getSupportFragmentManager();
        EditAddressDialog editNameDialogFragment = EditAddressDialog.newInstance(address);
        editNameDialogFragment.show(fm, "fragment_edit_name");

    }

    @Override
    public void onSaveButtonClicked(Address address) {
        if(address.getId() != 0)
            eventBus.post(new CRUDAddressRequestEvent(CRUD.UPDATE, address));
        else
            eventBus.post(new CRUDAddressRequestEvent(CRUD.CREATE, address));
    }

    private interface EditAddressResult {
        void onAddressEdited(Address address);
    }

}
