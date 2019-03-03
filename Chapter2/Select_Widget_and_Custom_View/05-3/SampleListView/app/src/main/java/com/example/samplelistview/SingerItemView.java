package com.example.samplelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

// SingerItemVIew 클래스는 리니어 레이아웃을 상속하므로 다른 뷰들을 포함할 수 있다.
public class SingerItemView extends LinearLayout {
    TextView textView;
    TextView textView2;
    TextView textView3;
    ImageView imageView;

    // Context 객체와 SingerItemView 객체를 파라미터로 전달받는다.
    public SingerItemView(Context context) {
        super(context);
        init(context);
    }

    public void init(Context context) {
        // XML 레이아웃의 정보를 객체화하기 위해 LayoutInflator 객체를 참조
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        // 인플레이트
        inflater.inflate(R.layout.singer_item, this, true);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        imageView = findViewById(R.id.imageView);
    }

    public void setName(String name) {
        textView.setText(name);
    }

    public void setMobile(String mobile) {
        textView2.setText(mobile);
    }

    public void setAge(int age) {
        textView3.setText(String.valueOf(age));
    }

    public void setImageView(int resId) {
        imageView.setImageResource(resId);
    }
}