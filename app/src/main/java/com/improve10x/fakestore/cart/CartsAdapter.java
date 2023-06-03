package com.improve10x.fakestore.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.fakestore.models.CartProduct;
import com.improve10x.fakestore.databinding.CartItemBinding;

import java.util.List;

public class CartsAdapter extends RecyclerView.Adapter<CartViewHolder> {
    private List<CartProduct> cartProducts;

    public CartsAdapter(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    void setData(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CartItemBinding binding = CartItemBinding.inflate(inflater, parent, false);
        CartViewHolder viewHolder = new CartViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartProduct cartProduct = cartProducts.get(position);
        holder.binding.itemidTxt.setText(String.valueOf(cartProduct.getProductId()));
        holder.binding.quantityTxt.setText(String.valueOf(cartProduct.getQuantity()));
        if (cartProduct.getQuantity() == 0) {
            holder.binding.removeBtn.setVisibility(View.GONE);
            holder.binding.quantityTxt.setVisibility(View.GONE);
        } else {
            holder.binding.quantityTxt.setText(cartProduct.getQuantity() + "");
            holder.binding.removeBtn.setVisibility(View.VISIBLE);
            holder.binding.quantityTxt.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return cartProducts.size();
    }
}
