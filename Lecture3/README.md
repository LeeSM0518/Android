# 3. 사용자 인터페이스 기초

* **setContentView()**
  * 파라미터: res/layout 안에 있는 레이아웃의 xml 코드를 받는다.
  * 기능: 액티비티에 파라미터로 받은 xml을 현재 클래스의 레이아웃으로 설정
* **findViewById()** 
  * 파라미터: 뷰의 id
  * 기능: 파라미터와 일치하는 뷰를 가져와서 저장할려는 객체에 매핑해준다.



## 뷰와 뷰그룹

* **뷰그룹** 
  * 다른 뷰들을 담는 컨테이너 기능
  * 흔히 레이아웃(layout)이라고 불린다.
  * ViewGroup 상속
* **뷰**
  * 컨트롤 또는 위젯이라고도 불린다.
  * UI를 구성하는 기초적인 빌딩 블록이다.
  * View 상속



## 마진과 패딩

* **패딩** : 뷰의 경계와 뷰의 내용물 사이의 간격
* **마진** : 자식 뷰 주위의 여백



## 레이아웃의 종류

* LinearLayout
* TableLayout
* GridLayout
* RelativeLayout
* TabLayout



## 선형 레이아웃

* orientation

  * horizontal : 수평
  * vertical : 수직

* **right 와 bottom을 같이 사용하려면?**

  ```xml
  android:gravity = "right|bottom"
  ```



## 가중치(Weight)

선형 레이아웃의 자식 뷰들의 가중치가 각각 1, 2, 3 이면 남아있는 공간의 1/6, 2/6, 3/6 을 각각 할당받는다.



## TableLayout

```xml
<TableLayout
             ...>
  
  <TableRow>
    ...
  </TableRow>
  
  <TableRow>
    ...
  </TableRow>
  
  <TableRow>
    ...
  </TableRow>
  
</TableLayout>
```

* 세 개의 행 생성



## RelativeLayout

```xml
<RelativeLayout
                ...>
  
  <TextView
            android:id="@+id/address"
            ...
            />
  
  <EditText
            android:id="@+id/input"
            ...
            android:layout_below="@+id/address"
            />
  
</RelativeLayout>
```



