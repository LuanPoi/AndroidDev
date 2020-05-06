package com.example.quintoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewItemName;
        CheckBox checkBoxItemBuy;
        public ViewHolder(View itemView){
            super(itemView);

            textViewItemName = itemView.findViewById(R.id.textViewItemName);
            checkBoxItemBuy = itemView.findViewById(R.id.checkBoxItemBuy);
        }
    }
    private  List<Product>products;
    public ProductsAdapter(List<Product> products){
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.recycle_item_content, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.textViewItemName.setText(product.name);
        holder.checkBoxItemBuy.setChecked(product.needToBuy);
    }
}
