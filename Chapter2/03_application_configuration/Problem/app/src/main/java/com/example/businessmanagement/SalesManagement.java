package com.example.businessmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SalesManagement extends AppCompatActivity {
    public static final int REQUEST_SALES_ACTIVITY = 102;
    public static final int REQUEST_LOGIN = 105;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_management);
    }

    public void menuButtonClicked(View v) {
        Intent intent = new Intent();
        intent.putExtra("Activity", "SalesActivity");
        setResult(REQUEST_SALES_ACTIVITY, intent);
        finish();
    }

    public void loginButtonClicked(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("Activity", "SalesActivity");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TOP);
        setResult(REQUEST_LOGIN, intent);
        startActivity(intent);
    }
}
