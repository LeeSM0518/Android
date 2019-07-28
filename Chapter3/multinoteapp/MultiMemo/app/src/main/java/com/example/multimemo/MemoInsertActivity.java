package com.example.multimemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.multimemo.common.TitleBitmapButton;

public class MemoInsertActivity extends AppCompatActivity {

    private static final String TAG = "MemoInsertActivity";

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_insert);
    }
}