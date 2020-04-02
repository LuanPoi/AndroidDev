package com.example.segundoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button buttonIr2Tela;
    EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName = findViewById(R.id.editTextName);
        buttonIr2Tela = findViewById(R.id.buttonIr2Tela);
        buttonIr2Tela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model.getInstance().text = editTextName.
                        getText().toString();
                Intent intent = new Intent(MainActivity.this,
                        Main2Activity.class
                        );
                startActivity(intent);

            }
        });
    }


}
