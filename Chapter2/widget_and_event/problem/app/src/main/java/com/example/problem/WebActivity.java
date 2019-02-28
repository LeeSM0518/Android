package com.example.problem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class WebActivity extends AppCompatActivity {
    // WebView 변수 선언과 url 을 visibility 옵션을 위한 변수 선언
    boolean isPageOpen = false;
    private WebView webView;

    // 애니메이션을 위한 변수 선언
    Animation translateDownAnim;
    Animation translateUpAnim;

    // 레이아웃과 버튼, 입력상자 변수 선언
    LinearLayout urlPage;
    Button urlButton;
    Button urlConnectButton;
    EditText urlEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        // url 관련 객체들 참조
        urlPage = findViewById(R.id.urlPage);
        urlButton = findViewById(R.id.urlOpenCloseButton);
        urlConnectButton = findViewById(R.id.urlConnectButton);
        urlEditText = findViewById(R.id.urlEditText);

        // 애니메이션 객체 참조
        translateDownAnim = AnimationUtils.loadAnimation(this,
                R.anim.translate_down);
        translateUpAnim = AnimationUtils.loadAnimation(this,
                R.anim.translate_up);

        //
        SlidingPageAnimationListener animationListener =
                new SlidingPageAnimationListener();
        translateUpAnim.setAnimationListener(animationListener);
        translateDownAnim.setAnimationListener(animationListener);

        urlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPageOpen) {
                    urlPage.startAnimation(translateUpAnim);
                    urlPage.setVisibility(View.GONE);
                } else {
                    urlPage.setVisibility(View.VISIBLE);
                    urlPage.startAnimation(translateDownAnim);
                }
            }
        });

        webView = findViewById(R.id.activity_main_webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        urlConnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl(urlEditText.getText().toString());
            }
        });
    }

    // 애니메이션을 감시할 리스너 클래스
    private class SlidingPageAnimationListener implements Animation.AnimationListener {
        @Override
        // 애니메이션이 시작했을 때 동작하는 메소드
        public void onAnimationStart(Animation animation) {
            if (isPageOpen) {
                urlButton.setText("Open");
            } else {
                urlButton.setText("Close");
            }
        }

        @Override
        // 애니메이션이 끝났을 때 동작하는 메소드
        public void onAnimationEnd(Animation animation) {
            if (isPageOpen) {
                urlPage.setVisibility(View.INVISIBLE);
                urlPage.setVisibility(View.GONE);
                isPageOpen = false;
            } else {
                isPageOpen = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
