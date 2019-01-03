package com.example.lenovo.samplelinearlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);     // 이 부분이 activity_main.xml 파일을 파라미터로 전달하면 이 레이아웃 파일이 액티비티에 설정된다.
    }
}