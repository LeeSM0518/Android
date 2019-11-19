package com.example.lecture8;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Assignment3 extends AppCompatActivity {

    static final int GET_STRING = 1;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment3);

        Button button = findViewById(R.id.returnButton);
        textView = findViewById(R.id.text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Assignment3_2.class);
                startActivityForResult(intent, GET_STRING);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == GET_STRING &&
                resultCode == RESULT_OK) {
            textView.setText(data.getStringExtra("INPUT_TEXT"));
        }
    }
}
