package com.improve10x.fakestore.category;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.fakestore.databinding.CategoryItemBinding;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    CategoryItemBinding binding;

    public CategoryViewHolder(@NonNull CategoryItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
