package com.example.sampleaudiorecorder;

import android.content.ContentValues;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.File;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {

    MediaRecorder recorder;     // 음성 재생을 위한 MediaPlayer 변수 선언
    MediaPlayer player;         // 음성 녹음을 위한 MediaRecorder 변수 선언

    String filename;            // 녹음된 음성을 저장할 파일 위치 변수 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startRecorderButton = findViewById(R.id.recorderButton);
        startRecorderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecording();
            }
        });

        Button stopRecorderButton = findViewById(R.id.recorderStopButton);
        stopRecorderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecording();
            }
        });

        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPlay();
            }
        });

        Button stopButton = findViewById(R.id.pauseButton);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlay();
            }
        });

        // SD 카드 경로 저장
        String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
        // 파일 위치 지정
        filename = sdcard + File.separator + "recorded.mp4";
        AutoPermissions.Companion.loadAllPermissions(this, 101);
    }

    public void startRecording() {
        // MediaRecorder 객체 생성
        if (recorder == null) {
            recorder = new MediaRecorder();
        }

        // MediaRecorder 객체에 필요한 정보 설정
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        recorder.setOutputFile(filename);

        // prepare()와 start() 메소드를 이용해 녹음 시작
        try {
            recorder.prepare();
            recorder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopRecording() {
        if (recorder == null) return;

        // 녹음 중지 후 리소스 해제
        recorder.stop();
        recorder.release();
        recorder = null;

        ContentValues values = new ContentValues(10);

        values.put(MediaStore.MediaColumns.TITLE, "Recorded");
        values.put(MediaStore.Audio.Media.ALBUM, "Audio Album");
        values.put(MediaStore.Audio.Media.DISPLAY_NAME, "Recorded Audio");
        values.put(MediaStore.Audio.Media.IS_RINGTONE, 1);
        values.put(MediaStore.Audio.Media.IS_MUSIC, 1);
        values.put(MediaStore.MediaColumns.DATE_ADDED,
                System.currentTimeMillis() / 1000);
        values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp4");
        values.put(MediaStore.Audio.Media.DATA, filename);

        // 녹음된 파일을 내용 제공자를 이용해 녹음 목록으로 저장
        Uri audioUri = getContentResolver().insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values);
        if (audioUri == null) {
            Log.d("SampleAudioRecorder", "Audio insert failed.");
            return;
        }
    }

    public void startPlay() {
        killMediaPlayer();

        // MediaPlayer 객체 생성 후 경로를 지정한 뒤, 녹음 파일 재생
        try {
            player = new MediaPlayer();
            player.setDataSource("file://" + filename);
            player.prepare();
            player.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopPlay() {
        // 녹음 파일 정지
        if (player != null) {
            player.stop();
        }
    }

    private void killMediaPlayer() {
        // 녹음 파일 재생 리소스 해제
        if (player != null) {
            try {
                player.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

    @Override
    public void onDenied(int i, String[] strings) {
        Toast.makeText(this, "permissions denied : " + strings.length, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGranted(int i, String[] strings) {
        Toast.makeText(this, "permissions granted : " + strings.length, Toast.LENGTH_LONG).show();
    }
}
