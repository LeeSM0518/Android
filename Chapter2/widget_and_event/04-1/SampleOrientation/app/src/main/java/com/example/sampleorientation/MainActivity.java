package com.example.sampleorientation;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String name;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showToast("onCreate 호출됨.");

        editText = (EditText) findViewById(R.id.editText);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼을 클릭했을 때 사용자가 입력한 값을 name 변수에 할당
                name = editText.getText().toString();
                Log.d("save","입력된 값을 변수에 저장했습니다 : " + name );
                Toast.makeText(getApplicationContext(),
                        "입력된 값을 변수에 저장했습니다 : " + name, Toast.LENGTH_LONG).show();
            }
        });

        // 저장되었던 값 복원
        if (savedInstanceState != null) {
            // 이 화면이 초기화될 때 name 변수의 값 복원
            name = savedInstanceState.getString("name");
            Log.d("load", "값을 복원했습니다 : " + name);
            Toast.makeText(getApplicationContext(), "값을 복원했습니다 : " + name,
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // name 변수의 값 저장
        outState.putString("name", name);
    }

    @Override
    protected void onStart() {
        super.onStart();
        showToast("onStart 호출됨.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showToast("onStop 호출됨.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showToast("onDestroy 호출됨.");
    }

    public void showToast(String data) {
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }
}