package com.example.samplethreadanimation;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    // 이미지 객체를 넣어둘 리스트
    ArrayList<Drawable> drawableList = new ArrayList<Drawable>();

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        startAnimation();
    }

    public void startAnimation() {
        Resources res = getResources();

        drawableList.add(res.getDrawable(R.drawable.face1));
        drawableList.add(res.getDrawable(R.drawable.face2));
        drawableList.add(res.getDrawable(R.drawable.face3));
        drawableList.add(res.getDrawable(R.drawable.face4));
        drawableList.add(res.getDrawable(R.drawable.face5));

        AnimThread thread = new AnimThread();
        thread.start();
    }

    // 이미지를 사용해 애니메이션을 구현하는 스레드
    class AnimThread extends Thread {
        public void run() {
            int index = 0;
            // 스레드를 실행하면 다섯 개의 이미지를
            // 번갈아 가면서 화면에 보여준다.
            for (int i = 0; i < 100; i++) {
                // 이미지 파일 로딩
                final Drawable drawable = drawableList.get(index);
                index += 1;
                if (index > 4) {
                    index = 0;
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // 이미지뷰를 설정한다.
                        imageView.setImageDrawable(drawable);
                    }
                });

                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
