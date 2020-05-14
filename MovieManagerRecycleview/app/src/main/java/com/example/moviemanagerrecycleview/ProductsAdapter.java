package com.example.moviemanagerrecycleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewItemName;
        RatingBar ratingBarMovieInput;
        ImageView imageViewMovie;


        public ViewHolder(View itemView){
            super(itemView);
            textViewItemName = itemView.findViewById(R.id.textViewItemName);
            ratingBarMovieInput = itemView.findViewById(R.id.ratingBarMovieInput);
            imageViewMovie = itemView.findViewById(R.id.imageViewMovie);
            ratingBarMovieInput.setNumStars(5);
        }
    }
    private List<Product> products;
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Product product = products.get(position);
        holder.textViewItemName.setText(product.name);
        holder.ratingBarMovieInput.setRating(product.rating);
        holder.imageViewMovie.setImageURI(product.image);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                products.remove(product);
                notifyDataSetChanged();
                return true;
            }
        });
    }


}
