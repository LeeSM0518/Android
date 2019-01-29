package com.example.businessmanagement;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_LOGIN = 105;
    public static final int REQUEST_MAIN_ACTIVITY = 103;
    private String savedId = "nalsm98";
    private String savedPassword = "1234";
    private String inputId;
    private String privatePW;
    private String inputPassword;
    private EditText idText;
    private EditText passwordText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = (Button)findViewById(R.id.loginBtn);
        idText = (EditText)findViewById(R.id.idEditText);
        passwordText = (EditText)findViewById(R.id.passwordEditText);
    }

    // 오버라이드한 후 메소드명과 매개변수를 모두 쳐야 오버라이딩이 됨을 알 수 있다.
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == REQUEST_MAIN_ACTIVITY) {
            Toast.makeText(getApplicationContext(),
                    "로그아웃 하셨습니다." + "\nResultCode : " +
                            data.getStringExtra("Activity") , Toast.LENGTH_LONG ).show();
        }

        else if (resultCode == REQUEST_LOGIN) {
            Toast.makeText(getApplicationContext(),
                    "로그아웃 하셨습니다." + "\nResultCode : " +
                            data.getStringExtra("Activity") , Toast.LENGTH_LONG ).show();
        }
    }

    public void loginClicked(View v) {
        inputId = idText.getText().toString();
        inputPassword = passwordText.getText().toString();

        UserID userID = new UserID(idText.getText().toString(), passwordText.getText().toString());

        if(savedId.equals(inputId) && savedPassword.equals(inputPassword)) {
            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            intent.putExtra("userID", userID);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_NEW_TASK);

            Toast.makeText(getApplicationContext(), "로그인 합니다.", Toast.LENGTH_LONG).show();
            startActivityForResult(intent, REQUEST_LOGIN);
        }

        else {
            Toast.makeText(getApplicationContext(), "일치하는 회원정보가 없습니다." , Toast.LENGTH_LONG).show();
        }
    }
}
