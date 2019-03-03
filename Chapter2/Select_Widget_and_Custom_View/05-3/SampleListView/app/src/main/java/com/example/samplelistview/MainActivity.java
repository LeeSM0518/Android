package com.example.samplelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    ListView listView;
    SingerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 리스트뷰 객체 참조
        listView = findViewById(R.id.listView);

        // 어댑터 객체 참조
        adapter = new SingerAdapter();

        // 어댑터에 각 아이템의 데이터 추가
        adapter.addItem(new SingerItem("소녀시대", "010-1000-1000", 20, R.drawable.singer));
        adapter.addItem(new SingerItem("걸스데이", "010-2000-2000", 22, R.drawable.singer2));
        adapter.addItem(new SingerItem("여자친구", "010-3000-3000", 21, R.drawable.singer3));
        adapter.addItem(new SingerItem("티아라", "010-4000-4000", 24, R.drawable.singer4));
        adapter.addItem(new SingerItem("AOA", "010-5000-5000", 25, R.drawable.singer5));

        // 리스트뷰에 어댑터 객체 설정
        listView.setAdapter(adapter);

        // 아이템을 클릭했을 때 토스트 메시지를 보여주도록 리스너 설정
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SingerItem item = (SingerItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "선택 : " + item.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    // BaseAdapter 를 상속하여 새로운 어댑터 클래스 정의
    class SingerAdapter extends BaseAdapter {
        // 각 아이템의 데이터를 담고 있는 SingerItem 객체를
        // 저장할 ArrayList 객체 생성
        ArrayList<SingerItem> items = new ArrayList<SingerItem>();

        @Override
        // 전체 아이템의 개수를 리턴하는 메소드 정의
        // (어댑터에서 관리하는 아이템의 개수를 리턴)
        public int getCount() {
            return items.size();
        }

        public void addItem(SingerItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        // 아이템에 표시할 뷰 리턴하는 메소드 정의
        // 첫 번째 파라미터 : 아이템의 인덱스, 리스트 뷰에서 아이템의 위치
        // 두 번째 파라미터 : 현재 인덱스에 해당하는 뷰 객체
        // 세 번째 파라미터 : 이 뷰를 포함하고 있는 부모 컨테이너 객체.
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            SingerItemView view = new SingerItemView(getApplicationContext());
            SingerItem item = items.get(position);
            view.setName(item.getName());
            view.setMobile(item.getMobile());
            view.setAge(item.getAge());
            view.setImageView(item.getResId());

            return view;
        }
    }
}