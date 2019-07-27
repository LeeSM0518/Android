package com.example.multimemo;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

public class MultiMemoActivity extends AppCompatActivity {

    public static final String TAG = "MultiMemoActivity";

    ListView mMemoListrView;
    MemoListAdapter mMemoListAdapter;
    int mMemoCount = 0;
    TextView itemCount;

    // TODO DB 추가

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public String getVoiceUriStr(String idVoice) {
        Log.d(TAG, "Voice ID : " + idVoice);

        String voiceUriStr = null;
        if (idVoice != null && idVoice.trim().length() > 0 && !idVoice.equals("-1")) {
            String SQL = "select URI from " + Memo
        }
    }

    private void viewMemo(int position) {
        MemoListItem item = (MemoListItem) mMemoListAdapter.getItem(position);

        Intent intent = new Intent(getApplicationContext(), MemoInsertActivity.class);
        intent.putExtra(BasicInfo.KEY_MEMO_MODE, BasicInfo.MODE_VIEW);
        intent.putExtra(BasicInfo.KEY_MEMO_ID, item.getId());
        intent.putExtra(BasicInfo.KEY_MEMO_TITLE, item.getData(BasicInfo.MEMO_TITLE));
        intent.putExtra(BasicInfo.KEY_MEMO_DATE, item.getData(BasicInfo.MEMO_DATE));
        intent.putExtra(BasicInfo.KEY_MEMO_TEXT, item.getData(BasicInfo.MEMO_TEXT));

        intent.putExtra(BasicInfo.KEY_ID_HANDWRITING, item.getData(BasicInfo.MEMO_ID_HANDWRITING));
        intent.putExtra(BasicInfo.KEY_URI_HANDWRITING, item.getData(BasicInfo.MEMO_URI_HANDWRITING));

        intent.putExtra(BasicInfo.KEY_ID_PHOTO, item.getData(BasicInfo.MEMO_ID_PHOTO));
        intent.putExtra(BasicInfo.KEY_URI_PHOTO, item.getData(BasicInfo.MEMO_URI_PHOTO));

        intent.putExtra(BasicInfo.KEY_ID_VIDEO, item.getData(BasicInfo.MEMO_ID_VIDEO));
        intent.putExtra(BasicInfo.KEY_URI_VIDEO, item.getData(BasicInfo.MEMO_URI_VIDEO));

        intent.putExtra(BasicInfo.KEY_ID_VOICE, item.getData(BasicInfo.MEMO_ID_VOICE));
        intent.putExtra(BasicInfo.KEY_URI_VOICE, item.getData(BasicInfo.MEMO_URI_VOICE));

        startActivityForResult(intent, BasicInfo.REQ_VIEW_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case BasicInfo.REQ_INSERT_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    loadMemoListData();
                }
                break;

            case BasicInfo.REQ_VIEW_ACTIVITY:
                loadMemoListData();
                break;
        }
    }
}
