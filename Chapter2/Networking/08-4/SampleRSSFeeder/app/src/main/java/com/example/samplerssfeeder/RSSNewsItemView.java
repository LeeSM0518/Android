package com.example.samplerssfeeder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.nio.channels.IllegalChannelGroupException;

public class RSSNewsItemView extends LinearLayout {

    private ImageView mIcon;
    private TextView mText01;
    private TextView mText02;
    private TextView mText03;
    private WebView mText04;

    public RSSNewsItemView(Context context, RSSNewsItem aItem) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.listitem, this, true);

        mIcon = findViewById(R.id.iconItem);
        mIcon.setImageDrawable(aItem.getIcon());

        mText01 = (TextView) findViewById(R.id.dataItem01);
        mText01.setText(aItem.getTitle());

        mText02 = (TextView) findViewById(R.id.dataItem02);
        mText02.setText(aItem.getPubDate());

        mText03 = (TextView) findViewById(R.id.dataItem03);
        String category = aItem.getCategory();
        if (category != null) {
            mText03.setText(category);
        }

        mText04 = (WebView) findViewById(R.id.dataItem04);

        setTextToWebView(aItem.getDescription());
    }

    public void setText(int index, String data) {
        if (index == 0) {
            mText01.setText(data);
        } else if (index == 1) {
            mText02.setText(data);
        } else if (index == 2) {
            mText03.setText(data);
        } else if (index == 3) {
            setTextToWebView(data);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void setTextToWebView(String msg) {
        Log.d("RSSNewsItemView", "setTextWebView() called.");

        String outData = msg;

        outData = outData.replace("\"//", "\"http://");

        mText04.loadDataWithBaseURL("http://localhost/", outData, "text/html", "utf-8", null);
    }

    public void setIcon(Drawable icon) {
        mIcon.setImageDrawable(icon);
    }

}
