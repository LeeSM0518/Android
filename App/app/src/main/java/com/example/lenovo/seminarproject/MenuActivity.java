package com.example.lenovo.seminarproject;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onButton4Clicked(View v) {
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://newclass.hanbat.ac.kr/ctnt/ice/"));
        startActivity(myIntent);
    }

    public void onButton5Clicked(View v) {
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://newclass.hanbat.ac.kr/ctnt/ice/cms.php?mno=02.05"));
        startActivity(myIntent);
    }

    public void onButton6Clicked(View v) {
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://newclass.hanbat.ac.kr/ctnt/ice/board.php?code=ice_notice&mno=05.01&mode=list"));
        startActivity(myIntent);
    }

    public void onButton7Clicked(View v) {
        Toast.makeText(getApplicationContext(), "돌아가기 버튼을 눌렀어요.", Toast.LENGTH_LONG).show();
        finish();
    }
}