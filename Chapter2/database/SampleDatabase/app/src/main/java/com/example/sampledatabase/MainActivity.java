package com.example.sampledatabase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText databaseEditText;
    EditText tableEditText;
    TextView statusTextView;

    DatabaseHelper dbHelper;
    SQLiteDatabase database;    // 데이터베이스 객체 선언

    String tableName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseEditText = findViewById(R.id.createDatabaseEditText);
        tableEditText = findViewById(R.id.createTableEditText);
        statusTextView = findViewById(R.id.status);

        Button databaseButton = findViewById(R.id.createDatabaseButton);
        databaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String databaseName = databaseEditText.getText().toString();
                // 데이터 베이스 생성 메소드 호출
                createDatabase(databaseName);
            }
        });

        Button tableButton = findViewById(R.id.createTableButton);
        tableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableName = tableEditText.getText().toString();
                // 테이블 생성 메소드 호출
                createTable(tableName);
                // 레코드 입력 메소드 호출
                insertRecord();
            }
        });

        // 데이터 조회
        Button executeButton = findViewById(R.id.executeButton);
        executeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeQuery();
            }
        });
    }

    private void createDatabase(String name) {
        println("createDatabase 호출됨.");

        // 데이터베이스 헬퍼 객체를 생성하고 데이터베이스 객체 참조
        dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        println("데이터베이스 생성함 : " + name);
    }

    private void createTable(String name) {
        println("createTable 호출됨.");

        if (databaseEditText == null) {
            println("데이터베이스를 먼저 생성하세요.");
            return;
        }

        // 테이블 생성을 위한 SQL 문 실행
        database.execSQL("create table if not exists " + name + "("
                + " _id integer PRIMARY KEY autoincrement, "
                + " name text, "
                + " age integer, "
                + " mobile text)");

        println("테이블 생성함 : " + name);
    }

    private void insertRecord() {
        println("insertRecord 호출됨.");

        if (database == null) {
            println("데이터베이스를 먼저 생성하세요.");
            return;
        }

        if (tableName == null) {
            println("테이블을 먼저 생성하세요.");
            return;
        }

        // 레코드 입력을 위한 SQL 문 실행
        database.execSQL("insert into " + tableName
                + "(name, age, mobile) "
                + " values "
                + "('John', 20, '010-1000-1000')");

        println("레코드 추가함.");
    }

    public void println(String data) {
        statusTextView.append(data + "\n");
    }

    // 데이터 조회 메소드
    public void executeQuery() {
        println("executeQuery 호출됨.");

        // rawQuery 메소드를 통해 SELECT SQL문 실행
        Cursor cursor = database.rawQuery("select _id, name, age, mobile from emp", null);
        int recordCount = cursor.getCount();
        println("레코드 개수 : " + recordCount);

        for (int i = 0; i < recordCount; i++) {
            cursor.moveToNext();

            // 커서 객체를 이용해 컬럼 가져오기
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String mobile = cursor.getString(3);

            println("레코드 #" + i + " : " + id + ", " + name + ", " + age + ", " + mobile);
        }

        // 커서 닫기
        cursor.close();
    }

    private int insertRecordParam(String name) {
        println("inserting records using parameters.");

        int count = 1;

        // 파라미터로 전달할 ContentValues 객체 생성
        ContentValues recordValues = new ContentValues();

        // 파라미터 값 추가
        recordValues.put("name", "Rice");
        recordValues.put("age", 43);
        recordValues.put("phone", "010-3322-9811");

        // insert() 메소드 호출하여 레코드 추가
        int rowPosition = (int) database.insert(name, null, recordValues);

        return count;
    }

    private int updateRecordParam(String name) {
        println("updating records using parameters");

        ContentValues recordValues = new ContentValues();
        recordValues.put("age", 43);
        String[] whereArgs = {"Rice"};
        // update() 메소드 호출하여 레코드 수정
        int rowAffected = database.update("employee", recordValues,
                "name = ?", whereArgs);

        return rowAffected;
    }

    private int deleteRecordParam(String name) {
        println("deleting records using parameters.");

        String[] whereArgs = {"Rice"};

        // delete() 메소드 호출하여 레코드 삭제
        int rowAffected = database.delete(name, "name = ?", whereArgs);

        return rowAffected;
    }

    private void executeRawQueryParam2() {
        String[] columns = {"name", "age", "phone"};
        String whereStr = "where age > ?";
        String[] whereParams = {"30"};

        // query() 메소드를 호출하여 데이터 쿼리
        Cursor c1 = database.query(tableName, columns,
                whereStr, whereParams, null, null, null);

        // 커서의 getCount() 메소드를 이용해 레코드 개수 확인
        int recordCount = c1.getCount();
        println("cursor count : " + recordCount + "\n");

        for (int i = 0; i < recordCount; i++) {
            // 다음 레코드로 이동
            c1.moveToNext();

            // 현재 레코드의 첫 번째 문자열 확인
            String name = c1.getString(0);
            int age = c1.getInt(1);
            String phone = c1.getString(2);

            println("Record #" + i + " : " + name + ", " + age + ", " + phone);
        }
        // 커서 닫기
        c1.close();
    }
}
