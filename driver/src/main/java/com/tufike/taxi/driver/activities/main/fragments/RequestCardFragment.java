package com.tufike.taxi.driver.activities.main.fragments;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tufike.taxi.common.models.Request;
import com.tufike.taxi.driver.R;
import com.tufike.taxi.driver.databinding.FragmentRequestCardBinding;

public class RequestCardFragment extends Fragment {
    private Request request;
    FragmentRequestCardBinding binding;
    private CountDownTimer countDownTimer;
    private OnFragmentInteractionListener mListener;
    private static final String ARG_REQUEST = "request";

    public RequestCardFragment() {

    }

    public static RequestCardFragment newInstance(Request request) {
        RequestCardFragment fragment = new RequestCardFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_REQUEST, request);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            request = (Request) getArguments().getSerializable(ARG_REQUEST);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_request_card, container,false);
        binding.setRequest(request);
        countDownTimer = new CountDownTimer(5 * 60000, 50) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                if(mListener != null && request != null)
                    mListener.onDecline(request);
            }
        };
        countDownTimer.start();
        if(getResources().getBoolean(R.bool.use_miles))
            binding.textDistance.setText(getString(R.string.unit_distance_miles,request.distance / 1609.344f));
        else
            binding.textDistance.setText(getString(R.string.unit_distance,request.distance / 1000f));
        if(getResources().getBoolean(R.bool.use_miles))
            binding.textFromYou.setText(getString(R.string.unit_distance_miles,request.fromDriver / 1000f));
        else
            binding.textFromYou.setText(getString(R.string.unit_distance,request.fromDriver / 1000f));
        binding.buttonAccept.setOnClickListener(view -> {
            countDownTimer.cancel();
            mListener.onAccept(request);
        });
        binding.buttonDecline.setOnClickListener(view -> {
            countDownTimer.cancel();
            mListener.onDecline(request);
        });
        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onAccept(Request request);
        void onDecline(Request request);
    }
}
