package com.example.samplerssfeeder;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class RSSListView extends ListView {

    private RSSListView adapter;

    private OnDataSelectionListener selectionListener;

    public RSSListView(Context context) {
        super(context);

        init();
    }

    public RSSListView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        setOnItemClickListener(new OnItemClickAdapter());
    }

    public void setAdapter(BaseAdapter adapter) {
        super.setAdapter(adapter);
    }

    public BaseAdapter getAdapter() {
        return (BaseAdapter) super.getAdapter();
    }

    public void setOnDataSelectionListener(OnDataSelectionListener listener) {
        this.selectionListener = listener;
    }

    public OnDataSelectionListener getSelectionListener() {
        return selectionListener;
    }

    class OnItemClickAdapter implements OnItemClickListener {

        public OnItemClickAdapter() {

        }

        public void onItemClick(AdapterView parent, View v, int position, long id) {

            if (selectionListener == null) {
                return;
            }

            int rowIndex = -1;
            int columnIndex = -1;

            selectionListener.onDataSelected(parent, v, position, id);
        }
    }
}
