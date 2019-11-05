package com.example.lecture5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Assignment1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Assignment1View view = new Assignment1View(this);
        setContentView(view);
    }
}
