package com.example.samplejsoup;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    // 데이터 배열 선언
    private ArrayList<ItemObject> mList;

    // View 참조 클래스
    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView_img;
        private TextView textView_title, textView_release, textView_director;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView_img = itemView.findViewById(R.id.imageView_img);
            textView_title = itemView.findViewById(R.id.textView_title);
            textView_release = itemView.findViewById(R.id.textView_release);
            textView_director = itemView.findViewById(R.id.textView_director);
        }
    }

    // 생성자
    public MyAdapter(ArrayList<ItemObject> list) {
        this.mList = list;
    }


    @NonNull
    @Override
    // View 가 생성되었을 때 호출
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // View 인플레이터
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    // 각 위치에 맞는 데이터 대입
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder viewHolder, int i) {
        viewHolder.textView_title.setText(String.valueOf(mList.get(i).getTitle()));
        viewHolder.textView_release.setText(String.valueOf(mList.get(i).getRelease()));
        viewHolder.textView_director.setText(String.valueOf(mList.get(i).getDirector()));

        // build.gradle 에 아래 주석추가
        // implementation 'com.github.bumptech.glide:glide:4.9.0'
        Glide.with(viewHolder.itemView).load(mList.get(i).getImg_url())
                .override(300, 400)
                .into(viewHolder.imageView_img);
    }

    @Override
    // 아이템 개수 리턴 메소드
    public int getItemCount() {
        return mList.size();
    }


}
