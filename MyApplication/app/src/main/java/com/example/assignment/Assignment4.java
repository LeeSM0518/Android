package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Assignment4 extends AppCompatActivity {

    private EditText text;
    private RadioButton celsiusButton;
    private RadioButton fahrenheitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment4);
        text = findViewById(R.id.editText);
        celsiusButton = findViewById(R.id.tempRadioButton1);
        fahrenheitButton = findViewById(R.id.tempRadioButton2);
    }

    public void onClicked(View view) {
        if (text.getText().length() == 0) {
            Toast.makeText(this, "정확한 값을 입력하시오.",
                    Toast.LENGTH_LONG).show();
        } else {
            float inputValue = Float.valueOf(text.getText().toString());
            if (celsiusButton.isChecked()) {
                text.setText(String.valueOf(convertFahrenheitToCelsius(inputValue)));
                celsiusButton.setChecked(false);
                fahrenheitButton.setChecked(true);
            } else if (fahrenheitButton.isChecked()) {
                text.setText(String.valueOf(convertCelsiusToFahrenheit(inputValue)));
                fahrenheitButton.setChecked(false);
                celsiusButton.setChecked(true);
            }
        }
    }

    private double convertFahrenheitToCelsius(float fahrenheit) {
        return ((fahrenheit - 32) * 5.0 / 9.0);
    }

    private double convertCelsiusToFahrenheit(float celsius) {
        return ((celsius * 9) / 5.0) + 32;
    }

}
