package com.example.sampleparcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_MENU = 101;
    public static final String KEY_SIMPLE_DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButton1Clicked(View v) {
        // 인텐트 객체 생성
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);

        // SimpleData 객체 생성
        SimpleData data = new SimpleData(100, "Hello Android!");

        // 인텐트에 부가 데이터로 넣기
        intent.putExtra(KEY_SIMPLE_DATA, data);

        startActivityForResult(intent, REQUEST_CODE_MENU);
    }
}
