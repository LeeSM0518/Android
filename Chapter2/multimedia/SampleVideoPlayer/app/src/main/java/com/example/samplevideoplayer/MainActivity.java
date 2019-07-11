package com.example.samplevideoplayer;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    // 재생할 동영상의 파일 위치를 URL로 정의
    public static final String VIDEO_URL = "https://sites.google.com/site/ubiaccessmobile/sample_video.mp4";
    // VideoView 변수 선언
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // videoView 객체 참조
        videoView = findViewById(R.id.videoView);

        // 미디어 컨트롤러 객체 생성 후 VideoView 객체에 설정
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);

        Button button = findViewById(R.id.startButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // VideoView 객체에 재생할 동영상 URL 설정
                videoView.setVideoURI(Uri.parse(VIDEO_URL));
                videoView.requestFocus();
                videoView.start();
            }
        });
    }
}