package com.example.businessmanagement;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    public static final int REQUEST_CUSTOM_ACTIVITY = 100;
    public static final int REQUEST_MAIN_ACTIVITY = 103;
    public static final int REQUEST_PRODUCT_ACTIVITY = 101;
    public static final int REQUEST_SALES_ACTIVITY = 102;
    public static final int REQUEST_LOGIN = 105;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == REQUEST_CUSTOM_ACTIVITY) {
            Toast.makeText(getApplicationContext(), data.getStringExtra("Activity") + " 로 부터 화면전환됨.", Toast.LENGTH_LONG).show();
        }

        if (resultCode == REQUEST_PRODUCT_ACTIVITY) {
            Toast.makeText(getApplicationContext(), data.getStringExtra("Activity") + " 로 부터 화면전환됨.", Toast.LENGTH_LONG).show();
        }
        if (resultCode == REQUEST_SALES_ACTIVITY) {
            Toast.makeText(getApplicationContext(), data.getStringExtra("Activity") + " 로 부터 화면전환됨.", Toast.LENGTH_LONG).show();
        }
    }

    public void customButtonClicked(View v) {
        Intent customIntent = new Intent(getApplicationContext(), CustomerManagement.class);
        customIntent.putExtra("Activity", "MenuActivity");
        startActivityForResult(customIntent, REQUEST_CUSTOM_ACTIVITY );
    }

    public void productButtonClicked(View v) {
        Intent productIntent = new Intent(getApplicationContext(), ProductManagement.class);
        productIntent.putExtra("Activity", "MenuActivity");
        startActivityForResult(productIntent, REQUEST_PRODUCT_ACTIVITY);
    }

    public void salesButtonClicked(View v) {
        Intent salesIntent = new Intent(getApplicationContext(), SalesManagement.class);
        salesIntent.putExtra("Activity", "MenuActivity");
        startActivityForResult(salesIntent, REQUEST_SALES_ACTIVITY);
    }

    public void loginButtonClicked(View v) {
        Intent loginIntent = new Intent(getApplicationContext(), MainActivity.class);
        loginIntent.putExtra("Activity", "MenuActivity");
        setResult(REQUEST_LOGIN, loginIntent);
        finish();
    }
}
