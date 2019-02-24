package com.example.samplepagesliding;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    // 슬라이딩 페이지가 보이는지 여부
    boolean isPageOpen = false;

    // 왼쪽으로 이동 애니메이션 객체
    Animation translateLeftAnim;
    // 오른쪽으로 이동 애니메이션 객체
    Animation translateRightAnim;

    // 슬라이딩으로 보여줄 페이지
    LinearLayout page;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);

        page = (LinearLayout) findViewById(R.id.page);

        // 애니메이션 객체 참조
        translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        // 슬라이딩 애니메이션을 감시할 리스너
        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
        translateLeftAnim.setAnimationListener(animListener);
        translateRightAnim.setAnimationListener(animListener);
    }

    public void onButton1Clicked(View v) {
        // 페이지가 열려 있으면 오른쪽으로 애니메이션
        if (isPageOpen) {
            page.startAnimation(translateRightAnim);
        }
        // 페이지가 닫혀 있으면 보이도록 한 후 왼쪽으로 애니메이션
        else {
            page.setVisibility(View.VISIBLE);
            page.startAnimation(translateLeftAnim);
        }
    }

    private class SlidingPageAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onAnimationEnd(Animation animation) {
            // 페이지가 열려 있으면 안보이도록 하고
            // 버튼의 텍스트로 'Open'으로 변경
            if(isPageOpen) {
                page.setVisibility(View.INVISIBLE);

                button.setText("Open");
                isPageOpen = false;
            }
            // 페이지가 닫혀 있으면 버튼의 텍스트를 'Close'로 변경
            else {
                button.setText("Close");
                isPageOpen = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
