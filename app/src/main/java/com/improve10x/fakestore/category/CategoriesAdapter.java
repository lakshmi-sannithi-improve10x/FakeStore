package com.improve10x.fakestore.category;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.fakestore.databinding.CategoryItemBinding;
import com.improve10x.fakestore.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private List<Product> products;
    //private OnServiceActionListener listener;
    //void setActionListener(OnServiceActionListener listener){
       // this.listener =listener;
   // }

    public CategoriesAdapter(List<Product> products) {
        this.products = products;
    }

    void updateData(List<Product> products) {
        this.products = products;
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
        Product product = products.get(position);
        holder.binding.nameTxt.setText(product.getName());
        // holder.binding.categorynameTxt.setOnClickListener(view -> {
        // listener.onClick(category);
        // });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
