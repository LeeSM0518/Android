package com.example.lenovo.samplescrollview;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    ScrollView scrollView;
    ImageView imageView;        // 일반적으로 이미지를 화면에 보여줄 때 사용
    BitmapDrawable bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // findViewById() 메소드를 호출해서 id 값을 파라미터로 전달하여 객체를 참조한다.
        // XML 레이아웃 파일에서 id 지정할 때 - @+id/아이디
        // 자바 소스 코드에서 참조할 때 - R.id.아이디
        scrollView = (ScrollView) findViewById(R.id.scrollView);    // 레이아웃에 정의된 뷰 객체 참조
        imageView = (ImageView) findViewById(R.id.imageView);
        scrollView.setHorizontalScrollBarEnabled(true);             // 수평 스크롤바 사용 기능 설정

        // 리소스의 이미지 참조
        // getIntrinsticWidth(), Height() 으로 원본 이미지의 가로와 세로크기를 알 수 있다.
        Resources res = getResources();
        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image01);
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();

        // 이미지 리소스와 이미지 크기 설정
        // 위에서 불러온 이미지 크기로 이미지뷰 크기를 설정한다.
        imageView.setImageDrawable(bitmap);
        imageView.getLayoutParams().width = bitmapWidth;
        imageView.getLayoutParams().height = bitmapHeight;
    }

    public void onButton1Clicked(View v) {
        changeImage();
    }

    // 다른 이미지 리소스로 변경
    private void changeImage() {
        Resources res = getResources();
        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image02);
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();

        imageView.setImageDrawable(bitmap);
        imageView.getLayoutParams().width = bitmapWidth;
        imageView.getLayoutParams().height = bitmapHeight;
    }
}