package com.example.sampleaudioplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 재생할 오디오의 파일 위치를 URL 로 정의
    public static final String AUDIO_URL = "http://sites.google.com/site/ubiaccessmobile/sample_audio.amr";

    // MediaPlayer 변수 선언
    MediaPlayer mediaPlayer;
    // 재생 위치를 저장할 변수 선언
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 음악 재생 메소드 호출
                playAudio(AUDIO_URL);
                Toast.makeText(getApplicationContext(),
                        "음악 파일 재생 시작됨.",
                        Toast.LENGTH_LONG).show();
            }
        });

        Button stopButton = findViewById(R.id.stopButton);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    // 음악 정지 메소드 호출
                    mediaPlayer.stop();
                    Toast.makeText(getApplicationContext(),
                            "음악 파일 재생 중지됨.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        Button pauseButton = findViewById(R.id.pauseButton);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    // 현재 재생 위치를 저장
                    position = mediaPlayer.getCurrentPosition();
                    // 음악 일시 정지 메소드 호출
                    mediaPlayer.pause();
                    Toast.makeText(getApplicationContext()
                            , "음악 파일 재생 일시정지됨."
                            , Toast.LENGTH_LONG).show();
                }
            }
        });

        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                    // 음악 시작
                    mediaPlayer.start();
                    // 중단했었던 위치로 이동
                    mediaPlayer.seekTo(position);
                    Toast.makeText(getApplicationContext(),
                            "음악 파일 재생 재시작됨.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void playAudio(String url) {
        // 이미 리소스를 사용하고 있을 경우에 release() 메소드를 호출하여
        // 리소스를 해제하는 역할
        killMediaPlayer();

        try {
            // MediaPlayer 객체 생성하고 초기화 후 시작
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        killMediaPlayer();
    }

    private void killMediaPlayer() {
        if (mediaPlayer != null) {
            try {
                // MediaPlayer 리소스 해제
                mediaPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
