package com.example.sampledatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

// 새로운 헬퍼 클래스 정의
public class DatabaseHelper extends SQLiteOpenHelper {

    public static String NAME = "employee.db";
    public static int VERSION = 1;

    public DatabaseHelper(Context context) {
        // 상위 클래스의 생성자 호출
        super(context, NAME, null, VERSION);
    }

    // 데이터베이스 파일이 처음으로 만들어질 때 호출되는 메소드 정의
    @Override
    public void onCreate(SQLiteDatabase db) {
        println("onCreate 호출됨");

        String sql = "create table if not exists emp("
                + " _id integer PRIMARY KEY autoincrement, "
                + " name text, "
                + " age integer, "
                + " mobile text)";

        db.execSQL(sql);
    }

    // 데이터베이스가 오픈될 때 호출되는 메소드 정의
    @Override
    public void onOpen(SQLiteDatabase db) {
        println("onOpen 호출됨.");
    }

    // 데이터베이스의 버전이 바뀌었을 때 호출되는 메소드 정의
   @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        println("onUpgrade 호출됨 : " + oldVersion + " -> " + newVersion);

        if (newVersion > 1) {
            db.execSQL("DROP TABLE IF EXISTS emp");
        }
    }

    public void println(String data) {
        Log.d("DatabaseHelper", data);
    }
}
