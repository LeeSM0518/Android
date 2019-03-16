package com.example.samplemultitouch;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout viewContainer;

    ImageDisplayView displayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        // XML 레이아웃에 정의한 리니어 레이아웃 객체 참조
        viewContainer = (LinearLayout) findViewById(R.id.viewerContainer);
        Bitmap sourceBitmap = loadImage();
        if (sourceBitmap != null) {
            // 새로 정의한 ImageDisplayView 객체 생성
            displayView = new ImageDisplayView(this);

            // ImageDisplayView 객체에 사진 이미지를 로딩하여 설정
            displayView.setImageData(sourceBitmap);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            );
            // addView()를 이용해 리니어 레이아웃 객체에 ImageDisplayView 객체 추가
            viewContainer.addView(displayView, params);
        }
    }

    private Bitmap loadImage() {
        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.beach);

        return bitmap;
    }
}
