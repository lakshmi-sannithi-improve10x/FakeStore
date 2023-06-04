package com.improve10x.fakestore.category;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.improve10x.fakestore.BaseActivity;
import com.improve10x.fakestore.databinding.ActivityCategoriesBinding;
import com.improve10x.fakestore.models.Product;
import com.improve10x.fakestore.product.ProductsActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesActivity extends BaseActivity  {

    private ActivityCategoriesBinding binding;
    private ArrayList<Product> products = new ArrayList<>();
    private CategoriesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Categories");
        setupCategoriesRv();
        connectAdapter();
        getApi();
    }





    private void getApi() {
        Call<List<Product>> call = service.fetchCategories();
        call.enqueue(new Callback<List<Product>>() {
         @Override
        public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
              adapter.updateData(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(CategoriesActivity.this, "failure", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void connectAdapter() {
        binding.categoryRv.setLayoutManager(new LinearLayoutManager(this));
        binding.categoryRv.setAdapter(adapter);
    }

    private void setupCategoriesRv() {
        adapter = new CategoriesAdapter(products);
    }
}
