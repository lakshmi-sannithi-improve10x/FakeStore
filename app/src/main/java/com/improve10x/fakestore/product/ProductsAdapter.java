package com.improve10x.fakestore.product;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.fakestore.databinding.ProductItemBinding;
import com.improve10x.fakestore.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    private List<Product> products;
    private OnItemActionListener actionListener;

    void setProductListener(OnItemActionListener listener) {
        this.actionListener = listener;
    }

    //TODO: setDataAdapter
    public ProductsAdapter(List<Product> products) {
        this.products = products;
    }

    void updateData(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ProductItemBinding binding = ProductItemBinding.inflate(inflater, parent, false);
        ProductViewHolder viewHolder = new ProductViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
       // holder.binding.titleTxt.setText(product.getTitle());
       // holder.binding.ratingRb.setRating(Float.parseFloat(String.valueOf(product.getRating().getRate())));
      //  holder.binding.countTxt.setText(String.valueOf(product.getRating().getCount()));
       // holder.binding.priceTxt.setText(String.valueOf(product.getPrice()));
        //holder.binding.rateTxt.setText(String.valueOf(product.getRating().getRate()));
        //Picasso.get().load(product.getImageUrl()).into(holder.binding.imageIv);
        holder.binding.getRoot().setOnClickListener(view -> {
            actionListener.onClick(product.getId());
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
