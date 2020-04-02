package com.example.loginegaleria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText editTextLoginInput;
    EditText editTextPasswordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextLoginInput = findViewById(R.id.editTextLoginInput);
        editTextPasswordInput = findViewById(R.id.editTextPasswordInput);
    }

    public void buttonSignInClicked(View v) {
        if(editTextLoginInput.getText().toString().isEmpty() || editTextPasswordInput.getText().toString().isEmpty()){
            return;
        }
        if(Model.getInstance().login(editTextLoginInput.getText().toString(), Long.parseLong(editTextPasswordInput.getText().toString()))){
            startActivity(new Intent(LoginActivity.this, GalleryActivity.class));
        }else {
            sendAlert(1);
            return;
        }
    }

    public void buttonRegisterClicked(View V){
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    private void sendAlert(int errorCode){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Alert");

        alert.setMessage((errorCode == 1)? "Login or Password are incorrect." : "");

        alert.setPositiveButton(android.R.string.ok, null);

        AlertDialog dialog = alert.create();
        dialog.show();
    }
}
