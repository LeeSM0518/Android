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



## 아이템을 위한 XML 레이아웃 정의하기(예제)