package com.example.lecture5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

public class Assignment7View extends View {

    public Assignment7View(Context context) {
        super(context);
        setBackgroundColor(Color.YELLOW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        Matrix m = new Matrix();
        m.preScale(1, -1);

        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.house);
        Bitmap mb = Bitmap.createBitmap(b, 0, 0,
                b.getWidth(), b.getHeight(), m, false);
        Bitmap sb = Bitmap.createScaledBitmap(b, 600, 600, false);
        canvas.drawBitmap(mb, 0, 0, null);
        canvas.drawBitmap(sb, 100, 100, null);

    }
}
