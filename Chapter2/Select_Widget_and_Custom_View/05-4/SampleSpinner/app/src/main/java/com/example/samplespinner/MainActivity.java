package com.example.samplespinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    String[] items = { "mike", "angel", "crow", "john", "ginnie", "sally", "cohen", "rice" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 텍스트뷰 객체 참조
        textView = findViewById(R.id.textView);

        // 스피너 객체 참조
        Spinner spinner = findViewById(R.id.spinner);

        // 문자열 배열을 어댑터로 매핑
        // 첫 번째 파라미터 : Context 객체
        // 두 번째 파라미터 : 뷰를 초기화할 때 사용하는 XML
        // (android.R.layout.simple_spinner_dropdown_item : 안드로이드 API에 들어 있는 미리 정의된 레이아웃, 단순 스피너 아이템의 레이아웃)
        // 세 번째 파라미터 : 아이템으로 보일 문자열 데이터들의 배열
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, items);

        // 문자열로만 구성된 아이템들을 스피너로 보여주기 위한 메소드
        // 스피너의 각 아이템들을 보여줄 뷰에 사용되는 레이아웃을 지정
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        // 스피너 객체를 어댑터의 객체로 전달
        spinner.setAdapter(adapter);

        // 아이템 선택 이벤트 처리
        // 스피너 객체가 아이템 선택 이벤트를 처리할 수 있도록 하는 리스너
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            // 아이템이 선택되었을 때 호출됨
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(items[position]);
            }

            // 아무것도 선택되지 않았을 때 호출됨
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                textView.setText("");
            }
        });
    }
}