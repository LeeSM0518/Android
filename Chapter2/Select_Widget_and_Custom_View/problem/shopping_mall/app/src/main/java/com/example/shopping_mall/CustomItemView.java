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
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(
                context.LAYOUT_INFLATER_SERVICE
        );

        inflater.inflate(R.layout.custom_item, this, true);

        imageView = findViewById(R.id.userImage);
        customBirthDate = findViewById(R.id.customBirthDateText);
        customName = findViewById(R.id.customNameText);
        customPhoneNumber = findViewById(R.id.customPhoneNumberText);
    }

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
