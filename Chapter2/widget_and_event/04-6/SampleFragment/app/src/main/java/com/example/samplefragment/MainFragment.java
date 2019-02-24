package com.example.samplefragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // inflate() 메소드로 전달되는 첫 번째 파라미터는 XML 레이아웃 파일이고
        // 두 번째 파라미터는 이 XML 레이아웃이 설정될 뷰그룹 객체이므로
        // container 객체를 전달한다.
        // inflate() 메소드를 호출하여 반환된 ViewGroup 객체 또한 이 프래그먼트의
        // 가장 상위 레이아웃인데 인플레이션된 상태로 반환되므로
        // 이 객체를 return 키워드로 리턴한다.
        // 이 프래그먼트의 가장 상위 레이아웃은 인플레이션을 통해 참조한 rootView 객체이다.
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main,
                container, false);

        Button button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity();
                activity.onFragmentChanged(0);
            }
        });

        return rootView;
    }
}
