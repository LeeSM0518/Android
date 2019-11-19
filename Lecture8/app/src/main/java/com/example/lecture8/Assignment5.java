package com.example.lecture8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class Assignment5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment5);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("LifeCycle", "onStart() 호출");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("LifeCycle", "onResume() 호출");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("LifeCycle", "onPause() 호출");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("LifeCycle", "onStop() 호출");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("LifeCycle", "onDestroy() 호출");
    }
}
