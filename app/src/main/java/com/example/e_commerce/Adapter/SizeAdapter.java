package com.example.e_commerce.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.R;
import com.example.e_commerce.databinding.ViewholderSizeBinding;

import java.util.List;

public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.Viewholder> {
    private List<String> items;
    private int selectedPosition = -1;
    private int lastSelectedPosition = -1;
    private Context context;

    public SizeAdapter(List<String> items) {
        this.items = items;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ViewholderSizeBinding binding;

        public Viewholder(ViewholderSizeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public SizeAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ViewholderSizeBinding binding = ViewholderSizeBinding.inflate(LayoutInflater.from(context), parent, false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SizeAdapter.Viewholder holder, int position) {

        holder.binding.sizeTxt.setText(items.get(position));

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastSelectedPosition = selectedPosition;
                selectedPosition = holder.getAdapterPosition();
                notifyItemChanged(lastSelectedPosition);
                notifyItemChanged(selectedPosition);
            }
        });

        if (selectedPosition == position) {
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg_selected);
            holder.binding.sizeTxt.setTextColor(context.getResources().getColor(R.color.blue));

        } else {
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg);
            holder.binding.sizeTxt.setTextColor(context.getResources().getColor(R.color.black));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
