package com.example.lenovo.problem;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    ScrollView scrollView;
    ScrollView scrollView2;
    ImageView imageView;
    ImageView imageView2;
    BitmapDrawable bitmap;
    BitmapDrawable bitmap2;
    int bitmapWidth;
    int bitmapWidth2;
    int bitmapHeight;
    int bitmapHeight2;
    int change = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = (ScrollView)findViewById(R.id.scrollView);
        scrollView2 = (ScrollView)findViewById(R.id.scrollView2);
        imageView = (ImageView)findViewById(R.id.imageView1);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        scrollView.setHorizontalScrollBarEnabled(true);
        scrollView2.setHorizontalScrollBarEnabled(true);

        Resources res = getResources();
        bitmap = (BitmapDrawable)res.getDrawable(R.drawable.image);
        bitmap2 = (BitmapDrawable)res.getDrawable(R.drawable.image2);
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapWidth2 = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();
        int bitmapHeight2 = bitmap.getIntrinsicHeight();

        imageView.setImageDrawable(bitmap);
        imageView.getLayoutParams().height = bitmapHeight;
        imageView.getLayoutParams().width = bitmapWidth;

        imageView2.setImageDrawable(bitmap2);
        imageView2.getLayoutParams().height = bitmapHeight2;
        imageView2.getLayoutParams().width = bitmapWidth2;
    }

    public void onButtonClicked(View v) {
        changeImage();
    }

    public void onButtonClicked2(View v) {
        Intent myIntent = new Intent(getApplicationContext(), message.class);
        startActivity(myIntent);
    }
    private void changeImage() {
        if(change == 1) {
            imageView.setImageDrawable(bitmap);
            imageView2.setImageDrawable(bitmap2);
            change = 0;
        } else {
            imageView2.setImageDrawable(bitmap);
            imageView.setImageDrawable(bitmap2);
            change = 1;
        }
    }
}