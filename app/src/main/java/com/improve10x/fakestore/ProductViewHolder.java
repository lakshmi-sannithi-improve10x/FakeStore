package com.improve10x.fakestore;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.fakestore.databinding.ProductItemBinding;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    ProductItemBinding binding;

    public ProductViewHolder(@NonNull ProductItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
