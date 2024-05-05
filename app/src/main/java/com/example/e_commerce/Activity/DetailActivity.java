package com.example.e_commerce.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.e_commerce.Adapter.ColorAdapter;
import com.example.e_commerce.Adapter.SizeAdapter;
import com.example.e_commerce.Adapter.SliderAdapter;
import com.example.e_commerce.Domain.ItemsDomain;
import com.example.e_commerce.Domain.SliderItems;
import com.example.e_commerce.Helper.ManagmentCart;
import com.example.e_commerce.databinding.ActivityDetailBinding;

import java.util.ArrayList;

public class DetailActivity extends BaseActivity {
    ActivityDetailBinding binding;
    private ItemsDomain object;
    private int numberOrder=1;
    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        managmentCart = new ManagmentCart(this);

        getBundles();
        banners();
        initLists();
        bottomNavigation();
    }

    private void bottomNavigation() {
        binding.cartBtn.setOnClickListener(v -> startActivity(new Intent(DetailActivity.this,CartActivity.class)));
    }

    private void initLists() {
        // Initialize sizeList
        ArrayList<String> sizeList = new ArrayList<>();
        for (String size : object.getSize()) {
            sizeList.add(String.valueOf(size));
        }
        binding.sizeList.setAdapter(new SizeAdapter(sizeList));
        binding.sizeList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Initialize colorList
        ArrayList<String> colorList = new ArrayList<>();
        for (String imageUrl : object.getPicUrl()) {
            colorList.add(imageUrl);
        }
        binding.colorList.setAdapter(new ColorAdapter(colorList));
        binding.colorList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }


    private void banners() {
        ArrayList<SliderItems> sliderItems = new ArrayList<>();
        for (String picUrl : object.getPicUrl()) {
            sliderItems.add(new SliderItems(picUrl));
        }
        binding.slider.setAdapter(new SliderAdapter(sliderItems, binding.slider));
        binding.slider.setClipToPadding(true);
        binding.slider.setClipChildren(true);
        binding.slider.setOffscreenPageLimit(1);

        if (sliderItems.size() > 1) {
            binding.dotsIndicator.setVisibility(View.VISIBLE);
            binding.dotsIndicator.setViewPager2(binding.slider);
        }
    }


    private void getBundles() {
        object = (ItemsDomain) getIntent().getSerializableExtra("object");
        binding.titleTxt.setText(object.getTitle());
        binding.descriptionTxt.setText(object.getDescription());
        binding.priceTxt.setText("$" + object.getPrice());
        binding.ratingTxt.setText(object.getRating() + "Rating");

        binding.addToCartBtn.setOnClickListener(v -> {
            object.setNumberInCart(numberOrder);
            managmentCart.insertFood(object);

        });
        binding.backBtn.setOnClickListener(v -> finish());
        binding.cartBtn.setOnClickListener(v -> {
        });
    }
}