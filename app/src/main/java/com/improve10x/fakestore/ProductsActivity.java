package com.improve10x.fakestore;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.improve10x.fakestore.databinding.ActivityProductsBinding;
import com.improve10x.fakestore.models.Product;
import com.improve10x.fakestore.network.FakeApi;
import com.improve10x.fakestore.network.FakeApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductsActivity extends AppCompatActivity {
    private ActivityProductsBinding binding;
    private Product[] products = new Product[0];
    private ProductsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Products");
        setupAdapter();
        connectAdapter();
        productsApi();
    }

    private void productsApi() {
        FakeApiService service = new FakeApi().createFakeApiService();
        Call<List<Product>> call = service.getProducts("electronics");
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Toast.makeText(ProductsActivity.this, "Success", Toast.LENGTH_SHORT).show();
                adapter.updateData(products);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(ProductsActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void connectAdapter() {
        binding.productsRv.setLayoutManager(new GridLayoutManager(this,1));
        binding.productsRv.setAdapter(adapter);
    }

    private void setupAdapter() {
        adapter = new ProductsAdapter(products);
    }
}