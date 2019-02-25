package com.example.sampleweb;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private Handler handler = new Handler();
    private Button loadButton;
    private EditText urlInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadButton = (Button) findViewById(R.id.loadButton);
        urlInput = (EditText) findViewById(R.id.urlInput);
        webView = (WebView) findViewById(R.id.webView);     // 웹뷰 객체 참조

        // 웹뷰에 WebSettings 설정
        WebSettings webSettings = webView.getSettings();
        // 웹뷰에 자바스크립트가 동작할 수 있는 환경 조성
        webSettings.setJavaScriptEnabled(true);

        // 웹뷰에 클라이언트 객체 지정
        webView.setWebChromeClient(new WebChromeClient());
        // 웹뷰에 자바스크립트 인터페이스 객체 지정
        webView.addJavascriptInterface(new JavaScriptMethods(), "sample");
        // 웹뷰에 샘플 페이지 로딩
        webView.loadUrl("file:///android_asset/www/sample.html");

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 사용자가 직접 입력한 일반 웹페이지 로딩
                webView.loadUrl(urlInput.getText().toString());
            }
        });
    }

    final class JavaScriptMethods {
        JavaScriptMethods(){}

        // 애플리케이션에서 정의한 메소드로 웹페이지에서 호출할 대상
        @android.webkit.JavascriptInterface
        public void clickOnFace() {
            handler.post(new Runnable() {   // 핸들러로 처리
                @Override
                public void run() {
                    // 애플리케이션 화면의 버튼 글자 변경
                    loadButton.setText("클릭 후 열기");
                    // 웹페이지의 자바스크립트 함수 호출
                    webView.loadUrl("javascript:changeFace()");
                }
            });
        }
    }

    // 웹브라우저 클라이언트 클래스 정의
    final class WebBrowserClient extends WebChromeClient {
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            result.confirm();

            return true;
        }
    }
}

