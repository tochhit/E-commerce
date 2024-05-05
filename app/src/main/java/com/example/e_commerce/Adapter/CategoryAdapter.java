package com.example.e_commerce.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_commerce.Activity.ExplorerActivity;
import com.example.e_commerce.Domain.CategoryDomain;
import com.example.e_commerce.databinding.ViewholderCategoryBinding;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private ArrayList<CategoryDomain> items;
    private Context context;

    public CategoryAdapter(ArrayList<CategoryDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ViewholderCategoryBinding binding = ViewholderCategoryBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryDomain currentItem = items.get(position);
        holder.bind(currentItem);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ViewholderCategoryBinding binding;

        public ViewHolder(ViewholderCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(CategoryDomain item) {
            binding.titleC1.setText(item.getTitle());
            Glide.with(context)
                    .load(item.getPicUrl())
                    .into(binding.picC1);

            // Set click listener here
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Retrieve the clicked category
                    CategoryDomain clickedCategory = items.get(getAdapterPosition());
                    // Check the title of the clicked category
                    if (clickedCategory.getTitle().equals("Adidas")) {
                        // If the clicked category is Adidas, navigate to ExplorerActivity
                        Intent intent = new Intent(context, ExplorerActivity.class);
                        intent.putExtra("category", clickedCategory.getTitle());
                        context.startActivity(intent);
                    } else if (clickedCategory.getTitle().equals("Nike")) {
                        // If the clicked category is Nike, navigate to ExplorerActivity
                        Intent intent = new Intent(context, ExplorerActivity.class);
                        intent.putExtra("category", clickedCategory.getTitle());
                        context.startActivity(intent);
                    }
                    // Add more conditions for other categories if needed
                }
            });
        }
    }
}
