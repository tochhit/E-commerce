package com.example.e_commerce.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.e_commerce.Domain.ItemsDomain;
import com.example.e_commerce.Helper.ChangeNumberItemsListener;
import com.example.e_commerce.Helper.ManagmentCart;
import com.example.e_commerce.databinding.ViewholderCartBinding;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Viewholder> {
    ArrayList<ItemsDomain> listItemSeleted;
    ChangeNumberItemsListener changeNumberItemsListener;
    private ManagmentCart  managmentCart;

    public CartAdapter(ArrayList<ItemsDomain> listItemSeleted, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.listItemSeleted = listItemSeleted;
        this.changeNumberItemsListener = changeNumberItemsListener;
        managmentCart = new ManagmentCart(context);
    }

    @NonNull
    @Override
    public CartAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ViewholderCartBinding binding=ViewholderCartBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.Viewholder holder, int position) {
        holder.binding.titleTxt.setText(listItemSeleted.get(position).getTitle());
        holder.binding.feeEachItem.setText("$"+listItemSeleted.get(position).getPrice());
        holder.binding.totalEachItem.setText("$"+Math.round(listItemSeleted.get(position).getNumberInCart()*listItemSeleted.get(position).getPrice()));
        holder.binding.numberItemTxt.setText(String.valueOf(listItemSeleted.get(position).getNumberInCart()));

        RequestOptions requestOptions=new RequestOptions();
        requestOptions=requestOptions.transform(new CenterCrop());

        Glide.with(holder.itemView.getContext())
                .load(listItemSeleted.get(position).getPicUrl().get(0))
                .apply(requestOptions)
                .into(holder.binding.pic);

        holder.binding.plusCartBtn.setOnClickListener(v -> managmentCart.plusItem(listItemSeleted, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.onChanged();
        }));

        holder.binding.minusCartBtn.setOnClickListener(v -> managmentCart.minusItem(listItemSeleted, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.onChanged();
        }));

    }

    @Override
    public int getItemCount() {
        return listItemSeleted.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ViewholderCartBinding binding;

        public Viewholder(ViewholderCartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
