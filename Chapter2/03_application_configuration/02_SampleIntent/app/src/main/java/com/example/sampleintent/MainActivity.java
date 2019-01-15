package com.example.sampleintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 다른 액티비티를 띄우기 위한 요청코드 정의
    public static final int REQUEST_CODE_MENU = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    // 이 메소드의 첫 번째 파라미터는 액티비티 요청 코드이다. 즉, 어떤 액티비티로부터 응답을 받은 것인지 구분할 때 사용된다.
    // 두 번째 파라미터는 응답을 보내 온 액티비티로부터 전달된 응답 코드이다. 보통 Activity.RESULT_OK 상수로 정상 처리 됨을 알려준다.
    // 또한 임의로 만든 코드를 전달할 수도 있다.
    // 세 번째 파라미터는 새로 띄웠던 메뉴 액티비티로 부터 전달 받은 인텐트이다. 이 인텐트 객체는 메뉴 액티비티로 부터
    // 메인 액티비티로 데이터를 전달할 목적으로 사용된다.
    // 이 메소드는 새로 띄웟던 메뉴 액티비티가 응답을 보내오면 그 응답을 처리하는 역할을 한다.
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_MENU) {
            Toast.makeText(getApplicationContext(), "onActivityResult 메소드 호출됨. 요청 코드 : " + requestCode + ", 결과 코드 : "
                    + resultCode, Toast.LENGTH_LONG).show();

            if (requestCode == RESULT_OK) {
                String name = data.getExtras().getString("name");
                Toast.makeText(getApplicationContext(), "응답으로 전달된 name : " + name, Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onButton1Clicked(View v) {
        // 또 다른 액티비티를 띄우기 위한 인텐트 객체 생성
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);

        // 액티비티 띄우기, 액티비티를 띄울 목적으로 사용될 수도 있고, 액티비티 간에 데이터를 전달하는 데에도 사용될 수 있다.
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }
}
