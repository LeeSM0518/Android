package com.example.samplepdfview;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButton1Clicked(View v) {
        EditText editText = (EditText)findViewById(R.id.editText);

        // 입력상자에 입력된 파일명 확인
        String filename = editText.getText().toString();

        if (filename.length() > 0 ) {
            // openPDF() 메소드 호출
            openPDF(filename.trim());
        } else {
            Toast.makeText(getApplicationContext(), "PDF 파일명을 입력하세요.",
                    Toast.LENGTH_LONG).show();
        }
    }

    // PDF 파일 열기 기능을 정의한 메소드
    public void openPDF(String filename) {
        // SD 카드 폴더의 패스 추가. SD 카드에 저장되어 있는 PDF 파일을 지정하기 위해서
        String sdcardFolder = Environment.getExternalStorageDirectory().getAbsolutePath();
        String filepath = sdcardFolder + File.separator + filename;
        File file = new File(filepath);

        if (file.exists()) {
            // Uri 객체로 생성
            Uri uri = Uri.fromFile(file);

            // ACTION_VIEW 액션을 가지는 인텐트 객체 생성
            Intent intent = new Intent(Intent.ACTION_VIEW);

            // Uri 객체와 MIME 타입 지정
            intent.setDataAndType(uri, "application/pdf");

            try {
                // 액티비티 띄우기
                startActivity(intent);
            } catch (ActivityNotFoundException ex) {
                Toast.makeText(this, "PDF 파일을 보기 위한 뷰어 앱이 없습니다.",
                        Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "PDF 파일이 없습니다.", Toast.LENGTH_LONG).show();
        }
    }
}
