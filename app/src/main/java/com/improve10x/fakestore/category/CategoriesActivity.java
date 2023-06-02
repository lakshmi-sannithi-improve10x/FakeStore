package com.improve10x.fakestore.category;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.improve10x.fakestore.BaseActivity;
import com.improve10x.fakestore.product.ProductsActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesActivity extends BaseActivity implements OnServiceActionListener {

    private ActivityCategoriesBinding binding;

    private ArrayList<String> categories = new ArrayList<>();

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
        Call<List<String>> call = service.fetchCategories();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                adapter.updateData(response.body());
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(CategoriesActivity.this, "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void connectAdapter() {
        binding.categoryRv.setLayoutManager(new LinearLayoutManager(this));
        binding.categoryRv.setAdapter(adapter);
    }

    private void setupCategoriesRv() {
        adapter = new CategoriesAdapter(categories);
        adapter.setOnServiceActionListener(this);
    }

    @Override
    public void onClick(String category) {
        Intent intent = new Intent(this, ProductsActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}