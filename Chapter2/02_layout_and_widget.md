# 02. 레이아웃과 기본 위젯 사용하기

## 02-1. 대표적인 레이아웃 살펴보기

* **대표적인 레이아웃 다섯 가지**

  | 레이아웃 이름                         | 설 명                                                        |
  | ------------------------------------- | ------------------------------------------------------------ |
  | 제약 레이아웃<br />(ConstraintLayout) | 제약 조건(Constraint) 기반 모델<br />제약 조건을 사용해 화면을 구성하는 방법<br />안드로이드 스튜디오에서 자동으로 설정하는 디폴트 레이아웃 |
  | 리니어 레이아웃<br />(LinearLayout)   | 박스(Box) 모델<br />한 쪽 방향으로 차례대로 뷰를 추가하며 화면을 구성하는 방법<br />뷰가 차지할 수 있는 사각형 영역을 할당 |
  | 상대 레이아웃<br />(RelativeLayout)   | 규칙(Rule) 기반 모델<br />부모 컨테이너나 다른 뷰와의 상대적 위치로 화면을 구성하는 방법 |
  | 프레임 레이아웃<br />(FrameLayout)    | 싱글(Single) 모델<br />가장 상위에 있는 하나의 뷰 또는 뷰그룹만 보여주는 방법<br />여러 개의 뷰가 들어가면 중첩하여 쌓게 됨. 가장 단순하지만 여러 개의 뷰를 중첩한 후 각 뷰를 전환하여 보여주는 방식으로 자주 사용함 |
  | 테이블 레이아웃<br />(TableLayout)    | 격자(Grid) 모델<br />격자 모양의 배열을 사용하여 화면을 구성하는 방법<br />HTML에서 많이 사용하는 정렬 방식과 유사하지만 많이 사용하지는 않음 |

  > 리니어 레이아웃
  >
  > * 가로 방향 : **Horizontal**
  > * 세로 방향 : **Vertical**



### 뷰의 영역

![1546431978895](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1546431978895.png)

![1546432020501](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1546432020501.png)

* **뷰의 테두리(Border)**
* **테두리 바깥쪽 공간(Margin)**
* **테두리 안쪽 공간(Padding)** : 뷰의 내용물(Contents)까지의 공간

| 뷰의 margin / padding | 속성                                                         |
| --------------------- | ------------------------------------------------------------ |
| margin                | layout_margin<br />layout_marginTop<br />layout_marginBottom<br />layout_marginLeft<br />layout_marginRight |
| padding               | padding<br />paddingTop<br />paddingBottom<br />paddingLeft<br />paddingRight |



### 뷰의 배경색

* **XML 레이아웃에서 색상을 지정할 때는 # 기호를 앞에 붙인 후 ARGB(A:Alpha, R:Red, G:Green, B:Blue)의 순서대로 색상의 값을 기록한다.**

  예시)

  ```java
  16진수로 나타낸다.
  #ff0000		// 빨간색
  #00ff00		// 초록색
  #88ff00		// 반투명 빨간색
  ```

* **이미지를 배경으로 지정할 수도 있다.**

  : background 속성에 이미지 리소스의 위치를 값으로 설정하면 됩니다. 안드로이드의 미지 리소스는 /res/drawable 폴더에 들어가며 만약 house.png라는 이미지 파일이 있다면 파일 탐색기에서 프로젝트 폴더 밑의 /res/drawable 폴더를 찾는다. 그런 다음 해당 폴더에 이미지 파일을 복사한 후 XML 레이아웃 파일에 들어있는 뷰의 background 속성을 다음과 같이 설정하면 된다.

  ```java
  andorid:background="@drawable/house"
  ```





## 02-2. 리니어(Linear) 레이아웃 사용하기

### 방향 설정하기

* 한 방향으로 뷰를 쌓는 방법(속성:**orientation**)
  * 가로 : **horizontal**
  * 세로 : **vertical**