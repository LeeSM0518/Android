package com.example.sampleservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public static final String TAG = "MyService";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // Log.d() 메소드를 통해서 로그를 출력시킨다.
    // Log의 첫 번째 파라미터로 태그 문자열(로그를 구별하는 역할)을 전달해야 한다.
    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate() 호출됨.");
    }

    @Override
    // 인텐트 객체를 전달 받는 중요한 메소드, 시스템에 의해 자동으로 다시 시작된다.
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() 호출됨");

        // 인텐트 객체가 null 인지 먼저 체크
        if (intent == null) {
            // 이 값을 반환하면 서비스가 비정상 종료되었을 때 시스템이 자동으로 재시작한다.
            return Service.START_STICKY;
        } else {
            // 코드를 너무 많이 넣으면 복잡하므로
            // 메소드를 새롭게 정의한 후 호출한다.
            processCommand(intent);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void processCommand(Intent intent) {
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");

        Log.d(TAG, "command : " + command + ", name : " + name );

        // 5초 동안 1초에 한 번씩 로그를 출력시킨다.
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) { };

            Log.d(TAG, "Waiting " + i + " seconds.");
        }

        // 인텐트 객체를 new 연산자로 생성
        // 첫 번째 파라미터로 getApplicationContext() 메소드 호출하여 Context 객체 전달
        // 두 번째 파라미터로 MainActivity.class 객체 전달
        Intent showIntent = new Intent(getApplicationContext(), MainActivity.class);

        // 인텐트 객체에 Flags 추가
        // 새로운 태스크를 생성하도록 FLAG_ACTIVITY_NEW_TASK 추가
        // 객체가 이미 만들어져 있을 때 재사용하도록 FLAG_ACTIVITY_SINGLE_TOP 과
        // FLAG_ACTIVITY_CLEAR_TOP 플래그 추가
        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_SINGLE_TOP |
                intent.FLAG_ACTIVITY_CLEAR_TOP);

        // 인텐트 객체에 부가 데이트 추가
        showIntent.putExtra("command", "show");
        showIntent.putExtra("name", name + " from service.");
        startActivity(showIntent);
    }
}
