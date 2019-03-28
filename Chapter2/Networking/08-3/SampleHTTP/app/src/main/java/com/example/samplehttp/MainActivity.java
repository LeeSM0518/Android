package com.example.samplehttp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public static String defaultUrl = "http://m.naver.com";
    Handler handler = new Handler();

    EditText editAddress;
    Button requestButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editAddress = findViewById(R.id.editAddressText);
        requestButton = findViewById(R.id.requestButton);
        textView = findViewById(R.id.txtMsg);

        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 입력상자에 입력된 URL 문자열 참조
                String urlStr = editAddress.getText().toString();

                // 웹으로 요청하기 위한 새로 정의한 스레드 객체 생성 및 시작
                ConnectThread thread = new ConnectThread(urlStr);
                thread.start();
            }
        });
    }

    class ConnectThread extends Thread {

        String urlStr;

        public ConnectThread(String urlStr) {
            this.urlStr = urlStr;
        }

        @Override
        public void run() {
            try {
                // 새로 정의한 request() 메소드 호출
                final String output = request(urlStr);

                // URL 구성요소 중의 하나인 텍스트뷰에
                // 보여주어야 하므로 핸들러 객체의
                // post 메소드를 호출
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(output);
                    }
                });
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        private String request(String urlStr) {
            StringBuilder output = new StringBuilder();

            try {
                // URL 문자열을 이용해 URL 객체 생성
                URL url = new URL(urlStr);

                // URL 객체를 이용해 HttpURLConnection 객체 생성
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                if (conn != null) {
                    // 연결 대기 시간을 10초로 설정
                    conn.setConnectTimeout(10000);
                    // GET 방식으로 요청한다는 내용을 설정
                    conn.setRequestMethod("GET");
                    // 객체의 입력과 출력이 가능하도록 만들어준다.
                    conn.setDoInput(true);
                    conn.setDoOutput(true);

                    // 응답 결과를 읽기 위한 스트림 객체 생성
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    String line = null;

                    while (true) {
                        // 반복문 안에서 한 줄씩 읽어 결과 문자열에 추가
                        line = reader.readLine();
                        if (line == null) {
                            break;
                        }
                        output.append(line + "\n");
                    }
                    reader.close();
                    conn.disconnect();
                }

            } catch (Exception ex) {
                Log.e("SampleHTTP", "Exception in processing response.", ex);
            }
            return output.toString();
        }
    }
}
