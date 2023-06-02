package com.improve10x.fakestore.productdetails;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.improve10x.fakestore.BaseActivity;
import com.improve10x.fakestore.databinding.ActivityProductDetailsBinding;
import com.improve10x.fakestore.models.Product;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends BaseActivity {
   private ActivityProductDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("ProductDetails");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
         int id = intent.getIntExtra("id", 0);
        getProductDetails(id);
    }

    private void getProductDetails(int id) {
        Call<Product> call = service.fetchProductDetails(id);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Product product = response.body();
                binding.titleTxt.setText(product.getTitle());
                binding.ratingRb.setRating(product.getRating().getRate());
                binding.rateTxt.setText(String.valueOf(product.getRating().getRate()));
                binding.countTxt.setText(String.valueOf(product.getRating().getCount()));
                binding.descriptionTxt.setText(product.getDescription());
                binding.priceTxt.setText(String.valueOf(product.getPrice()));
                Picasso.get().load(product.getImageUrl()).into(binding.imageIv);
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(ProductDetailsActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}