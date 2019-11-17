package com.example.multimemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.multimemo.db.MemoDatabase;

import java.io.File;
import java.util.Date;
import java.util.Locale;

public class MultiMemoActivity extends AppCompatActivity {

    public static final String TAG = "MultiMemoActivity";

    ListView mMemoListView;
    MemoListAdapter mMemoListAdapter;
    public static MemoDatabase mDatabase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Locale curLocale = getResources().getConfiguration().locale;
        BasicInfo.language = curLocale.getLanguage();
        Log.d("TAG", "current language : " + BasicInfo.language);

        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this,
                    "SD 카드가 없습니다. SD 카드를 넣은 후 다시 실행하십시오.",
                    Toast.LENGTH_LONG).show();
            return;
        } else {
            String externalPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            if (!BasicInfo.ExternalChecked && externalPath != null) {
                BasicInfo.ExternalPath = externalPath + File.separator;
                Log.d(TAG, "External Path : " + BasicInfo.ExternalPath);

                BasicInfo.FOLDER_PHOTO = BasicInfo.ExternalPath + BasicInfo.FOLDER_PHOTO;
                BasicInfo.FOLDER_VIDEO = BasicInfo.ExternalPath + BasicInfo.FOLDER_VIDEO;
                BasicInfo.FOLDER_VOICE = BasicInfo.ExternalPath + BasicInfo.FOLDER_VOICE;
                BasicInfo.FOLDER_HANDWRITING = BasicInfo.ExternalPath + BasicInfo.FOLDER_HANDWRITING;
                BasicInfo.DATABASE_NAME = BasicInfo.ExternalPath + BasicInfo.DATABASE_NAME;
                BasicInfo.ExternalChecked = true;
            }
        }

        mMemoListView = findViewById(R.id.memoList);
        mMemoListAdapter = new MemoListAdapter(this);
        mMemoListView.setAdapter(mMemoListAdapter);
        mMemoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewMemo(position);
            }
        });

        Button newMemoButton = findViewById(R.id.newMemoButton);
        newMemoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "newMemoButton clicked.");

                Intent intent = new Intent(getApplicationContext(), MemoInsertActivity.class);
                intent.putExtra(BasicInfo.KEY_MEMO_MODE, BasicInfo.MODE_INSERT);
                startActivityForResult(intent, BasicInfo.REQ_INSERT_ACTIVITY);
            }
        });

        Button closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        checkDangerousPermissions();
    }

    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;

        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 있음", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "권한 없음", Toast.LENGTH_SHORT).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, permissions[i] +
                            "권한이 승인됨.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, permissions[i] +
                            "권한이 승인되지 않음..", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onStart() {
        openDatabase();
        loadMemoListData();
        super.onStart();
    }

    public void openDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
            mDatabase = null;
        }

        mDatabase = MemoDatabase.getInstance(this);
        boolean isOpen = mDatabase.open();

        if (isOpen) Log.d(TAG, "Memo database is open.");
        else Log.d(TAG, "Memo database is not open.");
    }

    public int loadMemoListData() {
        String SQL = "select _id, TITLE, INPUT_DATE, CONTENT_TEXT, ID_PHOTO, ID_VIDEO, " +
                "ID_VOICE, ID_HANDWRITING from MEMO order by INPUT_DATE desc";

        int recordCount = -1;
        if (MultiMemoActivity.mDatabase != null) {
            Cursor outCursor = MultiMemoActivity.mDatabase.rawQuery(SQL);

            recordCount = outCursor.getCount();
            Log.d(TAG, "cursor count : " + recordCount + "\n");

            mMemoListAdapter.clear();

            for (int i = 0; i < recordCount; i++) {
                outCursor.moveToNext();

                String memoId = outCursor.getString(0);
                String memoTitle = outCursor.getString(1);

                String dateStr = outCursor.getString(2);
                if (dateStr != null && dateStr.length() > 10) {
                    try {
                        Date inDate = BasicInfo.dateFormat.parse(dateStr);
                        dateStr = BasicInfo.dateNameFormat2.format(inDate);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    dateStr = "";
                }

                String memoStr = outCursor.getString(3);
                String photoId = outCursor.getString(4);
                String photoUriStr = getPhotoUriStr(photoId);

                String videoId = outCursor.getString(5);
                String videoUriStr = null;

                String voiceId = outCursor.getString(6);
                String voiceUriStr = null;

                String handwritingId = outCursor.getString(7);
                String handwritingUriStr = null;

                handwritingUriStr = getHandwritingUriStr(handwritingId);

                videoUriStr = getVideoUriStr(videoId);
                voiceUriStr = getVoiceUriStr(voiceId);

                mMemoListAdapter.addItem(new MemoListItem(memoId, memoTitle, dateStr, memoStr,
                        handwritingId, handwritingUriStr, photoId, photoUriStr,
                        videoId, videoUriStr, voiceId, voiceUriStr));
            }
            outCursor.close();

            mMemoListAdapter.notifyDataSetChanged();
        }

        return recordCount;
    }

    public String getPhotoUriStr(String idPhoto) {
        String photoUriStr = null;

        if (idPhoto != null && !idPhoto.equals("-1")) {
            String SQL = "select URI from " + MemoDatabase.TABLE_PHOTO + " where _ID = " +
                    idPhoto + "";
            Cursor photoCursor = MultiMemoActivity.mDatabase.rawQuery(SQL);
            if (photoCursor.moveToNext()) {
                photoUriStr = photoCursor.getString(0);
            }
            photoCursor.close();
        } else if (idPhoto == null || idPhoto.equals("-1")) {
            photoUriStr = "";
        }

        return photoUriStr;
    }

    public String getHandwritingUriStr(String idHandwriting) {
        Log.d(TAG, "Handwriting ID : " + idHandwriting);

        String handwritingUriStr = null;

        if (idHandwriting != null && idHandwriting.trim().length() > 0 && !idHandwriting.equals("-1")) {
            String SQL = "select URI from " + MemoDatabase.TABLE_HANDWRITING + " where _ID = "
                    + idHandwriting + "";
            Cursor handwritingCursor = MultiMemoActivity.mDatabase.rawQuery(SQL);
            if (handwritingCursor.moveToNext()) {
                handwritingUriStr = handwritingCursor.getString(0);
            }
            handwritingCursor.close();
        } else {
            handwritingUriStr = "";
        }

        return handwritingUriStr;
    }

    public String getVideoUriStr(String idVideo) {
        Log.d(TAG, "Video ID : " + idVideo);

        String videoUriStr = null;

        if (idVideo != null && idVideo.trim().length() > 0 && !idVideo.equals("-1")) {
            String SQL = "select URI from " + MemoDatabase.TABLE_VIDEO + " where _ID = "
                    + idVideo + "";
            Cursor videoCursor = MultiMemoActivity.mDatabase.rawQuery(SQL);
            if (videoCursor.moveToNext()) {
                videoUriStr = videoCursor.getString(0);
            }
            videoCursor.close();
        } else {
            videoUriStr = "";
        }

        return videoUriStr;
    }

    public String getVoiceUriStr(String idVoice) {
        Log.d(TAG, "Voice ID : " + idVoice);

        String voiceUriStr = null;
        if (idVoice != null && idVoice.trim().length() > 0 && !idVoice.equals("-1")) {
            String SQL = "select URI from " + MemoDatabase.TABLE_VOICE + " where _ID = "
                    + idVoice + "";
            Cursor voiceCursor = MultiMemoActivity.mDatabase.rawQuery(SQL);
            if (voiceCursor.moveToNext()) {
                voiceUriStr = voiceCursor.getString(0);
            }
            voiceCursor.close();
        } else {
            voiceUriStr = "";
        }

        return voiceUriStr;
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
