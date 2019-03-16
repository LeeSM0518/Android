package com.example.samplemultitouch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

// 뷰를 상속하면서 OnTouchListener 인터페이스를 구현하는 클래스 정의
// 이미지에 대한 이벤트 처리 클래스이므로 View 클래스 상속
// 또한 이 클래스는 터치 이벤트를 처리 하므로 OnTouchListener 구현
public class ImageDisplayView extends View implements View.OnTouchListener {

    public static final String TAG = "ImageDisplayView";

    Context mContext;

    Canvas mCanvas;

    Bitmap mBitmap;

    Paint mPaint;

    int lastX;
    int lastY;

    Bitmap sourceBitmap;

    // 매트릭스 객체
    // 수학적인 연산을 통해 비트맵 이미지의 각 픽셀 값을 변경
    Matrix mMatrix;

    float sourceWidth = 0.0F;
    float sourceHeight = 0.0F;

    float bitmapCenterX;
    float bitmapCenterY;

    float scaleRatio;
    float totalScaleRatio;

    float displayWidth = 0.0F;
    float displayHeight = 0.0F;

    int displayCenterX = 0;
    int displayCenterY = 0;

    public float startX;
    public float startY;

    public static float MAX_SCALE_RATIO = 5.0F;
    public static float MIN_SCALE_RATIO = 0.1F;

    float oldDistance = 0.0F;

    int oldPointerCount = 0;
    boolean isScrolling = false;
    float distanceThreshold = 3.0F;

    public ImageDisplayView(Context context) {
        super(context);

        mContext = context;

        init();
    }

    public ImageDisplayView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        init();
    }

    // 매트릭스 객체를 초기화 및
    // 이 클래스를 구현하는 리스너 설정
    private void init() {
        mPaint = new Paint();
        mMatrix = new Matrix();

        lastX = -1;
        lastY = -1;

        setOnTouchListener(this);
    }

    // 뷰가 초기화되고 나서 화면에 보이기 전 크기가 전해지면
    // 호출되는 메소드 안에서 메모리 상에 새로운 비트맵 객체 생성
    // (뷰가 보이기전에 호출 됨)
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w > 0 && h > 0) {
            // 메모리에 비트맵 이미지를 만드는 메소드
            newImage(w, h);

            redraw();
        }
    }

    public void newImage(int width, int height) {
        Bitmap img = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(img);

        mBitmap = img;
        mCanvas = canvas;

        displayWidth = (float) width;
        displayHeight = (float) height;

        displayCenterX = width / 2;
        displayCenterY = height / 2;
    }

    public void drawBackground(Canvas canvas) {
        if (canvas != null) {
            canvas.drawColor(Color.BLACK);
        }
    }

    // 뷰가 화면에 그려지는 메소드 안에서
    // 메모리 상의 비트맵 객체 그리기
    // (더블 버퍼링 방식)
    protected void onDraw(Canvas canvas) {
        if (mBitmap != null) {
            // 단순히 메모리에 만들어져 있는 비트맵 이미지를 화면에 그려주는 역할
            canvas.drawBitmap(mBitmap, 0, 0, null);
        }
    }

    public void setImageData(Bitmap image) {
        recycle();

        sourceBitmap = image;

        sourceWidth = sourceBitmap.getWidth();
        sourceHeight = sourceBitmap.getHeight();

        bitmapCenterX = sourceBitmap.getWidth() / 2;
        bitmapCenterY = sourceBitmap.getHeight() / 2;

        scaleRatio = 1.0F;
        totalScaleRatio = 1.0F;
    }

    public void recycle() {
        if (sourceBitmap != null) {
            sourceBitmap.recycle();
        }
    }

    public void redraw() {
        if (sourceBitmap == null) {
            Log.d(TAG, "sourceBitmap is null in redraw().");
            return;
        }

        drawBackground(mCanvas);

        float originX = (displayWidth - (float) sourceBitmap.getWidth()) / 2.0F;
        float originY = (displayHeight - (float) sourceBitmap.getHeight()) / 2.0F;

        mCanvas.translate(originX, originY);
        mCanvas.drawBitmap(sourceBitmap, mMatrix, mPaint);
        mCanvas.translate(-originX, -originY);

        invalidate();
    }

    // 뷰를 터치할 때 호출되는 메소드 다시 정의
    // 메모리 상의 비트맵 이미지에 대상이 되는
    // 사진 이미지를 변형한 후 그려주는 부분이 가장 중요
    public boolean onTouch(View v, MotionEvent ev) {
        // 손가락으로 눌렀는 지, 움직이고 있는지, 떼어졌는 지
        final int action = ev.getAction();

        // 터치했을 때 몇 개의 손가락으로 터치하는지 개수 확인
        int pointerCount = ev.getPointerCount();
        Log.d(TAG, "Pointer Count : " + pointerCount);

        switch (action) {
            // 손가락으로 눌렀을 때의 기능 추가
            case MotionEvent.ACTION_DOWN:
                if (pointerCount == 1) {
                    float curX = ev.getX();
                    float curY = ev.getY();

                    startX = curX;
                    startY = curY;
                } else if (pointerCount == 2) {
                    oldDistance = 0.0F;

                    isScrolling = true;
                }
                return true;

            // 손가락으로 움직일 때의 기능 추가
            case MotionEvent.ACTION_MOVE:
                if (pointerCount == 1) {
                    if (isScrolling) {
                        return true;
                    }
                    float curX = ev.getX();
                    float curY = ev.getY();

                    if (startX == 0.0F) {
                        startX = curX;
                        startY = curY;

                        return true;
                    }

                    float offsetX = startX - curX;
                    float offsetY = startY - curY;

                    if (oldPointerCount == 2) {

                    } else {
                        Log.d(TAG, "ACTION_MOVE : " +
                                offsetX + ", " + offsetY);
                    }

                    startX = curX;
                    startY = curY;
                } else if (pointerCount == 2) {
                    float x1 = ev.getX(0);
                    float y1 = ev.getY(0);
                    float x2 = ev.getX(1);
                    float y2 = ev.getY(1);

                    float dx = x1 - x2;
                    float dy = y1 - y2;
                    float distance = new Double(
                            Math.sqrt(new Float(dx * dx + dy * dy).doubleValue())).floatValue();

                    float outScaleRatio = 0.0F;
                    if (oldDistance == 0.0F) {
                        oldDistance = distance;

                        break;
                    }

                    if (distance > oldDistance) {
                        if ((distance - oldDistance) < distanceThreshold) {
                            return true;
                        }

                        outScaleRatio = scaleRatio + (oldDistance / distance * 0.05F);
                    } else if (distance < oldDistance) {
                        if ((oldDistance - distance) < distanceThreshold) {
                            return true;
                        }

                        outScaleRatio = scaleRatio - (distance / oldDistance * 0.05F);
                    }

                    if (outScaleRatio < MIN_SCALE_RATIO || outScaleRatio > MAX_SCALE_RATIO) {
                        Log.d(TAG, "Invalid scaleRatio : " + outScaleRatio);
                    } else {
                        Log.d(TAG, "Distance : " + distance + ", ScaleRatio : " + outScaleRatio);

                        // 두 손가락으로 움직이고 있을 때
                        scaleImage(outScaleRatio);
                    }

                    oldDistance = distance;
                }

                oldPointerCount = pointerCount;
                break;

            // 손가락을 떼었을 때의 기능 추가
            case MotionEvent.ACTION_UP:
                if (pointerCount == 1) {
                    float curX = ev.getX();
                    float curY = ev.getY();

                    float offsetX = startX - curX;
                    float offsetY = startY - curY;

                    if (oldPointerCount == 2) {

                    } else {
                        // 한 손가락으로 움직이고 있을 때
                        // 이전에 움직였을 때의 좌표값과
                        // 차이를 계산한 후 그만큼 이미지를 이동시키도록
                        // moveImage 호출
                        moveImage(-offsetX, -offsetY);
                    }
                } else {
                    isScrolling = false;
                }

                return true;
        }
        return true;
    }

    // 매트릭스 객체를 사용해 이미지 크기 변경
    private void scaleImage(float inScaleRatio) {
        Log.d(TAG, "scaleImage() called : " + inScaleRatio);

        // postScale() 비트맵 이미지를 확대 또는 축소 가능
        // 첫 번째 파라미터 : X축을 기준으로 확대하는 비율
        // 두 번째 파라미터 : Y축을 기준으로 확대하는 비율
        // 세 번째, 네 번째 파라미터 : 확대 또는 축소할 때 기준이 되는 위치(보통 비트맵 이미지의 중간점)
        mMatrix.postScale(inScaleRatio, inScaleRatio, bitmapCenterX, bitmapCenterY);

        // 비트맵 이미지를 회전시킬 때 사용
        // 첫 번째 파라미터 : 회전 각도
        mMatrix.postRotate(0);

        totalScaleRatio = totalScaleRatio + inScaleRatio;

        redraw();
    }

    // 매트릭스 객체를 사용해 이미지 이동
    private void moveImage(float offsetX, float offsetY) {
        Log.d(TAG, "moveImageI() called : " + offsetX + ", " + offsetY);

        // 비트맵 이미지를 이동시킬 때 사용
        // 첫 번째, 두 번째 파라미터 : 이동할 만큼의 X와 Y 좌표값
        mMatrix.postTranslate(offsetX, offsetY);

        // 화면에 다시 그려준다.
        redraw();
    }
}
