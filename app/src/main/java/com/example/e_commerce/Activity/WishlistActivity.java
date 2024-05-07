package com.example.e_commerce.Activity;

import android.os.Bundle;

import com.example.e_commerce.databinding.ActivityWishlistBinding;

public class WishlistActivity extends BaseActivity {

    ActivityWishlistBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityWishlistBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVarialbe();

    }
    private void setVarialbe() {
        binding.backBtn.setOnClickListener(v -> finish());
    }
}