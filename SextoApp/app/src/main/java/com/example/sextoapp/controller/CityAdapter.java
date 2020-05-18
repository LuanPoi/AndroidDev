package com.example.sextoapp.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sextoapp.R;
import com.example.sextoapp.model.City;
import com.example.sextoapp.model.DataStore;

import java.util.List;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityHolder> {
    private DataStore dataStore = DataStore.getInstance();
    private Context context;

    public CityAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public CityAdapter.CityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_cities, parent, false);
        return new CityHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.CityHolder holder, int position) {
        City city = dataStore.getCities().get(position);
        holder.textViewCityName.setText(city.getName());
        holder.textViewCityPopulation.setText(Integer.toString(city.getPopulation()));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dataStore.removeCity(city);
                notifyDataSetChanged();
                return true;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tempIntent =  new Intent(context, dataInputActivity.class);
                tempIntent.putExtra("request_code", 102);
                tempIntent.putExtra("city_id", city.getId());
                tempIntent.putExtra("city_name", city.getName());
                tempIntent.putExtra("city_population", city.getPopulation());
                tempIntent.putExtra("city_array_position", position);
                context.startActivity(tempIntent);

                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return DataStore.getInstance().getCityListSize();
    }

    public class CityHolder extends RecyclerView.ViewHolder{
        TextView textViewCityName;
        TextView textViewCityPopulation;

        public CityHolder(View itemView){
            super(itemView);
            textViewCityName = itemView.findViewById(R.id.textViewCityName);
            textViewCityPopulation = itemView.findViewById(R.id.textViewCityPopulation);
        }
    }
}
