package com.example.lecture3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main9Activity extends AppCompatActivity {

    String numbers = "";
    List<Button> buttonList = new ArrayList<>();
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button button0 = findViewById(R.id.button0);

        editText = findViewById(R.id.editText);

        buttonList = Arrays.asList(button0, button1, button2, button3, button4,
                button5, button6, button7, button8, button9);

        for (Button button : buttonList) {
            setButtonListener(button);
        }

        Button okButton = findViewById(R.id.okButton);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), numbers,
                        Toast.LENGTH_LONG).show();
            }
        });

        Button clearButton = findViewById(R.id.buttonClear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers = "";
                editText.setText(numbers);
            }
        });
    }

    public void setButtonListener(final Button button) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers += button.getText();
                editText.setText(numbers);
            }
        };
        button.setOnClickListener(listener);
    }

}
