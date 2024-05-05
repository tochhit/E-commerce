package com.example.e_commerce.Activity;

import android.os.Bundle;

import com.example.e_commerce.databinding.ActivityExplorerBinding;

public class ExplorerActivity extends BaseActivity {

    ActivityExplorerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityExplorerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVarialbe();

    }
    private void setVarialbe() {
        binding.backBtn.setOnClickListener(v -> finish());
    }
}