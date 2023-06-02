package com.improve10x.fakestore;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.improve10x.fakestore.databinding.ActivityProductDetailsBinding;
import com.improve10x.fakestore.models.Product;
import com.improve10x.fakestore.network.FakeApi;
import com.improve10x.fakestore.network.FakeApiService;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity {
    ActivityProductDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("ProductDetails");
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        getProductDetails(id);
    }

    private void getProductDetails(int id) {
        FakeApiService service = new FakeApi().createFakeApiService();
        Call<Product> call = service.fetchProductDetails(id);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Toast.makeText(ProductDetailsActivity.this, "Success", Toast.LENGTH_SHORT).show();
                Product product = response.body();
                binding.titleTxt.setText(product.getTitle());
                binding.ratingRb.setRating(product.getRating().getRate());
                binding.rateTxt.setText(String.valueOf(product.getRating().getRate()));
                binding.countTxt.setText(String.valueOf(product.getRating().getCount()));
                binding.descriptionTxt.setText(product.getDescription());
                binding.costTxt.setText(String.valueOf(product.getPrice()));
                binding.categoryTxt.setText(product.getCategory());
                Picasso.get().load(product.getImageUrl()).into(binding.imageIv);
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(ProductDetailsActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}