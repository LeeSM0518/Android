# 05-1. 나인패치 이미지 알아보기

* **나인패치(Nine Patch)** : 이미지가 늘어나거나 줄어들 때 생기는 이미지 왜곡을 해결하는 방법
* 이미지를 수정할 때 파일 이름을 **XXX.9.png**처럼 파일 확장자 앞에 **'.9'**를 붙여주어야 한다. 그러면 안드로이드가 원본 이미지의 가로, 세로 끝부분의 픽셀들을 모두 나인패치 이미지의 정보를 담은 것으로 인식한다.



## 예제(나인패치 이미지를 화면에 표시하기)

* **/res/layout/activity_xml**

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
  
          <Button
              android:text="Small"
              android:textColor="#ffffffff"
              android:background="@drawable/button_image_01"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content" />
  
          <Button
              android:background="@drawable/button_image_01"
              android:textColor="#ffffffff"
              android:text="MediumMediumMedium"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content" />
  
          <Button
              android:text="LongLongLongLongLongLongLongLong"
              android:textColor="#ffffffff"
              android:background="@drawable/button_image_01"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content" />
  
          <Button
              android:text="Small"
              android:background="@drawable/button_image_02"
              android:textColor="#ffffffff"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content" />
  
          <Button
              android:text="MediumMediumMedium"
              android:textColor="#ffffffff"
              android:background="@drawable/button_image_02"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content" />
  
          <Button
              android:background="@drawable/button_image_02"
              android:textColor="#ffffffff"
              android:text="LongLongLongLongLongLongLongLong"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content" />
  
      </LinearLayout>
  
  </android.support.constraint.ConstraintLayout>
  ```

* **실행 결과**

  ![1551438585459](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1551438585459.png)

  > 위의 세개 버튼은 button_image01.png 를 가져온 것이며 이미지가 왜곡되어 보이는 것을 확인할 수 있다. 하지만 밑의 세개 버튼은 **button_image02.9.png** 를 가져온 것으로 이미지가 왜곡되지 않고 그대로 유지되는 효과를 확인할 수 있다.

* **뷰의 배경으로 색상과 이미지를 지정하는 자바 코드 메소드들**

  ```java
  void setBackgroundColor (int color);
  void setBackgroundDrawable (Drawable d);
  void setBackgroundResource (int resid);
  ```



# 05-2. [비트맵] 버튼 만들기

* **[비트맵] 버튼(Bitmap Button)** : 그래픽 이미지로 구성된 버튼

  * **비트맵(Bitmap)** : 메모리 상에 만들어진 이미지

  * 비트맵 이미지를 이용할려면 버튼이 눌렸을 때와 떼어졌을 때의 이벤트를 구분하여 처리해야 한다.

  * 여러 앱에서 재사용하기 위해서는 버튼 클래스로 부터 상속받아(**뷰를 상속하여 새로운 뷰를 만든다.**) 새로운 클래스로 정의해야 한다.

  * **뷰에 관한 메소드들**

    ```java
    // 뷰가 스스로의 크기를 정할 때 호출되는 메소드
    // widthMeasureSpec과 heightMeasureSpec은 부모 컨테이너에서 이 뷰에게 허용하는
    // 여유 공간의 폭과 높이에 대한 정보이다.
    public void onMeasure (int widthMeasureSpec, int heightMeasureSpec);
    
    // 부모 컨테이너로 크기값을 리턴하고 싶을 때 사용하는 메소드
    // 이 메소드의 두 파라미터는 뷰의 폭과 높이이다.
    void setMeasuredDimension (int measuredWidth, int measuredHeight);
    
    // 스스로를 레이아웃에 맞게 그릴 때 호출되는 메소드
    public void onDraw(Canvas canvas);
    ```

* **뷰 위에 그래픽을 그리는 과정**

  ![1551439286356](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1551439286356.png)

  > 1. 뷰가 화면 상에 보일 때는 **onDraw()** 메소드 호출. 이 메소드를 재정의 하면 내가 보여주고자 하는 내용물을 버튼 위에 그릴 수 있다.
  > 2. 버튼이나 무엇을 터치 후 옮겼을 때 이미지 또는 그래픽을 다시 그려야 할 필요가 있는데 이때는 **invalidate()** 메소드 호출. 이 메소드가 호출되면 onDraw() 메소드가 다시 호출된다.



## 예제(비트맵 버튼을 직접 만들어보기)

* **/res/values/dimens.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <resources>
      <dimen name="text_size">16dp</dimen>
  </resources>
  
  ```

* **/java/com~/BitmapButton.java**

  ```java
  package com.example.samplebitmapwidget;
  
  import android.content.Context;
  import android.graphics.Color;
  import android.graphics.Typeface;
  import android.support.v7.widget.AppCompatButton;
  import android.util.AttributeSet;
  import android.view.MotionEvent;
  
  import static android.provider.ContactsContract.ProviderStatus.STATUS_NORMAL;
  
  // AppCompatButton 클래스를 상속하여 새로운 클래스 정의
  // 버튼의 기능을 그대로 유지한 채 어떤 기능을 추가하고 싶을 때
  
  public class BitmapButton extends AppCompatButton {
  
      // 아이콘 리소스 정의
      int iconNormal = R.drawable.bitmap_button_normal;
      int iconClicked = R.drawable.bitmap_button_clicked;
  
      // 아이콘 상태 정의
      int iconStatus = STATUS_NORMAL;
      public static int STATUS_NORMAL = 0;
      public static int STATUS_CLICKED = 1;
  
      // 소스 코드에서 객체를 생성했을 때 호출되는 생성자
      public BitmapButton(Context context) {
          super(context);
  
          init();
      }
  
      // XML 에 추가된 버튼이 인플레이션될 때 호출되는 생성자
      public BitmapButton(Context context, AttributeSet atts) {
          super(context, atts);
  
          init();
      }
  
      // 초기화 - 텍스트 크기는 /res/values/dimens.xml 에 정의한 값을 참조함.
      // 배경 이미지와 폰트의 크기, 색상, 글꼴을 설정한다.
      public void init() {
          // 배경 이미지 설정
          setBackgroundResource(iconNormal);
  
          int defaultTextColor = Color.WHITE;
          float defaultTextSize = getResources().getDimension(R.dimen.text_size);
          Typeface defaultTypeface = Typeface.DEFAULT_BOLD;
  
          // 글자 색상 설정
          setTextColor(defaultTextColor);
          // 글자 크기 설정
          setTextSize(defaultTextSize);
          // 글자의 폰트 설정
          setTypeface(defaultTypeface);
      }
  
      // 아이콘 리소스 설정
      public void setIcon(int iconNormal, int iconClicked) {
          this.iconNormal = iconNormal;
          this.iconClicked = iconClicked;
      }
  
      public boolean onTouchEvent(MotionEvent event) {
          super.onTouchEvent(event);
  
          int action = event.getAction();
  
          switch (action) {
  
              // 버튼을 눌렀을 때는 iconClicked 변수에 할당된 이미지 설정
              case MotionEvent.ACTION_DOWN :
                  setBackgroundResource(this.iconClicked);
  
                  iconStatus = STATUS_CLICKED;
  
                  break;
  
              case MotionEvent.ACTION_OUTSIDE :
              case MotionEvent.ACTION_CANCEL :
              case MotionEvent.ACTION_UP :
                  setBackgroundResource(this.iconNormal);
  
                  iconStatus = STATUS_NORMAL;
  
                  break;
          }
  
          invalidate();
  
          return true;
      }
  }
  ```

* **/res/layout/activity_main.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      xmlns:tools="http://schemas.android.com/tools"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      tools:context=".MainActivity">
  
      <com.example.samplebitmapwidget.BitmapButton
          android:id="@+id/button"
          android:layout_centerInParent="true"
          android:text="시작하기"
          android:layout_width="200dp"
          android:layout_height="80dp" />
  
  </RelativeLayout>
  ```

* **실행 결과**

  ![1551441302728](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1551441302728.png)



# 05-3. 리스트뷰 사용하기

* **리스트뷰** : 일반적으로 리스트 형태로 된 화면 컨트롤

* **선택 위젯(Selection Widget)** : 여러 개의 아이템 중에 하나를 선택할 수 있는 위젯들

* **선택 위젯과 어뎁터**

  : 원본 데이터를 위젯에 직접 설정하지 않고 어댑터라는 클래스를 사용. 선택할 수 있는 여러 개의 아이템이 표시도니느 선택 위젯은 어댑터(Adapter)를 통해 각각의 아이템을 화면에 디스플레이한다. 그리고 어댑터가 데이터 관리 기능을 담당한다.

  ![1551442226379](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1551442226379.png)

  > **getView() 메소드** : 이 메소드에서 리턴하는 뷰가 하나의 아이템으로 디스플레이된다.

* **하나의 아이템에 여러 정보를 담아 리스트뷰로 보여줄 때 해야 할 일들**

  | 속성                                | 설명                                                         |
  | ----------------------------------- | ------------------------------------------------------------ |
  | 아이템을 위한 XML 레이아웃 정의하기 | 리스트뷰에 들어갈 각 아이템의 레이아웃을 XML로 정의한다.     |
  | 아이템을 위한 뷰 정의하기           | 리스트뷰에 들어갈 각 아이템을 하나의 뷰로 정의한다. 이 뷰는 여러 개의 뷰를 담고 있는 뷰그룹이어야 합니다. |
  | 어댑터 정의하기                     | 데이터 관리 역할을 하는 어댑터 클래스를 만들고 그 안에 각 아이템으로 표시할 뷰를 반환하는 getView() 메소드를 정의합니다. |
  | 리스트뷰 다루기                     | 화면에 리스트뷰를 추가하고 아이템이 선택되었을 때 호출될 리스너 객체를 정의합니다. |



## 예제(아이템을 위한 XML 레이아웃 정의하기)

* **/res/layout/singer_item.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      android:orientation="horizontal">
  
      <!-- 왼쪽에 보이는 이미지 뷰 정의 -->
      <ImageView
          android:id="@+id/imageView"
          android:layout_width="80dp"
          android:layout_height="80dp"
          android:src="@drawable/singer" />
  
      <LinearLayout
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
          android:layout_marginLeft="10dp"
          android:orientation="vertical">
  
          <!-- 첫 번째 줄의 텍스트뷰 정의 -->
          <TextView
              android:id="@+id/textView"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:text="New Text"
              android:textColor="#ff5805"
              android:textSize="40dp"
              android:textStyle="bold" />
  
  
          <!-- 두 번째 줄을 표시할 상대 레이아웃 정의 -->
          <RelativeLayout
              android:layout_width="match_parent"
              android:layout_height="wrap_content">
  
              <!-- 두 번째 줄의 왼쪽에 있는 텍스트뷰 정의 -->
              <TextView
                  android:id="@+id/textView2"
                  android:layout_width="wrap_content"
                  android:layout_height="wrap_content"
                  android:layout_alignParentLeft="true"
                  android:layout_marginTop="6dp"
                  android:text="New Text"
                  android:textColor="#0223e0"
                  android:textSize="20dp" />
  
              <!-- 두 번째 줄의 오른쪽에 있는 텍스트뷰 정의 -->
              <TextView
                  android:id="@+id/textView3"
                  android:layout_width="wrap_content"
                  android:layout_height="wrap_content"
                  android:layout_alignParentRight="true"
                  android:text="New Text"
                  android:textColor="#4902d6"
                  android:textSize="20dp" />
  
          </RelativeLayout>
      </LinearLayout>
  
  </LinearLayout>
  ```

* **/java/com~/SingerItemView.java**

  ```java
  package com.example.samplelistview;
  
  import android.content.Context;
  import android.view.LayoutInflater;
  import android.widget.ImageView;
  import android.widget.LinearLayout;
  import android.widget.TextView;
  
  // SingerItemVIew 클래스는 리니어 레이아웃을 상속하므로 다른 뷰들을 포함할 수 있다.
  public class SingerItemView extends LinearLayout {
      TextView textView;
      TextView textView2;
      TextView textView3;
      ImageView imageView;
  
      // Context 객체와 SingerItemView 객체를 파라미터로 전달받는다.
      public SingerItemView(Context context) {
          super(context);
          init(context);
      }
  
      public void init(Context context) {
          // XML 레이아웃의 정보를 객체화하기 위해 LayoutInflator 객체를 참조
          LayoutInflater inflater = (LayoutInflater)
                  context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
          // 인플레이트
          inflater.inflate(R.layout.singer_item, this, true);
  
          textView = findViewById(R.id.textView);
          textView2 = findViewById(R.id.textView2);
          textView3 = findViewById(R.id.textView3);
          imageView = findViewById(R.id.imageView);
      }
  
      public void setName(String name) {
          textView.setText(name);
      }
  
      public void setMobile(String mobile) {
          textView2.setText(mobile);
      }
  
      public void setAge(int age) {
          textView3.setText(String.valueOf(age));
      }
  
      public void setImageView(int resId) {
          imageView.setImageResource(resId);
      }
  }
  ```

* **/java/com~/SingerItem.java**

  ```java
  package com.example.samplelistview;
  
  // 한 아이템에 필요한 데이터는 하나의 객체로 만들어 놓는 것이 좋으므로
  // 클래스로 정의한다.
  public class SingerItem {
  
      String name;
      String mobile;
      int age;
      int resId;
  
      public SingerItem(String name, String mobile) {
          this.name = name;
          this.mobile = mobile;
      }
  
      public SingerItem(String name, String mobile, int age, int resId) {
          this.name = name;
          this.mobile = mobile;
          this.age = age;
          this.resId = resId;
      }
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      public String getMobile() {
          return mobile;
      }
  
      public void setMobile(String mobile) {
          this.mobile = mobile;
      }
  
      public int getAge() {
          return age;
      }
  
      public void setAge(int age) {
          this.age = age;
      }
  
      public int getResId() {
          return resId;
      }
  
      public void setResId(int resId) {
          this.resId = resId;
      }
  }
  ```

* **/java/com~/MainActivity.java**

  ```java
  package com.example.samplelistview;
  
  import android.support.v7.app.AppCompatActivity;
  import android.os.Bundle;
  import android.view.View;
  import android.view.ViewGroup;
  import android.widget.AdapterView;
  import android.widget.BaseAdapter;
  import android.widget.EditText;
  import android.widget.ListView;
  import android.widget.Toast;
  
  import java.util.ArrayList;
  
  public class MainActivity extends AppCompatActivity {
  
      EditText editText;
  
      ListView listView;
      SingerAdapter adapter;
  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
  
          // 리스트뷰 객체 참조
          listView = findViewById(R.id.listView);
  
          // 어댑터 객체 참조
          adapter = new SingerAdapter();
  
          // 어댑터에 각 아이템의 데이터 추가
          adapter.addItem(new SingerItem("소녀시대", "010-1000-1000", 20, R.drawable.singer));
          adapter.addItem(new SingerItem("걸스데이", "010-2000-2000", 22, R.drawable.singer2));
          adapter.addItem(new SingerItem("여자친구", "010-3000-3000", 21, R.drawable.singer3));
          adapter.addItem(new SingerItem("티아라", "010-4000-4000", 24, R.drawable.singer4));
          adapter.addItem(new SingerItem("AOA", "010-5000-5000", 25, R.drawable.singer5));
  
          // 리스트뷰에 어댑터 객체 설정
          listView.setAdapter(adapter);
  
          // 아이템을 클릭했을 때 토스트 메시지를 보여주도록 리스너 설정
          listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  SingerItem item = (SingerItem) adapter.getItem(position);
                  Toast.makeText(getApplicationContext(), "선택 : " + item.getName(), Toast.LENGTH_LONG).show();
              }
          });
      }
  
      // BaseAdapter 를 상속하여 새로운 어댑터 클래스 정의
      class SingerAdapter extends BaseAdapter {
          // 각 아이템의 데이터를 담고 있는 SingerItem 객체를
          // 저장할 ArrayList 객체 생성
          ArrayList<SingerItem> items = new ArrayList<SingerItem>();
  
          @Override
          // 전체 아이템의 개수를 리턴하는 메소드 정의
          // (어댑터에서 관리하는 아이템의 개수를 리턴)
          public int getCount() {
              return items.size();
          }
  
          public void addItem(SingerItem item) {
              items.add(item);
          }
  
          @Override
          public Object getItem(int position) {
              return items.get(position);
          }
  
          @Override
          public long getItemId(int position) {
              return position;
          }
  
          @Override
          // 아이템에 표시할 뷰 리턴하는 메소드 정의
          // 첫 번째 파라미터 : 아이템의 인덱스, 리스트 뷰에서 아이템의 위치
          // 두 번째 파라미터 : 현재 인덱스에 해당하는 뷰 객체
          // 세 번째 파라미터 : 이 뷰를 포함하고 있는 부모 컨테이너 객체.
          public View getView(int position, View convertView, ViewGroup viewGroup) {
              SingerItemView view = new SingerItemView(getApplicationContext());
              SingerItem item = items.get(position);
              view.setName(item.getName());
              view.setMobile(item.getMobile());
              view.setAge(item.getAge());
              view.setImageView(item.getResId());
  
              return view;
          }
      }
  }
  ```

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
  
          <ListView
              android:id="@+id/listView"
              android:layout_width="match_parent"
              android:layout_height="match_parent">
  
          </ListView>
  
      </LinearLayout>
  
  </android.support.constraint.ConstraintLayout>
  ```

* **실행 결과**

  ![1551601810820](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1551601810820.png)



# 05-4. 스피너 사용하기

* **스피너(Spinner)** : 리스트뷰처럼 여러 아이템 중에서 하나를 선택하는 전형적인 위젯.



## 예제(스피너 사용하기)

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
  
          <!-- 선택된 아이템이 무엇인지 표시할 텍스트뷰 정의 -->
          <TextView
              android:id="@+id/textView"
              android:background="#0000ff"
              android:textColor="#ffffff"
              android:textSize="50dp"
              android:layout_gravity="center_horizontal"
              android:layout_height="wrap_content"
              android:layout_width="wrap_content"
              />
  
          <!-- 스피너 정의 -->
          <Spinner
              android:id="@+id/spinner"
              android:layout_width="match_parent"
              android:layout_height="wrap_content">
          </Spinner>
  
      </LinearLayout>
  
  </android.support.constraint.ConstraintLayout>
  ```

* **/java/com~/MainActivity.java**

  ```java
  package com.example.samplespinner;
  
  import android.support.v7.app.AppCompatActivity;
  import android.os.Bundle;
  import android.view.View;
  import android.widget.AdapterView;
  import android.widget.ArrayAdapter;
  import android.widget.Spinner;
  import android.widget.TextView;
  
  public class MainActivity extends AppCompatActivity {
  
      TextView textView;
  
      String[] items = { "mike", "angel", "crow", "john", "ginnie", "sally", "cohen", "rice" };
  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
  
          // 텍스트뷰 객체 참조
          textView = findViewById(R.id.textView);
  
          // 스피너 객체 참조
          Spinner spinner = findViewById(R.id.spinner);
  
          // 문자열 배열을 어댑터로 매핑
          // 첫 번째 파라미터 : Context 객체
          // 두 번째 파라미터 : 뷰를 초기화할 때 사용하는 XML
          // (android.R.layout.simple_spinner_dropdown_item : 안드로이드 API에 들어 있는 미리 정의된 레이아웃, 단순 스피너 아이템의 레이아웃)
          // 세 번째 파라미터 : 아이템으로 보일 문자열 데이터들의 배열
          ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                  this, android.R.layout.simple_spinner_dropdown_item, items);
  
          // 문자열로만 구성된 아이템들을 스피너로 보여주기 위한 메소드
          // 스피너의 각 아이템들을 보여줄 뷰에 사용되는 레이아웃을 지정
          adapter.setDropDownViewResource(
                  android.R.layout.simple_spinner_dropdown_item
          );
  
          // 스피너 객체를 어댑터의 객체로 전달
          spinner.setAdapter(adapter);
  
          // 아이템 선택 이벤트 처리
          // 스피너 객체가 아이템 선택 이벤트를 처리할 수 있도록 하는 리스너
          spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
  
              // 아이템이 선택되었을 때 호출됨
              @Override
              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                  textView.setText(items[position]);
              }
  
              // 아무것도 선택되지 않았을 때 호출됨
              @Override
              public void onNothingSelected(AdapterView<?> parent) {
                  textView.setText("");
              }
          });
      }
  }
  ```

* **실행 결과**

  ![1551606012831](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1551606012831.png)



## 예제(하나의 문자열을 하나의 아이템으로 보여주기)

* **java/com~/MainActivity.java(프로젝트 안만들었음)**

  ```java
  public class MainActivity extends ListActivity {
      ...
      // 리스트에 표시될 아이템들의 데이터를 배열로 정의
      String[] items = {
          "mike", "angel", "crow", "john", "ginnie", "sally", "cohen", "rice"
      };
      
      // ArrayAdapter를 이용해 어댑터 객체를 생성한 후 리스트에 설정
      public void onCreate(Bundle savedInstanceState) {
          ...
          setListAdapter(new ArrayAdapter<String>(
          	this,
              android.R.layout.simple_list_item_1,
              items
              )
          );
          ...
          
          // 리스트의 아이템이 선택되었을 때 처리하는 메소드 정의
          protected void onListItemClick(ListView l, View v, int position, long id) {
              super.onListItemClick(l, v, position, id);
              
              String text = " position: " + position + " " + items[position];
          }
      }
  }
  ```



# 05-5. 그리드뷰 사용하기

* **그리드뷰(GridView)** : 화면이 큰 PC 또는 웹에서 자주 사용하는 테이블(Table) 형태와 유사하게 데이터를 보여준다.
* 격자 모양으로 보여주는 그리드뷰도 **여러 아이템 중의 하나를 선택하는 특징**이 있어 **선택 위젯**으로 분류한다. 그러므로 **어댑터**를 이용해 뷰에 들어가는 데이터를 설정하고 **getView() 메소드**를 이용해 각 아이템이 표현되는 모양을 결정한다.
* **주의할 점** : 그리드뷰는 2차원적인 데이터, 즉 행과 열이 있는 데이터이기 때문에 정확하게 위치를 설정해줘야 한다.



## 예제(그리드뷰 사용)

