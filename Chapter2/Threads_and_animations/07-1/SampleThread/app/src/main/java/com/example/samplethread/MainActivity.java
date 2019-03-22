package com.example.samplethread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ProgressBar progressBar;

    // 새로 정의한 핸들러의 변수 선언
    ProgressHandler handler;

    boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progress);

        // 액티비티가 만들어질 때 핸들러 객체 생성
        handler = new ProgressHandler();
    }

    public void onStart() {
        super.onStart();

        // 프로그레스바 값 설정
        progressBar.setProgress(0);

        // 액티비티가 시작될 때 스레드를 만들어 시작
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 20 && isRunning; i++) {
                        // 핸들러로 메시지 전송
                        // 스레드 안에서 필요한 경우 작업 상태나
                        // 결과를 핸들러의 sendMessage() 메소드로 전송
                        Thread.sleep(1000);
                        Message msg = handler.obtainMessage();
                        handler.sendMessage(msg);
                    }
                } catch (Exception ex) {
                    Log.e("MainActivity", "Exception in processing message", ex);
                }
            }
        });

        isRunning = true;
        thread1.start();
    }

    public void onStop() {
        super.onStop();

        isRunning = false;
    }

    // Handler 클래스를 상속하여 새로운 핸들러 클래스 정의
    public class ProgressHandler extends Handler {
        // 전달된 메시지를 처리하는 handleMessage() 메소드 안에서
        // 프로그레스바의 값 업데이트
        public void handleMessage(Message msg) {
            progressBar.incrementProgressBy(5);
            if (progressBar.getProgress() == progressBar.getMax()) {
                textView.setText("Done");
            } else {
                textView.setText("Working ..." + progressBar.getProgress());
            }
        }
    }
}
