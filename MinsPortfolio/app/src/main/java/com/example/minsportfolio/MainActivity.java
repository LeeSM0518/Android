package com.example.minsportfolio;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 메인 액티비티의 아래 쪽에 보이는 네비게이션 뷰 매칭
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // 네비게이션 뷰 컨트롤러 객체 생성
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        // 네비게이션 뷰에 컨트롤러를 적용시킨다.
        NavigationUI.setupWithNavController(navView, navController);
    }

}
