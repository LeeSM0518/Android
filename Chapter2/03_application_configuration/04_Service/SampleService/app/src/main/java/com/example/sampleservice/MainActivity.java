package com.example.sampleservice;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);   // 입력 상자 참조

        // getIntent() 메소드를 호출하여 인텐트 객체를 참조한다.
        Intent passedIntent = getIntent();
        processIntent(passedIntent);
    }

    // 만약 MainActivity 가 메모리에 만들어져 있다면
    // onNewIntent() 메소드로 전달묀다.
    protected void onNewIntent(Intent intent) {
        // processIntent() 메소드를 만들고 그 안에서 객체 처리
        processIntent(intent);

        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent) {
        if (intent != null) {
            String command = intent.getStringExtra("command");
            String name = intent.getStringExtra("name");

            // 인텐트로 전달 받은 데이터를 토스트 메시지로 출력
            Toast.makeText(this, "command : " + command + " , name : " + name,
                    Toast.LENGTH_LONG).show();
        }
    }

    public void onButton1Clicked(View v) {
        String name = editText.getText().toString();        // 입력 상자 내용 가져옴

        // 인텐트 객체 생성
        Intent intent = new Intent(this, MyService.class);

        // 두 개의 부가 데이터
        // 서비스 쪽으로 전달한 인텐트 객체의 데이터가 어떤 목적으로 사용되는지 구별하기 위함
        intent.putExtra("command", "show");

        // 입력상자에서 가져온 문자열을 전달하기 위함
        intent.putExtra("name", name);
        startService(intent);
    }
}
