package com.example.problem;

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

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textViewKind, textViewTitle, textViewContext, textViewDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewContext = itemView.findViewById(R.id.textViewContext);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewKind = itemView.findViewById(R.id.textViewKind);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.textViewTitle.setText(String.valueOf(mList.get(i).getTitle()));
        viewHolder.textViewKind.setText(String.valueOf(mList.get(i).getKind()));
        viewHolder.textViewDate.setText(String.valueOf(mList.get(i).getDate()));
        viewHolder.textViewContext.setText(String.valueOf(mList.get(i).getContext()));

        Glide.with(viewHolder.itemView).load(mList.get(i).getImgUrl())
                .override(700, 500)
                .into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
