package com.example.samplelayoutinflater;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout container;     // 리니어 레이아웃 변수를 하나 생성한다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // container 변수를 XML 코드에서 id가 container인 리니어 레이아웃 객체를 참조시킨다.
        container = (LinearLayout) findViewById(R.id.container);

        // button 변수에 XML 코드에서 id가 button2인 버튼을 참조시킨다.
        Button button = (Button) findViewById(R.id.button2);

        // 버튼을 눌렀을 때의 메소드를 익명 객체로 구현
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getSystemService() 메소드를 사용해서 LayoutInflater 객체를 참조한다.
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                // <내부적으로 객체화 과정>
                // 참조된 inflater을 inflate() 메소드를 호출해서 sub1과 container 객체를 파라미터로 전달
                // 이것은 container을 id로 갖는 리니어 레이아웃 객체에 sub1.xml 파일의 레이아웃을 설정하라는 의미이다.
                inflater.inflate(R.layout.sub1, container, true);

                CheckBox checkBox = (CheckBox) container.findViewById(R.id.checkBox);
                checkBox.setText("로딩이되었어요.");
            }
        });
    }
}
