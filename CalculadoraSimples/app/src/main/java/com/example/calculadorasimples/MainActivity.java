package com.example.calculadorasimples;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TextView titleLabel;
    TextView firstNumberLabel;
    TextView secondNumberLabel;
    TextView resultLabel;
    TextView resultValueLabel;
    EditText firstNumberInput;
    EditText secondNumberInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleLabel = findViewById(R.id.textViewTitleLabel);
        firstNumberLabel = findViewById(R.id.textViewFirstNumberLabel);
        secondNumberLabel = findViewById(R.id.textViewSecondNumberLabel);
        resultLabel = findViewById(R.id.textViewResultLabel);
        resultValueLabel = findViewById(R.id.textViewResultValueLabel);
        firstNumberInput = findViewById(R.id.editTextFirstNumberInput);
        secondNumberInput = findViewById(R.id.editTextSecondNumberInput);

        resultValueLabel.setText("");
    }

    public void plusButtonPressed(View v){
        executeButton(1);
    }

    public void minusButtonPressed(View v){
        executeButton(2);
    }

    public void timesButtonPressed(View v){
        executeButton(3);
    }

    public void dividedButtonPressed(View v){
        executeButton(4);
    }

    private boolean inputRequirementsSatisfied(int operation){
        if ((firstNumberInput.getText().toString().isEmpty()) || (secondNumberInput.getText().toString().isEmpty())){
            return false;
        }
        switch(operation){
            case 1:
                return true;
            case 2:
                return true;
            case 3:
                return true;
            case 4:
                if(secondNumberInput.getText().toString().equals("0")){
                    sendAlert(1);
                    return false;
                }
                return true;
            default:
                return false;
        }
    }

    private Double calculate(Editable firstValue, Editable secondValue, int operation){
        double valueOne = Double.parseDouble(firstValue.toString());
        double valueTwo = Double.parseDouble(secondValue.toString());

        switch(operation){
            case 1:
                return valueOne + valueTwo;
            case 2:
                return valueOne - valueTwo;
            case 3:
                return valueOne * valueTwo;
            case 4:
                return valueOne / valueTwo;
            default:
                return null;
        }
    }

    private void executeButton(int operation){
        if(!inputRequirementsSatisfied(operation)){
            return;
        }
        Double result = calculate((firstNumberInput.getText()), secondNumberInput.getText(), operation);
        resultValueLabel.setText(result.toString());
        if(result != null) {
            firstNumberInput.setText(result.toString());
            secondNumberInput.setText("");
        }
    }

    private void sendAlert(int errorCode){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Operação inválida:");

        alert.setMessage((errorCode == 1)? "Divisão por zero inválida." : "");

        alert.setPositiveButton(android.R.string.yes, null);

        AlertDialog dialog = alert.create();
        dialog.show();
    }
}
