package com.example.multimemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class MemoListAdapter extends BaseAdapter {

    private Context mContext;

    private List<MemoListItem> mItems = new ArrayList<>();

    public MemoListAdapter(Context context) {
        mContext = context;
    }

    public void clear() {
        mItems.clear();
    }

    public void addItem(MemoListItem item) {
        mItems.add(item);
    }
    public void setListItems(List<MemoListItem> list) {
        mItems = list;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    public boolean areaAllItemSelectable() {
        return false;
    }

    public boolean isSelectable(int position) {
        try {
            return mItems.get(position).isSelectable();
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MemoListItemView itemView;

        if (convertView == null) itemView = new MemoListItemView(mContext);
        else itemView = (MemoListItemView) convertView;

        itemView.setContent(0, mItems.get(position).getData(BasicInfo.MEMO_TITLE));
        itemView.setContent(1, mItems.get(position).getData(BasicInfo.MEMO_DATE));
        itemView.setContent(2, mItems.get(position).getData(BasicInfo.MEMO_TEXT));
        itemView.setContent(3, mItems.get(position).getData(BasicInfo.MEMO_URI_HANDWRITING));
        itemView.setContent(4, mItems.get(position).getData(BasicInfo.MEMO_URI_PHOTO));
        itemView.setMediaState(mItems.get(position).getData(BasicInfo.MEMO_URI_VIDEO),
                mItems.get(position).getData(BasicInfo.MEMO_URI_VOICE));

        return itemView;
    }
}
