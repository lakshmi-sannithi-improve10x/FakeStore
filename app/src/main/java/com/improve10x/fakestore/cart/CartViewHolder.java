package com.improve10x.fakestore.cart;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.fakestore.databinding.CartItemBinding;

public class CartViewHolder extends RecyclerView.ViewHolder {
    CartItemBinding binding;

    public CartViewHolder(@NonNull CartItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
