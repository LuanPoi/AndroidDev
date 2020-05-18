package com.example.sextoapp.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.sextoapp.R;
import com.example.sextoapp.model.City;
import com.example.sextoapp.model.DataStore;

import java.io.IOException;
import java.util.Objects;

public class dataInputActivity extends AppCompatActivity {
    DataStore dataStore;
    EditText editTextCityNameInput;
    EditText editTextCityPopulationInput;
    int position = -1;
    long id = -1;
    int requestCode = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input);

        dataStore = DataStore.getInstance();
        editTextCityNameInput = findViewById(R.id.editTextCityNameInput);
        editTextCityPopulationInput = findViewById(R.id.editTextCityPopulationInput);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            requestCode = (int) extras.get("request_code");

            if(requestCode == 102){
                id = Long.parseLong(extras.get("city_id").toString());
                editTextCityNameInput.setText(extras.getString("city_name"));
                editTextCityPopulationInput.setText(extras.get("city_population").toString());
                position = extras.getInt("city_array_position");
            }
        }
    }

    public void imageButtonAyayaPressed(View v){
        if (!editTextCityNameInput.getText().toString().isEmpty() && !editTextCityPopulationInput.getText().toString().isEmpty()){
            if(requestCode == 101){
                dataStore.addCity(new City(editTextCityNameInput.getText().toString(), Integer.parseInt(editTextCityPopulationInput.getText().toString())));
                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }else if (requestCode == 102){
                City tempCity = new City(editTextCityNameInput.getText().toString(), Integer.parseInt(editTextCityPopulationInput.getText().toString()));
                tempCity.setId(id);
                dataStore.editCity(tempCity, position);
                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        }
    }
}
