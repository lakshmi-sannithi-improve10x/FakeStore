package com.improve10x.fakestore.cart;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.improve10x.fakestore.BaseActivity;
import com.improve10x.fakestore.models.Cart;
import com.improve10x.fakestore.models.CartProduct;
import com.improve10x.fakestore.databinding.ActivityCartBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends BaseActivity {
    private ActivityCartBinding binding;
     private List<CartProduct> cartProducts = new ArrayList<>();
    private CartsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Final Cart");
        setupAdapter();
        connectAdapter();
        getApi(1);
    }

    private void getApi(int cartId) {
        Call<Cart>  call = service.getCartItems(cartId);
        call.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                Toast.makeText(CartActivity.this, "success"+ response.body(), Toast.LENGTH_SHORT).show();
                adapter.setData(response.body().getProducts());
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupAdapter() {
        adapter = new CartsAdapter(cartProducts);


    }

    private void connectAdapter() {
        binding.cartRv.setLayoutManager(new LinearLayoutManager(this));
        binding.cartRv.setAdapter(adapter);
    }
}