package com.example.samplejavathread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean running;

    Integer value = 0;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼을 누르면 텍스트뷰에 현재 value에 할당된 정수 값을 표시
                textView.setText("스레드에서 받은 값 : " + value.toString());
            }
        });
    }

    protected void onResume() {
        super.onResume();

        running = true;

        // 액티비티가 화면에 보이면 새로 정의한 스레드 시작
        Thread thread1 = new BackgroundThread();
        thread1.start();
    }

    protected void onPause() {
        super.onPause();

        // 액티비티가 멈추면 스레드 중지
        running = false;
        value = 0;
    }

    // Thread 클래스를 상속하여 새로운 스레드 정의
    class BackgroundThread extends Thread {
        public void run() {
            while(running) {
                try {
                    // 스레드 안에서 1초마다 value의 값을 증가시킴
                    Thread.sleep(1000);
                    value++;
                } catch (InterruptedException ex) {
                    Log.e("SampleJavaThread", "Exception in thread.", ex);
                }
            }
        }
    }

}
