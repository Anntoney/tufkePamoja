package com.tufike.taxi.rider.activities.travel.fragments;

import android.app.Dialog;
import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tufike.taxi.common.models.Review;
import com.tufike.taxi.rider.R;
import com.tufike.taxi.rider.databinding.FragmentReviewBinding;

import java.util.Objects;


public class ReviewDialog extends DialogFragment {
    FragmentReviewBinding binding;

    private onReviewFragmentInteractionListener mListener;

    public ReviewDialog() {
        // Required empty public constructor
    }

    public static ReviewDialog newInstance() {
        /*Bundle args = new Bundle();
        args.putSerializable(ARG_ADDRESS, param1);
        fragment.setArguments(args);*/
        return new ReviewDialog();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    public View onCreateDialogView(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_review, container, false);
        return binding.getRoot();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        alertDialogBuilder.setTitle(R.string.review_dialog_title);
        View view = onCreateDialogView(getActivity().getLayoutInflater(), null);
        onViewCreated(view, null);
        alertDialogBuilder.setView(view);
        binding.ratingBar.setOnRatingBarChangeListener((ratingBar, v, b) -> {
            AlertDialog dialog = (AlertDialog) getDialog();
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
        });
        alertDialogBuilder.setPositiveButton(getString(R.string.alert_ok), (dialog, which) -> {
            mListener.onReviewTravelClicked(new Review((int) binding.ratingBar.getRating() * 20, binding.reviewText.getText().toString()));
        });
        return alertDialogBuilder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onReviewFragmentInteractionListener) {
            mListener = (onReviewFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement onEditAddressInteractionListener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        AlertDialog dialog = (AlertDialog) getDialog();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface onReviewFragmentInteractionListener {
        void onReviewTravelClicked(Review review);
    }
}
