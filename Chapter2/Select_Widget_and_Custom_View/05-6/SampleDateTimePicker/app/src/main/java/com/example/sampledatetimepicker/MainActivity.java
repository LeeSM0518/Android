package com.example.sampledatetimepicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy년 MM월 dd일 HH시 mm분"
    );

    TextView textView;
    DateTimePicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        picker = (DateTimePicker) findViewById(R.id.picker);

        // 이벤트 처리
        picker.setOnDateTimeChangedListener(
                new DateTimePicker.OnDateTimeChangedListener() {
                    @Override
                    public void onDateTimeChanged(DateTimePicker view, int year, int monthOfYear, int dayOfYear, int hourOfDay, int minute) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, monthOfYear, dayOfYear, hourOfDay, minute);

                        // 바뀐 시간 텍스트뷰에 표시
                        textView.setText(dateFormat.format(calendar.getTime()));
                    }
                }
        );

        Calendar calendar = Calendar.getInstance();
        calendar.set(picker.getYear(), picker.getMonth(), picker.getDayOfMonth(), picker.getCurrentHour(), picker.getCurrentMinute());
        textView.setText(dateFormat.format(calendar.getTime()));
    }
}
