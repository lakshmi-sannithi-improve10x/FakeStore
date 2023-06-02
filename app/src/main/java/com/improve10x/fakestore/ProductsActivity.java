package com.improve10x.fakestore;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.improve10x.fakestore.databinding.ActivityProductsBinding;
import com.improve10x.fakestore.models.Product;
import com.improve10x.fakestore.network.FakeApi;
import com.improve10x.fakestore.network.FakeApiService;
import com.improve10x.fakestore.network.OnProductActionListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductsActivity extends AppCompatActivity implements OnProductActionListener {
    private ActivityProductsBinding binding;
    private List<Product> products = new ArrayList<>();
    private ProductsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Products");
        Intent intent = getIntent();
        String category = intent.getStringExtra("category");
        setupAdapter();
        connectAdapter();
        productsApi(category);
    }

    private void productsApi(String category) {
        FakeApiService service = new FakeApi().createFakeApiService();
        Call<List<Product>> call = service.getProducts(category);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Toast.makeText(ProductsActivity.this, "Success", Toast.LENGTH_SHORT).show();
                adapter.updateData(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(ProductsActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void connectAdapter() {
        binding.productsRv.setLayoutManager(new GridLayoutManager(this,2));
        binding.productsRv.setAdapter(adapter);
    }

    private void setupAdapter() {
        adapter = new ProductsAdapter(products);
        adapter.setProductListener(this);
    }

    @Override
    public void onProductClick(int productId) {
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra("id",productId);
        startActivity(intent);
    }
}