package com.example.e_commerce.Activity;

import android.os.Bundle;

import com.example.e_commerce.databinding.ActivityMyOrderBinding;

public class MyOrderActivity extends BaseActivity {

    ActivityMyOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMyOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVarialbe();

    }
    private void setVarialbe() {
        binding.backBtn.setOnClickListener(v -> finish());
    }
}