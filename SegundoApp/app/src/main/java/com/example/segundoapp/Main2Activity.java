package com.example.segundoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    ImageView imageViewMain;
    Switch switchImageVisibility;
    TextView textViewNameDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textViewNameDisplay = findViewById(R.id.textViewNameDisplay);
        textViewNameDisplay.setText(Model.getInstance().text);
        imageViewMain = findViewById(R.id.imageViewMain);
        switchImageVisibility = findViewById(R.id.switchImageVisibility);
        switchImageVisibility.setChecked(true);
        switchImageVisibility.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked == true){
                            imageViewMain.setVisibility(View.VISIBLE);
                        }else{
                            imageViewMain.setVisibility(View.INVISIBLE);
                        }
                    }
                }
        );
    }

    public void buttonChangeImageClicked(View v){
        //imageViewMain.setImageResource(R.drawable.saad_jas_39_gripen);
        Drawable drawable = getResources().getDrawable(
                getResources().getIdentifier("saad_jas_39_gripen",
                        "drawable", getPackageName())
        );
        imageViewMain.setImageDrawable(drawable);
    }
}
