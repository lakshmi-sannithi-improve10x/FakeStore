package com.improve10x.fakestore;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.fakestore.databinding.ProductItemBinding;
import com.improve10x.fakestore.models.Product;
import com.squareup.picasso.Picasso;

public class ProductsAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    private Product[] products;

    public ProductsAdapter(Product[] products){
        this.products = products;
    }

    void updateData(Product[] products){
        this.products = products;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ProductItemBinding binding = ProductItemBinding.inflate(inflater,parent,false);
        ProductViewHolder viewHolder = new ProductViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products[position];
        holder.binding.titleTxt.setText(product.getTitle());
        holder.binding.ratingRb.setRating(product.getRating().getRate());
        holder.binding.countTxt.setText(product.getRating().getCount());
        holder.binding.priceTxt.setText(String.valueOf(product.getPrice()));
        Picasso.get().load(product.getImageUrl()).into(holder.binding.imageIv);
    }

    @Override
    public int getItemCount() {
        return products.length;
    }
}
