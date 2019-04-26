package com.tufike.taxi.rider.activities.promotions.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tufike.taxi.common.models.Promotion;
import com.tufike.taxi.rider.databinding.ItemPromotionBinding;

import java.util.List;

public class PromotionsRecyclerViewAdapter extends RecyclerView.Adapter<PromotionsRecyclerViewAdapter.ViewHolder> {
    private List<Promotion> promotions;
    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemPromotionBinding binding;
        ViewHolder(ItemPromotionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        void bind(Promotion promotion) {
            binding.setItem(promotion);
            binding.executePendingBindings();
        }
    }

    public PromotionsRecyclerViewAdapter(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    @Override
    public PromotionsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemPromotionBinding itemBinding = ItemPromotionBinding.inflate(layoutInflater, parent, false);
        return new PromotionsRecyclerViewAdapter.ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(PromotionsRecyclerViewAdapter.ViewHolder holder, int position) {
        Promotion promotion = promotions.get(position);
        holder.bind(promotion);

    }

    @Override
    public int getItemCount() {
        return promotions.size();
    }
}