package com.improve10x.fakestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.improve10x.fakestore.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
        ActivitySplashBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(this, CategoryActivity.class);
            startActivity(intent);
        },3000);
    }
}