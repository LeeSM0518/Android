package com.example.sampledialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage();
            }
        });
    }

    private void showMessage() {
        // 대화상자를 만들기 위한 빌더 객체 생성
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 알림 대화상자의 타이틀 설정
        builder.setTitle("안내");
        // 상자의 내용 설정
        builder.setMessage("종료하시겠습니까?");
        // 상자의 아이콘 설정
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        // 예 버튼 추가
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = "예 버튼이 눌렸습니다. ";
                textView.setText(message);
            }
        });

        // 취소 버튼 추가
        builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = "취소 버튼이 눌렸습니다. ";
                textView.setText(message);
            }
        });

        // 아니오 버튼 추가
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = "아니오 버튼이 눌렸습니다. ";
                textView.setText(message);
            }
        });

        // 대화상자 객체 생성 후 보여주기
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
