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



### 제스처 이벤트 처리하기(예제)

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

### 프로그레스바(예제)

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
  import android.view.WindowManager;
  import android.widget.Button;
  import android.widget.LinearLayout;
  import android.widget.ProgressBar;
  import android.widget.SeekBar;
  import android.widget.TextView;
  
  public class MainActivity extends AppCompatActivity {
      private int brightness = 100;   // 기본 밝기 값
      TextView seekBarText;
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
                  dialog = new ProgressDialog(MainActivity.this);
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
  
  
          seekBarText = (TextView) findViewById(R.id.seekBarText);
  
          Button button3 = (Button) findViewById(R.id.button3);
          button3.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  LinearLayout seekBarPanel = (LinearLayout) findViewById(R.id.seekBarPanel);
                  seekBarPanel.setVisibility(View.VISIBLE);
              }
          });
  
          SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
          // 시크바 값을 변경했을 때 처리할 리스너 설정하기
          seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
              @Override
              public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                  setBrightness(progress);
                  seekBarText.setText(" 시크바의 값 : " + progress);
              }
  
              @Override
              public void onStartTrackingTouch(SeekBar seekBar) {
  
              }
  
              @Override
              public void onStopTrackingTouch(SeekBar seekBar) {
  
              }
          });
      }
  
      private void setBrightness(int value) {
          if(value < 10) {
              value = 10;
          } else if (value > 100) {
              value = 100;
          }
  
          brightness = value;
  
          // 화면 밝기 변경
          WindowManager.LayoutParams params = getWindow().getAttributes();
          params.screenBrightness = (float) value / 100;
          getWindow().setAttributes(params);
      }
  }
  ```

  

### 시크바(예제)

* **시크바(SeekBar)** : 프로그레스바를 확장하여 만든 것, 프로그레스바의 속성을 갖고 있으면서 사용자가 값을 조정할 수 있게한다.

* **seek_bar.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      xmlns:app="http://schemas.android.com/apk/res-auto">
  
      <!-- 시크바가 들어 있는 레이아웃 -->
      <LinearLayout
          android:id="@+id/seekBarPanel"
          android:layout_width="88dp"
          android:layout_height="51dp"
          android:orientation="vertical"
          android:visibility="gone"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toTopOf="parent"
          >
  
          <!-- 시크바 정의 -->
          <SeekBar
              android:id="@+id/seekBar"
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              android:max="100"
              android:progress="100" />
  
          <TextView
              android:id="@+id/seekBarText"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:text="100"
              android:textSize="20dp" />
  
      </LinearLayout>
  
      <Button
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toBottomOf="@+id/seekBarPanel"
          android:id="@+id/button3"
          android:textSize="20dp"
          android:text="시크바 보여주기"
          android:textStyle="bold"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content" />
  
  </android.support.constraint.ConstraintLayout>
  ```

* **MainActivity.java**

  ```java
  package com.example.sampleprogress;
  
  import android.app.ProgressDialog;
  import android.support.v7.app.AppCompatActivity;
  import android.os.Bundle;
  import android.view.View;
  import android.view.WindowManager;
  import android.widget.Button;
  import android.widget.LinearLayout;
  import android.widget.ProgressBar;
  import android.widget.SeekBar;
  import android.widget.TextView;
  
  public class MainActivity extends AppCompatActivity {
      private int brightness = 100;   // 기본 밝기 값
      TextView seekBarText;
      ProgressBar progressBar;
      ProgressDialog dialog;
  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.seek_bar);
  
          seekBarText = (TextView) findViewById(R.id.seekBarText);
  
          Button button3 = (Button) findViewById(R.id.button3);
          button3.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  LinearLayout seekBarPanel = (LinearLayout) findViewById(R.id.seekBarPanel);
                  seekBarPanel.setVisibility(View.VISIBLE);
              }
          });
  
          SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
          // 시크바 값을 변경했을 때 처리할 리스너 설정하기
          seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
              @Override
              public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                  setBrightness(progress);
                  seekBarText.setText(" 시크바의 값 : " + progress);
              }
  
              @Override
              public void onStartTrackingTouch(SeekBar seekBar) {
  
              }
  
              @Override
              public void onStopTrackingTouch(SeekBar seekBar) {
  
              }
          });
      }
  
      private void setBrightness(int value) {
          if(value < 10) {
              value = 10;
          } else if (value > 100) {
              value = 100;
          }
  
          brightness = value;
  
          // 화면 밝기 변경
          WindowManager.LayoutParams params = getWindow().getAttributes();
          params.screenBrightness = (float) value / 100;
          getWindow().setAttributes(params);
      }
  }
  
  ```

  

## 04-4. 간단한 애니메이션 사용하기

* **트윈 애니메이션(Tweened Animation) 방법** : 이동, 확대/축소, 회전과 같이 일정한 패턴을 가지고 움직이는 애니매이션을 구현할 때 사용.

* 애니메이션이 어떻게 동작하도록 할것인지를 정의한 정보는 XML로 만든다.

  ![1548822186012](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548822186012.png)

* **/res/anim/flow.xml(애니메이션 파일)**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <set xmlns:android="http://schemas.android.com/apk/res/android">
      <!-- 위치 이동을 위한 애니메이션 액션 정의 -->
      <!-- fromXDelta : X축 어디서부터 이동할 것인지 -->
      <!-- toXDelta : X축 어디까지 이동할 것인지 -->
      <!-- 100% ~ 0% : 뷰의 오른쪽 끝 지점에서 왼쪽 끝지점까지 이동-->
      <!-- duration : 애니메이션이 지속되는 시간 -->
      <!-- alpha 태그 : 투명도 변경 -->
      <translate
          android:fromXDelta="100%p"
          android:toXDelta="0%p"
          android:duration="6000"
          android:repeatCount="3"
          />
  
      <!-- 투명도 변경을 위한 애니메이션 액션 정의 -->
      <alpha
          android:fromAlpha="0.5"
          android:toAlpha="1"
          android:repeatCount="3"
          />
  </set>
  ```

* **MainActivity.java**

  ```java
  package com.example.samplebuttonanimation;
  
  import android.support.v7.app.AppCompatActivity;
  import android.os.Bundle;
  import android.view.View;
  import android.view.animation.Animation;
  import android.view.animation.AnimationUtils;
  import android.widget.Button;
  import android.widget.TextView;
  import android.widget.Toast;
  
  public class MainActivity extends AppCompatActivity {
      TextView textView;
      Animation flowAnim;
  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
  
          textView = (TextView) findViewById(R.id.textView);
          // 애니메이션 객체 로딩
          flowAnim = AnimationUtils.loadAnimation(this, R.anim.flow);
          // 애니메이션 리스너 설정하여 종료되는 시점 확인.
          flowAnim.setAnimationListener(new Animation.AnimationListener() {
              @Override
              public void onAnimationStart(Animation animation) {
  
              }
  
              @Override
              public void onAnimationEnd(Animation animation) {
                  Toast.makeText(getApplicationContext(),
                          "애니메이션 종료됨.",
                          Toast.LENGTH_LONG).show();
              }
  
              @Override
              public void onAnimationRepeat(Animation animation) {
  
              }
          });
      }
  
      // 텍스트뷰에 애니메이션 적용
      public void onButton1Clicked(View v){
          textView.startAnimation(flowAnim);
      }
  }
  ```

* **실행결과**

  ![1548823670993](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548823670993.png)

  ![1548823683301](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548823683301.png)

  ![1548823697854](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548823697854.png)



## 04-5. 페이지 슬라이딩 사용하기

**페이지 슬라이딩** : 버튼 등을 눌렀을 때 보이지 않던 뷰가 슬라이딩 방식으로 보이는 것으로 여러 뷰를 하나씩 전환하면서 보여주는 방식에 애니메이션을 결합한 것이다.

* **activity_main.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:tools="http://schemas.android.com/tools"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      tools:context=".MainActivity">
  
      <!-- 첫 번째 레이아웃 : 바탕 레이아웃 -->
      <LinearLayout
          android:background="#ff5555ff"
          android:orientation="vertical"
          android:layout_width="match_parent"
          android:layout_height="match_parent">
          <TextView
              android:textColor="#ffffffff"
              android:text="Base Area"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content" />
      </LinearLayout>
  
      <!-- 두 번째 레이아웃 : 슬라이딩으로 보일 레이아웃 -->
      <!-- visibility 속성 : 'gone'으로 설정하여 보이지 않도록 한다. -->
      <LinearLayout
          android:id="@+id/page"
          android:orientation="vertical"
          android:layout_gravity="right"
          android:visibility="gone"
          android:background="#ffffff66"
          android:layout_width="200dp"
          android:layout_height="match_parent">
          <TextView
              android:layout_weight="1"
              android:textColor="#ff000000"
              android:text="Area #1"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content" />
          <TextView
              android:layout_weight="1"
              android:text="Area #2"
              android:textColor="#ff000000"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content" />
      </LinearLayout>
  
      <!-- 세 번째 레이아웃 : 버튼이 들어있는 레이아웃 -->
      <LinearLayout
          android:orientation="vertical"
          android:layout_gravity="right|center_vertical"
          android:background="#00000000"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content">
          <!-- [Open/Close] 버튼 -->
          <Button
              android:id="@+id/button"
              android:text="Open"
              android:onClick="onButton1Clicked"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content" />
      </LinearLayout>
  
  </FrameLayout>
  ```

* **translate_right.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <set xmlns:android="http://schemas.android.com/apk/res/android"
      android:interpolator="@android:anim/accelerate_decelerate_interpolator">
      <translate
          android:fromXDelta="0%p"
          android:toXDelta="100%p"
          android:duration="500"
          android:repeatCount="0"
          android:fillAfter="true"
          />
  </set>
  ```

* **translate_left.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <set xmlns:android="http://schemas.android.com/apk/res/android"
      android:interpolator="@android:anim/accelerate_decelerate_interpolator">
      <translate
          android:fromXDelta="100%p"
          android:toXDelta="0%p"
          android:duration="500"
          android:repeatCount="0"
          android:fillAfter="true"
          />
  </set>
  ```

* **MainActivity.java**

  ```java
  package com.example.samplepagesliding;
  
  import android.annotation.SuppressLint;
  import android.support.annotation.Nullable;
  import android.support.v7.app.AppCompatActivity;
  import android.os.Bundle;
  import android.view.View;
  import android.view.animation.Animation;
  import android.view.animation.AnimationUtils;
  import android.widget.Button;
  import android.widget.LinearLayout;
  
  public class MainActivity extends AppCompatActivity {
  
      // 슬라이딩 페이지가 보이는지 여부
      boolean isPageOpen = false;
  
      // 왼쪽으로 이동 애니메이션 객체
      Animation translateLeftAnim;
      // 오른쪽으로 이동 애니메이션 객체
      Animation translateRightAnim;
  
      // 슬라이딩으로 보여줄 페이지
      LinearLayout page;
      Button button;
  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
  
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          button = (Button) findViewById(R.id.button);
  
          page = (LinearLayout) findViewById(R.id.page);
  
          // 애니메이션 객체 참조
          translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left);
          translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right);
  
          // 슬라이딩 애니메이션을 감시할 리스너
          SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
          translateLeftAnim.setAnimationListener(animListener);
          translateRightAnim.setAnimationListener(animListener);
      }
  
      public void onButton1Clicked(View v) {
          // 페이지가 열려 있으면 오른쪽으로 애니메이션
          if (isPageOpen) {
              page.startAnimation(translateRightAnim);
          }
          // 페이지가 닫혀 있으면 보이도록 한 후 왼쪽으로 애니메이션
          else {
              page.setVisibility(View.VISIBLE);
              page.startAnimation(translateLeftAnim);
          }
      }
  
      private class SlidingPageAnimationListener implements Animation.AnimationListener {
  
          @Override
          public void onAnimationStart(Animation animation) {
  
          }
  
          @SuppressLint("SetTextI18n")
          @Override
          public void onAnimationEnd(Animation animation) {
              // 페이지가 열려 있으면 안보이도록 하고
              // 버튼의 텍스트로 'Open'으로 변경
              if(isPageOpen) {
                  page.setVisibility(View.INVISIBLE);
  
                  button.setText("Open");
                  isPageOpen = false;
              }
              // 페이지가 닫혀 있으면 버튼의 텍스트를 'Close'로 변경
              else {
                  button.setText("Close");
                  isPageOpen = true;
              }
          }
  
          @Override
          public void onAnimationRepeat(Animation animation) {
  
          }
      }
  }
  ```



## 04-6. 프래그먼트

: 전체 화면 안에 부분 화면을 만들어 넣으면 자주 화면이 전환되는 불편함이 없어지고 넓은 화면을 잘 활용할 수 있게 된다.

### 프래그먼트에 대해 이해하기

: 부분 화면을 만든다는 것은 전체 화면을 위해 만든 레이아웃 안에 또 다른 레이아웃을 넣는 것이다. 이를 위해서 **프레임 레이아웃** 안에 여러 개의 레이아웃을 넣어 중첩시킨 후 가시성 속성으로 필요한 레이아웃만 보여줄 수 있게 만든다.

 하나의 화면을 여러 부분으로 나누어 보여주거나 각각의 부분 화면 단위로 바꾸어 보여주고 싶은 경우에 사용하는 것이 **프래그먼트(Fragment)**이다.

* **프래그먼트 사용 목적**
  * 분할된 화면들을 독립적으로 구성하기 위해 사용함
  * 분할된 화면들의 상태를 관리하기 위해 사용함

 프래그먼트는 **항상 액티비티 위에 올라가 있어야 한다**는 점을 기억해야 한다.

* **프래그먼트 동작 방식**

  ![1548827746259](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548827746259.png)

  * **왼쪽 동작 방식** : 액티비티가 동작하는 방식이다. 액티비티를 관리하는 시스템의 모듈은 액티비티 매니저이며, 이 액티비티 매니저에 의해 액티비티가 독립적으로 동작할 수 있다.
  * **오른쪽 동작 방식** : 프래그먼트 매니저라는 것을 만들어 프래그먼트들을 관리하도록 되어있다. 액티비티와 프래그먼트 간에 데이터를 전달할 때는 단순히 메소드를 만들고 메소드를 호출하는 방식을 사용한다.



### 프래그먼트를 화면에 추가하는 방법 이해하기

* **프래그먼트 사용하는 목적** : 분할된 화면을 독립적으로 사용하기 위한 것이다.

* **예시**

  XML 레이아웃 파일(/res/layout/fragment_main.xml)

  ```xml
  ...
  <TextView>
      android:layout_width="wrap_content"
      android:layoit_height="wrap_content"
      android:text="Hello world!"
  </TextView>
  ...
  ```

  자바 소스 파일(MainFragment.java)

  ```java
  ...
      class MainFragment extends Fragment {
          ...
              public View onCreateView(...) {
              ...
              inflater.inflate(...);	// xml 파일 인플레이션
          }
      }
  ...
  ```

  프래그먼트 추가 파일

  ```xml
  <fragment
            android:id="@+id/fragment"
            android:name="org.androidtown.fragment.MainFragment"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            />
  ```

  > 먼저 프래그먼트를 위한 XML 레이아웃을 만든다.
  >
  > /res/layout 폴더 안에 XML 레이아웃 파일인 fragment_main.xml을 만들고 화면을 정의한다.
  >
  > 그 다음에는 프래그먼트를 위한 자바 소스를 만든다. 프래그먼트는 Fragment 클래스를 상속할 수 있다.

* **프래그먼트 클래스의 주요 메소드들**

  ```java
  // 이 프래그먼트를 포함하는 액티비티를 리턴한다.
  public final Activity getActivity()
  
  // 이 프래그먼트를 포함하는 액티비티에서 프래그먼트 객체들과 의사소통하는 프래그먼트 매니저를 리턴한다.
  public final FragmentManager getFragmentManager()
      
  // 이 프래그먼트를 포함하는 부모가 프래그먼트일 경우 리턴함. 액티비티이면 null을 리턴
  public final Fragment getParentFragment()
      
  // 이 프래그먼트의 ID를 리턴함
  public final int getId()    
  ```

  > 프래그먼트 클래스를 XML 레이아웃 파일의 내용을 자바 소스 파일과 매칭하기 위해 XML 레이아웃 파일의 내용을 인플레이션한 후 onCreateView() 메소드 안에 넣는다. 따라서 인플레이션을 위한 inflate() 메소드를 호출하면 되고 인플레이션 과정이 끝나면 프래그먼트가 하나의 뷰처럼 동작할 수 있는 상태가 된다.

* **프래그먼트 매니저(FragmentManager) 주요 메소드**

  ```java
  // 프래그먼트를 변경하기 위한 트랜젝션을 시작함.
  public abstract FragmentTransaction beginTransaction()
      
  // ID를 이용해 프래그먼트 객체를 찾음
  public abstract Fragment findFragmentById (int id)
      
  // 태그 정보를 사용해 프래그먼트 객체를 찾음
  public abstract Fragment findFragmentByTag (String tag)
  
  // 트랜젝션은 commit() 메소드를 호출하면 실행되지만 비동기(asynchronous) 방식으로 실행되므로 즉시 실행하고 싶다면 이 메소드를 추가로 호출해야 한다.
  public abstract boolean executePedingTransactions()
  ```

  > 메인 화면을 위해 만들어진 activity_main.xml 파일에 직접 \<fragment> 라는 태그를 이용해 프래그먼트를 추가할 수도 있고, 새로 정의한 프래그먼트 클래스의 인스턴스 객체를 new 연산자를 이용해 만들어준 후 **FragmentManager 객체의 add() 메소드**를 이용해 액티비티에 추가할 수도 있다.

* **프래그먼트의 대표적인 특성**

  | 특성          | 설명                                                         |
  | ------------- | ------------------------------------------------------------ |
  | 뷰 특성       | 뷰그룹에 추가되거나 레이아웃의 일부가 될 수 있음<br />(뷰에서 상속받은 것은 아니며 뷰를 담고 있는 일종의 틀임) |
  | 액티비티 특성 | 액티비티처럼 수명주기(Lifecycle) 가지고 있음<br />(context 객체는 아니며 라이프사이클은 액티비티에 종속됨) |

* **미리 정의된 몇가지 프래그먼트 클래스**

  * **DialogFragment** : 액티비티의 수명주기에 의해 관리되는 대화상자
  * **ListFragment** : 데이터를 리스트뷰 형태로 보여줄 수 있도록 하며 ListActivity 클래스와 비슷하다.



### 프래그먼트 만들어 화면에 추가하기(예제)

* **/res/layout/fragment_main.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <android.support.constraint.ConstraintLayout
      xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent"
      android:layout_height="match_parent">
  
      <LinearLayout
          android:orientation="vertical"
          android:layout_width="match_parent"
          android:layout_height="match_parent">
  
          <TextView
              android:text="메인 프래그먼트"
              android:textSize="30dp"
              android:id="@+id/textView"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content" />
  
          <Button
              android:id="@+id/button"
              android:text="메뉴 화면으로"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content" />
  
      </LinearLayout>
  
  </android.support.constraint.ConstraintLayout>
  ```

* **/java/MainFragment.java**

  ```java
  package com.example.samplefragment;
  
  import android.os.Bundle;
  import android.support.annotation.NonNull;
  import android.support.annotation.Nullable;
  import android.support.v4.app.Fragment;
  import android.view.LayoutInflater;
  import android.view.View;
  import android.view.ViewGroup;
  
  public class MainFragment extends Fragment {
  
      @Nullable
      @Override
      public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
  
          // inflate() 메소드로 전달되는 첫 번째 파라미터는 XML 레이아웃 파일이고
          // 두 번째 파라미터는 이 XML 레이아웃이 설정될 뷰그룹 객체이므로
          // container 객체를 전달한다.
          // inflate() 메소드를 호출하여 반환된 ViewGroup 객체 또한 이 프래그먼트의
          // 가장 상위 레이아웃인데 인플레이션된 상태로 반환되므로
          // 이 객체를 return 키워드로 리턴한다.
          ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main,
                  container, false);
          return rootView;
      }
  }
  ```

* **/res/layout/activity_main.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <RelativeLayout
      xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      xmlns:tools="http://schemas.android.com/tools"
      android:id="@+id/container"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      tools:context=".MainActivity">
  
      <fragment
          android:id="@+id/mainFragment"
          android:layout_width="match_parent"
          android:layout_height="match_parent"
          android:name="com.example.samplefragment.MainFragment"
          />
  
  </RelativeLayout>
  ```

* **/java/MainActivity.java**

  ```java
  package com.example.samplefragment;
  
  import android.support.v7.app.AppCompatActivity;
  import android.os.Bundle;
  
  public class MainActivity extends AppCompatActivity {
  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
      }
  }
  ```

  > **액티비티 메인 화면 위에 프래그먼트 화면이 올라가 있는것을 알 수 있다.**

* **실행 결과**

  ![1549959973339](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1549959973339.png)



### 버튼 클릭했을 때 코드에서 프래그먼트 추가하기(예제)

* **프래그먼트 사용하는 과정**

  1. 프래그먼트를 위한 XML 레이아웃 만들기
  2. 프래그먼트 클래스 만들기
  3. 액티비티를 XML 레이아웃에 추가하기

* **자바 소스 코드를 사용해 XML 레이아웃에 추가하는 방법**

  1. XML 레이아웃에 추가하는 방법
  2. 자바 소스 코드로 추가하는 방법

* **예제**

  **/res/layout/fragment_menu.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <android.support.constraint.ConstraintLayout
      xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent"
      android:layout_height="match_parent">
  
      <LinearLayout
          android:background="@color/colorPrimary"
          android:orientation="vertical"
          android:layout_width="match_parent"
          android:layout_height="match_parent">
  
          <TextView
              android:text="메뉴 프래그먼트"
              android:textSize="30dp"
              android:id="@+id/textView2"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content" />
  
          <Button
              android:id="@+id/button2"
              android:text="메인 화면으로"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content" />
  
      </LinearLayout>
  
  </android.support.constraint.ConstraintLayout>
  ```

  **/java/MenuFragment.java**

  ```java
  package com.example.samplefragment;
  
  import android.os.Bundle;
  import android.support.annotation.NonNull;
  import android.support.annotation.Nullable;
  import android.support.v4.app.Fragment;
  import android.view.LayoutInflater;
  import android.view.View;
  import android.view.ViewGroup;
  
  public class MenuFragment extends Fragment {
      @Nullable
      @Override
      public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
  
          // inflate() 메소드로 전달되는 첫 번째 파라미터의 값이
          // R.layout.fragment_menu 이다. 그래서 MenuFragment 클래스에는
          // fragment_menu.xml 파일의 내용이 인플레이션되어 설정된다.
          ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_menu,
                  container, false);
          return rootView;
      }
  }
  ```

  **/res/layout/fragment_main.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <android.support.constraint.ConstraintLayout
      xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent"
      android:layout_height="match_parent">
  
      <LinearLayout
          android:orientation="vertical"
          android:layout_width="match_parent"
          android:layout_height="match_parent">
  
          <TextView
              android:text="메인 프래그먼트"
              android:textSize="30dp"
              android:id="@+id/textView"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content" />
  
          <Button
              android:id="@+id/button"
              android:text="메뉴 화면으로"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content" />
  
      </LinearLayout>
  
  </android.support.constraint.ConstraintLayout>
  ```

  **/java/MainFragment.java**

  ```java
  package com.example.samplefragment;
  
  import android.os.Bundle;
  import android.support.annotation.NonNull;
  import android.support.annotation.Nullable;
  import android.support.v4.app.Fragment;
  import android.view.LayoutInflater;
  import android.view.View;
  import android.view.ViewGroup;
  import android.widget.Button;
  
  public class MainFragment extends Fragment {
  
      @Nullable
      @Override
      public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
  
          // inflate() 메소드로 전달되는 첫 번째 파라미터는 XML 레이아웃 파일이고
          // 두 번째 파라미터는 이 XML 레이아웃이 설정될 뷰그룹 객체이므로
          // container 객체를 전달한다.
          // inflate() 메소드를 호출하여 반환된 ViewGroup 객체 또한 이 프래그먼트의
          // 가장 상위 레이아웃인데 인플레이션된 상태로 반환되므로
          // 이 객체를 return 키워드로 리턴한다.
          // 이 프래그먼트의 가장 상위 레이아웃은 인플레이션을 통해 참조한 rootView 객체이다.
          ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main,
                  container, false);
  
          Button button = (Button) rootView.findViewById(R.id.button);
          button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  MainActivity activity = (MainActivity) getActivity();
                  activity.onFragmentChanged(0);
              }
          });
  
          return rootView;
      }
  }
  ```

  **/java/MainActivity.java**

  ```java
  package com.example.samplefragment;
  
  import android.support.v7.app.AppCompatActivity;
  import android.os.Bundle;
  
  public class MainActivity extends AppCompatActivity {
      MainFragment mainFragment;
      MenuFragment menuFragment;
  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
  
          mainFragment = (MainFragment)
              getSupportFragmentManager().findFragmentById(R.id.mainFragment);
          menuFragment = new MenuFragment();
      }
  
      // 프래그먼트 매니저는 프래그먼트를 다루는 작업을 해 주는 객체로
      // 프래그먼트를 추가, 삭제 또는 교체 등의 작업을 할 수 있게 해준다.
      // 그런데 이런 작업들은 프래그먼트를 변경할 때
      // 오류가 생기면 다시 원상태로 돌릴 수 있어야 하므로
      // 트랜잭션 객체를 만들어 실행한다.
      public void onFragmentChanged(int index) {
          if (index == 0) {
              // 트랜잭션 객체는 beginTransaction() 메소드를
              // 호출하면 시작되고 commit() 메소드를 호출하면 실행된다.
              getSupportFragmentManager().beginTransaction()
                      .replace(R.id.container, menuFragment).commit();
  
          } else if (index == 1) {
              getSupportFragmentManager().beginTransaction()
                      .replace(R.id.container, mainFragment).commit();
  
          }
      }
  }
  ```

* **실행 결과**

  ![1549963597998](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1549963597998.png)

  

* **액티비티와 프래그먼트가 의사소통하는 방식**

  ![1549963565628](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1549963565628.png)



### 프래그먼트의 수명주기

: 프래그먼트는 액티비티를 본떠 만들면서 액티비티처럼 독립적으로 동작하도록 **수명주기(Life Cycle) 메소드**를 추가했습니다. 따라서 상태에 따라 콜백 함수가 호출되므로 그 안에 필요한 기능을 넣을 수 있다. 즉, 액티비티 안에 들어 있는 프래그먼트도 필요에 따라 화면에 보이거나 보이지 않게 되므로 액티비티 처럼 각각의 상태가 관리되는 것이 필요하다.

* **화면에 보이기 전에 호출되는 상태 메소드**

  | 메소드                                          | 설명                                                         |
  | ----------------------------------------------- | ------------------------------------------------------------ |
  | onAttach(Activity)                              | 프래그먼트가 액티비티와 연결될 때 호출됨.                    |
  | onCreate(Bundle)                                | 프래그먼트가 초기화될 때 호출됨.<br />(new 연산자를 이용해 새로운 프래그먼트 객체를 만드는 시점이 아니라는 점에 주의해야 함.) |
  | onCreateView(LayoutInflator, ViewGroup, Bundle) | 프래그먼트와 관련되는 뷰 계층을 만들어서 리턴함.             |
  | onActivityCreated(Bundle)                       | 프래그먼트와 연결된 액티비티가 onCreate() 메소드의 작업을 완료했을 때 호출됨. |
  | onStart()                                       | 프래그먼트와 연결된 액티비티가 onStart()되어 사용자에게 프래그먼트가 보일 때 호출됨. |
  | onResume()                                      | 프래그먼트와 연결된 액티비티가 onResume()되어 사용자와 상호작용할 수 있을 때 호출됨. |

  > 가장 먼저 **onAttach() 메소드**가 호출되면서 액티비티에 프래그먼트가 추가되면 그 다음에 **onCreate() 메소드**가 호출. onAttach() 메소드는 액티비티를 위해 설정해야 하는 정보들을 처리한다. **onCreateView() 메소드**는 프래그먼트와 관련되는 뷰들의 계층도를 구성하는 과정에서 호출. 액티비티의 onCreate() 메소드는**onActivityCreated()** 메소드와 동일하다.

* **중지되면서 호출되는 상태 메소드**

  | 메소드          | 설명                                                         |
  | --------------- | ------------------------------------------------------------ |
  | onPause()       | 프래그먼트와 연결된 액티비티가 onPause()되어 사용자와 상호작용을 중지할 때 호출됨. |
  | onStop()        | 프래그먼트와 연결된 액티비티가 onStop()되어 화면에서 더 이상 보이지 않을 때나 프래그먼트의 기능이 중지되었을 때 호출됨. |
  | onDestroyView() | 프래그먼트와 관련된 뷰 리소스를 해제할 수 있도록 호출됨.     |
  | onDestroy()     | 프래그먼트의 상태를 마지막으로 정리할 수 있도록 호출됨.      |
  | onDetach()      | 프래그먼트가 액티비티와 연결을 끊기 바로 전에 호출됨.        |

  * **onPause() == onStop(), 동일한 상태 메소드**
  * **onDetach() 와 onAttach() 는 정반대**

* **프래그먼트 수명주기**

  ![1551007993516](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1551007993516.png)

  > **Fragment Start** : 액티비티에 프래그먼트 추가
  >
  > **Fragment Is Running** : 프래그먼트 활성화(액티비티 화면에 보이는 상태)
  >
  > **Fragment End** : 액티비티에서 프래그먼트 제거

  * **주의할 점**

    : 액티비티 위에 프래그먼트가 올라가기 전까지는 프래그먼트로 동작하지 않는다는 점

    ```java
    // 프래그먼트 객체는 만들어졌지만 프래그먼트로 동작하지는 않음.
    MyFragment fragment = new MyFragment();
    // 액티비티에 추가된 후 프래그먼트로 동작함.
    getSupportFragmentManager().beginTransaction().add(fragment).commit();
    ```



### 두 개의 프래그먼트로 구성된 이미지 뷰어 만들기(예제)

* **/res/layout/fragment_list.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <android.support.constraint.ConstraintLayout
      xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent"
      android:layout_height="match_parent">
      <LinearLayout
          android:orientation="vertical"
          android:layout_width="match_parent"
          android:layout_height="match_parent">
  
          <ListView
              android:id="@+id/listView"
              android:layout_width="match_parent"
              android:layout_height="match_parent">
  
          </ListView>
  
      </LinearLayout>
  
  </android.support.constraint.ConstraintLayout>
  ```

  ![1551012063177](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1551012063177.png)

* **/java/com~/ListFragment.java**

  ```java
  package com.example.samplefragment2;
  
  import android.content.Context;
  import android.os.Bundle;
  import android.support.annotation.NonNull;
  import android.support.annotation.Nullable;
  import android.support.v4.app.Fragment;
  import android.view.LayoutInflater;
  import android.view.View;
  import android.view.ViewGroup;
  import android.widget.AdapterView;
  import android.widget.ArrayAdapter;
  import android.widget.ListView;
  
  import com.example.samplefragment2.R;
  
  // Fragment 클래스 상속
  public class ListFragment extends Fragment {
      // ArrayAdapter 를 리스트뷰의 setAdapter() 메소드로 설정하면
      // 그 안에 들어 있는 각각의 데이터가 리스트뷰의 각 줄에 보이게 된다.
      String[] values = {"첫 번째 이미지", "두 번째 이미지", "세 번째 이미지"};
  
      // 프래그먼트에 올라간 액티비티가 다른 액티비티로 변경될 때 마다
      // 해당 액티비티가 무엇인지 확인하기 위해 인터페이스를 구현
      public static interface ImageSelectionCallback {
          // 선택된 값으로 다른 프래그먼트의 이미지를 바꿔주기위해
          // 액티비티 쪽으로 데이터를 전달하기 위한 메소드 정의
          public void onImageSelected(int position);
      }
  
      public ImageSelectionCallback callback;
  
      @Override
      public void onAttach(Context context) {
          super.onAttach(context);
  
          // 이 프래그먼트가 어떤 액티비티에 올라갔는지를 알 수 있는
          // onAttach() 메소드에 MainActivity 객체를 참조한 후
          // callback 변수에 할당
          if (context instanceof ImageSelectionCallback) {
              callback = (ImageSelectionCallback) context;
          }
      }
  
      @Nullable
      @Override
      public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          // fragment_list.xml 파일 인플레이션
          ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list,
                  container, false);
          ListView listView = (ListView) rootView.findViewById(R.id.listView);
          ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                  android.R.layout.simple_list_item_1, values);
          listView.setAdapter(adapter);
  
          // 리스트뷰의 한 아이템을 선택했을 때 어떤 아이템을 선택했는지
          // 알아낸 후 처리
          listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  // position : 몇 번째 아이템이 클릭된 것인지
                  if(callback != null) {
                      callback.onImageSelected(position);
                  }
              }
          });
  
          return rootView;
      }
  }
  ```

* **/res/layout/fragment_viewer.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <android.support.constraint.ConstraintLayout
      xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent"
      android:layout_height="match_parent">
  
      <LinearLayout
          android:orientation="vertical"
          android:layout_width="match_parent"
          android:layout_height="match_parent">
  
          <ImageView
              android:id="@+id/imageView"
              android:layout_width="match_parent"
              android:layout_height="match_parent" />
  
      </LinearLayout>
  
  </android.support.constraint.ConstraintLayout>
  ```

* **/java/com~/ViewerFragment.java**

  ```java
  package com.example.samplefragment2;
  
  import android.os.Bundle;
  import android.support.annotation.NonNull;
  import android.support.annotation.Nullable;
  import android.support.v4.app.Fragment;
  import android.view.LayoutInflater;
  import android.view.View;
  import android.view.ViewGroup;
  import android.widget.ImageView;
  
  public class ViewerFragment extends Fragment {
      ImageView imageView;
  
      @Nullable
      @Override
      public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          // 인플레이션
          ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_viewer,
                  container, false);
  
          // 이미지뷰 객체를 찾아 imageView 변수에 할당
          imageView = (ImageView) rootView.findViewById(R.id.imageView);
  
          return rootView;
      }
  
      // 액티비티에서 이 프래그먼트에 있는 이미지뷰에 이미지를 설정할 수 있도록 한다.
      public void setImage(int resId) {
          imageView.setImageResource(resId);
      }
  }
  ```

* **/res/activity_main.xml**

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
  
          <fragment
              android:id="@+id/listFragment"
              android:layout_weight="1"
              android:name="com.example.samplefragment2.ListFragment"
              android:layout_width="match_parent"
              android:layout_height="match_parent"/>
  
          <fragment
              android:id="@+id/viewerFragment"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              android:layout_weight="1"
              android:name="com.example.samplefragment2.ViewerFragment"
              />
  
      </LinearLayout>
  
  </android.support.constraint.ConstraintLayout>
  ```

  ![1551012195012](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1551012195012.png)

* **/java/com~/MainActivity.java**

  ```java
  package com.example.samplefragment2;
  
  import android.os.PersistableBundle;
  import android.support.v4.app.Fragment;
  import android.support.v4.app.FragmentManager;
  import android.support.v7.app.AppCompatActivity;
  import android.os.Bundle;
  
  // MainActivity 는 ImageSelectionCallback 인터페이스를 구현하도록 만들고
  // 이 인터페이스에 정의된 onImageSelected() 메소드도 구현한다.
  public class MainActivity extends AppCompatActivity implements ListFragment.ImageSelectionCallback {
  
      ListFragment listFragment;
      ViewerFragment viewerFragment;
  
      int[] images = {R.drawable.dream01, R.drawable.dream02, R.drawable.dream03};
  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
  
          FragmentManager manager = getSupportFragmentManager();
  
          // 두 개의 프래그먼트를 찾아 변수에 할당한다
          listFragment = (ListFragment) manager.findFragmentById(R.id.listFragment);
          viewerFragment = (ViewerFragment) manager.findFragmentById(R.id.viewerFragment);
      }
  
  
      @Override
      // onImageSelected() 메소드는 리스트 프래그먼트에서 호출하게 되며
      // onImageSelected() 메소드가 호출되면 뷰어 프래그먼트의 setImage() 메소드를 호출하여
      // 이미지가 바뀌도록 한다.
      public void onImageSelected(int position) {
          viewerFragment.setImage(images[position]);
      }
  }
  ```

* **실행 결과**

  ![1551012247455](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1551012247455.png)



## 04-7. 액션바와 탭 사용하기

### 화면에 메뉴 기능 넣기(예제)

* **메뉴**

  * **옵션 메뉴(Option Menu)** : 시스템 [메뉴] 버튼을 눌렀을 때 숨어있던 메뉴가 보인다.
  * **컨텍스트 메뉴(Context Menu)** : 입력상자를 길게 눌렀을 때 '복사하기', '붙여넣기' 등의 팝업 형태의 메뉴가 뜨는 것

* **옵션 메뉴와 컨텍스트 메뉴 설정 메소드**

  : 두 메소드를 다시 정의하기만 하면 매우 쉽게 메뉴를 추가할 수 있다.

  ```java
  boolean onCreateOptionMenu (Menu menu)
  void onCreateContextMenu (ContextMenu menu, View v,
                           ContextMenu.ContextMenuInfo menuInfo)
  ```

* **메뉴 아이템 추가하는 메소드들**

  ```java
  MenuItem add (int groupId, int itemId, int order, CharSequence title)
  MenuItem add (int groupId, int itemId, int order, int titleRes)
  
  // 아이템이 많아서 서브 메뉴로 추가하고 싶을 때
  SubMenu addSubMenu (int titleRes)
  ```

  > **파라미터**
  >
  > **groupId** : 아이템을 하나의 그룹으로 묶을 때 사용
  >
  > **itemId** : 아이템이 갖는 고유 ID 값, 아이템이 선택되었을 때 각각의 아이템을 구분할 때 사용할 수 있다.

* **예제(옵션 메뉴 추가하기)**

  **/res/menu/menu_main.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <menu xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto">
  
      <item
          android:id="@+id/menu_refresh"
          android:title="새로고침"
          android:icon="@drawable/menu_refresh"
          app:showAsAction="always"
          />
  
      <item
          android:id="@+id/menu_search"
          android:title="검색"
          android:icon="@drawable/menu_search"
          app:showAsAction="always"
          />
  
      <item
          android:id="@+id/menu_settings"
          android:title="설정"
          android:icon="@drawable/menu_settings"
          app:showAsAction="always"
          />
  
  </menu>
  ```

  | showAsAction 속성 값 | 설 명                                                        |
  | -------------------- | ------------------------------------------------------------ |
  | always               | 항상 액션바에 아이템을 추가하여 표시합니다.                  |
  | never                | 액션바에 아이템을 추가하여 표시하지 않습니다.(디폴트 값)     |
  | ifRoom               | 액션바에 여유 공간이 있을 때만 아이템을 표시합니다.          |
  | withText             | title 속성으로 설정된 제목을 같이 표시합니다.                |
  | collapseActionView   | 아이템에 설정한 뷰(actionViewLayout으로 설정한 뷰)의 아이콘만 표시합니다. |

  ![1551014322747](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1551014322747.png)

  **/java/com~/MainActivity.java**

  ```java
  package com.example.sampleoptionmenu;
  
  import android.support.v7.app.AppCompatActivity;
  import android.os.Bundle;
  import android.view.Menu;
  import android.view.MenuItem;
  import android.widget.Toast;
  
  public class MainActivity extends AppCompatActivity {
  
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
  }
  ```

  **실행 결과**

  ![1551014593897](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1551014593897.png)

  > **컨텍스트 메뉴를 특정 뷰에 등록하고 싶은 경우**
  >
  > : registerForContextMenu() 메소드 재정의



### 액션바 좀 더 살펴보기(예제, 오류)

: 액션바는 기본적으로 제목을 보여주는 타이틀의 기능을 한다.

* **액션바 메소드**

  ```java
  ActionBar abar = getActionBar();
  abar.show();			// 액션바 보이기
  abar.hide();			// 액션바 감추기
  abar.setSubtitle()		// 부제목 달아주기
  ```

* **예제(SampleActionBar01, 오류!!, 액션바 추가하기)**

  

  | 디스플레이 옵션 상수 | 설 명                                                    |
  | -------------------- | -------------------------------------------------------- |
  | DISPLAY_USE_LOGO     | 홈 아이콘 부분에 로고 아이콘을 사용합니다.               |
  | DISPLAY_SHOW_HOME    | 홈 아이콘을 표시하도록 합니다.                           |
  | DISPLAY_HOME_AS_UP   | 홈 아이콘에 뒤로 가기 모양의 < 아이콘을 같이 표시합니다. |
  | DISPLAY_SHOW_TITLE   | 타이틀을 표시하도록 합니다.                              |

  > 액션바의 디스플레이 옵션으로 설정할 수 있는 상수들



### 탭으로 보여주기(예제)

: 때로는 하나의 화면에 여러 가지 구성 요소를 넣어두고 필요에 따라 전환하여 보여주는 것이 좋은 경우가 있는데 대표적인 것이 서브 화면들이다.

* **탭(Tab)** : 하나의 뷰에서 여러 개의 정보를 보고자 할 때 일반적으로 사용하는 뷰

* **탭 위젯의 구성요소**

  ![1551072517074](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1551072517074.png)

  * **탭 호스트(TabHost)** : 탭 위젯 전체. 크게 상단의 탭 위젯과 하단의 프레임 레이아웃으로 구성된다.
  * **탭 위젯(TabWidget)** : 각각의 서브 화면을 선택할 수 있는 탭들이 추가되어 있다.
  * **프레임 레이아웃(Frame Layout)**

* **예제(탭에 선택에 따른 프래그먼트 화면 전환)**

  **/res/layout/activity_main.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      xmlns:tools="http://schemas.android.com/tools"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      tools:context=".MainActivity">
  
      <RelativeLayout
          android:layout_width="match_parent"
          android:layout_height="match_parent">
  
          <!-- 액션바 영역을 포함한 전체 화면의 위치를 잡아주는 역할 -->
          <android.support.design.widget.CoordinatorLayout
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              >
  
              <!-- CoordinatorLayout과 AppBarLayout(액션바)를 같이 사용하면 다른 레이아웃 들이 간격이나 위치가 자동으로 결정된다 -->
              <!-- 탭 위젯 -->
              <android.support.design.widget.AppBarLayout
                  android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
                  android:layout_width="match_parent"
                  android:layout_height="wrap_content">
  
  
                  <android.support.v7.widget.Toolbar
                      android:layout_width="match_parent"
                      android:layout_height="wrap_content"
                      android:background="@color/colorPrimaryDark"
                      android:theme="@style/ThemeOverlay.AppCompat.Dark"
                      android:elevation="1dp"
                      android:id="@+id/toolbar"
                      >
  
                      <TextView
                          android:layout_width="wrap_content"
                          android:layout_height="wrap_content"
                          android:id="@+id/titleText"
                          android:textAppearance="@style/Base.TextAppearance.Widget.AppCompat.Toolbar.Title"
                          android:text="타이틀"
                          />
  
                  </android.support.v7.widget.Toolbar>
  
                  <!-- 탭의 버튼들이 들어갈 수 있는 레이아웃 -->
                  <!-- tabMode, tabGravity 를 fill 로 설정하여 탭 버튼들이 동일한 크기를 갖도록 한다. -->
                  <android.support.design.widget.TabLayout
                      android:id="@+id/tabs"
                      app:tabMode="fixed"
                      app:tabGravity="fill"
                      app:tabTextColor="@color/colorPrimary"
                      app:tabSelectedTextColor="@color/colorAccent"
                      android:elevation="1dp"
                      android:background="@android:color/background_light"
                      android:layout_width="match_parent"
                      android:layout_height="wrap_content"/>
  
              </android.support.design.widget.AppBarLayout>
  
              <!-- 탭 콘텐츠 -->
              <!-- 프래그먼트를 넣어 줄 위치 -->
                  <FrameLayout
                      app:layout_behavior="@string/appbar_scrolling_view_behavior"
                      android:id="@+id/container"
                      android:layout_width="match_parent"
                      android:layout_height="match_parent">
                  </FrameLayout>
  
          </android.support.design.widget.CoordinatorLayout>
  
      </RelativeLayout>
  
  </android.support.constraint.ConstraintLayout>
  ```

  **/res/values/styles.xml**

  ```xml
  <resources>
  
      <!-- Base application theme. -->
      <!-- NoActionBat 로 수정 -->
      <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
          <!-- Customize your theme here. -->
          <item name="colorPrimary">@color/colorPrimary</item>
          <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
          <item name="colorAccent">@color/colorAccent</item>
      </style>
  
  </resources>
  ```

  **/res/layout/fragment1.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <android.support.constraint.ConstraintLayout
      xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent"
      android:layout_height="match_parent">
  
      <LinearLayout
          android:background="@color/design_default_color_primary_dark"
          android:layout_width="match_parent"
          android:layout_height="match_parent">
  
          <Button
              android:id="@+id/button1"
              android:text="첫번째"
              android:textSize="30dp"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content" />
  
      </LinearLayout>
  
  </android.support.constraint.ConstraintLayout>
  ```

  **/res/layout/fragment2.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <android.support.constraint.ConstraintLayout
      xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent"
      android:layout_height="match_parent">
  
      <LinearLayout
          android:background="@color/colorPrimary"
          android:layout_width="match_parent"
          android:layout_height="match_parent">
  
          <Button
              android:id="@+id/button2"
              android:text="두번째"
              android:textSize="30dp"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content" />
  
      </LinearLayout>
  
  </android.support.constraint.ConstraintLayout>
  ```

  **/res/layout/fragment3.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <android.support.constraint.ConstraintLayout
      xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent"
      android:layout_height="match_parent">
  
      <LinearLayout
          android:background="@color/colorAccent"
          android:layout_width="match_parent"
          android:layout_height="match_parent">
  
          <Button
              android:id="@+id/button3"
              android:text="세번째"
              android:textSize="30dp"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content" />
  
      </LinearLayout>
  
  </android.support.constraint.ConstraintLayout>
  ```

  **/java/com~/Fragment1.java**

  ```java
  package com.example.sampletab;
  
  import android.os.Bundle;
  import android.support.annotation.NonNull;
  import android.support.annotation.Nullable;
  import android.support.v4.app.Fragment;
  import android.view.LayoutInflater;
  import android.view.View;
  import android.view.ViewGroup;
  
  public class Fragment1 extends Fragment {
  
      @Nullable
      @Override
      public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment1,
                  container, false);
          return rootView;
      }
  }
  ```

  **/java/com~/Fragment2.java**

  ```java
  package com.example.sampletab;
  
  import android.os.Bundle;
  import android.support.annotation.NonNull;
  import android.support.annotation.Nullable;
  import android.support.v4.app.Fragment;
  import android.view.LayoutInflater;
  import android.view.View;
  import android.view.ViewGroup;
  
  public class Fragment2 extends Fragment {
  
      @Nullable
      @Override
      public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment2,
                  container, false);
          return rootView;
      }
  }
  ```

  **/java/com~/Fragment3.java**

  ```java
  package com.example.sampletab;
  
  import android.os.Bundle;
  import android.support.annotation.NonNull;
  import android.support.annotation.Nullable;
  import android.support.v4.app.Fragment;
  import android.view.LayoutInflater;
  import android.view.View;
  import android.view.ViewGroup;
  
  public class Fragment3 extends Fragment {
  
      @Nullable
      @Override
      public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment3,
                  container, false);
          return rootView;
      }
  }
  ```

  **/java/com~/MainActivity.java**

  ```java
  package com.example.sampletab;
  
  import android.support.design.widget.TabLayout;
  import android.support.v4.app.Fragment;
  import android.support.v7.app.ActionBar;
  import android.support.v7.app.AppCompatActivity;
  import android.os.Bundle;
  import android.util.Log;
  import android.support.v7.widget.Toolbar;
  
  public class MainActivity extends AppCompatActivity {
      Toolbar toolbar;
  
      Fragment1 fragment1;
      Fragment2 fragment2;
      Fragment3 fragment3;
  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
  
          toolbar = (Toolbar) findViewById(R.id.toolbar);
          setSupportActionBar(toolbar);
  
          ActionBar actionBar = getSupportActionBar();
          actionBar.setDisplayShowTitleEnabled(false);
  
          fragment1 = new Fragment1();
          fragment2 = new Fragment2();
          fragment3 = new Fragment3();
  
          getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
  
          TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
  
          // addTab() 메소드로 탭 버튼을 추가한다.
          tabs.addTab(tabs.newTab().setText("통화기록"));
          tabs.addTab(tabs.newTab().setText("스팸기록"));
          tabs.addTab(tabs.newTab().setText("연락처"));
  
          tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
              @Override
              // 탭 버튼이 선택될 때마다 호출되는 리스너
              public void onTabSelected(TabLayout.Tab tab) {
                  int position = tab.getPosition();
                  Log.d("MainActivity", "선택된 탭: " + position);
  
                  Fragment selected = null;
                  // position 정보를 확인한 후 처리
                  if (position == 0) {
                      selected = fragment1;
                  } else if (position == 1) {
                      selected = fragment2;
                  } else if (position == 2) {
                      selected = fragment3;
                  }
  
                  // container 라는 id를 가진 FrameLayout 안에 각각의 탭 버튼에
                  // 해당하는 프래그먼트 화면이 보이도록 한다.
                  getSupportFragmentManager().beginTransaction().replace(
                          R.id.container, selected
                  ).commit();
              }
  
              @Override
              public void onTabUnselected(TabLayout.Tab tab) {
  
              }
  
              @Override
              public void onTabReselected(TabLayout.Tab tab) {
  
              }
          });
      }
  
  }
  ```

  **실행 결과**

  ![1551077532465](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1551077532465.png)

  ![1551077549185](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1551077549185.png)

  ![1551077561927](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1551077561927.png)



## 04-8. 웹브라우저 사용하기

: 웹브라우저를 앱 안에 넣고 싶은 경우에는 **웹뷰(WebView)**를 사용하면 되는데 XML 레이아웃에서는 **\<WebView>** 태그로 정의합니다. 그리고 항상 매니페스트에 인터넷 접속 권한이 등록되어 있어야 합니다.

* **매니페스트에 인터넷 접속 권한 등록**

  ```xml
  <uses-permission android:name="android.permission.INTERNET" />
  ```

* **웹뷰의 설정 정보**

  * **WebSettings** 객체로 설정한다.
  * **getSetings()** 메소드를 사용해 참조할 수 있으며 '캐시 여부', '폰트 크기 설정', '화면 확대 여부', '스크립트 허용 여부' 등을 설정할 수 있다.
  * 자바스크립트를 허용하기 위해서는 **setJavaScriptEnabled(true)**로 설정해야 한다.
  * 웹페이지를 로딩하여 화면에 보여주기 위해서는 **loadUrl() 메소드**를 사용한다.
  * **goForward()나 goBack() 메소드**를 이용하면 앞 페이지 또는 뒤 페이지로도 이동할 수 있다.



#### 예제(앱의 화면 안에 웹뷰를 넣어보기, 에러!!)

* **/res/layout/activity_main.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <LinearLayout
      android:orientation="vertical"
      xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      xmlns:tools="http://schemas.android.com/tools"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      tools:context=".MainActivity">
  
      <LinearLayout
          android:id="@+id/layout"
          android:orientation="horizontal"
          android:layout_width="match_parent"
          android:layout_height="match_parent">
  
          <!-- 웹페이지를 열기 위한 버튼 정의 -->
          <Button
              android:id="@+id/loadButton"
              android:text=" 열기 "
              android:padding="4dp"
              android:textSize="20dp"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content" />
  
          <!-- 웹페이지 URL 입력상자 정의 -->
          <EditText
              android:id="@+id/urlInput"
              android:textSize="20dp"
              android:layout_width="match_parent"
              android:layout_height="wrap_content"/>
          
      </LinearLayout>
  
      <!-- 웹뷰 정의 -->
      <WebView
          android:id="@+id/webView"
          android:layout_width="match_parent"
          android:layout_height="match_parent">
      </WebView>
  
  </LinearLayout>
  ```

  **/java/com~/MainActivity.java**

  ```java
  package com.example.sampleweb;
  
  import android.os.Handler;
  import android.support.v7.app.AppCompatActivity;
  import android.os.Bundle;
  import android.view.View;
  import android.webkit.JavascriptInterface;
  import android.webkit.JsResult;
  import android.webkit.WebChromeClient;
  import android.webkit.WebSettings;
  import android.webkit.WebView;
  import android.widget.Button;
  import android.widget.EditText;
  
  public class MainActivity extends AppCompatActivity {
  
      private WebView webView;
      private Handler handler = new Handler();
      private Button loadButton;
      private EditText urlInput;
  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
  
          loadButton = (Button) findViewById(R.id.loadButton);
          urlInput = (EditText) findViewById(R.id.urlInput);
          webView = (WebView) findViewById(R.id.webView);     // 웹뷰 객체 참조
  
          // 웹뷰에 WebSettings 설정
          WebSettings webSettings = webView.getSettings();
          // 웹뷰에 자바스크립트가 동작할 수 있는 환경 조성
          webSettings.setJavaScriptEnabled(true);
  
          // 웹뷰에 클라이언트 객체 지정
          webView.setWebChromeClient(new WebChromeClient());
          // 웹뷰에 자바스크립트 인터페이스 객체 지정
          webView.addJavascriptInterface(new JavaScriptMethods(), "sample");
          // 웹뷰에 샘플 페이지 로딩
          webView.loadUrl("file:///android_asset/www/sample.html");
  
          loadButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  // 사용자가 직접 입력한 일반 웹페이지 로딩
                  webView.loadUrl(urlInput.getText().toString());
              }
          });
      }
  
      final class JavaScriptMethods {
          JavaScriptMethods(){}
  
          // 애플리케이션에서 정의한 메소드로 웹페이지에서 호출할 대상
          @android.webkit.JavascriptInterface
          public void clickOnFace() {
              handler.post(new Runnable() {   // 핸들러로 처리
                  @Override
                  public void run() {
                      // 애플리케이션 화면의 버튼 글자 변경
                      loadButton.setText("클릭 후 열기");
                      // 웹페이지의 자바스크립트 함수 호출
                      webView.loadUrl("javascript:changeFace()");
                  }
              });
          }
      }
  
      // 웹브라우저 클라이언트 클래스 정의
      final class WebBrowserClient extends WebChromeClient {
          public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
              result.confirm();
  
              return true;
          }
      }
  }
  ```

  **/assets/www/sample.html**

  ```html
  <html>
      <!-- 자바스크립트 함수 정의한 것으로 애플리케이션에서 호출할 대상 -->
      <script language="javaScript">
          function changeFace() {
              document.getElementById("face").src="face_angry.png";
          }
      </script>
      <!-- 웹페이지에서 보이는 그림을 눌렀을 때 clickOnFace() 메소드 호출 -->
      <body>
          <a onclick="window.sample.clickOnFace()">
              <div style="width:120px;
                   margin:0px auto;
                   padding:10dp;
                   text-align:center;
                   border:2px solid #202020;"
                   >
                  <!-- 웹페이지의 이미지 ID를 'face'로 지정 -->
                  <img id="face" src="face_normal.png"/>
                  <br>
              </div>
          </a>
      </body>
  </html>
  ```




## 04-9. 키패드 설정하기

: 예를 들어, 로그인 화면에서 [로그인] 버튼을 눌러 성공적으로 로그인되었을 때 열려있는 소프트 키패드를 닫히도록 한다. 이럴때 필요한 키패드와 관련된 기능은 **InputMethodManager 객체**를 사용해야 사용할 수 있다. 이 객체는 시스템 서비스이므로 **getSystemService()** 메소드로 참조한 후 다음과 메소드를 사용해야 한다.

```java
boolean showSoftInput(View view, int flags)
boolean hideSoftInputFromWindow(IBinder windowToken, int flags[, ResultReceiver resultReceiver])
```

 그리고 입력상자에 입력될 문자열의 종류인 **inputType** 속성을 설정한다.



#### 예제(inputType 속성으로 키패드를 다르게 띄움)

* **/res/layout/activity_main.xml**

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
              android:text="inputType: text|textCapWords"
              android:textSize="20dp"
              android:layout_width="match_parent"
              android:layout_height="wrap_content" />
  
          <EditText
              android:padding="10dp"
              android:textSize="18dp"
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              android:inputType="text|textCapWords"
              />
  
          <TextView
              android:text="inputType: number|numberSigned|numberDecimal"
              android:textSize="20dp"
              android:layout_width="match_parent"
              android:layout_height="wrap_content" />
  
          <EditText
              android:padding="10dp"
              android:textSize="18dp"
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              android:inputType="number|numberSigned|numberDecimal"
              />
  
          <TextView
              android:text="inputType: textEmailAddress"
              android:textSize="20dp"
              android:layout_width="match_parent"
              android:layout_height="wrap_content" />
  
          <EditText
              android:padding="10dp"
              android:textSize="18dp"
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              android:inputType="textEmailAddress"
              />
  
          <TextView
              android:text="inputType: textPassword"
              android:textSize="20dp"
              android:layout_width="match_parent"
              android:layout_height="wrap_content" />
  
          <EditText
              android:padding="10dp"
              android:textSize="18dp"
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              android:inputType="textPassword"
              />
  
          <TextView
              android:text="inputType: phone"
              android:textSize="20dp"
              android:layout_width="match_parent"
              android:layout_height="wrap_content" />
  
          <EditText
              android:padding="10dp"
              android:textSize="18dp"
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              android:inputType="phone"
              />
  
      </LinearLayout>
  
  </android.support.constraint.ConstraintLayout>
  ```

* **실행 결과**

  ![1551186859511](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1551186859511.png)



* **inputType 속성**

  | inputType 속성 값 | 설 명           |
  | ----------------- | --------------- |
  | number            | 숫자            |
  | numberSigned      | 0보다 큰 숫자   |
  | numberDecimal     | 정수            |
  | text              | 텍스트          |
  | textPassword      | 패스워드로 표시 |
  | textEmailAddress  | 이메일로 표시   |
  | phone             | 전화번호로 표시 |
  | time              | 시간            |
  | date              | 날짜            |




## 확인 문제

### 메인 화면

* **/res/layout/activity_main.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      xmlns:tools="http://schemas.android.com/tools"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      tools:context=".MainActivity">
  
      <Button
          android:id="@+id/signUpButton"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:layout_marginStart="8dp"
          android:layout_marginTop="200dp"
          android:layout_marginEnd="8dp"
          android:text="회원 가입"
          android:textSize="30dp"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintHorizontal_bias="0.498"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toTopOf="parent" />
  
      <Button
          android:id="@+id/webButton"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:layout_marginStart="8dp"
          android:layout_marginTop="8dp"
          android:layout_marginEnd="8dp"
          android:text="웹브라우저"
          android:textSize="30dp"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toBottomOf="@+id/signUpButton" />
  
  
  </android.support.constraint.ConstraintLayout>
  ```

  ![1551333817457](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1551333817457.png)

* **/java/com~/MainActivity.java**

  ```java
  package com.example.problem;
  
  import android.content.Intent;
  import android.support.v7.app.AppCompatActivity;
  import android.os.Bundle;
  import android.view.View;
  import android.webkit.WebView;
  import android.widget.Button;
  
  public class MainActivity extends AppCompatActivity {
      Button signUpButton;
      Button webButton;
  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
  
          signUpButton = findViewById(R.id.signUpButton);
          webButton = findViewById(R.id.webButton);
  
          // 회원가입 액티비티로 이동
          signUpButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                  startActivity(intent);
              }
          });
  
          // 웹브라우저 액티비티로 이동
          webButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(getApplicationContext(), WebActivity.class);
                  startActivity(intent);
              }
          });
      }
  }
  ```



### 고객 정보 입력 화면

* **/res/layout/activity_sign_up.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      xmlns:tools="http://schemas.android.com/tools"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      tools:context=".SignUpActivity">
  
      <TextView
          android:id="@+id/nameTextView"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:layout_marginStart="8dp"
          android:layout_marginTop="180dp"
          android:layout_marginEnd="8dp"
          android:text="이    름"
          android:textSize="25dp"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintHorizontal_bias="0.187"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toTopOf="parent" />
  
      <EditText
          android:id="@+id/nameEditText"
          android:layout_width="200dp"
          android:layout_height="wrap_content"
          android:layout_marginStart="8dp"
          android:layout_marginTop="180dp"
          android:layout_marginEnd="8dp"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintHorizontal_bias="0.491"
          app:layout_constraintStart_toEndOf="@+id/nameTextView"
          app:layout_constraintTop_toTopOf="parent" />
  
      <TextView
          android:id="@+id/ageTextView"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:layout_marginStart="8dp"
          android:layout_marginTop="12dp"
          android:layout_marginEnd="8dp"
          android:text="나    이"
          android:textSize="25dp"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintHorizontal_bias="0.187"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toBottomOf="@id/nameTextView" />
  
      <EditText
          android:id="@+id/ageEditText"
          android:layout_width="200dp"
          android:layout_height="wrap_content"
          android:layout_marginStart="8dp"
          android:layout_marginTop="8dp"
          android:layout_marginEnd="8dp"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintHorizontal_bias="0.491"
          app:layout_constraintStart_toEndOf="@+id/nameTextView"
          app:layout_constraintTop_toBottomOf="@+id/nameEditText" />
  
      <TextView
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:layout_marginStart="8dp"
          android:layout_marginTop="48dp"
          android:layout_marginEnd="8dp"
          android:text="생년월일"
          android:textSize="25dp"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintHorizontal_bias="0.198"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toBottomOf="@id/ageTextView" />
  
      <TextView
          android:id="@+id/birthTextView"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:layout_marginStart="8dp"
          android:layout_marginTop="344dp"
          android:layout_marginEnd="8dp"
          android:text=" 2019년 2월 00일 "
          android:textSize="30dp"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintHorizontal_bias="0.497"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toTopOf="parent" />
  
      <Button
          android:id="@+id/saveButton"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:layout_marginStart="8dp"
          android:layout_marginTop="396dp"
          android:layout_marginEnd="8dp"
          android:text=" 저 장 "
          android:textSize="25dp"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintHorizontal_bias="0.498"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toTopOf="parent" />
  
  </android.support.constraint.ConstraintLayout>
  ```

  ![1551333922391](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1551333922391.png)

* **dialog_birth.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <android.support.constraint.ConstraintLayout
      xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:tools="http://schemas.android.com/tools"
      android:layout_width="match_parent"
      android:layout_height="match_parent">
  
      <LinearLayout
          android:gravity="center_horizontal"
          android:orientation="vertical"
          android:layout_width="match_parent"
          android:layout_height="match_parent">
  
          <LinearLayout
              android:orientation="horizontal"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content">
  
              <EditText
                  android:id="@+id/yearEditText"
                  android:hint="2019"
                  android:textSize="25dp"
                  android:layout_width="wrap_content"
                  android:layout_height="wrap_content" />
  
              <TextView
                  android:text="년"
                  android:textSize="25dp"
                  android:layout_width="wrap_content"
                  android:layout_height="wrap_content" />
  
              <EditText
                  android:id="@+id/monthEditText"
                  android:hint="02"
                  android:textSize="25dp"
                  android:layout_width="wrap_content"
                  android:layout_height="wrap_content" />
  
              <TextView
                  android:text="월"
                  android:textSize="25dp"
                  android:layout_width="wrap_content"
                  android:layout_height="wrap_content" />
  
              <EditText
                  android:id="@+id/dayEditText"
                  android:layout_width="wrap_content"
                  android:layout_height="wrap_content"
                  android:hint="27"
                  android:textSize="25dp" />
  
              <TextView
                  android:text="일"
                  android:textSize="25dp"
                  android:layout_width="wrap_content"
                  android:layout_height="wrap_content" />
  
          </LinearLayout>
  
          <LinearLayout
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:orientation="horizontal"
              tools:layout_editor_absoluteX="0dp"
              tools:layout_editor_absoluteY="0dp">
  
              <Button
                  android:id="@+id/settingButton"
                  android:layout_width="wrap_content"
                  android:layout_height="wrap_content"
                  android:text="설정"
                  android:textSize="25dp" />
  
              <Button
                  android:id="@+id/cancelButton"
                  android:text="취소"
                  android:textSize="25dp"
                  android:layout_width="wrap_content"
                  android:layout_height="wrap_content" />
  
          </LinearLayout>
  
      </LinearLayout>
  
  
  </android.support.constraint.ConstraintLayout>
  ```

  ![1551333980408](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1551333980408.png)

* **/java/com~/SignUpActivity.java**

  ```java
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
  ```

  ![1551334037631](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1551334037631.png)



### 웹브라우저 화면

* **/res/layout/activity_web.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      xmlns:tools="http://schemas.android.com/tools"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      tools:context=".WebActivity">
  
      <LinearLayout
          android:orientation="vertical"
          android:layout_width="match_parent"
          android:layout_height="match_parent">
  
              <Button
                  android:layout_gravity="center_horizontal"
                  android:id="@+id/urlOpenCloseButton"
                  android:layout_width="wrap_content"
                  android:layout_height="wrap_content"
                  android:text="열기"
                  android:textSize="25dp" />
  
              <LinearLayout
                  android:id="@+id/urlPage"
                  android:layout_width="match_parent"
                  android:layout_height="wrap_content"
                  android:layout_marginStart="8dp"
                  android:layout_marginTop="8dp"
                  android:layout_marginEnd="8dp"
                  android:visibility="gone"
                  android:gravity="top"
                  android:orientation="horizontal">
  
                  <EditText
                      android:id="@+id/urlEditText"
                      android:layout_width="300dp"
                      android:layout_height="wrap_content"
                      android:hint="http://"
                      android:textSize="25dp" />
  
                  <Button
                      android:id="@+id/urlConnectButton"
                      android:text=" 이동 "
                      android:textSize="25dp"
                      android:layout_width="wrap_content"
                      android:layout_height="wrap_content" />
  
              </LinearLayout>
  
              <WebView
                  android:id="@+id/activity_main_webview"
                  android:fadingEdge="none"
                  android:layout_width="match_parent"
                  android:layout_height="match_parent">
              </WebView>
  
          </LinearLayout>
  
  </android.support.constraint.ConstraintLayout>
  ```

  ![1551334131076](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1551334131076.png)

* **/res/anim/translate_down.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <set xmlns:android="http://schemas.android.com/apk/res/android">
      <translate
          android:fromYDelta="-10%p"
          android:toYDelta="0%p"
          android:duration="500"
          android:repeatCount="0"
          android:fillAfter="true"
          />
  
  </set>
  ```

* **/res/anim/translate_up.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <set xmlns:android="http://schemas.android.com/apk/res/android">
      <translate
          android:fromYDelta="0%p"
          android:toYDelta="0%p"
          android:duration="500"
          android:repeatCount="0"
          android:fillAfter="true"
          />
  
  </set>
  ```

* **/java/com~/WebActivity.java**

  ```java
  package com.example.problem;
  
  import android.support.v7.app.AppCompatActivity;
  import android.os.Bundle;
  import android.view.View;
  import android.view.animation.Animation;
  import android.view.animation.AnimationUtils;
  import android.webkit.WebSettings;
  import android.webkit.WebView;
  import android.webkit.WebViewClient;
  import android.widget.Button;
  import android.widget.EditText;
  import android.widget.LinearLayout;
  
  public class WebActivity extends AppCompatActivity {
      // WebView 변수 선언과 url 을 visibility 옵션을 위한 변수 선언
      boolean isPageOpen = false;
      private WebView webView;
  
      // 애니메이션을 위한 변수 선언
      Animation translateDownAnim;
      Animation translateUpAnim;
  
      // 레이아웃과 버튼, 입력상자 변수 선언
      LinearLayout urlPage;
      Button urlButton;
      Button urlConnectButton;
      EditText urlEditText;
  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_web);
  
          // url 관련 객체들 참조
          urlPage = findViewById(R.id.urlPage);
          urlButton = findViewById(R.id.urlOpenCloseButton);
          urlConnectButton = findViewById(R.id.urlConnectButton);
          urlEditText = findViewById(R.id.urlEditText);
  
          // 애니메이션 객체 참조
          translateDownAnim = AnimationUtils.loadAnimation(this,
                  R.anim.translate_down);
          translateUpAnim = AnimationUtils.loadAnimation(this,
                  R.anim.translate_up);
  
          //
          SlidingPageAnimationListener animationListener =
                  new SlidingPageAnimationListener();
          translateUpAnim.setAnimationListener(animationListener);
          translateDownAnim.setAnimationListener(animationListener);
  
          urlButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if (isPageOpen) {
                      urlPage.startAnimation(translateUpAnim);
                      urlPage.setVisibility(View.GONE);
                  } else {
                      urlPage.setVisibility(View.VISIBLE);
                      urlPage.startAnimation(translateDownAnim);
                  }
              }
          });
  
          webView = findViewById(R.id.activity_main_webview);
  
          WebSettings webSettings = webView.getSettings();
          webSettings.setJavaScriptEnabled(true);
  
          webView.setWebViewClient(new WebViewClient(){
              @Override
              public boolean shouldOverrideUrlLoading(WebView view, String url) {
                  view.loadUrl(url);
                  return true;
              }
          });
  
          urlConnectButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  webView.loadUrl(urlEditText.getText().toString());
              }
          });
      }
  
      // 애니메이션을 감시할 리스너 클래스
      private class SlidingPageAnimationListener implements Animation.AnimationListener {
          @Override
          // 애니메이션이 시작했을 때 동작하는 메소드
          public void onAnimationStart(Animation animation) {
              if (isPageOpen) {
                  urlButton.setText("Open");
              } else {
                  urlButton.setText("Close");
              }
          }
  
          @Override
          // 애니메이션이 끝났을 때 동작하는 메소드
          public void onAnimationEnd(Animation animation) {
              if (isPageOpen) {
                  urlPage.setVisibility(View.INVISIBLE);
                  urlPage.setVisibility(View.GONE);
                  isPageOpen = false;
              } else {
                  isPageOpen = true;
              }
          }
  
          @Override
          public void onAnimationRepeat(Animation animation) {
  
          }
      }
  }
  ```

  ![1551334249269](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1551334249269.png)