# 고급 위젯과 프래그먼트

* 프래그먼트X

<br>

## 어댑터 뷰

배열이나 파일, 데이터베이스에 저장된 데이터를 화면에 표시할 때 유용한 뷰

<br>

## 리스트 뷰

항목들을 수직으로 보여주는 어댑터 뷰로서 상하로 스크롤이 가능

<br>

## 리스트 뷰 예제

![image-20191112141503580](../../Library/Application Support/typora-user-images/image-20191112141503580.png)

* **빈칸** : Adapter 생성

<br>

## 리스트 뷰와 arrayAdapter

![image-20191112141957538](../../Library/Application Support/typora-user-images/image-20191112141957538.png)

* **빈칸** : ListActivity 상속받았기 때문에 Listener 없이 이벤트 처리 함수 바로 사용

<br>

### Activity에서 커스텀 어댑터 클래스 활용

![image-20191112142856995](../../Library/Application Support/typora-user-images/image-20191112142856995.png)

* **빈칸**
  * 커스텀 어댑터로 설정
  * ListView 이벤트 처리

<br>

### 커스텀 어댑터 클래스 작성

![image-20191112143011599](../../Library/Application Support/typora-user-images/image-20191112143011599.png)

* **빈칸**
  * ArrayAdapter(..)
  * getView()로 항목 customization

<br>

## 그리드 뷰 예제

![image-20191112143928527](../../Library/Application Support/typora-user-images/image-20191112143928527.png)

* **빈칸**
  * 커스텀 어탭터 생성
  * 커스텀 어댑터 설정

<br>

### 커스텀 어댑터 클래스 작성

![image-20191112144020564](../../Library/Application Support/typora-user-images/image-20191112144020564.png)

* **빈칸**
  * <u>getView()로</u> 항목 customization

<br>

## 스피너 예제

![image-20191112144504420](../../Library/Application Support/typora-user-images/image-20191112144504420.png)

* <u>android prompt</u> spinner와 title로 활용

![image-20191112142051259](../../Library/Application Support/typora-user-images/image-20191112142051259.png)