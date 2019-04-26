package com.tufike.taxi.rider.activities.addresses.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tufike.taxi.common.models.Address;
import com.tufike.taxi.rider.databinding.ItemAddressBinding;

import java.util.List;


public class AddressesRecyclerViewAdapter extends RecyclerView.Adapter<AddressesRecyclerViewAdapter.ViewHolder> {
    private List<Address> addresses;
    public final OnAddressItemInteractionListener listener;
    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemAddressBinding binding;
        ViewHolder(ItemAddressBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        void bind(Address address, OnAddressItemInteractionListener listener) {
            binding.setAddress(address);
            binding.buttonEdit.setOnClickListener(view -> listener.onEdit(address));
            binding.buttonDelete.setOnClickListener(view -> listener.onDelete(address));
            binding.executePendingBindings();
        }
    }

    public AddressesRecyclerViewAdapter(List<Address> addresses, OnAddressItemInteractionListener listener) {
        this.addresses = addresses;
        this.listener = listener;
    }

    @Override
    public AddressesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemAddressBinding itemBinding = ItemAddressBinding.inflate(layoutInflater, parent, false);
        return new AddressesRecyclerViewAdapter.ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(AddressesRecyclerViewAdapter.ViewHolder holder, int position) {
        Address address = addresses.get(position);
        holder.bind(address, listener);

    }

    @Override
    public int getItemCount() {
        return addresses.size();
    }
    public interface OnAddressItemInteractionListener {
        void onEdit(Address address);
        void onDelete(Address address);
    }
}