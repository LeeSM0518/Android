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
  * [SMS 입력 액티비티 및 글자수 표시 message.java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/09_problem/Problem/app/src/main/java/com/example/lenovo/problem/message.java)
  * [SMS 입력 액티비티 및 글자수 표시, activity_message.xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/09_problem/Problem/app/src/main/res/layout/activity_message.xml)

<br>

#### 03. 애플리케이션 구성하기

* [개념 정리](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/Application_Configuration.md)

##### 03-1. 레이아웃 인플레이션 이해하기

* **예제(내부적 객체화 구현, 버튼 눌러서 액티비티 추가하기)**
  * [메인 액티비티 xml, activity_main.xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/01_SampleLayoutInflater/app/src/main/res/layout/activity_main.xml)
  * [메인 액티비티 java, MainActivity.java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/01_SampleLayoutInflater/app/src/main/java/com/example/samplelayoutinflater/MainActivity.java)
  * [추가 액티비티, sub1.xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/01_SampleLayoutInflater/app/src/main/res/layout/sub1.xml)

##### 03-2. 액티비티 구성과 액티비티 간 전환

* **예제(액티비티 간의 데이터 전송)**
  * [매니페스트, AndroidManifest.xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/02_SampleIntent/app/src/main/AndroidManifest.xml)
  * [메인 액티비티 xml, activity_main.xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/02_SampleIntent/app/src/main/res/layout/activity_main.xml)
  * [메인 액티비티 java, MainActivity.java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/02_SampleIntent/app/src/main/java/com/example/sampleintent/MainActivity.java)
  * [메뉴 액티비티 xml, activity_menu.xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/02_SampleIntent/app/src/main/res/layout/activity_menu.xml)
  * [메뉴 액티비티 java, MenuActivity.java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/02_SampleIntent/app/src/main/java/com/example/sampleintent/MenuActivity.java)

##### 03-3. 인텐트 살펴보기

* **예제(암시적 인텐트로 PDF 문서 보여주기)**
  * [메니페스트, Manifest.xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/03_Intent/SamplePDFView/app/src/main/AndroidManifest.xml)
  * [메인 액티비티 xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/03_Intent/SamplePDFView/app/src/main/res/layout/activity_main.xml)
  * [메인 액티비티 java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/03_Intent/SamplePDFView/app/src/main/java/com/example/samplepdfview/MainActivity.java)

##### 03-4. 액티비티를 위한 플래그와 부가 데이터

* **예제(Parcel 객체를 이용한 액티비티 간의 데이터 전달)**
  * [Parsel 객체, SimpleData.java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/03_Intent/SampleParcelable/app/src/main/java/com/example/sampleparcelable/SimpleData.java)
  * [메인 액티비티 xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/03_Intent/SampleParcelable/app/src/main/res/layout/activity_main.xml)
  * [메인 액티비티 java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/03_Intent/SampleParcelable/app/src/main/java/com/example/sampleparcelable/MainActivity.java)
  * [메뉴 액티비티 xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/03_Intent/SampleParcelable/app/src/main/res/layout/activity_menu.xml)
  * [메뉴액티비티 java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/03_Intent/SampleParcelable/app/src/main/java/com/example/sampleparcelable/MenuActivity.java)

##### 03-5. 액티비티의 수명주기

* **예제(액티비티의 수명주기 확인)**
  * [메인 액티비티 java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/03_Intent/SampleLifeCycle/app/src/main/java/com/example/samplelifecycle/MainActivity.java)
  * [메뉴 액티비티 java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/03_Intent/SampleLifeCycle/app/src/main/java/com/example/samplelifecycle/MenuActivity.java)
  * [메인 액티비티 xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/03_Intent/SampleLifeCycle/app/src/main/res/layout/activity_main.xml)
  * [메뉴 액티비티 xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/03_Intent/SampleLifeCycle/app/src/main/res/layout/activity_menu.xml)

##### 03-6. 서비스

* **예제(서비스로부터 전달 받은 인텐트를 액티비티에서 처리)**
  * [매니페스트 xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/04_Service/SampleService/app/src/main/AndroidManifest.xml)
  * [메인 액티비티 java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/04_Service/SampleService/app/src/main/java/com/example/sampleservice/MainActivity.java)
  * [서비스 java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/04_Service/SampleService/app/src/main/java/com/example/sampleservice/MyService.java)
  * [메인 액티비티 xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/04_Service/SampleService/app/src/main/res/layout/activity_main.xml)

##### 03-7. 브로드캐스트 수신자

* **예제(브로드캐스트 수신자로 SMS 문자를 받음)**
  * [매니페스트 xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/05_Broadcast_Receiver/app/src/main/AndroidManifest.xml)
  * [브로드캐스트, SMS 수신자 java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/05_Broadcast_Receiver/app/src/main/java/com/example/samplereceiver/SmsReceiver.java)
  * [SMS 액티비티, xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/03_application_configuration/05_Broadcast_Receiver/app/src/main/java/com/example/samplereceiver/SmsActivity.java)

##### 도전 문제(영업관리 앱, 세 개 이상의 화면 전환 및 인텐트 데이터 전달)

* **코드**
  * [각 액티비티들, java](https://github.com/LeeSM0518/Android/tree/master/Chapter2/03_application_configuration/Problem/app/src/main/java/com/example/businessmanagement)
  * [각 액티비티들, xml](https://github.com/LeeSM0518/Android/tree/master/Chapter2/03_application_configuration/Problem/app/src/main/res/layout)

<br>

### 04. 다양한 위젯과 이벤트 활용하기

* [개념정리](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/widget_and_event.md)

##### 04-1. 이벤트 처리 이해하기

* **예제(터치 및 제스처 이벤트 처리하기)**
  * [메인 액티비티, java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-1/SampleEvent/app/src/main/java/com/example/sampleevent/MainActivity.java)
  * [메인 액티비티, xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-1/SampleEvent/app/src/main/res/layout/activity_main.xml)
* **예제(포커스 이벤트 처리하기)**
  * [메인 액티비티, java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-1/SampleFocus/app/src/main/java/com/example/samplefocus/MainActivity.java)
  * [메인 액티비티, xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-1/SampleFocus/app/src/main/res/layout/activity_main.xml)
  * [포커스 이벤트 처리, xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-1/SampleFocus/app/src/main/res/drawable/button_selector.xml)
* **에제(단말 방향을 전환했을 때 이벤트 처리)**
  * [메인 액티비티(자바 코드로 방향 전환), java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-1/SampleOrientation/app/src/main/java/com/example/sampleorientation/MainActivity.java)
  * [세로 방향 액티비티(자바 코드로 방향 전환), xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-1/SampleOrientation/app/src/main/res/layout/activity_main.xml)
  * [가로 방향 액티비티(자바 코드로 방향 전환), xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-1/SampleOrientation/app/src/main/res/layout-land/activity_main.xml)
  * [메인 액티비티(매니페스트로 방향 전환), java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-1/SampleOrientation/app/src/main/java/com/example/sampleorientation/Main2Activity.java)
  * [메인 액티비티(매니페스트로 방향 전환), xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-1/SampleOrientation/app/src/main/res/layout/activity_main2.xml)
  * [매니페스트 파일](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-1/SampleOrientation/app/src/main/AndroidManifest.xml)

##### 04-2. 토스트와 대화상자

* **예제(토스트 위치 바꿔 보여주기)**
  * [메인 액티비티, java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-2/SampleToast/app/src/main/java/com/example/sampletoast/MainActivity.java)
  * [메인 액티비티, xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-2/SampleToast/app/src/main/res/layout/activity_main.xml)
  * [토스트 레이아웃, xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-2/SampleToast/app/src/main/res/layout/toastborder.xml)
  * [토스트 정의 파일, xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-2/SampleToast/app/src/main/res/drawable/toast.xml)
* **예제(알림 대화상자 보여주기)**
  * [메인 액티비티, java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-2/SampleDialog/app/src/main/java/com/example/sampledialog/MainActivity.java)
  * [메인 액티비티, xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-2/SampleDialog/app/src/main/res/layout/activity_main.xml)

##### 04-3. 프로그레스바 사용하기

* **예제(프로그레스바 사용하기)**
  * [메인 액티비티, java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-3/SampleProgress/app/src/main/java/com/example/sampleprogress/MainActivity.java)
  * [메인 액티비티, xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-3/SampleProgress/app/src/main/res/layout/activity_main.xml)
  * [시크바 레이아웃, xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-3/SampleProgress/app/src/main/res/layout/seek_bar.xml)

##### 04-4. 간단한 애니메이션 사용하기

* **예제(트윈 애니메이션 구현)**
  * [메인 액티비티, java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-4/SampleButtonAnimation/app/src/main/java/com/example/samplebuttonanimation/MainActivity.java)
  * [메인 액티비티, xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-4/SampleButtonAnimation/app/src/main/res/layout/activity_main.xml)
  * [애니메이션 파일, xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-4/SampleButtonAnimation/app/src/main/res/anim/flow.xml)

##### 04-5. 페이지 슬라이딩 사용하기

* **예제(페이지 슬라이딩 구현)**
  * [메인 액티비티, java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-4/SamplePageSliding/app/src/main/java/com/example/samplepagesliding/MainActivity.java)
  * [메인 액티비티, xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-4/SamplePageSliding/app/src/main/res/layout/activity_main.xml)
  * [애니메이션 파일(페이지 왼쪽으로), xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-4/SamplePageSliding/app/src/main/res/anim/translate_left.xml)
  * [애니메이션 파일(페이지 오른쪽으로), xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-4/SamplePageSliding/app/src/main/res/anim/translate_right.xml)

##### 04-6. 프래그먼트

* **예제(프래그먼트 만들어 화면에 추가하기 및 버튼으로 프래그먼트 전환)**
  * [메인 프래그먼트, java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-6/SampleFragment/app/src/main/java/com/example/samplefragment/MainFragment.java)
  * [메인 프래그먼트, xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-6/SampleFragment/app/src/main/res/layout/fragment_main.xml)
  * [메뉴 프래그먼트, java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-6/SampleFragment/app/src/main/java/com/example/samplefragment/MenuFragment.java)
  * [메뉴 프래그먼트, xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-6/SampleFragment/app/src/main/res/layout/fragment_menu.xml)
  * [메인 액티비티, java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-6/SampleFragment/app/src/main/java/com/example/samplefragment/MenuFragment.java)
  * [메인 액티비티, xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-6/SampleFragment/app/src/main/res/layout/activity_main.xml)
* **예제(두 개의 프래그먼트로 구성된 이미지 뷰어)**
  * [리스트 프래그먼트, java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-6/SampleFragment2/app/src/main/java/com/example/samplefragment2/ListFragment.java)
  * [리스트 프래그먼트, xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-6/SampleFragment2/app/src/main/res/layout/fragment_list.xml)
  * [뷰어 프래그먼트, java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-6/SampleFragment2/app/src/main/java/com/example/samplefragment2/ViewerFragment.java)
  * [뷰어 프래그먼트, xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-6/SampleFragment2/app/src/main/res/layout/fragment_viewer.xml)
  * [메인 액티비티, java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-6/SampleFragment2/app/src/main/java/com/example/samplefragment2/MainActivity.java)
  * [메인 액티비티, xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-6/SampleFragment2/app/src/main/res/layout/activity_main.xml)

##### 04-7. 액션바와 탭 사용하기

* **예제(화면에 메뉴 기능 넣기)**
  * [메인 액티비티, java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-7/SampleOptionMenu/app/src/main/java/com/example/sampleoptionmenu/MainActivity.java)
  * [메인 액티비티, xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-7/SampleOptionMenu/app/src/main/res/layout/activity_main.xml)
  * [메뉴 레이아웃, xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-7/SampleOptionMenu/app/src/main/res/menu/menu_main.xml)
* **예제(탭으로 보여주기)**
  * [프래그먼트1, java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-7/SampleTab/app/src/main/java/com/example/sampletab/Fragment1.java)
  * [프래그먼트2, java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-7/SampleTab/app/src/main/java/com/example/sampletab/Fragment2.java)
  * [프래그먼트3, java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-7/SampleTab/app/src/main/java/com/example/sampletab/Fragment3.java)
  * [메인 액티비티, java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-7/SampleTab/app/src/main/java/com/example/sampletab/MainActivity.java)
  * [프래그먼트1, xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-7/SampleTab/app/src/main/res/layout/fragment1.xml)
  * [프래그먼트2, xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-7/SampleTab/app/src/main/res/layout/fragment2.xml)
  * [프래그먼트3, xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-7/SampleTab/app/src/main/res/layout/fragment3.xml)
  * [메인 액티비티, xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-7/SampleTab/app/src/main/res/layout/activity_main.xml)

##### 04-8. 웹브라우저 사용하기

* **예제(앱의 화면 안에 웹뷰를 넣기)**
  * [메인 액티비티, java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-8/SampleWeb/app/src/main/java/com/example/sampleweb/MainActivity.java)
  * [자바스크립트 코드, html](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-8/SampleWeb/app/src/main/assets/www/sample.html)
  * [메인 액티비티, xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-8/SampleWeb/app/src/main/res/layout/activity_main.xml)

##### 04-9. 키패드 설정하기

* **예제(inputType 속성으로 키패드를 다르게 띄움)**
  * [메인 액티비티, java](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-9/SampleKeypad/app/src/main/java/com/example/samplekeypad/MainActivity.java)
  * [메인 액티비티, xml](https://github.com/LeeSM0518/Android/blob/master/Chapter2/widget_and_event/04-9/SampleKeypad/app/src/main/res/layout/activity_main.xml)

##### 도전 문제(고객 정보 입력 화면 및 웹브라우저 화면)

* **코드**
  * [액티비티 Java files](https://github.com/LeeSM0518/Android/tree/master/Chapter2/widget_and_event/problem/app/src/main/java/com/example/problem)
  * [액티비티 xml files](https://github.com/LeeSM0518/Android/tree/master/Chapter2/widget_and_event/problem/app/src/main/res/layout)
  * [애니메이션 xml files](https://github.com/LeeSM0518/Android/tree/master/Chapter2/widget_and_event/problem/app/src/main/res/anim)




## 참고 자료

* [안드로이드 SMS 전송 샘플 소스 코드](http://blog.naver.com/PostView.nhn?blogId=wjdtjdgus956&logNo=120118277255)
* [글자 수를 표시하는 SMS 입력 액티비티 개념](http://kr-blog.gihwan.com/29)
* [안드로이드 SMS 소스 코드](https://github.com/Studying-Android/Android-tutorial/tree/master/SMSview/app/src/main)

* [안드로이드 웹뷰(WebView)를 이용한 예제 프로그램](http://zeany.net/4)