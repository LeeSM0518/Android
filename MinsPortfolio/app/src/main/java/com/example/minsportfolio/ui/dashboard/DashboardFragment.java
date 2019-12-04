package com.example.minsportfolio.ui.dashboard;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.minsportfolio.R;

public class DashboardFragment extends Fragment {

    // 스피너에 목록으로 들어갈 String 배열
    String[] items = {"TIL", "Vue-tutorial", "MVC", "Android",
    "Development-Blog", "Design-pattern", "Modern-java", "Coding_Test", "Database", "Deep-learning",
    "Node.js", "OpenCV-python", "Python_Data_Analysis"};

    // 웹뷰에 띄울 모든 URL 에서 공통되는 부분
    String gitURL = "https://github.com/LeeSM0518/";

    // 웹뷰
    WebView webView;
    // 웹뷰 세팅
    WebSettings webSettings;

    // 프레그먼트 생성 메서드
    @SuppressLint("SetJavaScriptEnabled")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // xml 파일을 가져와서 객체에 저장
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // 스피너를 XML 과 매칭
        Spinner spinner = root.findViewById(R.id.dashSpinner);

        // Spinner를 사용하기 위해 데이터와 통신할 수 있도록 어댑터 생성
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // 웹뷰를 XML과 매칭
        webView = root.findViewById(R.id.dashWebView);

        // 웹뷰에 웹 클라운트 객체를 주입하고 세팅을 해준다.
        webView.setWebViewClient(new WebViewClient());
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportMultipleWindows(false);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportZoom(false);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDomStorageEnabled(true);

        // 스피너의 값이 변경될 때 반응하는 리스너를 등록
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 현재 문자열을 공통 URL에 문자열을 더해 웹뷰에 띄워준다.
                webView.loadUrl(gitURL + items[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 처음에는 첫 번째 문자열이 스피너에 나와있기 때문에
                //  첫 번째 문자열을 웹뷰에 띄워준다.
                webView.loadUrl(gitURL + items[0]);
            }
        });

        return root;
    }

}