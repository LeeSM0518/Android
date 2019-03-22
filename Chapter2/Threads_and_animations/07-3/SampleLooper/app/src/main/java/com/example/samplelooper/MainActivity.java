package com.example.samplelooper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText mainText;
    EditText threadText;
    Button button;

    MainHandler mainHandler;
    ProcessThread thread1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainText = findViewById(R.id.mainEdit);
        threadText = findViewById(R.id.ThreadEdit);
        button = findViewById(R.id.button);

        mainHandler = new MainHandler();

        // 새로 정의한 스레드 객체 생성
        thread1 = new ProcessThread();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inStr = mainText.getText().toString();
                Message msgToSend = Message.obtain();
                msgToSend.obj = inStr;

                // 버튼을 눌렀을 때 스레드 내의 핸들러로 메시지 전송
                thread1.handler.sendMessage(msgToSend);
            }
        });

        thread1.start();
    }

    class ProcessThread extends Thread {
        // 스레드 내에 선언된 핸들러 객체
        ProcessHandler handler;

        public ProcessThread() {
            handler = new ProcessHandler();
        }

        // 스레드의 run() 메소드 안에서 루퍼 실행
        public void run() {
            Looper.prepare();
            Looper.loop();
        }
    }

    class ProcessHandler extends Handler {
        public void handleMessage(Message msg) {
            Message resultMsg = Message.obtain();
            resultMsg.obj = msg.obj + " Mike!!!";

            // 스레드 내의 핸들러에서 메인 스레드와
            // 핸들러로 메시지 전송
            mainHandler.sendMessage(resultMsg);
        }
    }

    class MainHandler extends Handler {
        public void handleMessage(Message msg) {
            String str = (String) msg.obj;

            // 메인 스레드의 핸들러 내에서 입력상자의 메시지 표시
            threadText.setText(str);
        }
    }
}
