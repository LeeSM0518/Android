package com.example.lenovo.hello;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Button을 클릭했을 때 처리해줄 이벤트(메소드)
    // Toast = 잠깐 보였다 없어지는 메시지
    // makeText() 메소드와 show() 메소드를 이용해서 메시지 출력

    // 버튼을 눌렀을 때 시작 버튼이 눌렸어요 라는 메시지를 잠깐 띄운다.
    //public void onButton1Clicked(View v){
    //    Toast.makeText(getApplicationContext(), "시작 버튼이 눌렸어요.", Toast.LENGTH_LONG).show();
    //}

    // Intent를 이용해 애플리케이션 구성 요소 간에 데이터를 전달하거나 실행하려는 기능이 무엇인지 시스템에 알려준다.

    public void onButton1Clicked(View v){
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.hanbat.ac.kr"));
        startActivity(myIntent);
    }

    public void onButton2Clicked(View v){
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1000-1000"));
        startActivity(myIntent);
    }

    // MenuActivity.class 라는 것이 인텐트의 파라미터로 전달
    // 새로 만든 액티비티 클래스의 이름을 지정해서 띄워주는 메소드
    public void onButton3Clicked(View v){
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
    }
}
