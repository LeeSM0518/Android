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
