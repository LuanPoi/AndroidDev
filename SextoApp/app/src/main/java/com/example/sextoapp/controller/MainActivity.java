package com.example.sextoapp.controller;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sextoapp.R;
import com.example.sextoapp.model.City;
import com.example.sextoapp.model.DataStore;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerViewCities;
    Button buttonAdd;
    CityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Cidades");

        recyclerViewCities = findViewById(R.id.recyclerViewCities);
        DataStore.getInstance().setContext(this);
        adapter = new CityAdapter();
        recyclerViewCities.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewCities.setLayoutManager(manager);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int population = random.nextInt(10000000);
                StringBuilder stringBuilder = new StringBuilder();
                int stringSize = random.nextInt(5)+5;
                for (int i = 0; i<stringSize; ++i){
                    char c = (char) (random.nextInt(96)+32);
                    stringBuilder.append(c);
                }
                City city = new City(stringBuilder.toString(), population);
                DataStore.getInstance().addCity(city);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
