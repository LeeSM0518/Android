package com.example.lecture6_2;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Assignment1 extends AppCompatActivity {

    private Assignment1View assignment1View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment1);
        assignment1View = findViewById(R.id.myview);
    }


    public void redraw(View v) {
        assignment1View.invalidate();
    }
}
