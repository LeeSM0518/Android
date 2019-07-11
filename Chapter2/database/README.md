# 09-1. 모바일 데이터베이스 

**SQLite** 는 대부분의 임베디드 데이터베이스가 그렇듯이 파일로 만들어진 하위 수준의 구조를 가지면서도 데이터베이스의 기능을 그대로 사용할 수 있도록 만든 것이다.



# 09-2. 데이터베이스와 테이블 만들기

데이터베이스를 만드는 가장 간단한 방법은 **Context 클래스에** 정의된 **openOrCreateDatabase()** 메소드를 사용하는 것이다.

* **Context 클래스에 정의된 데이터베이스 관련 메소드**

```java
// 데이터를 만들거나 여는 메소드
// 첫 번째 파라미터 : 데이터베이스의 이름
// 두 번째 파라미터 : 사용 모드
// 세 번째 파라미터 : Null이 아닌 객체를 지정할 경우에는 쿼리의 결과값으로 리턴되는 커서를 만들어낼 객체
public abstract SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory);

// 데이터베이스를 삭제하는 메소드
public abstract boolean deleteDatabase(String name);

// 데이터베이스가 만들어졌는지나 개수등을 확인할 때 사용하는 메소드
databaseList();

// 테이블을 만드는 메소드
public void execSQL(String sql) throws SQLException
```

* **사용모드**
  * MODE_PRIVATE
  * MODE_WORLD_READABLE
  * MODE_WORLD_WRITEABLE



## 데이터베이스와 테이블 만들기(예제)

**XML 코드**

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:orientation="vertical"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

        <LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:orientation="horizontal">

            <Button
                android:id="@+id/createDatabaseButton"
                android:layout_width="150dp"
                android:layout_height="wrap_content"
                android:text="데이터베이스 생성" />

            <EditText
                android:id="@+id/createDatabaseEditText"
                android:layout_width="243dp"
                android:layout_height="wrap_content" />

        </LinearLayout>

        <LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:orientation="horizontal">

            <Button
                android:id="@+id/createTableButton"
                android:layout_width="150dp"
                android:layout_height="wrap_content"
                android:text="테이블 생성" />

            <EditText
                android:id="@+id/createTableEditText"
                android:layout_width="242dp"
                android:layout_height="wrap_content" />

        </LinearLayout>

        <TextView
            android:id="@+id/status"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="상태 :"
            android:textSize="20dp" />


    </LinearLayout>

</android.support.constraint.ConstraintLayout>
```

**MainActivity.java**

```java
package com.example.sampledatabase;

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
    }

    private void createDatabase(String name) {
        println("createDatabase 호출됨.");

        // 데이터베이스 생성 또는 열기
        database = openOrCreateDatabase(name, MODE_PRIVATE, null);

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
}
```

**실행 결과**

<img src="../../capture/스크린샷 2019-07-11 오후 4.08.53.png" width=400>



* **SQLite에서 지원하는 컬럼 타입**

| 컬럼 타입             | 설명                        |
| --------------------- | --------------------------- |
| text, varchar         | 문자열                      |
| smallint, integer     | 정수                        |
| real, float, double   | 부동소수                    |
| boolean               | true 또는 false             |
| date, time, timestamp | 시간(날짜, 시간, 날짜+시간) |
| blob, binary          | 바이너리                    |

* **테이블을 만들고 추가하는 문법**

```mariadb
CREATE TABLE [IF NOT EXISTS] table_name(col_name column_definition, ...) [table_option] ...
INSERT INTO table_name<(column list)> VALUES (value, ...)
```



# 09-3. 헬퍼 클래스를 이용해 업그레이드 지원하기

데이터베이스를 만드는 것 이외에도 테이블의 정의가 바뀌거나 하여 **스키마를 업그레이드할 필요가 있을 때에는** API에서 제공하는 헬퍼(Helper) 클래스를 사용하는 것도 좋은 방법이다.

* **SQLiteOpenHelper 클래스 메소드**

```java
// 생성자
// 첫 번째 파라미터 : Context 객체로 액티비티 안에서 만들 경우 this로 지정가능
// 두 번째 파라미터 : 데이터베이스 이름
// 세 번째 파라미터 : 데이터 조회 시에 리턴하는 커서를 만들 객체
// 네 번째 파라미터 : 데이터베이스 업그레이드를 위해 사용, 만들어져 있는 데이터베이스의 버전 정보와 다르게 지정
public SQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version);

public abstract void onCreate(SQLiteDatabase db);
public abstract void onOpen(SQLiteDatabase db);
public abstract void onUpgrade(SQLiteDatabase cb, int oldVersion, int newVersion)
```



## 헬퍼 클래스 사용(예제)

**DatabaseHelper.java**

```java
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
```

**MainActivity.java**

```java
package com.example.sampledatabase;

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

        // 데이터베이스 생성 또는 열기
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

    public void executeQuery() {
        println("executeQuery 호출됨.");

        Cursor cursor = database.rawQuery("select _id, name, age, mobile from emp", null);
        int recordCount = cursor.getCount();
        println("레코드 개수 : " + recordCount);

        for (int i = 0; i < recordCount; i++) {
            cursor.moveToNext();

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String mobile = cursor.getString(3);

            println("레코드 #" + i + " : " + id + ", " + name + ", " + age + ", " + mobile);
        }

        cursor.close();
    }
}
```



# 09-4. 데이터 조회하기

## 데이터 조회(예제)

**MainActivity.java**

```java
package com.example.sampledatabase;

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
}
```

* **Cursor 클래스 메소드**

```java
// SQL 구문을 통해 리턴되는 값을 Cursor 객체로 반환하는 메소드
public Cursor rawQuery(String sql, String[] selectionArgs);
// 칼럼의 전체 개수 알아내는 메소드
getColumnCount();
// 칼럼에 대한 정보를 알아보는 메소드
getColumnCount();
// 모든 칼럼의 이름을 확인하는 메소드
getColumnNames();
// 각각 칼럼이 인덱스 값 확인하는 메소드
getColumnIndex();
```

* **SQL 조회 문법**

```mariadb
SELECT [*| DISTINCT] column_name [,columnname2]
FROM tablename1 [,tablename2]
WHERE [condition and|or condition...]
[GROUP BY column-list]
[HAVING conditions]
[ORDER BY "column-list" [ASC | DESC]]
```



# 09-5. SQL을 메소드 호출로 실행하기

**데이터베이스 조작과 조회를 위한 메소드들**

```java
// 레코드 추가
public long insert(String table, String nullColumnHack, ContentValues values);
// 레코드 수정
public int update(String table, ContentValues values, String whereCaluse, String[] whereArgs);
// 레코드 삭제
public int delete(String table, String whereClause, String[] whereArgs);
```

**Query 메소드들**

```java
public Cursor query (boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);
public Cursor query (String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy);
public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit)
```

* **distinct 파라미터** : 고유한 레코드만을 포함시킬 것인지 설정
* **table 파라미터** : 테이블의 이름
* **columns ** : 칼럼들의 이름을 배열로 저장
* **selection** : 조회 조건
* **selectionArgs** : 조회 조건으로 지정된 문자열에 들어 있는 '?' 기호를 대체할 파라미터들을 담고있다.
* **groupBy, having, orderBy**  : SQL의 groupBy, having, orderBy 와 같다.



## SQL 메소드 호출(예제)

**MainActivity.java**

```java
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
```



# 09-6. 데이터 베이스 내용 정리

| 순서            | 내용                                                         | 사용 메소드            |
| --------------- | ------------------------------------------------------------ | ---------------------- |
| DB 만들기       | 데이터베이스를 만들면 SQLiteDatabase 객체가 반환됨           | openOrCreateDatabase() |
| Table 만들기    | 'CREATE TABLE …' SQL을 정의한 후 실행                        | execSQL()              |
| 레코드 추가하기 | 'INSERT INTO …' SQL을 정의한 후 실행                         | execSQL()              |
| 데이터 조회하기 | 'SELECT FROM …' SQL을 정의한 후 실행. Cursor 객체가 반환되며 Cursor를 통해 확인한 레코드를 리스트뷰 등에 표시함 | rawQuery()             |



