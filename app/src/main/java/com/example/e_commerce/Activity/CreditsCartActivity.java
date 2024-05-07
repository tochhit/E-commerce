package com.example.e_commerce.Activity;

import android.os.Bundle;

import com.example.e_commerce.databinding.ActivityCreditsCartBinding;

public class CreditsCartActivity extends BaseActivity {

    ActivityCreditsCartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCreditsCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backBtn.setOnClickListener(v -> finish());

    }
}