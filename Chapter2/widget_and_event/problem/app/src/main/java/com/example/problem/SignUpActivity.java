package com.example.problem;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUpActivity extends AppCompatActivity {
    EditText nameEditText;
    EditText ageEditText;
    TextView birthTextView;
    Button saveButton;

    EditText yearEditText;
    EditText monthEditText;
    EditText dayEditText;

    // 현재 날짜를 받기 위한 객체 생성
    Date now = new Date();
    // 형식에 맞게 날짜 데이터 저장
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // xml 객체를 Java로 불러옴
        nameEditText = findViewById(R.id.nameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        birthTextView = findViewById(R.id.birthTextView);
        saveButton = findViewById(R.id.saveButton);
        yearEditText = findViewById(R.id.yearEditText);
        monthEditText = findViewById(R.id.monthEditText);
        dayEditText = findViewById(R.id.dayEditText);

        // 생년월일 데이터를 현재 날짜로 저장
        birthTextView.setText(sdf.format(now));

        // 생년월일 텍스트를 터치 했을 때 처리하는 이벤트
        birthTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                // 손가락이 눌렸을 때
                if (action == MotionEvent.ACTION_DOWN) {
                    showBirthBox();
                }

                return true;
            }
        });

        // 버튼 이벤트 처리
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 토스트로 이름, 나이, 생년월일을 출력시켜 준다.
                Toast.makeText(getApplicationContext(), "이름 : " + nameEditText.getText().toString() +
                        "\n나이 : " + ageEditText.getText().toString() + "\n생년월일 : " + birthTextView.getText().toString()
                        , Toast.LENGTH_SHORT ).show();
            }
        });
    }

    // 대화상자를 보여주는 메소드
    private void showBirthBox() {
        // 대화상자 객체 생성
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 레이아웃을 inflate 시켜주기 위해 inflater 객체 생성
        LayoutInflater inflater = getLayoutInflater();
        // view 에 dialog_birth 라는 xml 을 인플레이트 시켜준다.
        View view = inflater.inflate(R.layout.dialog_birth, null);
        // 대화상자의 제목을 지정
        builder.setTitle("생년월일");
        // 대화상자에 view 객체를 올린다.
        builder.setView(view);

        // 버튼과 입력상자들의 객체를 참조시킨다.
        final Button setting = (Button) view.findViewById(R.id.settingButton);
        final Button cancel = view.findViewById(R.id.cancelButton);
        final EditText yearEditText = view.findViewById(R.id.yearEditText);
        final EditText monthEditText = view.findViewById(R.id.monthEditText);
        final EditText dayEditText = view.findViewById(R.id.dayEditText);

        // 대화상자를 만들어준다.
        final AlertDialog dialog = builder.create();
        // 대화상자를 보여준다.
        dialog.show();

        // 설정 버튼을 눌렀을 때의 이벤트 처리
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 입력상자들의 값을 가져와서 생년월일에 저장시킨다.
                birthTextView.setText(yearEditText.getText().toString() + "년 " + monthEditText.getText().toString() + "월 " + dayEditText.getText().toString() + "일");
                // 대화상자를 없애준다.
                dialog.dismiss();
            }
        });

        // 취소 버튼을 눌렀을 때의 이벤트 처리
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}
