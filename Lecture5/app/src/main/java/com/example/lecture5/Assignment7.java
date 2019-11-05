package com.example.lecture5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Assignment7 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Assignment7View(this));
    }
}
