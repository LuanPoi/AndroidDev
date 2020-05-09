package com.example.sextoapp.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sextoapp.R;
import com.example.sextoapp.model.City;
import com.example.sextoapp.model.DataStore;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityHolder> {
    private List<City> cities = DataStore.getInstance().getCities();

    @NonNull
    @Override
    public CityAdapter.CityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_cities, parent, false);

        return new CityHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.CityHolder holder, int position) {
        City city = cities.get(position);
        holder.textViewCityName.setText(city.getName());
        holder.textViewCityPopulation.setText(Integer.toString(city.getPopulation()));
    }

    @Override
    public int getItemCount() {
        return cities.size();
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
