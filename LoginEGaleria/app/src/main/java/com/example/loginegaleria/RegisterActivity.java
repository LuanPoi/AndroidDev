package com.example.loginegaleria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    EditText editTextLoginInput;
    EditText editTextPasswordInput;
    EditText editTextConfirmPasswordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextLoginInput = findViewById(R.id.editTextLoginInput);
        editTextPasswordInput = findViewById(R.id.editTextPasswordInput);
        editTextConfirmPasswordInput =findViewById(R.id.editTextConfirmPasswordInput);
    }

    public void buttonRegisterClicked(View v){
        if(editTextLoginInput.getText().toString().isEmpty() || editTextPasswordInput.getText().toString().isEmpty() || editTextConfirmPasswordInput.getText().toString().isEmpty()){
            return;
        }

        if(!editTextPasswordInput.getText().toString().contentEquals(editTextConfirmPasswordInput.getText().toString())){
            sendAlert(1);
            return;
        }

        for (User user:
                Model.getInstance().registeredUsers) {
            if(user.getUsername().contentEquals(editTextLoginInput.getText().toString())){
                return;
            }
        }

        Model.getInstance().registeredUsers.add(
                new User(
                        editTextLoginInput.getText().toString(),
                        Long.parseLong(editTextPasswordInput.getText().toString())
                )
        );
        editTextLoginInput.setText("");
        editTextPasswordInput.setText("");
        editTextConfirmPasswordInput.setText("");
        sendAlert(2);
    }

    private void sendAlert(int alertCode){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Alert");
        switch (alertCode){
            case 1:
                alert.setMessage("The Password and Confirm Password fields aren't equal.");
                alert.setPositiveButton(android.R.string.ok, null);
                break;
            case 2:
                alert.setMessage("Registered!");
                alert.setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            }
                        });
                break;
        }



        AlertDialog dialog = alert.create();
        dialog.show();
    }
}
