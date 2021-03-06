package com.example.lenovo.samplelinearlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class LayoutCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_code);

        // new 연산자로 리니어 레이아웃을 만들고 방향 설정
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        // new 연산자로 레이아웃 안에 추가될 뷰들에 설정할 파라미터 생성
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );


        // 버튼에 파라미터 설정하고 레이아웃 추가
        Button button01 = new Button(this);
        button01.setText("Button 01");
        button01.setLayoutParams(params);
        mainLayout.addView(button01);

        // 새로 만든 레이아웃을 화면에 설정
        setContentView(mainLayout);
    }
}