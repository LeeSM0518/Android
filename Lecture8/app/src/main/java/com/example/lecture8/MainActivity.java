package com.example.lecture8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Animatable2;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<View> buttons = Arrays.asList(
                findViewById(R.id.assignment1Button),
                findViewById(R.id.assignment2Button),
                findViewById(R.id.assignment3Button),
                findViewById(R.id.assignment4Button),
                findViewById(R.id.assignment5Button),
                findViewById(R.id.assignment6Button));
        List<Class<? extends AppCompatActivity>> classes = Arrays.asList(
                Assignment1.class,
                Assignment2.class,
                Assignment3.class,
                Assignment4.class,
                Assignment5.class,
                Assignment6.class);

        for (int i = 0; i < buttons.size(); i++) {
            setOnclick(buttons.get(i), classes.get(i));
        }
    }

    void setOnclick(View view, final Class<?> clazz) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), clazz);
                startActivity(intent);
            }
        });
    }
}
