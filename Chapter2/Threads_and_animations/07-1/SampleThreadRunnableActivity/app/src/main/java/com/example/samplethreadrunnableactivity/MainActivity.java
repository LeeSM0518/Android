package com.example.samplethreadrunnableactivity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ProgressBar progressBar;

    // 핸들러 변수 선언
    Handler handler;

    // 새로 정의한 Runnable 객체의 변수 선언
    ProgressRunnable runnable;

    boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progress);

        // 액티비티가 만들어질 때 핸들러와 Runnable 객체 생성
        handler = new Handler();
        runnable = new ProgressRunnable();
    }

    public void onStart() {
        super.onStart();

        // 스레드 시
        progressBar.setProgress(0);
        // 액티비티가 시작될 때 스레드 시작
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                try {
                    for (int i = 0; i < 20 && isRunning; i++) {
                        Thread.sleep(1000);

                        // 스레드의 결과물을 화면에 표시하기 위해
                        // 핸들러 객체의 post() 메소드를 호출하면서
                        // 새로 생성한 Runnable 객체 전달
                        handler.post(runnable);
                    }
                } catch (Exception ex) {
                    Log.e("SampleThreadActivity", "Exception in processing message.", ex);
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

    // Runnable 인터페이스를 구현하는 새로운 클래스를
    // 정의하고 run() 메소드 안에 실행 코드 정의
    public class ProgressRunnable implements Runnable {
        public void run() {
            // 프로그레스바의 값을 5씩 증
            progressBar.incrementProgressBy(5);

            if (progressBar.getProgress() == progressBar.getMax()) {
                textView.setText("Runnable Done");
            } else {
                textView.setText("Runnable Working ..." + progressBar.getProgress());
            }
        }
    }
}
