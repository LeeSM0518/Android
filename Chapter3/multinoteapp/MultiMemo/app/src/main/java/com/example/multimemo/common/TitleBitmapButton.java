package com.example.multimemo.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.example.multimemo.R;

public class TitleBitmapButton extends android.support.v7.widget.AppCompatButton {

    Context context;
    Paint paint;
    int defaultColor = 0xffffffff;
    float defaultSize = 18;
    float defaultScaleX = 1.0F;
    Typeface defaultTypeface = Typeface.DEFAULT;
    String titleText = "";
    int iconStatus = 0;
    Bitmap iconNormalBitmap;
    Bitmap iconClickedBitmap;

    public static final int BITMAP_ALIGN_CENTER = 0;
    public static final int BITMAP_ALIGN_LEFT = 1;
    public static final int BITMAP_ALIGN_RIGHT = 2;

    int backgroundBitmapNormal = R.drawable.title_button;
    int backgorunBitmapClicked = R.drawable.title_button_clicked;

    int bitmapAlign = BITMAP_ALIGN_CENTER;
    int bitmapPadding = 10;

    boolean paintChanged = false;

    private boolean selected;

    private int tabId;

    public TitleBitmapButton(Context context) {
        super(context);

        this.context = context;
        init();
    }

    public TitleBitmapButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        init();
    }

    public void setTabId(int id) {
        tabId = id;
    }

    public void setSelected(boolean flag) {
        selected = flag;
        if (selected) {
            setBackgroundResource(backgorunBitmapClicked);
            paintChanged = true;
            defaultColor = Color.BLACK;
        } else {
            setBackgroundResource(backgroundBitmapNormal);
            paintChanged = true;
            defaultColor = Color.WHITE;
        }
    }

    public boolean isSelected() {
        return selected;
    }

    public void init() {
        setBackgroundResource(backgroundBitmapNormal);

        paint = new Paint();
        paint.setColor(defaultColor);
        paint.setAntiAlias(true);
        paint.setTextScaleX(defaultScaleX);
        paint.setTextSize(defaultSize);
        paint.setTypeface(defaultTypeface);

        selected = false;
    }

    public void setIconBitmap(Bitmap iconNormal, Bitmap iconClicked) {
        iconNormalBitmap = iconNormal;
        iconClickedBitmap = iconClicked;
    }

    public void setBackgroundBitmap(int resNormal, int resClicked) {
        backgroundBitmapNormal = resNormal;
        backgorunBitmapClicked = resClicked;

        setBackgroundResource(backgroundBitmapNormal);
    }

    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_UP:
                if (!selected) {
                    setBackgroundResource(backgroundBitmapNormal);

                    iconStatus = 0;

                    paintChanged = true;
                    defaultColor = Color.WHITE;
                }
                break;

            case MotionEvent.ACTION_DOWN:
                if (!selected) {
                    setBackgroundResource(backgorunBitmapClicked);

                    iconStatus = 1;

                    paintChanged = true;
                    defaultColor = Color.BLACK;
                }
                break;
        }

        invalidate();

        return true;
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int curWidth = getWidth();
        int curHeight = getHeight();

        if (paintChanged) {
            paint.setColor(defaultColor);
            paint.setTextScaleX(defaultScaleX);
            paint.setTextSize(defaultSize);
            paint.setTypeface(defaultTypeface);

            paintChanged = false;
        }

        Bitmap iconBitmap = iconNormalBitmap;

        if (iconStatus == 1) {
            iconBitmap = iconClickedBitmap;
        }

        if (iconBitmap != null) {
            int iconWidth = iconBitmap.getWidth();
            int iconHeight = iconBitmap.getHeight();
            int bitmapX = 0;
            if (bitmapAlign == BITMAP_ALIGN_CENTER) {
                bitmapX = (curWidth - iconWidth) / 2;
            } else if (bitmapAlign == BITMAP_ALIGN_LEFT) {
                bitmapX = bitmapPadding;
            } else if (bitmapAlign == BITMAP_ALIGN_RIGHT) {
                bitmapX = curWidth - bitmapPadding;
            }

            canvas.drawBitmap(iconBitmap, bitmapX, (curHeight - iconHeight)/2, paint);
        }

        Rect bounds = new Rect();
        paint.getTextBounds(titleText, 0, titleText.length(), bounds);
        float textWidth = ((float)curWidth - bounds.width()) / 2.0F;
        float textHeight = ((float)curHeight + bounds.height()) / 2.0F + 4.0F;

        canvas.drawText(titleText, textWidth, textHeight, paint);
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public int getDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(int defaultColor) {
        this.defaultColor = defaultColor;
        paintChanged = true;
    }

    public float getDefaultSize() {
        return defaultSize;
    }

    public void setDefaultSize(float defaultSize) {
        this.defaultSize = defaultSize;
        paintChanged = true;
    }

    public float getDefaultScaleX() {
        return defaultScaleX;
    }

    public void setDefaultScaleX(float defaultScaleX) {
        this.defaultScaleX = defaultScaleX;
        paintChanged = true;
    }

    public Typeface getDefaultTypeface() {
        return defaultTypeface;
    }

    public void setDefaultTypeface(Typeface defaultTypeface) {
        this.defaultTypeface = defaultTypeface;
        paintChanged = true;
    }

    public int getBitmapAlign() {
        return bitmapAlign;
    }

    public void setBitmapAlign(int bitmapAlign) {
        this.bitmapAlign = bitmapAlign;
    }

    public int getBitmapPadding() {
        return bitmapPadding;
    }

    public void setBitmapPadding(int bitmapPadding) {
        this.bitmapPadding = bitmapPadding;
    }

    public TitleBitmapButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
