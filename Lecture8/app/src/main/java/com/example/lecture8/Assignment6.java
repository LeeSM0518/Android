package com.example.lecture8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Assignment6 extends AppCompatActivity {

    Button button1, button2;
    TextView textView;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment6);
        textView = findViewById(R.id.countTextView);
        button1 = findViewById(R.id.countUpButton);
        button2 = findViewById(R.id.countDownButton);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                textView.setText("현재 개수 = " + count);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                textView.setText("현재 개수 = " + count);
            }
        });

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count");
            textView.setText("현재 개수 = " + count);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", count);
    }
}
