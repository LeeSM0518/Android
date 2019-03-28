package com.example.sampleandroidsocket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        button = findViewById(R.id.connectButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addr = editText.getText().toString().trim();

                ConnectThread thread = new ConnectThread(addr);
                thread.start();
            }
        });
    }

    class ConnectThread extends Thread {
        String hostname;

        public ConnectThread(String addr) {
            hostname = addr;
        }

        public void run() {
            try {
                int port = 11001;
                // [표준 자바와 동일] 소켓 연결을 위한
                // Socket 객체 생성
                Socket sock = new Socket(hostname, port);

                // [표준 자바와 동일] 데이터를 쓰기 위한
                // 스트림 객체를 만들고 데이터 쓰기
                ObjectOutputStream outStream = new ObjectOutputStream(sock.getOutputStream());
                outStream.writeObject("Hello AndroidTown on Android");
                outStream.flush();

                // [표준 자바와 동일] 데이터를 읽기 위한 스트림
                // 객체를 만들고 데이터 읽기
                ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());
                String obj = (String) instream.readObject();

                // 소켓에서 읽은 데이터를 로그로 출력하기
                Log.d("MainActivity", "서버에서 받은 메시지 : " + obj);

                Toast.makeText(getApplicationContext(), "\"서버에서 받은 메시지 : \" + obj", Toast.LENGTH_SHORT).show();

                sock.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}