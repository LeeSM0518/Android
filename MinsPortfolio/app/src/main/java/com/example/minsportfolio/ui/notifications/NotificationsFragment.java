package com.example.minsportfolio.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.minsportfolio.R;
import com.example.minsportfolio.noticustomview.NotiCustomItem;
import com.example.minsportfolio.noticustomview.NotiCustomItemView;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    // 리스트뷰 선언
    ListView customListView;
    // 어댑터 선언
    NotiCustomAdapter notiCustomAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // xml 파일을 가져와서 객체에 저장
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        // 뷰 객체로 부터 리스트 뷰 XML을 매칭시킴
        customListView = root.findViewById(R.id.notiListView);
        // 어댑터 객체 생성
        notiCustomAdapter = new NotiCustomAdapter();
        // 어댑터에 아이템 객체 생성 후 추가
        notiCustomAdapter.addItem(new NotiCustomItem(
                R.drawable.droneprize,
                "2018 Hanbat Drone Championship",
                "IT융합인력양성사업단장상, 귀하는 한밭대학교 드론융합기술센터 및" +
                        " 한국ITS학회가 공동으로 주관한 '2018 Hanbat Drone Championship' 에서 " +
                        "IT융합인력양성사업단장상으로 선정되어 이 상장을 수여합니다."
        ));
        notiCustomAdapter.addItem(new NotiCustomItem(
                R.drawable.guideeyesprize,
                "2019 한밭대학교 X-Corps 경진대회",
                "동상, 위 팀은 진로선택형 산업 및 공공기술 실전문제연구단이 개최한 " +
                        "'2019 한밭대학교 X-Corps 경진대회' 에서 위와 같이 입상하였으므로 " +
                        "이에 상장과 장학금을 수여합니다."
        ));

        // 리스트뷰에 어댑터 객체 적용
        customListView.setAdapter(notiCustomAdapter);

        return root;
    }

    // 어댑터 클래스
    public class NotiCustomAdapter extends BaseAdapter {

        // 아이템 객체들을 저장하는 리스트
        ArrayList<NotiCustomItem> items = new ArrayList<>();

        @Override
        // 아이템 객체들 개수 반환 메서드
        public int getCount() {
            return items.size();
        }

        @Override
        // 아이템 객체 위치로 부터 반환 메서드
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        // 아이템 위치 반환 메서드
        public long getItemId(int position) {
            return position;
        }

        // 아이템 객체 추가 메서드
        public void addItem(NotiCustomItem item) {
            items.add(item);
        }

        @Override
        // 아이템 뷰 객체 반환 메서드
        public View getView(int position, View convertView, ViewGroup parent) {
            NotiCustomItemView view = new NotiCustomItemView(getContext());
            NotiCustomItem item = items.get(position);
            view.setNotiImageView(item.getPrizeResId());
            view.setNotiNameView(item.getPrizeName());
            view.setNotiDtailView(item.getPrizeDetail());
            return view;
        }
    }

}