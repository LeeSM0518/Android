# Chapter 01. 기초 사항

참고 사이트: https://developer.android.com/

<br>

<img src="/Users/sangminlee/Android/capture/스크린샷 2019-09-10 오후 2.19.13.png">

<br>

## 애플리케이션의 구성 요소

- **애플리케이션은 컴포넌트로 이루어진다.**
  - 액티비티(activity)
  - 서비스(service)
  - 방송 수신자(broadcast receiver)
  - 컨텐트 제공자(content provider)

<br>

## 컴포넌트(시험)

<img src="/Users/sangminlee/Android/capture/스크린샷 2019-09-10 오후 2.25.04.png">

- **빈칸**
  - 화면있는 프로그램 (=> 액티비티)
  - 화면없는 프로그램 (=> 서비스)
  - 데이터 (=> 콘텐츠프로바이더)
  - 시스템알림 (=> 브로드캐스트리시버)
  - 화면간연결 (=> 인텐트)

<br>

## 액티비티

사용자 인터페이스 **화면을** 갖는 컴포넌트

<br>

### 액티비티의 예

**액티비티들이** 모여서 애플리케이션이 된다.

<img src="/Users/sangminlee/Android/capture/스크린샷 2019-09-10 오후 2.32.01.png">

<br>

## 서비스

* **백그라운드에서** 실행되는 컴포넌트로서 오랫동안 실행되는 작업이나 원격 프로세스를 위한 작업
* **화면이** 없음
* 예) 배경 음악을 재생하는 작업

<br>

## 방송 수신자

**Broadcast receiver** : 방송을 받고 반응하는 컴포넌트

* **방송(Broadcast)**
  * 시스템 알림
  * 화면 꺼짐
  * 배터리 잔량

<img src="../capture/스크린샷 2019-09-10 오후 2.34.07.png">

<br>

## 콘텐츠 제공자

**데이터를** 관리하고 다른 애플리케이션에서 제공하는 컴포넌트

<img src="../capture/스크린샷 2019-09-10 오후 2.38.30.png">

<br>

## 안드로이드에서 다른 컴포넌트를 사용할 수 있다.

<img src="../capture/스크린샷 2019-09-10 오후 2.41.27.png">

* **빈칸:** 다른 애플리케이션의 컴포너츠 사용가능

<br>

## 인텐트

* 애플리케이션간 컴포넌트 공유를 위한 메시지
* 액티비티(화면)간 정보 공유 및 화면전환을 위한 메시지
* 애플리케이션의 의도를 인텐트에 적어서 안드로이드에 전달하면 안드로이드가 가장 적절한 컴포넌트를 찾아 실행

* <u>Activity 간의 다리 역할</u>

<br>

## 매니페스트 파일

* 적재 목록
  * 액티비티 1, 2, ...
  * 콘텐트 제공자
  * 등. 앱에 대한 내용

<br>

## XML

* 웹 상에서 구조화된 텍스트 형식의 문서를 전송하고 수신하며 처리가 가능하도록 만든 마크업 언어
