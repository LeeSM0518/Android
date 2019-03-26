# 08-1. 네트워킹이란?

* **네트워킹** : 인터넷 망에 연결되어 있는 원격지의 서버 또는 원격지의 단말과 통신을 통해 데이터를 주고받는 일반적인 일들.

* **2-tier C/S(Client/Server) 방식** : 클라이언트와 서버가 일대일로 연결하는 방식

* **HTTP, FTP, POP3 프로토콜 이용 방식** : 서버로 간편하게 접속하여 처리하는 것

* **3-tier 방식** : 서버 측의 트래픽 증가 등의 문제를 해결하기 위해 만들어졌고 **부하 분산이나 관리** 등의 목적을 가진 방식. 이 방식을 사용하면 서버를 좀더 유연하게 구성할 수 있고 응용 서버와 데이터 서버로 구성하는 경우에는 **데이터베이스를 분리**시킴으로써 중간에 **비즈니스 로직(Business Logic)을** 처리하는 **응용 서버가 다양한 역할**을 할 수 있다.

* **P2P(Peer to Peer) 모델** : **단말끼리** 서버와 클라이언트 역할을 하면서 통신. 정보 검색이나 파일 송수신을 통한 정보 공유. (ex 메신저)

* **미들웨어(Middleware)** : 시스템들 간의 공통된 통신 방식이나 **지능화된 네트워크 서비스**를 제공하기 위해 만들어짐. 안드로이드에는 이러한 미들웨어 기술 중의 일부가 **프로세스 간 통신(IPC)**에 적용되어 있다.

  

# 08-2. 소켓 이용하기

* **TCP와 UDP** : IP 주소를 이용해 목적지 호스트를 찾아내고 포트를 이용해 통신 접속점을 찾아내는 **소켓 연결 방식.**
* **HTTP 프로토콜** : **비연결성(Sateless)**으로써 응답을 받고난 후 연결을 끊게 되므로 소켓 연결을 더 선호한다.
* 안드로이드에서는 소켓 연결을 시도하거나 응답을 받아 처리할 때 **스레드를** 사용해야 한다.



## 클라이언트와 안드로이드의 소켓 통신(예제)

### IntelliJ 에서 소켓 통신

* **JavaSocketClient.java**

  ```java
  package org.techtown.socket;
  
  import java.io.ObjectInputStream;
  import java.io.ObjectOutputStream;
  import java.net.Socket;
  
  public class JavaSocketClient {
  
      public static void main(String[] args) {
          try {
              int portNumber = 5001;
  
              // 소켓 연결을 위한 Socket 객체 생성
              Socket sock = new Socket("localhost", portNumber);
  
              // 데이터를 쓰기 위한 스트림 객체를 만들고 데이터 쓰기
              ObjectOutputStream outStream = new ObjectOutputStream(sock.getOutputStream());
              outStream.writeObject("Hello Android Town");
              outStream.flush();
  
              // 데이터를 읽기 위한 스트림 객체를 만들고 데이터 읽기
              ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());
              System.out.println(instream.readObject());
              sock.close();
  
          } catch (Exception ex) {
              ex.printStackTrace();
          }
      }
  }
  ```

* **JavaSocketServer.java**

  ```java
  package org.techtown.socket;
  
  import java.io.ObjectInputStream;
  import java.io.ObjectOutputStream;
  import java.net.InetAddress;
  import java.net.ServerSocket;
  import java.net.Socket;
  
  public class JavaSocketServer {
  
      public static void main(String[] args) {
          try {
              int portNumber = 5001;
  
              System.out.println("Starting Java Socket Server ...");
              // 객체 생성
              ServerSocket aServerSocket = new ServerSocket(portNumber);
              System.out.println("Listening at port " + portNumber + " ...");
  
              // 클라이언트 연결 대기
              while (true) {
                  // 소켓 객체 참조
                  Socket sock = aServerSocket.accept();
                  InetAddress clientHost = sock.getLocalAddress();
                  int clientPort = sock.getPort();
                  System.out.println("A client connected. host : " + clientHost +
                          ", port : " + clientPort);
  
                  // 데이터를 읽기 위한 스트림 객체를 만들고 데이터 읽기
                  ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());
                  Object obj = instream.readObject();
                  System.out.println("Input : " + obj);
  
                  // 데이터를 쓰기 위한 스트림 객체를 만들고 데이터 쓰기
                  ObjectOutputStream outstream = new ObjectOutputStream(sock.getOutputStream());
                  outstream.writeObject(obj + "from Server.");
                  outstream.flush();
                  sock.close();
              }
          } catch (Exception ex) {
              ex.printStackTrace();
          }
      }
  }
  ```

* **실행 결과**

  JavaSocketServer 실행 결과

  ```
  Starting Java Socket Server ...
  Listening at port 5001 ...
  A client connected. host : /127.0.0.1, port : 52953
  Input : Hello Android Town
  ```

  JavaSocketClient 실행 결과

  ```
  Hello Android Townfrom Server.
  ```

  