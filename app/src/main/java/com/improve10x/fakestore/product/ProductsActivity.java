package com.improve10x.fakestore.product;

import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.improve10x.fakestore.BaseActivity;
import com.improve10x.fakestore.productdetails.ProductDetailsActivity;
import com.improve10x.fakestore.databinding.ActivityProductsBinding;
import com.improve10x.fakestore.models.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductsActivity extends BaseActivity implements OnItemActionListener {
    private ActivityProductsBinding binding;
    private List<Product> products = new ArrayList<>();
    private String category;
    private ProductsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Products");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        category = intent.getStringExtra("category");
        setupAdapter();
        setupProductsRv();
    }

    private void productsApi(String category) {
        Call<List<Product>> call = service.getProducts(category);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                adapter.updateData(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(ProductsActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupProductsRv() {
        binding.productsRv.setLayoutManager(new GridLayoutManager(this, 2));
        binding.productsRv.setAdapter(adapter);
    }

    private void setupAdapter() {
        adapter = new ProductsAdapter(products);
        adapter.setProductListener(this);
    }

    @Override
    public void onClick(int productId) {
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra("id", productId);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        productsApi(category);
    }
}