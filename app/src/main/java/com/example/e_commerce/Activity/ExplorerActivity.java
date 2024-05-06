package com.example.e_commerce.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.e_commerce.Adapter.CategoryAdapter;
import com.example.e_commerce.Adapter.PopularAdapter;
import com.example.e_commerce.Domain.CategoryDomain;
import com.example.e_commerce.Domain.ItemsDomain;
import com.example.e_commerce.databinding.ActivityExplorerBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExplorerActivity extends BaseActivity {

    ActivityExplorerBinding binding;
    ArrayList<ItemsDomain> allItems;
    ArrayList<ItemsDomain> filteredItems;
    PopularAdapter popularAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityExplorerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();
        initCategory();
        initPopular();

        allItems = new ArrayList<>();
        filteredItems = new ArrayList<>();

    }
    private void initPopular() {
        DatabaseReference myRef = database.getReference("Items");
        binding.progressBarPopular.setVisibility(View.VISIBLE);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        ItemsDomain item = issue.getValue(ItemsDomain.class);
                        allItems.add(item);
                    }
                    // Initially show all items
                    filteredItems.addAll(allItems);
                    setupPopularRecyclerView();
                }
                binding.progressBarPopular.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                binding.progressBarPopular.setVisibility(View.GONE);
            }
        });
    }
    private void setupPopularRecyclerView() {
        // Initialize RecyclerView and adapter
        binding.viewPopular.setLayoutManager(new GridLayoutManager(ExplorerActivity.this, 2));
        popularAdapter = new PopularAdapter(filteredItems);
        binding.viewPopular.setAdapter(popularAdapter);
    }
    private void initCategory() {
        DatabaseReference myRef=database.getReference("Category");
        binding.progressBarBrand.setVisibility(View.VISIBLE);
        ArrayList<CategoryDomain> items=new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue:snapshot.getChildren()){
                        items.add(issue.getValue(CategoryDomain.class));
                    }
                    if(!items.isEmpty()){
                        binding.recyclerViewOfficial.setLayoutManager(new LinearLayoutManager(ExplorerActivity.this,
                                LinearLayoutManager.HORIZONTAL,false));
                        binding.recyclerViewOfficial.setAdapter(new CategoryAdapter(items));

                    }
                    binding.progressBarBrand.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> finish());

        binding.searchTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterItems(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
    private void filterItems(String searchText) {
        filteredItems.clear();
        if (TextUtils.isEmpty(searchText)) {
            // If search text is empty, show all items
            filteredItems.addAll(allItems);
        } else {
            // Filter items based on search text
            for (ItemsDomain item : allItems) {
                if (item.getTitle().toLowerCase().contains(searchText.toLowerCase())) {
                    filteredItems.add(item);
                }
            }
        }
        // Notify the adapter that data set has changed
        popularAdapter.notifyDataSetChanged();
    }
}