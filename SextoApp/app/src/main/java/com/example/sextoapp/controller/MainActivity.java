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

public class MainActivity extends AppCompatActivity implements CityDialogFragment.CityDialogListener {
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
//        buttonAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
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
            DialogFragment dialogFragment = new CityDialogFragment();
            dialogFragment.show(dialogFragment.getFragmentManager(), "cityDialog");
            return true;
        }else if(id == R.id.menuItemSortByPopulation){

        }
        return super.onOptionsItemSelected(item);
    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // User touched the dialog's positive button
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

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's negative button
        return;
    }


    public void addCityButtonPressed(View v){
        DialogFragment dialog = new CityDialogFragment();
        dialog.show(getSupportFragmentManager(), "AddCityDialogFragmentTag");
    }
}
