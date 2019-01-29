package com.example.sampleevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    GestureDetector detector;   // 제스처 디텍터 객체 선언

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 하드웨어의 뒤로가기 버튼 클릭시 실행되는 메소드
        if(keyCode == event.KEYCODE_BACK) {
            Toast.makeText(this, "시스템 [BACK] 버튼이 눌렸습니다.",
                    Toast.LENGTH_LONG).show();

            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        // 뷰 객체 참조
        View view = findViewById(R.id.view);

        // 리스너 등록
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // MotionEvent 객체에는 액션 정보나 터치한 곳의 좌표가 있다.

                int action = event.getAction(); // getAction() 메소드로 액션 정보 요청

                float curX = event.getX();
                float curY = event.getY();

                if (action == MotionEvent.ACTION_DOWN) {
                    // 손가락이 눌렀을 때
                    println("손가락 눌림 : " + curX + ", " + curY);
                } else if (action == MotionEvent.ACTION_MOVE) {
                    // 손가락이 눌린 상태로 움직일 때
                    println("손가락 움직임 : " + curX + ", " + curY);
                } else if (action == MotionEvent.ACTION_UP) {
                    // 손가락이 떼졌을 때
                    println("손가락 뗌 : " + curX + ", " + curY);
                }

                return true;
            }
        });

        detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                println("onDown() 호출됨");

                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                println("onShowPress() 호출됨");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                println("onSingleTapUp() 호출됨.");

                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                println("onScroll() 호출됨 : " + distanceX + ", " + distanceY);

                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                println("onLongPress() 호출됨.");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                println("onFling() 호출됨 : " + velocityX + ", " + velocityY);

                return true;
            }
        });

        View view2 = findViewById(R.id.view2);
        // 뷰를 터치했을 때 발생하는 터치 이벤트를 제스처 디텍터로 전달
        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // onTouchEvent() 메소드를 호출하면서 MotionEvent 객체를 전달
                // GestureDetector 객체가 터치 이벤트를 처리한 후 GestureDetector 객체에
                // 정의된 메소드를 호출한다.
                detector.onTouchEvent(event);
                return true;
            }
        });
    }


    public void println(String data) {
        textView.append(data + "\n");
    }
}
