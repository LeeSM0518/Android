package com.example.lenovo.problem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class message extends AppCompatActivity {

    boolean init;   // EditText의 사이즈 조정을 위한 처음 세팅
    EditText et1;
    TextView tv1;
    int width;      // EditText 전체 너비 구하기 위함

    message() { // 초기화가 되지 않은 상태이므로 init을 false로 저장
        init =false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        // widgets 가져오기
        et1 = (EditText)findViewById(R.id.txtMessage);
        tv1 = (TextView)findViewById(R.id.messageLength);

        // 80 바이트로 제한걸기
        et1.setFilters(new InputFilter[]{new InputFilter.LengthFilter(80)});

        // TextEdit의 글자가 추가됨에 따라 변화하는 메소드
        et1.addTextChangedListener(new myTextWatcher());
    }

    // EditText의 변화에 따른 값
    private class myTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            // 글자수를 가져와 표시한다.
            tv1.setText(s.length() + " / 80 바이트");
        }
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        if(!init) { // 가장 처음에만 세팅
            width = et1.getWidth();     // 전체 너비를 구함
            et1.setTextSize(TypedValue.COMPLEX_UNIT_PX, 100);   // 한글 설정을 위한 테스트
            float korSize = et1.getPaint().measureText("한");   // px 단위의 한글 비율 구하기

            // 한줄에 한글로 8글자가 들어가도록 텍스트 사이즈 조정
            et1.setTextSize(TypedValue.COMPLEX_UNIT_PX, width / 8 / (korSize/100));
            init = true;
            super.onWindowFocusChanged(hasFocus);
        }
    }

    // 전송 클릭 후 메시지를 잠깐 띄우는 메소드
    public void sendButton(View v) {
        Toast.makeText(getApplicationContext(), et1.getText().toString(), Toast.LENGTH_LONG).show();
    }

    // 닫기 클릭 후 메시지를 잠깐 띄우며 뒤로가는 메소드
    public void messageClose(View v) {
        Toast.makeText(getApplicationContext(), "메시지를 닫습니다." , Toast.LENGTH_LONG).show();
        finish();
    }

}