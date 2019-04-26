package com.tufike.taxi.rider.activities.travel.fragments;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tufike.taxi.common.components.BaseFragment;
import com.tufike.taxi.common.models.Review;
import com.tufike.taxi.rider.R;
import com.tufike.taxi.rider.databinding.FragmentTravelReviewBinding;
import com.tufike.taxi.rider.events.ReviewDriverEvent;

import org.greenrobot.eventbus.EventBus;

public class TabReviewFragment extends BaseFragment {
    FragmentTravelReviewBinding binding;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventBus = EventBus.getDefault();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_travel_review,container,false);
        binding.ratingBar.setOnRatingBarChangeListener((ratingBar, v, b) -> binding.buttonSaveReview.setEnabled(true));
        binding.buttonSaveReview.setOnClickListener(view -> {
            Review review = new Review((int)binding.ratingBar.getRating() * 20,binding.reviewText.getText().toString());
            eventBus.post(new ReviewDriverEvent(review));
        });
        return binding.getRoot();
    }
}
