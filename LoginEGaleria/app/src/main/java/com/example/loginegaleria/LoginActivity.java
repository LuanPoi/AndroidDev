package com.example.loginegaleria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        buttonSignIn = findViewById(R.id.buttonSignIn);
//        buttonRegister = findViewById(R.id.buttonRegister);
//
//        buttonSignIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
//                startActivity(intent);
//            }
//        });
    }

    public void buttonClicked(View v){
        Toast.makeText(this, "Clicked:", Toast.LENGTH_LONG)
                .show();
    }

    public void buttonLoginClicked(View v) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        Log.d("DEBUG", "Botão pressionado");
    }

    public void buttonRegisterClicked(View V){
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
}
