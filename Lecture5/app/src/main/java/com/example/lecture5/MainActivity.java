package com.example.lecture5;

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

        Button assignment1 = findViewById(R.id.assignment1Button);
        setOnClick(assignment1, Assignment1.class);
        Button assignment2 = findViewById(R.id.assignment2Button);
        setOnClick(assignment2, Assignment2.class);
        Button assignment3 = findViewById(R.id.assignment3Button);
        setOnClick(assignment3, Assignment3.class);
        Button assignment4 = findViewById(R.id.assignment4Button);
        setOnClick(assignment4, Assignment4.class);
        Button assignment5 = findViewById(R.id.assignment5Button);
        setOnClick(assignment5, Assignment5.class);
        Button assignment6 = findViewById(R.id.assignment6Button);
        setOnClick(assignment6, Assignment6.class);
        Button assignment7 = findViewById(R.id.assignment7Button);
        setOnClick(assignment7, Assignment7.class);
        Button assignment8 = findViewById(R.id.assignment8Button);
        setOnClick(assignment8, Assignment8.class);
        Button assignment9 = findViewById(R.id.assignment9Button);
        setOnClick(assignment9, Assignment9.class);
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
