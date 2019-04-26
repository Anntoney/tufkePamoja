package com.tufike.taxi.rider.activities.main.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tufike.taxi.common.models.Service;
import com.tufike.taxi.common.utils.DataBinder;
import com.tufike.taxi.rider.databinding.ItemServiceBinding;

import java.util.List;

public class ServicesListAdapter extends RecyclerView.Adapter<ServicesListAdapter.ViewHolder> {
    private List<Service> services;
    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemServiceBinding binding;
        ViewHolder(ItemServiceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        void bind(Service service) {
            binding.setItem(service);
            if(service.getMedia()!= null)
                DataBinder.setMedia(binding.image,service.getMedia());
            binding.executePendingBindings();
        }
    }

    public ServicesListAdapter(List<Service> services) {
        this.services = services;
    }

    @Override
    public ServicesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemServiceBinding itemBinding = ItemServiceBinding.inflate(layoutInflater, parent, false);
        itemBinding.textCost.setSelected(true);
        return new ServicesListAdapter.ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(ServicesListAdapter.ViewHolder holder, int position) {
        Service service = services.get(position);
        holder.bind(service);
    }

    @Override
    public int getItemCount() {
        return services.size();
    }
}