package com.example.problem;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    boolean running;

    ArrayList<ImageView> imageViews = new ArrayList<ImageView>();
    ArrayList<Drawable> drawables = new ArrayList<Drawable>();

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;

    Animation translateOutAnim;
    Animation translateInAnim;

    Animation translateOutAnim2;
    Animation translateInAnim2;

    int translateState1 = 0;
    int translateState2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);

        imageViews.add(imageView1);
        imageViews.add(imageView2);
        imageViews.add(imageView3);
        imageViews.add(imageView4);

        drawables.add(res.getDrawable(R.drawable.image1));
        drawables.add(res.getDrawable(R.drawable.image2));
        drawables.add(res.getDrawable(R.drawable.image3));
        drawables.add(res.getDrawable(R.drawable.image4));

        imageView1.setImageDrawable(drawables.get(0));
        imageView2.setImageDrawable(drawables.get(1));
        imageView3.setImageDrawable(drawables.get(2));
        imageView4.setImageDrawable(drawables.get(3));

        translateOutAnim = AnimationUtils.loadAnimation(this, R.anim.translate_out);
        translateInAnim = AnimationUtils.loadAnimation(this, R.anim.translate_in);

        translateOutAnim2 = AnimationUtils.loadAnimation(this, R.anim.translate_out2);
        translateInAnim2 = AnimationUtils.loadAnimation(this, R.anim.translate_in2);

        TranslateAnimListener1 animListener1 = new
                TranslateAnimListener1();

        TranslateAnimListener2 animListener2 = new
                TranslateAnimListener2();

        translateOutAnim.setAnimationListener(animListener1);
        translateOutAnim2.setAnimationListener(animListener2);

        Thread thread1 = new BackgroundThread1();
        thread1.start();

        Thread thread2 = new BackgroundThread2();
        thread2.start();
    }

    class BackgroundThread1 extends Thread {
        public void run() {
            imageView2.startAnimation(translateOutAnim);
        }
    }

    class BackgroundThread2 extends Thread {
        public void run() {
            imageView4.startAnimation(translateOutAnim2);
        }
    }

    private class TranslateAnimListener1 implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            if (translateState2 == 0) {
                imageView1.setImageDrawable(drawables.get(0));
                imageView2.setImageDrawable(drawables.get(1));
                imageView1.startAnimation(translateOutAnim);
                imageView2.startAnimation(translateInAnim);
                translateState2 = 1;
            } else {
                imageView1.setImageDrawable(drawables.get(1));
                imageView2.setImageDrawable(drawables.get(0));
                imageView1.startAnimation(translateOutAnim);
                imageView2.startAnimation(translateInAnim);
                translateState2 = 0;
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (translateState2 == 1) {
                imageView1.setImageDrawable(drawables.get(1));
                imageView2.setImageDrawable(drawables.get(0));
                imageView1.startAnimation(translateOutAnim);
                imageView2.startAnimation(translateInAnim);
                translateState2 = 1;
            } else {
                imageView1.setImageDrawable(drawables.get(0));
                imageView2.setImageDrawable(drawables.get(1));
                imageView1.startAnimation(translateOutAnim);
                imageView2.startAnimation(translateInAnim);
                translateState2 = 0;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    private class TranslateAnimListener2 implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            if (translateState1 == 0) {
                imageView3.setImageDrawable(drawables.get(2));
                imageView4.setImageDrawable(drawables.get(3));
                imageView3.startAnimation(translateOutAnim2);
                imageView4.startAnimation(translateInAnim2);
                translateState1 = 1;
            } else {
                imageView3.setImageDrawable(drawables.get(3));
                imageView4.setImageDrawable(drawables.get(2));
                imageView3.startAnimation(translateOutAnim2);
                imageView4.startAnimation(translateInAnim2);
                translateState1 = 0;
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (translateState1 == 1) {
                imageView3.setImageDrawable(drawables.get(3));
                imageView4.setImageDrawable(drawables.get(2));
                imageView3.startAnimation(translateOutAnim2);
                imageView4.startAnimation(translateInAnim2);
                translateState1 = 1;
            } else {
                imageView3.setImageDrawable(drawables.get(2));
                imageView4.setImageDrawable(drawables.get(3));
                imageView3.startAnimation(translateOutAnim2);
                imageView4.startAnimation(translateInAnim2);
                translateState1 = 0;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
