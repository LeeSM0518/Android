package com.example.samplefragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MenuFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // inflate() 메소드로 전달되는 첫 번째 파라미터의 값이
        // R.layout.fragment_menu 이다. 그래서 MenuFragment 클래스에는
        // fragment_menu.xml 파일의 내용이 인플레이션되어 설정된다.
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_menu,
                container, false);
        return rootView;
    }
}
