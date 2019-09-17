

# 3. 첫번째 애플리케이션

## 애플리케이션의 구성

## 패키지 폴더의 설명

## 자동 생성된 소스 관찰

## package kr.co.company.hello;



* **import** 문장은 외부에서 패키지나 클래스를 포함
* Activity로부터 **상속(extends)** 받았으므로 액티비티가 된다.



## @Override

메소드가 부모 클래스의 메소드를 재정의

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

## XML

* **요소(element)** : 시작 태그로 시작되어 종료 태그로 끝나는 논리적인 구성 요소

  ```xml
  <Greeting>Hello, world.</Greeting>
  ```

* **속성(attribute)** : 요소의 속성으로서 "이름/값"의 쌍으로 구성

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

* **R.class**

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

## 문자열 리소스

* 문자열 리소스은 *.xml 파일 내부 코드이다.
* **@:** 리소스 폴더에서 참조하라는 의미

<br>

## SUMMARY

* 애플리케이션은 **컴포넌트들의** 조합으로 만들어진다.
* **코드와 리소스는** 철저하게 분리된다.
* 코드와 리소스는 개발 도구에 의하여 자동으로 생성되는 **R.java** 에 의하여 서로 연결된다.