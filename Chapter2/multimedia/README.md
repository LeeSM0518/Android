# 10-1. 오디오 재생하기

멀티미디어 지원을 위해 제공하는 미디어 API는 android.media 패키지에 들어 있으며 그 안의 여러 클래스들 중에서 핵심이 되는 것은 **'미디어플레이어(MediaPlayer)'** 이다.

오디오 파일을 재생하기 위해서는 대상을 지정해야 하는데 이때 사용되는 데이터 소스 지정 방법은 크게 세가지로 나눌 수 있다.

| 파일 지정 방식                      | 방법                                                         |
| ----------------------------------- | ------------------------------------------------------------ |
| 인터넷에 있는 파일 위치 지정        | 미디어가 있는 위치를 URL로 지정한다.                         |
| 프로젝트 파일에 포함한 후 위치 지정 | 앱을 개발하여 배포하는 과정에서 프로젝트의 리소스 또는 애셋(assets) 폴더에 넣은 후 그 위치를 지정한다. |
| 단말 SD카드에 넣은 후 위치 지정     | 단말에 넣어 둔 SD카드에 파일을 넣은 후 그 위치를 지정합니다. 일반적으로 미디어 파일은 크기가 커서 SD카드에 저장하는 경우가 많으므로 가장 많이 사용되는 방식이다. |



**음악 파일 재생 과정**

1. 대상 파일을 알려주는 것으로 setDataSource() 메소드를 이용하여 URL 지정

2. prepare() 메소드를 호출하여 재생 준비

3. start() 메소드를 호출하여 음악 파일 재생



## 10-1. 오디오 재생(예제)

**AndroidManifest.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.sampleaudioplayer">

    <uses-permission android:name="android.permission.INTERNET"/>

    <application
        android:usesCleartextTraffic="true"
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```

**activity_main.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/startButton"
        android:layout_margin="30dp"
        android:layout_gravity="center"
        android:text="재생"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <Button
        android:id="@+id/stopButton"
        android:layout_margin="30dp"
        android:layout_gravity="center"
        android:text="중지"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <Button
        android:id="@+id/pauseButton"
        android:layout_margin="30dp"
        android:layout_gravity="center"
        android:text="일시정지"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <Button
        android:id="@+id/restartButton"
        android:layout_margin="30dp"
        android:layout_gravity="center"
        android:text="재시작"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

</LinearLayout>
```

**MainActivity.java**

```java
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
```

* **stop() 메소드를 이용하여 재생을 중지했을 때 또 다른 작업을 수행하고 싶을때 사용하는 메소드**

```java
// 재생이 중지되었을 때 호출되는 메소드
public abstract void onCompletion(MediaPlayer mp)
```



# 10-2. 동영상 재생하기

동영상을 재생하고 싶다면 **비디오뷰(VideoView)** 위젯을 이용하면 되는데 **XML 레이아웃에 \<VideoView> 태그를** 추가하기만 하면 동영상 플레이어를 만들 수 있다.



## 10-2. 동영상 재생(예제)

**AndroidManifest.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.samplevideoplayer">

    <uses-permission android:name="android.permission.INTERNET"/>

    <application
        android:usesCleartextTraffic="true"
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```

**activity_main.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/startButton"
        android:layout_marginTop="20dp"
        android:width="180dp"
        android:layout_gravity="center"
        android:text="재생"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <!-- 동영상이 재생될 영역-->
    <VideoView
        android:id="@+id/videoView"
        android:layout_marginTop="20dp"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</LinearLayout>
```

**MainActivity.java**

```java
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
```

**실행 결과**

<img src="../../capture/스크린샷 2019-07-12 오전 12.29.12">



# 10-3. 오디오 녹음하여 저장하기

오디오나 동영상을 재생할 때 미디어플레이어가 사용되는 것처럼 오디오 녹음이나 동영상 녹화를 위해서는 **미디어리코더(MediaRecorder)** 가 사용된다.

* **녹음하는 과정**

| 과정                          | 기능                                                         |
| ----------------------------- | ------------------------------------------------------------ |
| 미디어리코더 객체 생성        | 오디오 녹음을 위해 미디어리코더 객체를 new 연산자를 이용하여 만듭니다. |
| 오디오 입력 및 출력 형식 설정 | 오디오 정보를 입력받을 데이터 소스와 함께 출력 형식을 설정한다. |
| 오디오 인코더와 파일 지정     | 오디오 파일을 만들 때 필요한 인코더(Encoder)와 함께 파일 이름을 지정합니다. |
| 녹음 시작                     | 녹음을 시작하면 오디오 파일이 만들어지고 인코딩된 바이트 스트림이 저장됩니다. |
| 매니페스트에 권한 설정        | 앱에서 녹음을 하려면 RECORD_AUDIO 권한이 있어야 하므로 매니페스트에 이 권한을 추가한다. |



