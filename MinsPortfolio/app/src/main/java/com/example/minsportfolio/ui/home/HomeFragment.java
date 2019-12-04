package com.example.minsportfolio.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.minsportfolio.R;
import com.example.minsportfolio.homecustomview.HomeCustomItem;
import com.example.minsportfolio.homecustomview.HomeCustomItemView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    // 리스트뷰 선언
    ListView customListView;
    // 어댑터 선언
    HomeCustomAdapter customAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // xml 파일을 가져와서 객체에 저장
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // 뷰 객체로 부터 리스트 뷰 XML을 매칭시킴
        customListView = root.findViewById(R.id.customHomeListView);

        // 어댑터 객체 생성
        customAdapter = new HomeCustomAdapter();

        // 어댑터에 아이템 객체 생성 후 추가
        customAdapter.addItem(new HomeCustomItem(
                R.drawable.c,
                "C",
                "3년",
                "B",
                "C 프로그래밍(수업), 고급 C 프로그래밍(수업)"
        ));
        customAdapter.addItem(new HomeCustomItem(
                R.drawable.cpp,
                "C++",
                "2년",
                "C",
                "객체 지향 프로그래밍(수업), C/C++ 프로그래밍(수업), " +
                        "Arduino(아두이노)"
        ));
        customAdapter.addItem(new HomeCustomItem(
                R.drawable.python,
                "Python",
                "2년",
                "B",
                "RaspberryPI(프로젝트), Pandas(데이터 분석, 세미나), " +
                        "Crawling(크롤링, 세미나), Deep learning(딥러닝, 세미나), " +
                        "OpenCV(수업)"
        ));
        customAdapter.addItem(new HomeCustomItem(
                R.drawable.java,
                "Java",
                "2년",
                "A",
                "Swing(수업 프로젝트, Desktop App), javaFX (수업 프로젝트, Desktop App)," +
                        " Android(수업, 세미나), Web(Servlet, 세미나)"
        ));
        customAdapter.addItem(new HomeCustomItem(
                R.drawable.javascript,
                "JavaScript",
                "1년",
                "D",
                "Node.js(세미나), Vue.js(세미나)"
        ));

        // 리스트뷰에 어댑터 객체 적용
        customListView.setAdapter(customAdapter);

        return root;
    }

    // 어댑터 클래스
    public class HomeCustomAdapter extends BaseAdapter {

        // 아이템 객체들을 저장할 리스트 생성
        ArrayList<HomeCustomItem> items = new ArrayList<>();

        @Override
        // 아이템 개수 반환 메서드
        public int getCount() {
            return items.size();
        }

        @Override
        // 아이템 반환 메서드
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        // 아이템 위치 반환 메서드
        public long getItemId(int position) {
            return position;
        }

        // 아이템 객체 추가 메서드
        public void addItem(HomeCustomItem item) {
            items.add(item);
        }

        @Override
        // 아이템뷰 객체 반환 메서드
        public View getView(int position, View convertView, ViewGroup parent) {
            HomeCustomItemView view = new HomeCustomItemView(getContext());
            HomeCustomItem item = items.get(position);
            view.setLanguageImageView(item.getLanguageResId());
            view.setLanguageTextView(item.getLanguage());
            view.setCareerTextView(item.getCareer());
            view.setProficiencyTextView(item.getProficiency());
            view.setDoneListTextView(item.getDoneList());
            return view;
        }
    }

}