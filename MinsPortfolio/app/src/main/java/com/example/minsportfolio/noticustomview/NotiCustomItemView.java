package com.example.minsportfolio.noticustomview;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.minsportfolio.R;

// NotificationsFragment 의 ListView 에 들어갈 ItemView 클래스
public class NotiCustomItemView extends LinearLayout {

    ImageView notiImageView;    // 수상 이미지뷰
    TextView notiNameView;      // 대회 이름 텍스트뷰
    TextView notiDtailView;     // 대회 상세 내용 텍스트뷰

    // 생성자
    public NotiCustomItemView(Context context) {
        super(context);
        // 레이아웃 초기화 작업
        init(context);
    }

    private void init(Context context) {
        // XML에 미리 정의해둔 틀을 실제 메모리에 올려줄 Inflater 생성
        LayoutInflater layoutInflater  =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // ItemView의 XML에 틀을 올려준다.
        layoutInflater.inflate(R.layout.noti_custom_item, this, true);

        // 변수에 XML의 View 매칭
        notiImageView = findViewById(R.id.notiImageView);
        notiNameView = findViewById(R.id.notiNameTextView);
        notiDtailView = findViewById(R.id.notiDetailTextView);
    }

    // 실제로 보여줄 데이터 setter
    public void setNotiImageView(int resdId) {
        notiImageView.setImageResource(resdId);
    }

    public void setNotiNameView(String text) {
        notiNameView.setText("대회명 : " + text);
    }

    public void setNotiDtailView(String text) {
        notiDtailView.setText("상장 설명 : " + text);
    }
}
