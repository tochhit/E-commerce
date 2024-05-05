package com.example.e_commerce.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerce.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVarialbe();

    }
    private void setVarialbe() {
        binding.backBtn.setOnClickListener(v -> finish());
    }
}