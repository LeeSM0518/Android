package com.example.lecture3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Main6Activity extends AppCompatActivity {

    Button b1;
    Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        final LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        b1 = new Button(this);
        b1.setText("첫번째 버튼");
        linearLayout.addView(b1);

        b2 = new Button(this);
        b2.setText("두번째 버튼");
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setText("코드에서도 변경 가능");
                b2.setEnabled(false);
            }
        });
        linearLayout.addView(b2);

        setContentView(linearLayout);
    }
}
