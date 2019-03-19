package com.example.shopping_mall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

// CustomItemView 클래스가 리니어 레이아웃을 상속하므로
// 다른 뷰들을 포함할 수 있다.
public class CustomItemView extends LinearLayout {
    ImageView imageView;
    TextView customName;
    TextView customBirthDate;
    TextView customPhoneNumber;

    public CustomItemView(Context context) {
        super(context);

        init(context);
    }

    public void init(Context context) {
        // XML 레이아웃의 정보를 객체화하기 위해 LayoutInflator 객체 참조
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(
                context.LAYOUT_INFLATER_SERVICE
        );

        // 인플레이트
        inflater.inflate(R.layout.custom_item, this, true);

        // 객체 참조
        imageView = findViewById(R.id.userImage);
        customBirthDate = findViewById(R.id.customBirthDateText);
        customName = findViewById(R.id.customNameText);
        customPhoneNumber = findViewById(R.id.customPhoneNumberText);
    }

    // custom_item 레이아웃의 ViewSet 메소드
    public void setCustomName(String name) {
        customName.setText(name);
    }

    public void setCustomBirthDate(String birth) {
        customBirthDate.setText(birth);
    }

    public void setCustomPhoneNumber(String number) {
        customPhoneNumber.setText(number);
    }

}
