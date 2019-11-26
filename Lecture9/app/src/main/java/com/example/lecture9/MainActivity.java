package com.example.lecture9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.assignment1Button);
        button2 = findViewById(R.id.assignment2Button);
        button3 = findViewById(R.id.assignment3Button);

        setOnClick(button1, Assignment1.class);
        setOnClick(button2, Assignment2.class);
        setOnClick(button3, Assignment3.class);
    }

    void setOnClick(View view, final Class<?> clazz) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), clazz);
                startActivity(intent);
            }
        });
    }
}
