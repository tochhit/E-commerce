package com.example.e_commerce.Activity;

import android.os.Bundle;

import com.example.e_commerce.databinding.ActivityAccSettingBinding;

public class AccSettingActivity extends BaseActivity {

    ActivityAccSettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAccSettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backBtn.setOnClickListener(v -> finish());
    }
}