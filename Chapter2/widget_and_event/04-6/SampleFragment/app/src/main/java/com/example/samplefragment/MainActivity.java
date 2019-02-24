package com.example.samplefragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    MainFragment mainFragment;
    MenuFragment menuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = (MainFragment)
                getSupportFragmentManager().findFragmentById(R.id.mainFragment);
        menuFragment = new MenuFragment();
    }

    // 프래그먼트 매니저는 프래그먼트를 다루는 작업을 해 주는 객체로
    // 프래그먼트를 추가, 삭제 또는 교체 등의 작업을 할 수 있게 해준다.
    // 그런데 이런 작업들은 프래그먼트를 변경할 때
    // 오류가 생기면 다시 원상태로 돌릴 수 있어야 하므로
    // 트랜잭션 객체를 만들어 실행한다.
    public void onFragmentChanged(int index) {
        if (index == 0) {
            // 트랜잭션 객체는 beginTransaction() 메소드를
            // 호출하면 시작되고 commit() 메소드를 호출하면 실행된다.
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, menuFragment).commit();
        } else if (index == 1) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, mainFragment).commit();
        }
    }
}