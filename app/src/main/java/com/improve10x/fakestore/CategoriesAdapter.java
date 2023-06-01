package com.improve10x.fakestore;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.fakestore.databinding.CategoryItemBinding;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private List<String> categories;

    public CategoriesAdapter(List<String> categories) {
        this.categories = categories;
    }

    void updateData(List<String> categories){
        this.categories = categories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CategoryItemBinding binding = CategoryItemBinding.inflate(inflater, parent, false);
        CategoryViewHolder viewHolder = new CategoryViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        String category = categories.get(position);
        holder.binding.categoryitemTxt.setText(category);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
