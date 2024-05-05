package com.example.e_commerce.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_commerce.R;
import com.example.e_commerce.databinding.ViewholderColorBinding;

import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.Viewholder> {
    private List<String> items;
    private int selectedPosition = -1;
    private int lastSelectedPosition = -1;
    private Context context;

    public ColorAdapter(List<String> items) {
        this.items = items;
    }



    @NonNull
    @Override
    public ColorAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        ViewholderColorBinding binding= ViewholderColorBinding.inflate(LayoutInflater.from(context),parent,false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorAdapter.Viewholder holder, int position) {

        Glide.with(holder.itemView.getContext())
                .load(items.get(position))
                .into(holder.binding.pic);
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
        } else {
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg);
        }
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ViewholderColorBinding binding;

        public Viewholder(ViewholderColorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
