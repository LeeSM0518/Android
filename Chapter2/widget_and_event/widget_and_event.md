# 04. 다양한 위젯과 이벤트 활용하기

## 04-1. 이벤트 처리 이해하기

### 이벤트 처리 방식

* **키 이벤트(Key Event)** : 하드웨어 버튼이나 소프트 키패드로 키를 입력했을 때 발생

* **터치 이벤트(Touch Event)** : 손가락으로 터치

  * **위임 모델(Delegation Model)** : 이벤트 처리 방식으로써, 화면에서 발생하는 이벤트를 버튼과 같은 위젯 객체에 전달한 후 그 이후의 처리 과정을 버튼에 위임.
  * **OnClickListener** : 이벤트가 발생하면 즉시 동작할 수 있도록 만들어주는 리스너

  ![1548752372489](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548752372489.png)

* **이벤트 처리 메소드**

  ```java
  // 이 메소드들로 이벤트가 전달될 때 마다 처리할 수 있다.
  boolean onTouchEvent (MotionEvent event)
  boolean onKeyDown (int keyCode, KeyEvent event)
  boolean onKeyUp (int keyCode, KeyEvent event)
  ```

  > **'MotionEvent'** : 터치했을 때 이벤트
  >
  > **'KeyEvent'** : 키를 입력했을 때 이벤트

  

* **새로운 뷰를 정의하지 않고 기존의 뷰 객체에서 이벤트 처리**

  ```java
  // 사용자가 손가락으로 터치할 때마다 발생되는 이벤트 처리
  View.OnTouchListener : boolean onTouch (View v, MotionEvent event)
  // 키 입력이 발생할 때마다 발생되는 이벤트 처리
  View.OnKeyListener : boolean onKey (View v, int keyCode, KeyEvent event)
  // 버튼 터치할 때마다 발생되는 이벤트 처리
  View.OnClickListener : void onClick (View v)
  // 뷰에 포커스가 주어지거나 없어질 경우에 발생하는 이벤트 처리
  View.OnFocusChangeListener : void onFocusChange (View v, boolean hasFocus)
  ```



* **대표적인 이벤트 유형**

  | 속성           | 설명                                                         |
  | -------------- | ------------------------------------------------------------ |
  | 터치 이벤트    | 화면을 손가락으로 누를 때 발생                               |
  | 키 이벤트      | 키패드를 누를 때 발생                                        |
  | 제스처 이벤트  | 터치 이벤트 중에서 스크롤과 같이 일정 패턴으로 구분되는 이벤트 |
  | 포커스         | 뷰마다 순서대로 주어지는 포커스                              |
  | 화면 방향 변경 | 가로/세로 로 화면이 바뀔 때 이벤트                           |



* **제스처 이벤트 처리 메소드**

  | 메소드                 | 이벤트 유형                                              |
  | ---------------------- | -------------------------------------------------------- |
  | onDown()               | 화면이 눌렸을 경우                                       |
  | onShowPress()          | 화면이 눌렸다 떼어지는 경우                              |
  | onSingleTapUp()        | 화면이 한 손가락으로 눌렀다 떼어지는 경우                |
  | onSingleTapConfirmed() | 한 손가락으로 눌러지는 경우                              |
  | onDoubleTap()          | 두 손가락으로 누를 때                                    |
  | onDoubleTapEvent()     | 두 손가락으로 누른 상태에서 떼거나 이동할 때             |
  | onScroll()             | 누른 채 일정한 속도와 방향으로 움직였다 떼는 경우        |
  | onFling()              | 화면이 눌린 채 가속도를 붙여 손가락을 움직였다 떼는 경우 |
  | onLongPress()          | 손가락으로 오래 누르는 경우                              |



### 터치 이벤트 처리하기(예제)

**activity_main.xml**

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
        android:layout_height="match_parent"
        android:orientation="vertical">

        <View
            android:id="@+id/view"
            android:background="#ff004466"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="1"></View>

        <View
            android:id="@+id/view2"
            android:background="#ff4400"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="1"></View>

        <ScrollView
            android:id="@+id/scrollView"
            android:layout_weight="1"
            android:layout_width="match_parent"
            android:layout_height="0dp">

            <TextView
                android:id="@+id/textView"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                />

        </ScrollView>


    </LinearLayout>

</android.support.constraint.ConstraintLayout>
```



**MainActivity.java**

```java
public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);

        // 뷰 객체 참조
        View view = findViewById(R.id.view);

        // 리스너 등록
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // MotionEvent 객체에는 액션 정보나 터치한 곳의 좌표가 있다.

                int action = event.getAction(); // getAction() 메소드로 액션 정보 요청

                float curX = event.getX();
                float curY = event.getY();

                if (action == MotionEvent.ACTION_DOWN) {
                    // 손가락이 눌렀을 때
                    println("손가락 눌림 : " + curX + ", " + curY);
                } else if (action == MotionEvent.ACTION_MOVE) {
                    // 손가락이 눌린 상태로 움직일 때
                    println("손가락 움직임 : " + curX + ", " + curY);
                } else if (action == MotionEvent.ACTION_UP) {
                    // 손가락이 떼졌을 때
                    println("손가락 뗌 : " + curX + ", " + curY);
                }

                return true;
            }
        });
    }
    
    public void println(String data) {
        textView.append(data + "\n");
    }
}
```



**실행 결과**

![1548762817549](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548762817549.png)



### 제츠서 이벤트 처리하기(예제)

**MainActivity.java**

```java
package com.example.sampleevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    GestureDetector detector;   // 제스처 디텍터 객체 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        // 뷰 객체 참조
        View view = findViewById(R.id.view);

        // 리스너 등록
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // MotionEvent 객체에는 액션 정보나 터치한 곳의 좌표가 있다.

                int action = event.getAction(); // getAction() 메소드로 액션 정보 요청

                float curX = event.getX();
                float curY = event.getY();

                if (action == MotionEvent.ACTION_DOWN) {
                    // 손가락이 눌렀을 때
                    println("손가락 눌림 : " + curX + ", " + curY);
                } else if (action == MotionEvent.ACTION_MOVE) {
                    // 손가락이 눌린 상태로 움직일 때
                    println("손가락 움직임 : " + curX + ", " + curY);
                } else if (action == MotionEvent.ACTION_UP) {
                    // 손가락이 떼졌을 때
                    println("손가락 뗌 : " + curX + ", " + curY);
                }

                return true;
            }
        });

        detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                println("onDown() 호출됨");

                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                println("onShowPress() 호출됨");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                println("onSingleTapUp() 호출됨.");

                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                println("onScroll() 호출됨 : " + distanceX + ", " + distanceY);

                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                println("onLongPress() 호출됨.");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                println("onFling() 호출됨 : " + velocityX + ", " + velocityY);

                return true;
            }
        });

        View view2 = findViewById(R.id.view2);
        // 뷰를 터치했을 때 발생하는 터치 이벤트를 제스처 디텍터로 전달
        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // onTouchEvent() 메소드를 호출하면서 MotionEvent 객체를 전달
                // GestureDetector 객체가 터치 이벤트를 처리한 후 GestureDetector 객체에
                // 정의된 메소드를 호출한다.
                detector.onTouchEvent(event);
                return true;
            }
        });
    }


    public void println(String data) {
        textView.append(data + "\n");
    }
}
```



**실행 결과**

![1548762920787](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548762920787.png)



### 키 이벤트 처리하기(예제)

* **키 입력 이벤트 처리 메소드**

  ```java
  // 파라미타
  // KeyCode : 어떤 키가 사용되는지 구별할 때 사용
  // KeyEvent : 키 입력 이벤트에 대한 정보를 알고 싶을 때 사용
  boolean onKeyDown (int keyCode, KeyEvent event)
  boolean onKey (View v, int keyCode, KeyEvent event)
  ```

* **KeyCode 대표적인 키 값**

  | 키 코드             | 설 명                      |
  | ------------------- | -------------------------- |
  | KEYCODE_DPAD_LEFT   | 왼쪽 화살표                |
  | KEYCODE_DPAD_RIGHT  | 오른쪽 화살표              |
  | KEYCODE_DPAD_UP     | 위쪽                       |
  | KEYCODE_DPAD_DOWN   | 아래쪽                     |
  | KEYCODE_DPAD_CENTER | [중앙]버튼                 |
  | KEYCODE_CALL        | [통화]버튼                 |
  | KEYCODE_ENDCALL     | [통화 종료]버튼            |
  | KEYCODE_HOME        | [홈]버튼                   |
  | KEYCODE_BACK        | [뒤로가기]버튼             |
  | KEYCODE_VOLUME_UP   | [소리 크기 증가]버튼       |
  | KEYCODE_VOLUME_DOWN | [소리 크기 감소]버튼       |
  | KEYCODE_0-KEYCODE_9 | 숫자 0부터 9까지의 키 값   |
  | KEYCODE_A-KEYCODE_Z | 알파벳 A부터 Z까지의 키 값 |

  >하드웨어 [Back] 키를 누르는 경우는 자주 사용되므로 onBackPressed() 메소드만 다시 정의



* **MainActivity.java**

  ```java
  ...
  @Override
      public boolean onKeyDown(int keyCode, KeyEvent event) {
      // 하드웨어의 뒤로가기 버튼 클릭시 실행되는 메소드
          if(keyCode == event.KEYCODE_BACK) {
              Toast.makeText(this, "시스템 [BACK] 버튼이 눌렸습니다.",
                      Toast.LENGTH_LONG).show();
  
              return true;
          }
          return false;
      }
  ...
  ```



### 포커스 이벤트 처리하기(예제)

: 키를 입력할 때 발생하는 이벤트는 **포커스(Focus)**를 가진 뷰에게 전달됩니다. 이때 포커스는 화면에 보이는 뷰들 중의 하나에 부여되게 된다.

* **activity_main.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      xmlns:tools="http://schemas.android.com/tools"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      tools:context=".MainActivity">
  
      <LinearLayout
          android:orientation="vertical"
          android:layout_width="match_parent"
          android:layout_height="match_parent">
  
          <TextView
              android:id="@+id/textView"
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              android:background="#ff0000cc"
              android:text="포커스 받기"
              android:textSize="20dp"
              android:focusable="true"
              />
  
          <!-- button_selector.xml 코드를 배경색으로 가진다-->
          <EditText
              android:id="@+id/editText"
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              android:hint="텍스트를 입력하세요."
              android:textSize="20dp"
              android:background="@drawable/button_selector"
              />
  
          <Button
              android:id="@+id/showButton"
              android:layout_width="160dp"
              android:layout_height="wrap_content"
              android:text="보여주기"
              android:textSize="20dp"
              android:textStyle="bold"
              />
  
      </LinearLayout>
  
  </android.support.constraint.ConstraintLayout>
  ```

* **button_selector.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <selector xmlns:android="http://schemas.android.com/apk/res/android">
      <!-- 포커스가 잡혔을 때나 눌렸을 때 빨간색으로 변환 -->
      <item
          android:state_focused="true"
          android:state_pressed="true"
          android:drawable="@color/colorAccent"
          />
  
      <item
          android:state_focused="false"
          android:state_pressed="true"
          android:drawable="@color/colorAccent"
          />
  
      <item
          android:drawable="@color/colorPrimary"
          />
  </selector>
  ```



### 단말 방향을 전환했을 때 이벤트 처리하기(예제)

: 단말의 방향이 바뀌었을 때 세로 방향의 XML 레이아웃과 가로 방향의 XML 레이아웃을 따로 만들어 둘 필요가 있다.



* **가로 방향 xml 추가**

  ![1548766552498](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548766552498.png)

  > layout-land 라는 xml 폴더를 res 폴더에 하나 생성한다.
  >
  > 그리고 layout에 있는 xml 코드를 복사해서 layout-land 폴더에 넣는다.

* **MainActivity.java**

  ```java
  package com.example.sampleorientation;
  
  import android.support.v7.app.AppCompatActivity;
  import android.os.Bundle;
  import android.widget.Toast;
  
  public class MainActivity extends AppCompatActivity {
  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
  
          showToast("onCreate 호출됨.");
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
  ```

* **실행 결과**

  ![1548766678162](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548766678162.png)

  ![1548766693935](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548766693935.png)



이처럼 단말의 방향이 바뀔 때 액티비티가 메모리에서 없어졌다가 새로 만들어지는 것은 액티비티 안에 선언해 두었던 변수 값이 사라지는 문제점이 있다.

 이러한 문제를 해결하기 위해 **onSaveInstanceState 콜백 메소드**가 제공된다. 이 메소드는 액티비티가 종료되기 전의 상태를 저장할 수 있게 한다.

* **MainActivity.java**

  ```java
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
  ```

* **실행 결과**

  ![1548767942519](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548767942519.png)

  ![1548767959719](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548767959719.png)



* **단말의 방향이 바뀌는 것을 앱에서 이벤트로 전달받도록 하고 액티비티는 그대로 유지하는 방법**

  : 매니페스트에 액티비티를 등록할 때 **configChanges** 속성을 설정해야 한다.

* **AndroidManifest.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <manifest xmlns:android="http://schemas.android.com/apk/res/android"
      package="com.example.sampleorientation">
  
      <application
          android:allowBackup="true"
          android:icon="@mipmap/ic_launcher"
          android:label="@string/app_name"
          android:roundIcon="@mipmap/ic_launcher_round"
          android:supportsRtl="true"
          android:theme="@style/AppTheme">
          <activity
              android:name=".Main2Activity"
              // configChanges 추가
              android:configChanges="orientation|screenSize|keyboardHidden">
              <intent-filter>
                  <action android:name="android.intent.action.MAIN" />
  
                  <category android:name="android.intent.category.LAUNCHER" />
              </intent-filter>
          </activity>
      </application>
  
  </manifest>
  ```

* **Main2Activity.java**

  ```java
  package com.example.sampleorientation;
  
  import android.content.res.Configuration;
  import android.support.v7.app.AppCompatActivity;
  import android.os.Bundle;
  import android.widget.Toast;
  
  public class Main2Activity extends AppCompatActivity {
  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main2);
      }
  
      @Override
      public void onConfigurationChanged(Configuration newConfig) {
          super.onConfigurationChanged(newConfig);
  
          if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
              // 가로 방향 전환 시 처리
              showToast("방향 : ORIENTATION_LANDSCAPE");
          } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
              // 세로 방향 전환 시 처리
              showToast("Orientation : ORIENTATION_PORTRAIT");
          }
      }
  
      public void showToast(String data) {
          Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG).show();
      }
  }
  ```



## 04-2. 토스트와 대화상자

* **토스트** : 간단한 메시지를 잠깐 보여주었다가 없어지는 뷰로 앱 위에 떠 있는 뷰이다.

  * **메시지를 만드는 방법**

    ```java
    Toast.makeText(Context context, String message, int duration).show();
    ```

  * **토스트의 위치와 여백 지정하는 방법**

    ```java
    // 화면 상의 위치를 지정하는데 사용
    // gravity : Gravity.CENTER 와 같이 정렬 위치 지정
    public void setGravity(int gravity, int xOffset, int yOffset) 
    // 외부 여백을 지정
    public void setMargin(float horizontalMargin, float verticalMargin)
    ```



### 토스트 위치 바꿔 보여주기(예제)

**activity_main.xml**

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
        android:orientation="horizontal"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

        <EditText
            android:id="@+id/editText"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:hint="X 위치"
            android:inputType="numberSigned"
            android:textSize="20dp" />

        <EditText
            android:id="@+id/editText2"
            android:layout_weight="1"
            android:textSize="20dp"
            android:hint="Y 위치"
            android:inputType="numberSigned"
            android:layout_width="0dp"
            android:layout_height="wrap_content" />

        <Button
            android:id="@+id/button"
            android:layout_weight="1"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:textSize="20dp"
            android:text="띄우기"
            android:onClick="onButton1Clicked"
            />

    </LinearLayout>

</android.support.constraint.ConstraintLayout>
```

**MainActivity.java**

```java
package com.example.sampletoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
    }

    public void onButton1Clicked(View v) {
        try {
            Toast toastView = Toast.makeText(this,
                    "위치가 바뀐 토스트 메시지입니다.", Toast.LENGTH_LONG);

            // x 좌표와 y 좌표를 저장
            int xOffset = Integer.parseInt(editText.getText().toString());
            int yOffset = Integer.parseInt(editText2.getText().toString());

            // setGravity 로 토스트 메시지 좌표 지정
            toastView.setGravity(Gravity.TOP|Gravity.TOP, xOffset, yOffset);
            toastView.show();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
```

**실행 결과**

![1548771825948](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548771825948.png)



### 토스트 모양 바꿔 보여주기(예제)

* **MainActivity.java**

  ```java
  ...
      public void onButton2Clicked(View v) {
          // 레이아웃 인플레이터 객체 참조
          LayoutInflater inflater = getLayoutInflater();
  
          // 토스트를 위한 레이아웃 인플레이션
          // 토스트만을 위한 레이아웃을 정의한다면, 이 레이아웃은
          // 액티비티를 위한 것이 아니기 때문에 LayoutInflater 객체를
          // 사용해 직접 메모리에 객체화해야 한다.
          View layout = inflater.inflate(
                  R.layout.toastborder,
                  (ViewGroup) findViewById(R.id.toast_layout_root)
          );
  
          TextView text = (TextView) layout.findViewById(R.id.text);
  
          // 토스트 객체 생성
          Toast toast = new Toast(this);
          text.setText("모양 바꾼 토스트");
  
          toast.setGravity(Gravity.CENTER, 0, -100);
          toast.setDuration(Toast.LENGTH_SHORT);
  
          // 토스트가 보이는 뷰 설정
          toast.setView(layout);
          toast.show();
  
      }
  ...
  ```

* **/res/layout/toastborder.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <LinearLayout
      xmlns:android="http://schemas.android.com/apk/res/android"
      android:id="@+id/toast_layout_root"
      android:orientation="horizontal"
      android:padding="10dp"
      android:layout_width="match_parent"
      android:layout_height="match_parent">
      <TextView
          android:background="@drawable/toast"
          android:textSize="32dp"
          android:padding="20dp"
          android:id="@+id/text"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content" />
  
  </LinearLayout>
  ```

* **/res/drawable/toast.xml(토스트의 색상, 모양 등 Shape 정의 파일)**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <shape xmlns:android="http://schemas.android.com/apk/res/android"
      android:shape="rectangle"
      >
  
      <stroke
          android:width="4dp"
          android:color="#ffffff00"
          />
      <solid
          android:color="#ff883300"
          />
      <padding
          android:left="20dp"
          android:right="20dp"
          android:top="20dp"
          android:bottom="20dp"
          />
      <corners
          android:radius="15dp"
          />
  
  </shape>
  ```



### 스낵바 보여주기(예제)

1. 스낵바는 외부 라이브러리로 추가되었기 때문에 스낵바가 들어 있는 디자인 라이브러리(Design Library)를 프로젝트에 추가해야 사용할 수 있다. [File -> Project Structure] 메뉴를 누른다.

![1548776153157](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548776153157.png)

2. [app] 항목 -> [Dependencies] -> [+] 아이콘 -> [Library Dependency] 메뉴

   ![1548776281344](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548776281344.png)

3. com.android.support:design 이라는 항목 선택

   ![1548776372473](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548776372473.png)

4. **MainActivity.java**

   ```java
   ...
   public void onButton3Clicked(View v) {
           Snackbar.make(v, "스낵바입니다.", Snackbar.LENGTH_LONG).show();
       }
   ...
   ```



### 알림 대화상자 보여주기(예제)

: 일방적으로 메시지를 전달하는 역할을 주로 하며 "예", "아니오"와 같은 전형적인 응답을 처리한다.

* **MainActivity.java**

  ```java
  package com.example.sampledialog;
  
  import android.content.DialogInterface;
  import android.support.v7.app.AlertDialog;
  import android.support.v7.app.AppCompatActivity;
  import android.os.Bundle;
  import android.view.View;
  import android.widget.Button;
  import android.widget.TextView;
  
  public class MainActivity extends AppCompatActivity {
      TextView textView;
  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
  
          textView = (TextView) findViewById(R.id.textView);
  
          Button button = (Button) findViewById(R.id.button);
          button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  showMessage();
              }
          });
      }
  
      private void showMessage() {
          // 대화상자를 만들기 위한 빌더 객체 생성
          AlertDialog.Builder builder = new AlertDialog.Builder(this);
          builder.setTitle("안내");
          builder.setMessage("종료하시겠습니까?");
          builder.setIcon(android.R.drawable.ic_dialog_alert);
  
          // 예 버튼 추가
          builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                  String message = "예 버튼이 눌렸습니다. ";
                  textView.setText(message);
              }
          });
  
          // 취소 버튼 추가
          builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                  String message = "취소 버튼이 눌렸습니다. ";
                  textView.setText(message);
              }
          });
  
          // 아니오 버튼 추가
          builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                  String message = "아니오 버튼이 눌렸습니다. ";
                  textView.setText(message);
              }
          });
  
          // 대화상자 객체 생성 후 보여주기
          AlertDialog dialog = builder.create();
          dialog.show();
      }
  }
  ```

* **실행 결과**

  ![1548777965559](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548777965559.png)

  ![1548777995748](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548777995748.png)



## 04-3. 프로그레스바 사용하기

### 프로그레스바(예제), 에러!!

* **프로그레스바** : 작업의 진행 정도를 표시하거나 작업이 진행 중임을 사용자에게 알림.

  * **형태**

    * 막대 모양
    * 원 모양

  * **대표적인 메소드**

    ```java
    // 프로그레스바의 현재 값을 바꿀 때 사용
    void setProgress (int progress)
    void increamentProgressBy (int diff)
    
    // 타이틀바에 프로그레스바 표시
    requestWindowFeature(Window.FEATURE_PROGRESS);
    ```



* **activity_main.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      xmlns:tools="http://schemas.android.com/tools"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      tools:context=".MainActivity">
  
      <ProgressBar
          android:id="@+id/progressBar"
          style="?android:attr/progressBarStyleHorizontal"
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
          android:layout_marginStart="8dp"
          android:layout_marginLeft="8dp"
          android:layout_marginTop="8dp"
          android:indeterminateOnly="false"
          android:max="100"
          android:maxHeight="20dp"
          android:minHeight="20dp"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toTopOf="parent" />
  
      <LinearLayout
          android:layout_marginLeft="70dp"
          android:layout_marginTop="20dp"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toBottomOf="@id/progressBar"
          android:orientation="horizontal"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content">
  
          <Button
              android:id="@+id/button"
              android:layout_width="140dp"
              android:layout_height="wrap_content"
              android:text="보여주기"
              android:textSize="20dp"
              android:textStyle="bold" />
  
          <Button
              android:id="@+id/button2"
              android:layout_width="140dp"
              android:layout_height="wrap_content"
              android:text="닫기"
              android:textSize="20dp"
              android:textStyle="bold" />
  
      </LinearLayout>
  
  </android.support.constraint.ConstraintLayout>
  ```

* **MainActivity.java**

  ```java
  package com.example.sampleprogress;
  
  import android.app.ProgressDialog;
  import android.support.v7.app.AppCompatActivity;
  import android.os.Bundle;
  import android.view.View;
  import android.widget.Button;
  import android.widget.ProgressBar;
  
  public class MainActivity extends AppCompatActivity {
      ProgressBar progressBar;
      ProgressDialog dialog;
  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
  
          // 프로그레스바 객체를 참조하여 설정하기
          ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
          progressBar.setIndeterminate(false);
          progressBar.setMax(100);
          progressBar.setProgress(80);
  
          Button button = (Button) findViewById(R.id.button);
          button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  // 프로그레스 대화상자 객체 만들고 설정하기
                  dialog = new ProgressDialog(getApplicationContext());
                  dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                  dialog.setMessage("데이터를 확인하는 중입니다.");
  
                  dialog.show();
              }
          });
  
          Button button2 = (Button) findViewById(R.id.button2);
          button2.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if (dialog != null) {
                      // 프로그레스 대화상자 없애기
                      dialog.dismiss();
                  }
              }
          });
      }
  }
  ```

  

### 시크바(예제)

* **시크바(SeekBar)** : 프로그레스바를 확장하여 만든 것, 프로그레스바의 속성을 갖고 있으면서 사용자가 값을 조정할 수 있게한다.