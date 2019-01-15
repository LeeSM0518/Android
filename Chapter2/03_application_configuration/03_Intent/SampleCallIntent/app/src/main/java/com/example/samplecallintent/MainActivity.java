package com.example.samplecallintent;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_MENU = 100;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);   // 뷰 객체 참조

        Button button = (Button)findViewById(R.id.button);  // 버튼 객체 참조

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 입력 상자에 입력된 전화번호 확인
                String data = editText.getText().toString();

                // 전화걸기 화면을 보여줄 인텐트 객체 생성
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(data));

                // 액티비티 띄우기
                startActivity(intent);
            }
        });
    }

    public void onButton1Clicked(View v) {
        Intent intent = new Intent();

        ComponentName name = new ComponentName("org.techtown.sampleintent",
                "org.techtown.sampleintent.MainActivity");
        intent.setComponent(name);
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }
}
