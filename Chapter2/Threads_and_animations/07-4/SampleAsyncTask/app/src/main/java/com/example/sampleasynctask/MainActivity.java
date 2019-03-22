package com.example.sampleasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Button executeBtn;
    Button cancelBtn;
    TextView textView;

    // 새로 정의한 BackgroundTask 객체 선언
    BackgroundTask task;
    int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress);
        executeBtn = findViewById(R.id.executeButton);
        cancelBtn = findViewById(R.id.cancelButton);
        textView = findViewById(R.id.textView);

        executeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // [실행] 버튼을 눌렀을 때
                // 새로 정의한 BackgroundTask
                // 객체 생성과 execute() 메소드 실행
                task = new BackgroundTask();
                task.execute(100);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // [취소] 버튼을 눌렀을 때
                // cancel() 메소드 실행
                task.cancel(true);
            }
        });
    }

    // AsyncTask를 상속하여 새로운
    // BackgroundTask 클래스 정의
    // 제네릭
    // 첫 번째 파라미터 : doInBackground 파라미터
    // 두 번째 파라미터 : onProgressUpdate 파라미터
    // 세 번째 파라미터 : onPostExecute 파라미터
    class BackgroundTask extends AsyncTask<Integer, Integer, Integer> {
        protected void onPreExecute() {
            value = 0;
            progressBar.setProgress(value);
        }

        protected Integer doInBackground(Integer ... values) {
            while (isCancelled() == false) {
                value++;
                if (value >= 100) {
                    break;
                } else {
                    // doInBackground() 메소드 내에서
                    // publishProgress() 메소드 호출
                    // UI 업데이트
                    publishProgress(value);
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {}
            }
            return value;
        }

        // onProgressUpdate() 메소드 내에서
        // 프로그래스바의 텍스트뷰 변경
        protected void onProgressUpdate(Integer ... values) {
            progressBar.setProgress(values[0].intValue());
            textView.setText("Current Value : " + values[0].toString());
        }

        protected void onPostExecute(Integer result) {
            progressBar.setProgress(0);
            textView.setText("Finished.");
        }

        protected void onCancelled() {
            progressBar.setProgress(0);
            textView.setText("Cancelled.");
        }

    }
}
