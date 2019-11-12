package com.example.lecture6_2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Assignment3 extends AppCompatActivity {

    private SingleTouchView singleTouchView;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment3);
        singleTouchView = findViewById(R.id.drawing);
        LinearLayout paintLayout = findViewById(R.id.paint_colors);
        imageButton = (ImageButton) paintLayout.getChildAt(0);
    }

    public void clicked(View view) {
        if (view != imageButton) {
            String color = view.getTag().toString();
            singleTouchView.setColor(color);
            imageButton = (ImageButton) view;
            singleTouchView.setPaint(10f);
        }
    }
}
