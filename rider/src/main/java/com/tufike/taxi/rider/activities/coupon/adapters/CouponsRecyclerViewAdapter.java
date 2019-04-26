package com.tufike.taxi.rider.activities.coupon.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tufike.taxi.common.models.Coupon;
import com.tufike.taxi.rider.databinding.ItemCouponBinding;

import java.util.List;

public class CouponsRecyclerViewAdapter extends RecyclerView.Adapter<CouponsRecyclerViewAdapter.ViewHolder> {
    private List<Coupon> coupons;
    public final CouponsRecyclerViewAdapter.OnCouponItemInteractionListener listener;
    boolean isEditMode = false;

    public CouponsRecyclerViewAdapter(List<Coupon> coupons, CouponsRecyclerViewAdapter.OnCouponItemInteractionListener listener, boolean isEditMode) {
        this.coupons = coupons;
        this.listener = listener;
        this.isEditMode = isEditMode;
    }

    @NonNull
    @Override
    public CouponsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemCouponBinding itemBinding = ItemCouponBinding.inflate(layoutInflater, parent, false);
        if(!isEditMode)
            itemBinding.buttonSelect.setVisibility(View.INVISIBLE);
        return new CouponsRecyclerViewAdapter.ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(CouponsRecyclerViewAdapter.ViewHolder holder, int position) {
        Coupon coupon = coupons.get(position);
        holder.bind(coupon, listener);
    }

    @Override
    public int getItemCount() {
        return coupons.size();
    }

    public interface OnCouponItemInteractionListener {
        void onSelect(Coupon coupon);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemCouponBinding binding;
        ViewHolder(ItemCouponBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        void bind(Coupon coupon, CouponsRecyclerViewAdapter.OnCouponItemInteractionListener listener) {
            binding.setItem(coupon);
            binding.buttonSelect.setOnClickListener(v -> listener.onSelect(coupon));
            binding.executePendingBindings();
        }
    }
}