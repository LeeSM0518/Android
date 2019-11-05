package com.example.lecture5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.View;

public class Assignment3View extends View {

    private Paint mPaints, mFramePaint;
    private RectF mBigOval;
    private float mStart, mSweep;

    private static final float SWEEP_INC = 2;
    private static final float START_INC = 15;

    public Assignment3View(Context context) {
        super(context);

        mPaints = new Paint();
        mPaints.setAntiAlias(true);
        mPaints.setStyle(Paint.Style.FILL);
        mPaints.setColor(0x88FF0000);

        mFramePaint = new Paint();
        mFramePaint.setAntiAlias(true);
        mFramePaint.setStyle(Paint.Style.STROKE);
        mFramePaint.setStrokeWidth(3);
        mFramePaint.setColor(0x8800FF00);
        mBigOval = new RectF(40, 10, 900, 1000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(100);

        Typeface t;
        t = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL);
        paint.setTypeface(t);
        canvas.drawText("DEFAULT 폰트", 10, 400, paint);

        t = Typeface.create(Typeface.DEFAULT_BOLD, Typeface.NORMAL);
        paint.setTypeface(t);
        canvas.drawText("DEFAULT_BOLD 폰트", 10, 600, paint);

        t = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL);
        paint.setTypeface(t);
        canvas.drawText("MONOSPACE 폰트", 10, 800, paint);

        t = Typeface.create(Typeface.SERIF, Typeface.NORMAL);
        paint.setTypeface(t);
        canvas.drawText("SERIF 폰트", 10, 1000, paint);

        t = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL);
        paint.setTypeface(t);
        canvas.drawText("SANS_SERIF 폰트", 10, 1200, paint);
    }
}
