package com.example.lecture6_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Assignment4 extends AppCompatActivity {

    private SingleTouchView singleTouchView;
    private ImageButton imageButton;
    private ImageButton drawButton;
    private ImageButton eraseButton;
    private ImageButton saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment4);
        singleTouchView = findViewById(R.id.drawing2);
        LinearLayout paintLayout = findViewById(R.id.paint_colors2);
        imageButton = (ImageButton) paintLayout.getChildAt(0);

        ImageButton newButton = findViewById(R.id.new_btn2);
        drawButton = findViewById(R.id.draw_btn2);
        eraseButton = findViewById(R.id.erase_btn2);
        saveButton = findViewById(R.id.save_btn2);

        drawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singleTouchView.clear();
            }

        });

        eraseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singleTouchView.setColor("#FFFFFFFF");
                singleTouchView.setPaint(30f);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "저장이 완료되었습니다.",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void clicked2(View view) {
        if (view != imageButton) {
            String color = view.getTag().toString();
            singleTouchView.setColor(color);
            imageButton = (ImageButton) view;
            singleTouchView.setPaint(10f);
        }
    }

}
