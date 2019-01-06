package com.example.lenovo.problem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class message extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
    }

    public void onButton3Clicked(View v) {
        Toast.makeText(getApplicationContext(), "돌아가기" ,Toast.LENGTH_LONG).show();
        finish();
    }
}
