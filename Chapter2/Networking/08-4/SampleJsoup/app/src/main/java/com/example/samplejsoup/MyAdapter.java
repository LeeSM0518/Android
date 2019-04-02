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

    private ArrayList<ItemObject> mList;

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

    public MyAdapter(ArrayList<ItemObject> list) {
        this.mList = list;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder viewHolder, int i) {
        viewHolder.textView_title.setText(String.valueOf(mList.get(i).getTitle()));
        viewHolder.textView_release.setText(String.valueOf(mList.get(i).getRelease()));
        viewHolder.textView_director.setText(String.valueOf(mList.get(i).getDirector()));

        Glide.with(viewHolder.itemView).load(mList.get(i).getImg_url())
                .override(300, 400)
                .into(viewHolder.imageView_img);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}
