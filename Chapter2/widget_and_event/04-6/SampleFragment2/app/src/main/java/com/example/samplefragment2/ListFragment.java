package com.example.samplefragment2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.samplefragment2.R;

// Fragment 클래스 상속
public class ListFragment extends Fragment {
    // ArrayAdapter 를 리스트뷰의 setAdapter() 메소드로 설정하면
    // 그 안에 들어 있는 각각의 데이터가 리스트뷰의 각 줄에 보이게 된다.
    String[] values = {"첫 번째 이미지", "두 번째 이미지", "세 번째 이미지"};

    // 프래그먼트에 올라간 액티비티가 다른 액티비티로 변경될 때 마다
    // 해당 액티비티가 무엇인지 확인하기 위해 인터페이스를 구현
    public static interface ImageSelectionCallback {
        // 선택된 값으로 다른 프래그먼트의 이미지를 바꿔주기위해
        // 액티비티 쪽으로 데이터를 전달하기 위한 메소드 정의
        public void onImageSelected(int position);
    }

    public ImageSelectionCallback callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // 이 프래그먼트가 어떤 액티비티에 올라갔는지를 알 수 있는
        // onAttach() 메소드에 MainActivity 객체를 참조한 후
        // callback 변수에 할당
        if (context instanceof ImageSelectionCallback) {
            callback = (ImageSelectionCallback) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // fragment_list.xml 파일 인플레이션
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list,
                container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, values);
        listView.setAdapter(adapter);

        // 리스트뷰의 한 아이템을 선택했을 때 어떤 아이템을 선택했는지
        // 알아낸 후 처리
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // position : 몇 번째 아이템이 클릭된 것인지
                if(callback != null) {
                    callback.onImageSelected(position);
                }
            }
        });

        return rootView;
    }
}
