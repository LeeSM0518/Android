package com.example.samplebuttonanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Animation flowAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        // 애니메이션 객체 로딩
        flowAnim = AnimationUtils.loadAnimation(this, R.anim.flow);
        // 애니메이션 리스너 설정하여 종료되는 시점 확인.
        flowAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(getApplicationContext(),
                        "애니메이션 종료됨.",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    // 텍스트뷰에 애니메이션 적용
    public void onButton1Clicked(View v){
        textView.startAnimation(flowAnim);
    }
}