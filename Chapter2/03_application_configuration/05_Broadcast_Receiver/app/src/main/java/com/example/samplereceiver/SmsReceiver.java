package com.example.samplereceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {
    public static final String TAG = "SmsReceiver";
    public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    // SMS를 받으면 onReceive() 메소드가 자동으로 호출
    // 파라미터로 전달되는 Intent 객체 안에 SMS 데이터가 들어 있다.
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.i(TAG, "onReceive() 메소드 호출됨.");

        // 인텐트 안에 들어 있는 SMS 메시지를 파싱한다.
        // 인텐트 객체 안에 있는 Bundle 객체를 getExtras() 메소드로 참조한다.
        Bundle bundle = intent.getExtras();

        // parseSmsMessage() 메소드로 SMS 메시지 객체를 만든다.
        SmsMessage[] messages = parseSmsMessage(bundle);

        if (messages != null && messages.length > 0) {
            // SMS 발신 번호 확인
            // 발신자 번호를 확인하기 위해 getOriginaingAddress() 메소드 호출
            String sender = messages[0].getOriginatingAddress();
            Log.i(TAG, "SMS contents : " + sender);

            // SMS 메시지 확인
            // 문자 내용을 확인하기 위해 getMessageBody() 메소드 호출
            String contents = messages[0].getMessageBody();

            Log.i(TAG, "SMS contents : " + contents);

            // SMS 수신 기간 확인
            // 문자를 받은 시각을 확인하기 위해 getTimestampMillis() 메소드 호출
            Date receivedDate = new Date(messages[0].getTimestampMillis());

            Log.i(TAG, "SMS received date : " + receivedDate.toString());

            sendToActivity(context, sender, contents, receivedDate);
        }
    }

    // SmsActivity 로 인텐트를 보내기 위해 만든 메소드
    private void sendToActivity(Context context, String sender, String contents,
                                Date receivedDate) {

        // 메시지를 보여줄 액티비티를 띄운다.
        // Intent 객체를 만들 때 두 번째 파라미터로 SmsActivity.class 객체를
        // 전달했으므로 startActivity() 메소드를 사용해 이 인텐트를
        // 시스템으로 전달하면 시스템이 그 인텐트를 SmsActivity  쪽으로 전달한다.
        Intent myIntent = new Intent(context, SmsActivity.class);

        // 플래그를 이용한다.
        // 브로드캐스트 수신자는 화면이 없으므로 인텐트의 플래그로
        // FLAG_ACTIVITY_NEW_TASK 를 추가해야 한다는 점을 잊지 말자!!
        // 그리고 이미 메모리에 만든 SmsActivity 가 있을 때 추가로 만들지 않도록
        // FLAG_ACTIVITY_SINGLE_TOP 플래그도 추가한다.
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        myIntent.putExtra("sender", sender);
        myIntent.putExtra("contents", contents);
        myIntent.putExtra("receivedDate", format.format(receivedDate));

        context.startActivity(myIntent);
    }

    // SMS 데이터를 확인할 수 있도록 만드는 안드로이드 API에 정해둔 코드
    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];

        int smsCount = objs.length;
        for (int i = 0; i < smsCount; i++) {
            // PDU 포맷으로 되어 있는 메시지를 복원합니다.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String format = bundle.getString("format");

                // SMS 데이터를 확인하기 위해 SmsMessage 클래스의 createFromPdu() 메소드를 사용해
                // SmsMessage 객체로 변환 후 SMS 데이터를 확인할 수 있다.
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i], format);
            } else {
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }
        }

        return messages;
    }
}