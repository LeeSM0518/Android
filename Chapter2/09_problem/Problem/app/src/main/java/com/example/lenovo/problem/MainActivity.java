package com.example.lenovo.problem;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    ScrollView scrollView1;
    ScrollView scrollView2;
    ImageView imageView1;
    ImageView imageView2;
    BitmapDrawable bitmap1;
    BitmapDrawable bitmap2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView1 = (ScrollView) findViewById(R.id.scrollView1);
        scrollView2 = (ScrollView) findViewById(R.id.scrollView2);

        imageView1 = (ImageView)findViewById(R.id.cat);
        imageView2 = (ImageView)findViewById(R.id.image02);

        Resources res = getResources();
        bitmap1 = (BitmapDrawable)res.getDrawable(R.drawable.cat);
        bitmap2 = (BitmapDrawable)res.getDrawable(R.drawable.image02);
        int bitmapWidth1 = bitmap1.getIntrinsicWidth();
        int bitmapHeight1 = bitmap1.getIntrinsicHeight();

        int bitmapWidth2 = bitmap2.getIntrinsicWidth();
        int bitmapHeight2 = bitmap2.getIntrinsicHeight();

        imageView1.setImageDrawable(bitmap1);
        imageView1.getLayoutParams().width = bitmapWidth1;
        imageView1.getLayoutParams().height = bitmapHeight1;
        imageView2.setImageDrawable(bitmap2);
        imageView2.getLayoutParams().width = bitmapWidth2;
        imageView2.getLayoutParams().height = bitmapHeight2;
    }


}