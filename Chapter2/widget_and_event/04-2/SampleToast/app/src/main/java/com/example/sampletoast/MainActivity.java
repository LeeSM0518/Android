package com.example.sampletoast;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    EditText editText2;
    Layout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
    }

    public void onButton1Clicked(View v) {
        try {
            Toast toastView = Toast.makeText(this,
                    "위치가 바뀐 토스트 메시지입니다.", Toast.LENGTH_LONG);

            // x 좌표와 y 좌표를 저장
            int xOffset = Integer.parseInt(editText.getText().toString());
            int yOffset = Integer.parseInt(editText2.getText().toString());

            // setGravity 로 토스트 메시지 좌표 지정
            toastView.setGravity(Gravity.TOP|Gravity.TOP, xOffset, yOffset);
            toastView.show();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void onButton2Clicked(View v) {
        // 레이아웃 인플레이터 객체 참조
        LayoutInflater inflater = getLayoutInflater();

        // 토스트를 위한 레이아웃 인플레이션
        // 토스트만을 위한 레이아웃을 정의한다면, 이 레이아웃은
        // 액티비티를 위한 것이 아니기 때문에 LayoutInflater 객체를
        // 사용해 직접 메모리에 객체화해야 한다.
        View layout = inflater.inflate(
                R.layout.toastborder,
                (ViewGroup) findViewById(R.id.toast_layout_root)
        );

        TextView text = (TextView) layout.findViewById(R.id.text);

        // 토스트 객체 생성
        Toast toast = new Toast(this);
        text.setText("모양 바꾼 토스트");

        toast.setGravity(Gravity.CENTER, 0, -100);
        toast.setDuration(Toast.LENGTH_SHORT);

        // 토스트가 보이는 뷰 설정
        toast.setView(layout);
        toast.show();

    }

    public void onButton3Clicked(View v) {
        Snackbar.make(v, "스낵바입니다.", Snackbar.LENGTH_LONG).show();
    }
}