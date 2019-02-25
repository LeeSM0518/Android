package com.example.sampleactionbar01;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ActionBar abar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    // 액티비티가 만들어질 때 미리 자동 호출되어 화면에 메뉴 기능을 추가할 수 있도록 한다.
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // 화면이 처음 만들어질 때 메뉴를 정해 놓는 것이 아니라 화면이 띄워지고
    // 나서 메뉴를 바꾸고 싶다면 onPrepareOptionMenu() 메소드를 다시 정의하여 사용해야 한다.

    @Override
    // 사용자가 하나의 메뉴 아이템을 선택했을 대 자동 호출되는 메소드 오버라이딩
    // 현재 메뉴 아이템의 id 값이 무엇인지 확인하여 그에 맞는 기능을 하도록 만든다.
    public boolean onOptionsItemSelected(MenuItem item) {
        int curId = item.getItemId();
        switch (curId) {
            case R.id.menu_refresh:
                Toast.makeText(this, "새로고침 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_search:
                Toast.makeText(this, "검색 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_settings:
                Toast.makeText(this, "설정 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onButton1Clicked(View v) {
        abar.setLogo(R.drawable.home);
        // 액션바의 디스플레이 옵션 설정
        abar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME |
                ActionBar.DISPLAY_USE_LOGO);
    }
}