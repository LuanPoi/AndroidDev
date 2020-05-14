package com.example.sextoapp.controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menuItemSortByName){
            // TODO: Fazer o sort das cidades por nome
            return true;
        }else if(id == R.id.menuItemSortByPopulation){
            // TODO: Fazer o sort das cidades pelo tamanho da populacao
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addCityButtonPressed(View v){
        // TODO: Chamar a activity de inserção ou alteraçaõ de dados
    }
}
