package com.example.lecture6_2;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Assignment2 extends AppCompatActivity {

    private Assignment2View assignment2View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment2);
        assignment2View = findViewById(R.id.myview2);
    }

    public void redraw2(View v) {
        assignment2View.invalidate();
    }
}
