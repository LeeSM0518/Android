package com.example.samplebitmapwidget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

import static android.provider.ContactsContract.ProviderStatus.STATUS_NORMAL;

// AppCompatButton 클래스를 상속하여 새로운 클래스 정의
// 버튼의 기능을 그대로 유지한 채 어떤 기능을 추가하고 싶을 때

public class BitmapButton extends AppCompatButton {

    // 아이콘 리소스 정의
    int iconNormal = R.drawable.bitmap_button_normal;
    int iconClicked = R.drawable.bitmap_button_clicked;

    // 아이콘 상태 정의
    int iconStatus = STATUS_NORMAL;
    public static int STATUS_NORMAL = 0;
    public static int STATUS_CLICKED = 1;

    // 소스 코드에서 객체를 생성했을 때 호출되는 생성자
    public BitmapButton(Context context) {
        super(context);

        init();
    }

    // XML 에 추가된 버튼이 인플레이션될 때 호출되는 생성자
    public BitmapButton(Context context, AttributeSet atts) {
        super(context, atts);

        init();
    }

    // 초기화 - 텍스트 크기는 /res/values/dimens.xml 에 정의한 값을 참조함.
    // 배경 이미지와 폰트의 크기, 색상, 글꼴을 설정한다.
    public void init() {
        // 배경 이미지 설정
        setBackgroundResource(iconNormal);

        int defaultTextColor = Color.WHITE;
        float defaultTextSize = getResources().getDimension(R.dimen.text_size);
        Typeface defaultTypeface = Typeface.DEFAULT_BOLD;

        // 글자 색상 설정
        setTextColor(defaultTextColor);
        // 글자 크기 설정
        setTextSize(defaultTextSize);
        // 글자의 폰트 설정
        setTypeface(defaultTypeface);
    }

    // 아이콘 리소스 설정
    public void setIcon(int iconNormal, int iconClicked) {
        this.iconNormal = iconNormal;
        this.iconClicked = iconClicked;
    }

    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        int action = event.getAction();

        switch (action) {

            // 버튼을 눌렀을 때는 iconClicked 변수에 할당된 이미지 설정
            case MotionEvent.ACTION_DOWN :
                setBackgroundResource(this.iconClicked);

                iconStatus = STATUS_CLICKED;

                break;

            case MotionEvent.ACTION_OUTSIDE :
            case MotionEvent.ACTION_CANCEL :
            case MotionEvent.ACTION_UP :
                setBackgroundResource(this.iconNormal);

                iconStatus = STATUS_NORMAL;

                break;
        }

        invalidate();

        return true;
    }
}
