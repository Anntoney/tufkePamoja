package com.tufike.taxi.rider.activities.addresses.fragments;

import android.app.Dialog;
import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.tufike.taxi.common.models.Address;
import com.tufike.taxi.rider.R;
import com.tufike.taxi.rider.databinding.FragmentEditAddressBinding;


public class EditAddressDialog extends DialogFragment implements OnMapReadyCallback {
    FragmentEditAddressBinding binding;
    private static final String ARG_ADDRESS = "address";
    private Address address;
    GoogleMap googleMap;
    SupportMapFragment mapFragment;
    private onEditAddressInteractionListener mListener;

    public EditAddressDialog() {
        // Required empty public constructor
    }

    public static EditAddressDialog newInstance(Address param1) {
        EditAddressDialog fragment = new EditAddressDialog();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ADDRESS, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            address = (Address) getArguments().getSerializable(ARG_ADDRESS);
        }
    }

    public View onCreateDialogView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_address, container, false);
        binding.setAddress(address);
        return binding.getRoot();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        if (address.getId() != 0)
            alertDialogBuilder.setTitle(R.string.edit_address_dialog_title);
        else
            alertDialogBuilder.setTitle(R.string.add_address_dialog_title);
        View view = onCreateDialogView(getActivity().getLayoutInflater(), null, null);
        onViewCreated(view, null);
        alertDialogBuilder.setView(view);
        mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        binding.textTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                AlertDialog dialog = (AlertDialog) getDialog();
                if (s.toString().trim().length() == 0)
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                else
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        alertDialogBuilder.setPositiveButton(R.string.alert_ok, (dialog, which) -> {
            address.setTitle(binding.textTitle.getText().toString());
            address.setAddress(binding.textAddress.getText().toString());
            address.setLocation(googleMap.getCameraPosition().target);
            mListener.onSaveButtonClicked(address);
        });
        alertDialogBuilder.setNegativeButton(R.string.alert_cancel, (dialog, which) -> {
            if (dialog != null) {
                dialog.dismiss();
            }
        });
        return alertDialogBuilder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onEditAddressInteractionListener) {
            mListener = (onEditAddressInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement onEditAddressInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;

    }

    @Override
    public void onPause() {
        if (mapFragment != null && getActivity() != null)
            getActivity().getSupportFragmentManager().beginTransaction().remove(mapFragment).commitAllowingStateLoss();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        AlertDialog dialog = (AlertDialog) getDialog();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        if (address.getLocation() != null)
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(address.getLocation(), 16f));
    }

    public interface onEditAddressInteractionListener {
        void onSaveButtonClicked(Address address);
    }
}
