package com.example.lecture7;

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

        Button button1, button2, button3, button4, button5, button6, button7;
        button1 = findViewById(R.id.assignment1Button);
        button2 = findViewById(R.id.assignment2Button);
        button3 = findViewById(R.id.assignment3Button);
        button4 = findViewById(R.id.assignment4Button);
        button5 = findViewById(R.id.assignment5Button);
        button6 = findViewById(R.id.assignment6Button);
        button7 = findViewById(R.id.assignment7Button);
        setOnClick(button1, Assignment1.class);
        setOnClick(button2, Assignment2.class);
        setOnClick(button3, Assignment3.class);
        setOnClick(button4, Assignment4.class);
        setOnClick(button5, Assignment5.class);
        setOnClick(button6, Assignment6.class);
        setOnClick(button7, Assignment7.class);
    }

    void setOnClick(Button button, final Class<?> clazz) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), clazz);
                startActivity(intent);
            }
        });
    }
}
