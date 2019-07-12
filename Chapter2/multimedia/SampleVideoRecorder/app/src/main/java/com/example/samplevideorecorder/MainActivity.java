package com.example.samplevideorecorder;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.File;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {

    MediaPlayer player;
    MediaRecorder recorder;

    String filename;

    SurfaceHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 카메라 미리보기가 보일 SurfaceView 객체 생성 및 설정
        SurfaceView surface = new SurfaceView(this);
        holder = surface.getHolder();
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        // XML 레이아웃에 정의한 FrameLayout 객체에 SurfaceView 추가
        FrameLayout frame = findViewById(R.id.frameLayout);
        frame.addView(surface);

        Button startRecorderButton = findViewById(R.id.startRecorderButton);
        startRecorderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecording();
            }
        });

        Button stopRecorderButton = findViewById(R.id.stopRecorderButton);
        stopRecorderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecording();
            }
        });

        Button startPlayButton = findViewById(R.id.startPlayButton);
        startPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPlay();
            }
        });

        Button stopPlayButton = findViewById(R.id.stopPlayButton);
        stopPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlay();
            }
        });

        String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
        filename = sdcard + File.separator + "recorded.mp4";

        AutoPermissions.Companion.loadAllPermissions(this, 101);
    }

    public void startRecording() {
        // MediaRecorder 객체 생성
        if (recorder == null) recorder = new MediaRecorder();

        // MediaRecorder 객체에 필요한 정보 설정
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        recorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);
        recorder.setOutputFile(filename);

        // MediaRecorder에 미리보기 디스플레이어로 SurfaceView 객체의 화면 설정
        recorder.setPreviewDisplay(holder.getSurface());

        // 메소드로 녹화 시작
        try {
            recorder.prepare();
            recorder.start();
        } catch (Exception e) {
            e.printStackTrace();

            recorder.release();
            recorder = null;
        }
    }

    public void stopRecording() {
        if (recorder == null) return;

        // 녹화 중지 후 리소스 해제
        recorder.stop();
        recorder.reset();
        recorder.release();
        recorder = null;

        ContentValues values = new ContentValues(10);

        values.put(MediaStore.MediaColumns.TITLE, "RecordedVideo");
        values.put(MediaStore.Audio.Media.ALBUM, "Video Album");
        values.put(MediaStore.Audio.Media.ARTIST, "Mike");
        values.put(MediaStore.Audio.Media.DISPLAY_NAME, "Recorded Video");
        values.put(MediaStore.MediaColumns.DATA, System.currentTimeMillis() / 1000);
        values.put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4");
        values.put(MediaStore.Audio.Media.DATA, filename);

        // 녹화된 파일을 내용 제공자를 이용해 동영상 목록으로 저장
        Uri videoUri = getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values);
        if (videoUri == null) {
            Log.d("SampleVideoRecorder", "Video insert failed");
            return;
        }

        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, videoUri));
    }

    public void startPlay() {
        if (player == null) {
            player = new MediaPlayer();

            try {
                player.setDataSource(filename);
                player.setDisplay(holder);
                player.prepare();
                player.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stopPlay() {
        if (player == null) return;

        player.stop();
        player.release();
        player = null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

    @Override
    public void onDenied(int i, String[] strings) {
        Toast.makeText(this, "permissions denied : " + strings.length,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGranted(int i, String[] strings) {
        Toast.makeText(this, "permissions granted : " + strings.length,
                Toast.LENGTH_LONG).show();
    }

}
