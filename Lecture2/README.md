

# 3. 첫번째 애플리케이션

## 애플리케이션의 구성

1. 파일 및 패키지

   ![image-20191005130749509](../../Library/Application Support/typora-user-images/image-20191005130749509.png)

   * **빈칸** : 프로젝트 뷰

2. 자바 소스

3. 컴파일시의 메시지를 표시

<br>

## 패키지 폴더의 설명

| 폴더 또는 파일 | 설명                                              |
| -------------- | ------------------------------------------------- |
| java           | 자바 소스 파일들이 들어있는 폴더이다.             |
| Gradle Scripts | 그레이들(Gradle)은 빌드 시에 필요한 스크립트이다. |
| res            | 각종 리소스들이 저장되는 폴더이다.                |
| manifest       | XML 파일로 앱의 전반적인 정보를 가지고 있다.      |



## 자동 생성된 소스 관찰

![image-20191005135004301](../../Library/Application Support/typora-user-images/image-20191005135004301.png)

* **빈칸** : MainActivity 클래스 정의



## package kr.co.company.hello;

* **패키지는** 클래스들을 보관하는 컨테이너
* 일반적으로 인터넷의 도메인 이름을 역순으로 사용

<br>

## import android.support.v7.app.AppCompatActivity

* **import** 문장은 외부에서 **패키지나 클래스를** 포함
* 앞에 **android가 붙은 패키지는** 안드로이드가 제공하는 패키지를 의미



## public class MainActivity extends <br>AppCompatActivity { ... }

* 클래스의 정의
* **Activity로부터 상속(extends) 받았으므로 액티비티가 된다.**
* **액티비티는** 안드로이드에서 애플리케이션을 구성하는 컴포넌트 중의 하나다.



## @Override

메소드가 부모 클래스의 메소드를 재정의 하였다는 것을 나타낸다.

```java
@Override
protected void onCreate(Bundle savedInstanceState){ ... }
```

<br>

## protected void <u>onCreate() { ... }</u>

* onCreate() 메소드는 액티비티가 생성되는 순간에 한번만 호출
* 모든 **초기화와 사용자 인터페이스 설정이** 여기에 들어간다.
  * setContentView() : 액티비티의 UI를 적용

<br>

## super.onCreate(Bundle savedInstanceState);

* **super** : 부모 클래스를 지칭
  * super.onCreate(SavedInstanceState); // 초기화 과정

<br>

## setContentView(R.layout.activity_main);

* **setContentView()** : 액티비티의 화면을 설정하는 메소드
* R.layout.activity_main == activity_main.xml

<br>

## 오류 수정

**[Alt+Enter]** 를 누르면 자동으로 필요한 패키지 혹은 클래스를 import에 추가

<br>

## 안드로이드 애플리케이션은 어디서 실행이 시작되는가?

* 액티비티별로 실행
* 개발자는 가장 먼저 실행되는 액티비티를 지정 **(Manifest 파일)**
* 앱 실행 => 지정된 액티비티 실행
* 액티비티 중에서는 onCreate() 메소드가 가장 먼저 실행

<br>

## 사용자 인터페이스 (User Interface)

* XML을 이용하여서 사용자 화면을 디자인
* Android 앱의 모든 사용자 인터페이스 요소는 **View 와 ViewGroup** 객체를 사용하여 구축
  * **View** : 사용자가 상호 작용할 수 있는 무언가를 화면에 그리는 객체
  * **ViewGroup** : 다른 View 객체를 보유하는 객체

<br>

## 매니페스트 파일 작성

* 애플리케이션을 구성하고 있는 컴포넌트를 기술
* 실행 시에 필요한 권한을 지정

<br>

## \<TextView> 의 속성 설명

| 속성                  | 의미                                                         |
| --------------------- | ------------------------------------------------------------ |
| xmlns:android         | XML 파일에서 최외곽 태그는 이 속성을 정의하여야 한다.<br>안드로이드 이름공간에 정의된 속성들을 참조하려고 한다는 것을 암시 |
| android:id            | 유일한 아이디를 할당한다.                                    |
| android:layout_width  | 화면에서 폭을 얼마나 차지할 것인지 정의                      |
| android:layout_height | 화면에서 길이를 얼마나 차지할 것인지 정의                    |
| android:text          | 화면에 표시하는 텍스트 설정                                  |



## XML

- **요소(element)** : 시작 태그로 시작되어 종료 태그로 끝나는 논리적인 구성 요소

  ```xml
  <Greeting>Hello, world.</Greeting>
  ```

- **속성(attribute)** : 요소의 속성으로서 "이름/값"의 쌍으로 구성

  ```xml
  <img src="madonna.jps" alt='by Raphael'/>
  ```

<br>

## 코드에서 리소스를 참조하는 방법

MainActivity.java 에서 R 객체의 layout.activity_main 을 호출하게 되면

R 객체에 있는 activity_main의 값을 매칭시켜 놓은 xml 파일을 호출해주게 된다.

* **MainActivity.class**

  ```java
  setContentView.(R.layout.activity_main);
  ```

* **R.class (자동 생성됨)**

  ```java
  public static final class layout {
    public static final int activity_main = 0x7f0300000;
  }
  ```

* **activity_main.xml**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  ...
  ```

<br>

## 코드와 리소스를 분리하는 이유

안드로이드가 탑재된 장치들이 다양해지면서 **언어나 화면 크기에 따라서, 리소스를 다르게 하는 것이 필요**

<br>

## 문자열 리소스

* 문자열 리소스은 *.xml 파일 내부 코드이다.
* **@:** 리소스 폴더에서 참조하라는 의미

<br>

## SUMMARY

* 애플리케이션은 **컴포넌트들의** 조합으로 만들어진다.
* **코드와 리소스는** 철저하게 분리된다.
* 코드와 리소스는 개발 도구에 의하여 자동으로 생성되는 **R.java** 에 의하여 서로 연결된다.

<br>

## 과제

1. **안드로이드에서 뷰(View)는 무엇인가?**

   View는 사용자가 상호 작용할 수 있는 무언가를 화면에 그리는 객체이다.

2. **애플리케이션 아이콘 파일은 어떤 폴더에 저장되는가?**

   /res/drawable 폴더

3. **안드로이드 앱은 어떤 언어로 개발되는가?**

   Java or Kotlin

4. **사용자 액티비티를 생성할 떄 상속받아야 되는 클래스의 이름 은 무엇인가?**

   AppCompatActivity

5. **XML로 작성된 레이아웃 파일은 프로젝트의 어떤 폴더에 저장 되는가?**

   /res/layout 폴더

6. **액티비티의 화면을 main.xml로 설정하는 문장을 쓰시오.**

   setContentView(R.layout.activity_main);

7. **안드로이드에서 문자열을 리소스로 표현하는 것을 권장하는 이유는 무엇인가?**

   리소스 파일에 선언해 놓으면 여러 곳에 같은 내용으로 써있는 문자열을 쉽게 변경할 수 있고 문자열 관리가 쉽다.

8. **매니페스트 파일에는 어떤 내용들이 기술되는가?**

   애플리케이션을 구성하고 있는 컴포넌트를 기술하고 실행 시에 필요한 권장을 지정한다.

9. **코드와 리소스를 서로 연결하는 파일로서 개발 도구가 자동으 로 생성되는 파일의 이름은?**

   R.java