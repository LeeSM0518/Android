package com.example.lecture6_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1, button2, button3, button4;
        button1 = findViewById(R.id.assignment1Button);
        button2 = findViewById(R.id.assignment2Button);
        button3 = findViewById(R.id.assignment3Button);
        button4 = findViewById(R.id.assignment4Button);
        setOnClickMethod(button1, Assignment1.class);
        setOnClickMethod(button2, Assignment2.class);
        setOnClickMethod(button3, Assignment3.class);
        setOnClickMethod(button4, Assignment4.class);
    }

    void setOnClickMethod(Button button, final Class<?> clazz) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), clazz);
                startActivity(intent);
            }
        });
    }
}
