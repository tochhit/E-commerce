package com.example.e_commerce.Helper;

import android.content.Context;
import android.widget.Toast;
import com.example.e_commerce.Domain.ItemsDomain;
import java.util.ArrayList;

public class ManagmentCart {

    private Context context;
    private TinyDB tinyDB;

    public ManagmentCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(ItemsDomain item) {
        ArrayList<ItemsDomain> listFood = getListCart();
        boolean existAlready = false;
        int index = 0;

        for (ItemsDomain listItem : listFood) {
            if (listItem.getTitle().equals(item.getTitle())) {
                existAlready = true;
                index = listFood.indexOf(listItem);
                break;
            }
        }

        if (existAlready) {
            listFood.get(index).setNumberInCart(item.getNumberInCart());
        } else {
            listFood.add(item);
        }
        tinyDB.putListObject("CartList", listFood);
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<ItemsDomain> getListCart() {
        return tinyDB.getListObject("CartList") != null ? tinyDB.getListObject("CartList") : new ArrayList<>();
    }

    public void minusItem(ArrayList<ItemsDomain> listFood, int position, ChangeNumberItemsListener listener) {
        if (listFood.get(position).getNumberInCart() == 1) {
            listFood.remove(position);
        } else {
            listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart() - 1);
        }
        tinyDB.putListObject("CartList", listFood);
        listener.onChanged();
    }

    public void plusItem(ArrayList<ItemsDomain> listFood, int position, ChangeNumberItemsListener listener) {
        listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("CartList", listFood);
        listener.onChanged();
    }

    public double getTotalFee() {
        ArrayList<ItemsDomain> listFood = getListCart();
        double fee = 0.0;
        for (ItemsDomain item : listFood) {
            fee += item.getPrice() * item.getNumberInCart();
        }
        return fee;
    }
}