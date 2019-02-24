# Anroid Seminar

안드로이드 앱 프로그래밍

[![Version](https://img.shields.io/badge/version-2019.01.02-blue.svg)](https://github.com/LeeSM0518/Android/blob/master/VERSION.md)

* 안드로이드 학습 내용 및 예제 작성

<br>

## 분류

### Chapter 01. 안드로이드 첫 걸음

#### 첫 번째 앱 만들기(전화 걸기 및 메뉴 들어가기)

* [MainActivity.java](https://github.com/LeeSM0518/Android/blob/master/Chapter1/Hello/app/src/main/java/com/example/lenovo/hello/MainActivity.java)
* [MenuActivity.java](https://github.com/LeeSM0518/Android/blob/master/Chapter1/Hello/app/src/main/java/com/example/lenovo/hello/MenuActivity.java)
* [activity_main.xml](https://github.com/LeeSM0518/Android/blob/master/Chapter1/Hello/app/src/main/res/layout/activity_main.xml)
* [activity_menu.xml](https://github.com/LeeSM0518/Android/blob/master/Chapter1/Hello/app/src/main/res/layout/activity_menu.xml)

<br>

### Chapter 02. 안드로이드 완벽 가이드

#### 01. 안드로이드 스튜디오와 기본 레이아웃 익히기

* [개념 정리](https://github.com/LeeSM0518/Android/blob/master/Chapter2/layout_basic.md)

<br>

#### 02. 레이아웃과 기본 위젯 사용하기

* [개념 정리](https://github.com/LeeSM0518/Android/blob/master/Chapter2/layout_and_widget.md)
* **이미지뷰 전환, SMS 어플**
  * [이미지뷰 전환, MainActivity.java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/09_problem/Problem/app/src/main/java/com/example/lenovo/problem/MainActivity.java)
  * [이미지뷰 전환, activity_main.xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/09_problem/Problem/app/src/main/res/layout/activity_main.xml)
  * [SMS 입력 화면 및 글자수 표시 message.java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/09_problem/Problem/app/src/main/java/com/example/lenovo/problem/message.java)
  * [SMS 입력 화면 및 글자수 표시, activity_message.xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/09_problem/Problem/app/src/main/res/layout/activity_message.xml)

<br>

### 03. 애플리케이션 구성하기

* [개념 정리](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/Application_Configuration.md)

#### 03-1. 레이아웃 인플레이션 이해하기

* **예제(내부적 객체화 구현, 버튼 눌러서 화면 추가하기)**
  * [메인 화면 xml, activity_main.xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/01_SampleLayoutInflater/app/src/main/res/layout/activity_main.xml)
  * [메인 화면 java, MainActivity.java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/01_SampleLayoutInflater/app/src/main/java/com/example/samplelayoutinflater/MainActivity.java)
  * [추가 화면, sub1.xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/01_SampleLayoutInflater/app/src/main/res/layout/sub1.xml)

#### 03-2. 화면 구성과 화면 간 전환

* **예제(액티비티 간의 데이터 전송)**
  * [매니페스트, AndroidManifest.xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/02_SampleIntent/app/src/main/AndroidManifest.xml)
  * [메인 화면 xml, activity_main.xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/02_SampleIntent/app/src/main/res/layout/activity_main.xml)
  * [메인 화면 java, MainActivity.java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/02_SampleIntent/app/src/main/java/com/example/sampleintent/MainActivity.java)
  * [메뉴 화면 xml, activity_menu.xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/02_SampleIntent/app/src/main/res/layout/activity_menu.xml)
  * [메뉴 화면 java, MenuActivity.java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/02_SampleIntent/app/src/main/java/com/example/sampleintent/MenuActivity.java)

#### 03-3. 인텐트 살펴보기

* **예제(암시적 인텐트로 PDF 문서 보여주기)**
  * [메니페스트, Manifest.xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/03_Intent/SamplePDFView/app/src/main/AndroidManifest.xml)
  * [메인 화면 xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/03_Intent/SamplePDFView/app/src/main/res/layout/activity_main.xml)
  * [메인 화면 java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/03_Intent/SamplePDFView/app/src/main/java/com/example/samplepdfview/MainActivity.java)

#### 03-4. 액티비티를 위한 플래그와 부가 데이터

* **예제(Parcel 객체를 이용한 액티비티 간의 데이터 전달)**
  * [Parsel 객체, SimpleData.java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/03_Intent/SampleParcelable/app/src/main/java/com/example/sampleparcelable/SimpleData.java)
  * [메인 화면 xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/03_Intent/SampleParcelable/app/src/main/res/layout/activity_main.xml)
  * [메인 화면 java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/03_Intent/SampleParcelable/app/src/main/java/com/example/sampleparcelable/MainActivity.java)
  * [메뉴 화면 xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/03_Intent/SampleParcelable/app/src/main/res/layout/activity_menu.xml)
  * [메뉴화면 java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/03_Intent/SampleParcelable/app/src/main/java/com/example/sampleparcelable/MenuActivity.java)

#### 03-5. 액티비티의 수명주기

* **예제(액티비티의 수명주기 확인)**
  * [메인 화면 java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/03_Intent/SampleLifeCycle/app/src/main/java/com/example/samplelifecycle/MainActivity.java)
  * [메뉴 화면 java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/03_Intent/SampleLifeCycle/app/src/main/java/com/example/samplelifecycle/MenuActivity.java)
  * [메인 화면 xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/03_Intent/SampleLifeCycle/app/src/main/res/layout/activity_main.xml)
  * [메뉴 화면 xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/03_Intent/SampleLifeCycle/app/src/main/res/layout/activity_menu.xml)

#### 03-6. 서비스

* **예제(서비스로부터 전달 받은 인텐트를 액티비티에서 처리)**
  * 

<br>


## 참고 자료

* [안드로이드 SMS 전송 샘플 소스 코드](http://blog.naver.com/PostView.nhn?blogId=wjdtjdgus956&logNo=120118277255)
* [글자 수를 표시하는 SMS 입력 화면 개념](http://kr-blog.gihwan.com/29)
* [안드로이드 SMS 소스 코드](https://github.com/Studying-Android/Android-tutorial/tree/master/SMSview/app/src/main)

