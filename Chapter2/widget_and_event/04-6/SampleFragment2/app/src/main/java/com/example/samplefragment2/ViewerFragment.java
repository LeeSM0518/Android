package com.example.samplefragment2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ViewerFragment extends Fragment {
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 인플레이션
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_viewer,
                container, false);

        // 이미지뷰 객체를 찾아 imageView 변수에 할당
        imageView = (ImageView) rootView.findViewById(R.id.imageView);

        return rootView;
    }

    // 액티비티에서 이 프래그먼트에 있는 이미지뷰에 이미지를 설정할 수 있도록 한다.
    public void setImage(int resId) {
        imageView.setImageResource(resId);
    }
}
