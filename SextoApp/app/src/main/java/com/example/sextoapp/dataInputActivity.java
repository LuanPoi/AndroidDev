package com.example.sextoapp;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;

import android.os.Bundle;
import android.view.View;

public class dataInputActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input);


        final MediaPlayer ayayaSound = MediaPlayer.create(this, R.ass);
    }

    public void imageButtonAyayaPressed(View v){
        ayayaSound.start();
    }
}
