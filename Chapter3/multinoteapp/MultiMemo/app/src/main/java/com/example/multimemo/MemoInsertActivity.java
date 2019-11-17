package com.example.multimemo;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.multimemo.common.TitleBitmapButton;
import com.example.multimemo.db.MemoDatabase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class MemoInsertActivity extends AppCompatActivity {

    private static final String TAG = "MemoInsertActivity";

    Button dateButton;
    Button titleButton;

    ImageView imageView;
    Button videoButton;
    Button recordButton;

    EditText editText;

    Button saveButton;
    Button closeButton;

    String mMemoMode;
    String mMemoId;
    String mMemoTitle;
    String mMemoDate;

    String mMediaPhotoId;
    String mMediaPhotoUri;
    String mMediaVideoId;
    String mMediaVideoUri;

    String tempPhotoUri;
    String tempVideoUri;
    String tempVoiceUri;

    String mDateStr;
    String mTitleStr;
    String mMemoStr;

    boolean isPhotoCaptured;
    boolean isVideoRecorded;
    boolean isVoiceRecorded;

    boolean isPhotoFileSaved;
    boolean isVideoFileSaved;
    boolean isVoiceFileSaved;

    boolean isPhotoCanceled;
    boolean isVideoCanceled;
    boolean isVoiceCanceled;

    Calendar mCalendar = Calendar.getInstance();

    Bitmap resultPhotoBitmap;

    int textViewMode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_insert);

        dateButton = findViewById(R.id.dateButton);
        titleButton = findViewById(R.id.titleButton);
        imageView = findViewById(R.id.imageView);
        videoButton = findViewById(R.id.videoButton);
        recordButton = findViewById(R.id.recordButton);

        editText = findViewById(R.id.inputEditText);

        saveButton = findViewById(R.id.saveButton);
        closeButton = findViewById(R.id.closeButton);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPhotoCaptured || isPhotoFileSaved) {
                    showDialog(BasicInfo.CONTENT_PHOTO_EX);
                } else {
                    showDialog(BasicInfo.CONTENT_PHOTO);
                }
            }
        });
    }

    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mCalendar.set(year, month, dayOfMonth);

            String monthStr = String.valueOf(month + 1);
            if (month < 9) monthStr = "0" + monthStr;

            String dayStr = String.valueOf(dayOfMonth);
            if (dayOfMonth < 10) dayStr = "0" + dayStr;

            dateButton.setText(year + "년 " + monthStr + "월 " + dayStr + "일");
        }
    };

    private void setCalendar() {
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mDateStr = dateButton.getText().toString();
                Calendar calendar = Calendar.getInstance();
                Date date = new Date();
                try {
                    date = BasicInfo.dateDayNameFormat.parse(mDateStr);
                } catch (Exception ex) {
                    Log.d(TAG, "Exception in parsing date : " + date);
                }

                calendar.setTime(date);

                new DatePickerDialog(
                        MemoInsertActivity.this,
                        dateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });

        int year = mCalendar.get(Calendar.YEAR);
        int monthOfYear = mCalendar.get(Calendar.MONTH);
        int dayOfMonth = mCalendar.get(Calendar.DAY_OF_MONTH);

        String monthStr = String.valueOf(monthOfYear+1);
        if (monthOfYear < 9) {
            monthStr = "0" + monthStr;
        }

        String dayStr = String.valueOf(dayOfMonth);
        if (dayOfMonth < 10) {
            dayStr = "0" + dayStr;
        }

        dateButton.setText(year + "년 " + monthStr + "월 " + dayStr + "일");
    }

    private String insertPhoto() {
        String photoName = null;

        if (isPhotoCaptured) {
            try {
                if (mMemoMode != null && (mMemoMode.equals(BasicInfo.MODE_MODIFY)
                        || mMemoMode.equals(BasicInfo.MODE_VIEW))) {
                    Log.d(TAG, "previous photo is newly created for modify mode.");

                    String SQL = "delete from " + MemoDatabase.TABLE_PHOTO +
                            " where _ID = '" + mMediaPhotoId + "'";
                    Log.d(TAG, "SQL : " + SQL);
                    if (MultiMemoActivity.mDatabase != null) {
                        MultiMemoActivity.mDatabase.execSQL(SQL);
                    }

                    File previousFile = new File(BasicInfo.FOLDER_PHOTO + mMediaPhotoUri);
                    if (previousFile.exists()) previousFile.delete();
                }

                File photoFolder = new File(BasicInfo.FOLDER_PHOTO);

                if (!photoFolder.isDirectory()) {
                    Log.d(TAG, "creating photo folder : " + photoFolder);
                    photoFolder.mkdirs();
                }

                photoName = createFilename();

                FileOutputStream outputStream = new FileOutputStream(BasicInfo.FOLDER_PHOTO +
                        photoName);
                resultPhotoBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                outputStream.close();

                if (photoName != null) {
                    Log.d(TAG, "isCaptured : " + isPhotoCaptured);

                    String SQL = "insert into " + MemoDatabase.TABLE_PHOTO + "(URI) values(" +
                            "'" + photoName + "')";
                    if (MultiMemoActivity.mDatabase != null)
                        MultiMemoActivity.mDatabase.execSQL(SQL);
                }
            } catch (IOException ex) {
                Log.d(TAG, "Exception in copying photo : " + ex.toString());
            }
        }

        return photoName;
    }

    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder builder = null;

        switch (id) {
            case BasicInfo.CONFIRM_TEXT_INPUT:

        }
    }

    private void deleteMemo() {
        Log.d(TAG, "deleting previous photo record and file : " + mMediaPhotoId);
        String SQL = "delete from " + MemoDatabase.TABLE_PHOTO +
                " where _ID = '" + mMediaPhotoId + "'";
        Log.d(TAG, "SQL : " + SQL);
        if (MultiMemoActivity.mDatabase != null) {
            MultiMemoActivity.mDatabase.execSQL(SQL);
        }

        File photoFile = new File(BasicInfo.FOLDER_PHOTO + mMediaPhotoUri);
        if (photoFile.exists()) {
            photoFile.delete();
        }

        Log.d(TAG, "deleting previous memo record : " + mMemoId);
        SQL = "delete from " + MemoDatabase.TABLE_MEMO +
                " where _id = '" + mMemoId + "'";
        Log.d(TAG, "SQL : " + SQL);
        if (MultiMemoActivity.mDatabase != null) {
            MultiMemoActivity.mDatabase.execSQL(SQL);
        }

        setResult(RESULT_OK);
        finish();
    }

    private String createFilename() {
        Date curDate = new Date();
        String curDateStr = String.valueOf(curDate.getTime());

        return curDateStr;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case  BasicInfo.REQ_PHOTO_CAPTURE_ACTIVITY:
                Log.d(TAG, "onActivityResult() for REQ_CAPTURE_ACTIVITY.");

                if (requestCode == RESULT_OK) {
                    Log.d(TAG, "resultCode : " + resultCode);
                    boolean isPhotoExists = checkCapturedPhotoFile();
                    if (isPhotoExists) {
                        Log.d(TAG, "image file exists : " + BasicInfo.FOLDER_PHOTO
                                + "captured");
                        resultPhotoBitmap = BitmapFactory.decodeFile(
                                BasicInfo.FOLDER_PHOTO
                                + "captured");

                        tempPhotoUri = "captured";

                        imageView.setImageBitmap(resultPhotoBitmap);
                        isPhotoCaptured = true;

                        imageView.invalidate();
                    } else {
                        Log.d(TAG, "image file doesn't exists : " + BasicInfo.FOLDER_PHOTO +
                                "captured");
                    }
                }
                break;

            case BasicInfo.REQ_PHOTO_SELECTION_ACTIVITY:
                Log.d(TAG, "onActivityResult() for REQ_PHOTO_LOADING_ACTIVITY.");

                if (requestCode == RESULT_OK) {
                    Log.d(TAG, "resultCode : " + resultCode);

                    Uri getPhotoUri = data.getParcelableExtra(BasicInfo.KEY_URI_PHOTO);
                    try {
                        resultPhotoBitmap = BitmapFactory.decodeStream(getContentResolver()
                                .openInputStream(getPhotoUri), null, null);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }

                    imageView.setImageBitmap(resultPhotoBitmap);
                    isPhotoCaptured = true;

                    imageView.invalidate();
                }
                break;

            case BasicInfo.REQ_VIDEO_LOADING_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    String getVideoUri = data.getStringExtra(BasicInfo.KEY_URI_VIDEO);
                    tempVideoUri = BasicInfo.URI_MEDIA_FORMAT + getVideoUri;
                    isVideoRecorded = true;

                    videoButton.setCompoundDrawablesWithIntrinsicBounds(null,
                            getResources().getDrawable(R.drawable.rec), null, null);
                }
                break;

            case BasicInfo.REQ_VIDEO_RECORDING_ACTIVITY:
                Log.d(TAG, "onActivityResult() for REQ_VIDEO_RECORDING_ACTIVITY.");

                if (resultCode == RESULT_OK) {
                    boolean isVideoExists = checkRecordedVideoFile();
                    if (isVideoExists) {
                        tempVideoUri = "recorded";
                        isVideoRecorded = true;
                        videoButton.setCompoundDrawablesWithIntrinsicBounds(
                                null,
                                getResources().getDrawable(R.drawable.rec),
                                null, null
                        );
                    }
                }
                break;

            case BasicInfo.REQ_VOICE_RECORDING_ACTIVITY:
                Log.d(TAG, "onActivityResult() for REQ_VOICE_RECORDING_ACTIVITY.");

                if (resultCode == RESULT_OK) {
                    boolean isVoiceExists = checkRecordedVoiceFile();
                    if (isVoiceExists) {
                        tempVoiceUri = "recorded";

                        isVoiceRecorded = true;

                        recordButton.setCompoundDrawablesWithIntrinsicBounds(
                                null,
                                getResources().getDrawable(R.drawable.microphone),
                                null, null
                        );
                    }
                }
                break;
        }
    }

    // TODO PhotoCaptureActivity 구현
    private void showPhotoCaptureActivity() {
        Intent intent = new Intent(getApplicationContext(), PhotoCaptureActivity.class);
        startActivityForResult(intent, BasicInfo.REQ_PHOTO_CAPTURE_ACTIVITY);
    }

    // TODO PhotoSelectionActivity 구현
    private void showPhotoLoadingActivity() {
        Intent intent = new Intent(getApplicationContext(), PhotoSelectionActivity.class);
        startActivityForResult(intent, BasicInfo.REQ_PHOTO_SELECTION_ACTIVITY);
    }

    private boolean checkCapturedPhotoFile() {
        File file = new File(BasicInfo.FOLDER_PHOTO + "captured");
        return file.exists();
    }

    private boolean checkRecordedVideoFile() {
        File file = new File(BasicInfo.FOLDER_VIDEO + "recorded");
        return file.exists();
    }

    private boolean checkRecordedVoiceFile() {
        File file = new File(BasicInfo.FOLDER_VOICE + "recorded");
        return file.exists();
    }

}