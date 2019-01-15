package com.example.sampleintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button = (Button)findViewById(R.id.button);      // 버튼 객체 참조

        // 버튼 익명 객체 구현
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();                   // 인텐트 객체 생성

                // name의 값을 부가 데이터로 넣기, 키와 데이터 값을 쌍으로 넣어주어야 한다.
                // 다시 확인하고자 할 경우에는 키를 이용해 데이터 값을 가져올 수 있다.
                intent.putExtra("name", "mike");

                // 응답 보내기, 새로 띄운 액티비티에서 이전 액티비티로 인텐트를 전달하고 싶을 때 사용되는 메소드
                // 형식, setResult(응답 코드, 인텐트)
                setResult(RESULT_OK, intent);

                finish();   // 현재 액티비티 없애기
            }
        });
    }
}
