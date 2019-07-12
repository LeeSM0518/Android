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



## 10-3. 녹음하고 재생(예제)

**AndroidManifest.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.sampleaudiorecorder">

    <uses-permission android:name="android.permission.RECORD_AUDIO"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>

    <application
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

**build.gradle**

```
apply plugin: 'com.android.application'

android {
    compileSdkVersion 28
    defaultConfig {
        applicationId "com.example.sampleaudiorecorder"
        minSdkVersion 15
        targetSdkVersion 28
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}

allprojects {
    repositories {
        maven { url 'https://jitpack.io'}
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:28.0.0'
    implementation 'com.android.support.constraint:constraint-layout:1.1.3'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'
    implementation 'com.github.pedroSG94:AutoPermissions:1.0.3'
}
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
        android:id="@+id/recorderButton"
        android:text="녹음"
        android:layout_gravity="center"
        android:layout_marginTop="20dp"
        android:layout_width="130dp"
        android:layout_height="wrap_content" />
    <Button
        android:id="@+id/recorderStopButton"
        android:text="녹음중지"
        android:layout_gravity="center"
        android:layout_marginTop="20dp"
        android:layout_width="130dp"
        android:layout_height="wrap_content" />
    <Button
        android:id="@+id/startButton"
        android:text="재생"
        android:layout_gravity="center"
        android:layout_marginTop="20dp"
        android:layout_width="130dp"
        android:layout_height="wrap_content" />
    <Button
        android:id="@+id/pauseButton"
        android:text="재생중지"
        android:layout_gravity="center"
        android:layout_marginTop="20dp"
        android:layout_width="130dp"
        android:layout_height="wrap_content" />

</LinearLayout>
```

**MainActivity.java**

```java
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
```



# 10-4. 동영상 녹화하기

오디오 녹음에 사용하던 MediaRecorder 객체는 동영상 녹화에도 그대로 이용할 수 있다.



## 10-4. 동영상 녹화(예제)

**AndroidManifest.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.samplevideorecorder">

    <uses-permission android:name="android.permission.RECORD_AUDIO"/>
    <uses-permission android:name="android.permission.CAMERA"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>

    <application
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

**build.gradle**

```groovy
apply plugin: 'com.android.application'

android {
    compileSdkVersion 28
    defaultConfig {
        applicationId "com.example.samplevideorecorder"
        minSdkVersion 15
        targetSdkVersion 28
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}

allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:28.0.0'
    implementation 'com.android.support.constraint:constraint-layout:1.1.3'
    implementation 'com.github.pedroSG94:AutoPermissions:1.0.3'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'
}
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

    <LinearLayout
        android:orientation="horizontal"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <Button
            android:id="@+id/startRecorderButton"
            android:layout_marginTop="10dp"
            android:layout_marginLeft="15dp"
            android:text="녹화 시작"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />

        <Button
            android:id="@+id/stopRecorderButton"
            android:layout_marginTop="10dp"
            android:layout_marginLeft="10dp"
            android:text="녹화 정지"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />

        <Button
            android:id="@+id/startPlayButton"
            android:layout_marginTop="10dp"
            android:layout_marginLeft="10dp"
            android:text="재생 시작"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />

        <Button
            android:id="@+id/stopPlayButton"
            android:layout_marginTop="10dp"
            android:layout_marginLeft="10dp"
            android:text="재생 정지"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />

    </LinearLayout>

    <FrameLayout
        android:layout_margin="10dp"
        android:id="@+id/frameLayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

    </FrameLayout>

</LinearLayout>
```

**MainActivity.java**

```java
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
```



# 10-5. 카메라로 사진 찍어 저장하기

카메라로 사진을 찍기 위해 사용되는 방법은 크게 두 가지로 나눌 수 있다.

1. 앱의 화면에 **카메라 미리보기를** 보여주고 직접 사진을 찍어 처리하기
2. **인텐트를 이용해** 단말의 카메라 앱을 실행한 후 결과 사진을 받아 처리하기



## 10-5. 카메라 미리보기 사용(예제)

**AndroidManifest.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.samplecapture">

    <uses-permission android:name="android.permission.CAMERA"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>

    <uses-feature android:name="android.hardware.camera2"
        android:required="true"/>

    <application
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

**gradle.build**

```groovy
apply plugin: 'com.android.application'

android {
    compileSdkVersion 28
    defaultConfig {
        applicationId "com.example.samplecapture"
        minSdkVersion 15
        targetSdkVersion 28
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}

allprojects {
    repositories {
        maven {
            url 'https://jitpack.io'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:28.0.0'
    implementation 'com.android.support.constraint:constraint-layout:1.1.3'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'

    implementation 'com.github.pedroSG94:AutoPermissions:1.0.3'
}
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
        android:id="@+id/captureButton"
        android:text="사진찍어 저장하기"
        android:textSize="20dp"
        android:layout_gravity="center"
        android:layout_marginTop="20dp"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <FrameLayout
        android:id="@+id/frameLayout"
        android:layout_margin="20dp"
        android:layout_width="match_parent"
        android:layout_height="match_parent"></FrameLayout>

</LinearLayout>
```

**MainActivity.java**

```java
package com.example.samplecapture;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {

    CameraSurfaceView cameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout previewFrame = findViewById(R.id.frameLayout);

        // 새로 정의한 CameraSurfaceView 객체 생성
        cameraView = new CameraSurfaceView(this);
        previewFrame.addView(cameraView);

        Button button = findViewById(R.id.captureButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        AutoPermissions.Companion.loadAllPermissions(this, 101);
    }

    public void takePicture() {
        // 사진찍기 버튼을 클릭했을 때 capture() 메소드를 호출하면서 콜백 메소드 정의
        cameraView.capture(new Camera.PictureCallback() {
            // 찍은 사진 데이터를 받을 onPictureTaken() 메소드 정의
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                try {
                    // data 변수에 들어 있는 JPEG 이미지를 Bitmap 객체로 디코딩
                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    // 사진 목록에 추가 (미디어 앨범에 추가)
                    String outUriStr = MediaStore.Images.Media.insertImage(
                            getContentResolver(),
                            bitmap,
                            "Captured Image",
                            "Captured Image using Camera."
                    );

                    if (outUriStr == null) {
                        Log.d("SampleCapture", "Image insert failed.");
                        return;
                    } else {
                        Uri outUri = Uri.parse(outUriStr);
                        sendBroadcast(new Intent(
                                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, outUri
                        ));
                    }

                    // 카메라 미리보기 다시 시작
                    camera.startPreview();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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
        Toast.makeText(this, "permissions granted: " + strings.length,
                Toast.LENGTH_LONG).show();
    }

    // SurfaceView 클래스를 상속하고 Callback 인터페이스를 구현하는
    // 새로운 CameraSurfaceView 클래스 정의
    private class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
        private SurfaceHolder mHolder;
        private Camera camera = null;

        public CameraSurfaceView(Context context) {
            super(context);

            // 생성자에서 서피스홀더 객체 참조 후 설정
            mHolder = getHolder();
            mHolder.addCallback(this);
        }

        // 서피스뷰가 만들어질 때 카메라 객체를 참조한 후
        // 미리보기 화면으로 홀더 객체 설정
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            camera = Camera.open();

            setCameraOrientation();

            try {
                camera.setPreviewDisplay(mHolder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 서비스뷰의 화면 크기가 바뀌는 등의 변경 시점에 미리보기 시작
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            camera.startPreview();
        }

        // 서피스뷰가 없어질 때 미리보기 중지
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }

        // 카메라 객체의 takePicture() 메소드를 호출하여 사진 촬영
        public boolean capture(Camera.PictureCallback handler) {
            if (camera != null) {
                camera.takePicture(null, null, handler);
                return true;
            } else {
                return false;
            }
        }

        public void setCameraOrientation() {
            if (camera == null) return;

            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(0, info);

            WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            int rotation = manager.getDefaultDisplay().getRotation();

            int degrees = 0;

            switch (rotation) {
                case Surface.ROTATION_0: degrees = 0; break;
                case Surface.ROTATION_90: degrees = 90; break;
                case Surface.ROTATION_180: degrees = 180; break;
                case Surface.ROTATION_270: degrees = 270; break;
            }

            int result;

            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                result = (info.orientation + degrees) % 360;
                result = (360 - result) % 360;
            } else {
                result = (info.orientation - degrees + 360) % 360;
            }

            camera.setDisplayOrientation(result);
        }
    }
}
```



## 10-5. 인텐트를 이용해 카메라 사용(예제)

**AndroidManifest.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.samplecaptureintent">

    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>

    <uses-feature android:name="android.hardware.Camera"
        android:required="true"/>

    <application
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

        <provider
            android:authorities="com.example.samplecaptureintent.fileprovider"
            android:name="android.support.v4.content.FileProvider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/external"
                />
        </provider>

    </application>

</manifest>
```

**res/xml/external.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<paths xmlns:android="http://schemas.android.com/apk/res/android">
    <external-path name="sdcard" path="."/>
</paths>
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
        android:id="@+id/button"
        android:layout_gravity="center"
        android:layout_marginTop="15dp"
        android:text="사진찍기"
        android:textSize="20dp"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <ImageView
        android:id="@+id/imageView"
        android:layout_margin="20dp"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</LinearLayout>
```

**MainActivity.java**

```java
package com.example.samplecaptureintent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.File;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {

    ImageView imageView;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        AutoPermissions.Companion.loadAllPermissions(this, 101);
    }

    public void takePicture() {
        if (file == null) {
            file = createFile();
        }

        Uri fileUri = FileProvider.getUriForFile(this, "com.example.samplecaptureintent.fileprovider", file);

        // 인텐트 객체를 만들고 startActivityForResult() 메소드 호출
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 101);
        }
    }

    // 결과물로 받을 이미지 파일 생성
    private File createFile() {
        String filename = "capture.jpg";
        File storageDir = Environment.getExternalStorageDirectory();
        File outFile = new File(storageDir,filename);

        return outFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101 && resultCode == RESULT_OK) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            // 생성된 사진 파일로 비트맵 객체 만들기
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);

            // 비트맵 객체를 이미지뷰에 설정하기
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

    @Override
    public void onDenied(int requestCode, String[] permissions) {
        Toast.makeText(this, "permissions denied : " + permissions.length,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGranted(int requestCode, String[] permissions) {
        Toast.makeText(this, "permissions granted : " + permissions.length, Toast.LENGTH_LONG).show();
    }
}
```