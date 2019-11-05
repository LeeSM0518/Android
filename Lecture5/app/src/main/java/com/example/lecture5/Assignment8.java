package com.example.lecture5;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Assignment8 extends AppCompatActivity {

    private LinearLayout layout;
    private Button fadeButton, slideButton, explodeButton;
    private ImageView imageView1, imageView2;
    boolean visible;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment8);

        layout = findViewById(R.id.layout);
        fadeButton = findViewById(R.id.fadeButton);
        slideButton = findViewById(R.id.slideButton);
        explodeButton = findViewById(R.id.explodeButton);
        imageView1 = findViewById(R.id.imageView1Dog2);

        setOnClick(fadeButton, new Fade());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setOnClick(slideButton, new Slide());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setOnClick(explodeButton, new Explode());
        }
    }

    void setOnClick(Button button, final Transition transition) {
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(layout, transition);
                visible = !visible;
                imageView1.setVisibility(visible ? View.VISIBLE : View.GONE);
            }
        });
    }
}
