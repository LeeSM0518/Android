package com.example.lenovo.seminarproject;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButton1Clicked(View v) {
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hanbat.ac.kr/html/kr/"));
        startActivity(myIntent);
    }
    public void onButton2Clicked(View v) {
        Intent myIntent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(myIntent);
    }
    public void onButton3Clicked(View v) {
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hanbat.ac.kr/_prog/gboard/board.php?code=news"));
        startActivity(myIntent);
    }

    public void onButton8Clicked(View v) {
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://my.hanbat.ac.kr/user/login.face"));
        startActivity(myIntent);
    }

    public void onButton9Clicked(View v) {
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sugang.hanbat.ac.kr/sugang/sugang/doLogin.do"));
        startActivity(myIntent);
    }

    public void onButton10Clicked(View v) {
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hanbat.ac.kr/html/kr/cyber/cyber_02_01.html"));
        startActivity(myIntent);
    }

    public void onButton11Clicked(View v) {
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://cyber.hanbat.ac.kr/"));
        startActivity(myIntent);
    }
}