package com.example.minsportfolio.homecustomview;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.minsportfolio.R;

// HomeFragment 의 ListView 에 들어갈 ItemView 클래스
public class HomeCustomItemView extends LinearLayout {

    ImageView languageImageView;    // 언어 이미지
    TextView languageTextView;      // 언어 이름
    TextView careerTextView;        // 경력
    TextView proficiencyTextView;   // 숙련도
    TextView doneListTextView;      // 해본 것들

    // 생성자
    public HomeCustomItemView(Context context) {
        super(context);

        // 객체 초기화 작업
        init(context);
    }

    public void init(Context context) {
        // XML에 미리 정의해둔 틀을 실제 메모리에 올려줄 Inflater 생성
        LayoutInflater layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // ItemView의 XML에 틀을 올려준다.
        layoutInflater.inflate(R.layout.home_custom_item, this, true);

        // 변수에 XML의 View 매핑
        languageImageView = findViewById(R.id.homeLanguageImageView);
        languageTextView = findViewById(R.id.homeLanguageTextView);
        careerTextView = findViewById(R.id.homeCareerTextView);
        proficiencyTextView = findViewById(R.id.homeProfTextView);
        doneListTextView = findViewById(R.id.homeDoneListView);
    }

    // 실제로 보여줄 데이터 setter
    public void setLanguageImageView(int resId) {
        languageImageView.setImageResource(resId);
    }

    public void setLanguageTextView(String language) {
        languageTextView.setText(language);
    }

    public void setCareerTextView(String career) {
        careerTextView.setText("경력 : " + career);
    }

    public void setProficiencyTextView(String proficiency) {
        proficiencyTextView.setText("숙련도(A, B, C, D, E) : " + proficiency);
    }

    public void setDoneListTextView(String doneList) {
        doneListTextView.setText("해본 것들 : " + doneList);
    }
}
