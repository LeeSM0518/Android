package com.example.multimemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MemoListItemView extends LinearLayout {

    private ImageView itemPhoto;
    private TextView itemTitle;
    private TextView itemDate;
    private TextView itemText;
    private ImageView itemVideoState;
    private ImageView itemVoiceState;
    private ImageView itemHandwriting;
    private Context mContext;
    private String mVideoUri;
    private String mVoiceUri;

    Bitmap bitmap;

    public MemoListItemView(Context context) {
        super(context);
        mContext = context;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.memo_listitem, this, true);

        itemPhoto = findViewById(R.id.imageView);
        itemTitle = findViewById(R.id.titleTextView);
        itemText = findViewById(R.id.textView);
        itemHandwriting = findViewById(R.id.handwritingImageView);
        itemVideoState = findViewById(R.id.videoImageView);
        itemVoiceState = findViewById(R.id.recordImageView);

        itemVideoState.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mVideoUri != null && mVideoUri.trim().length() > 0 && !mVideoUri.equals("-1")) {
                    showVideoPlayingActivity();
                } else {
                    Toast.makeText(mContext, "재생할 동영상이 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        itemVoiceState.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mVoiceUri != null && mVoiceUri.trim().length() > 0 && !mVoiceUri.equals("-1")) {
                    showVoicePlayingActivity();
                } else {
                    Toast.makeText(mContext, "재생할 음성이 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showVoicePlayingActivity() {
        Intent intent = new Intent(mContext, VoicePlayActivity.class);
        intent.putExtra(BasicInfo.KEY_URI_VOICE, BasicInfo.FOLDER_VOICE + mVoiceUri);

        mContext.startActivity(intent);
    }

    public void showVideoPlayingActivity() {
        Intent intent = new Intent(mContext, VideoPlayActivity.class);
        if (BasicInfo.isAbsoluteVideoPath(mVideoUri)) {
            intent.putExtra(BasicInfo.KEY_URI_VIDEO, BasicInfo.FOLDER_VIDEO + mVideoUri);
        } else {
            intent.putExtra(BasicInfo.KEY_URI_VIDEO, mVideoUri);
        }

        mContext.startActivity(intent);
    }

    public void setContent(int index, String data) {
        final boolean DATA_NULL_CHECK = data == null || data.equals("-1") || data.equals("");

        if (index == 0) {
            itemTitle.setText(data);
        } else if (index == 1) {
            itemDate.setText(data);
        } else if (index == 2) {
            itemText.setText(data);
        } else if (index == 3) {
            if (DATA_NULL_CHECK) {
                itemHandwriting.setImageResource(R.drawable.handwriting2);
            } else {
                itemHandwriting.setImageURI(Uri.parse(BasicInfo.FOLDER_HANDWRITING + data));
            }
        } else if (index == 4) {
            if (DATA_NULL_CHECK) {
                if (BasicInfo.language.equals("ko")) {
                    itemPhoto.setImageResource(R.drawable.photo);
                }
            } else {
                if (bitmap != null) {
                    bitmap.recycle();
                }
            }

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 0;
            bitmap = BitmapFactory.decodeFile(BasicInfo.FOLDER_PHOTO + data, options);

            itemPhoto.setImageBitmap(bitmap);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setMediaState(String sVideoUri, String sVoiceUri) {
        mVideoUri = sVideoUri;
        mVoiceUri = sVoiceUri;

        if (sVideoUri == null || sVideoUri.trim().length() < 1 || sVideoUri.equals("-1")) {
            itemVideoState.setImageResource(R.drawable.recempty);
        } else {
            itemVideoState.setImageResource(R.drawable.rec);
        }

        if (sVoiceUri == null || sVoiceUri.trim().length() < 1 || sVoiceUri.equals("-1")) {
            itemVoiceState.setImageResource(R.drawable.microphone_empty);
        } else {
            itemVoiceState.setImageResource(R.drawable.microphone);
        }
    }

}
