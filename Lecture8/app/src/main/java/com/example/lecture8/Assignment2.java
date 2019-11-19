package com.example.lecture8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Assignment2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment2);
    }

    public void myListener1(View view) {
        Intent intent = new Intent(getApplicationContext(), Assignment2_1.class);
        startActivity(intent);
    }

    public void myListener2(View view) {
        Intent intent = new Intent(getApplicationContext(), Assignment2_2.class);
        startActivity(intent);
    }

    public void myListener3(View view) {
        Intent intent = new Intent(getApplicationContext(), Assignment2_3.class);
        startActivity(intent);
    }
}
