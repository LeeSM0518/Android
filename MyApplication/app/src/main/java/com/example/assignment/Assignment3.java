package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Assignment3 extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton button1, button2, button3;
    Button button;
    ImageView image;

    View.OnClickListener radioListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (radioGroup.getCheckedRadioButtonId()) {
                case R.id.radio0:
                    image.setImageResource(R.drawable.image0);
                    break;
                case R.id.radio1:
                    image.setImageResource(R.drawable.image1);
                    break;
                case R.id.radio2:
                    image.setImageResource(R.drawable.image2);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment3);
        setTitle("Survey");

        radioGroup = findViewById(R.id.radioGroup);
        button1 = findViewById(R.id.radio0);
        button2 = findViewById(R.id.radio1);
        button3 = findViewById(R.id.radio2);

        button1.setOnClickListener(radioListener);
        button2.setOnClickListener(radioListener);
        button3.setOnClickListener(radioListener);

        button = findViewById(R.id.button1);
        image = findViewById(R.id.imageView1);
    }
}
