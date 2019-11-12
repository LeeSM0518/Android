package com.example.lecture6_2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

public class Assignment2View extends View {

    private Paint[] mForegrounds = {
            makePaint(Color.BLACK), makePaint(Color.BLUE), makePaint(Color.GREEN),
            makePaint(Color.RED)};

    private static Random r = new Random();


    public Assignment2View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);
        int width = getWidth();
        int height = getHeight();
        for (int i = 0; i < 20; i++) {
            float x = r.nextInt(width);
            float y = r.nextInt(height);
            float radius = r.nextInt(80);
            Paint circleColor = mForegrounds[r.nextInt(mForegrounds.length)];
            canvas.drawCircle(x, y, radius, circleColor);
            canvas.drawRect(x, y, x, y, circleColor);
            canvas.drawText("random", x, y, circleColor);
        }
        for (int i = 0; i < 20; i++) {
            float x = r.nextInt(width);
            float y = r.nextInt(height);
            Paint circleColor = mForegrounds[r.nextInt(mForegrounds.length)];
            canvas.drawRect(x, y, x+x/2, y+y/2, circleColor);
        }
        for (int i = 0; i < 20; i++) {
            float x = r.nextInt(width);
            float y = r.nextInt(height);
            Paint circleColor = mForegrounds[r.nextInt(mForegrounds.length)];
            canvas.drawText("random", x, y, circleColor);
        }
    }

    private Paint makePaint(int color) {
        Paint p = new Paint();
        p.setColor(color);
        p.setTextSize(25);
        return p;
    }
}
