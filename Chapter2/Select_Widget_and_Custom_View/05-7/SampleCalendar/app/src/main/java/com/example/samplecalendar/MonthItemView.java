package com.example.samplecalendar;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class MonthItemView extends AppCompatTextView {

    private MonthItem item;

    public MonthItemView(Context context) {
        super(context);

        init();
    }

    public MonthItemView(Context context,@Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        setBackgroundColor(Color.WHITE);
    }

    public MonthItem getItem() {
        return item;
    }

    public void setItem(MonthItem item) {
        this.item = item;

        int day = item.getDay();
        if (day != 0) {
            setText(String.valueOf(day));
        } else {
            setText("");
        }
    }
}
