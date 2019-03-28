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
            // 소켓 연결을 수신하기 위한 ServerSocket 객체 생성
            ServerSocket aServerSocket = new ServerSocket(portNumber);
            System.out.println("Listening at port " + portNumber + " ...");

            // 반목문을 이용해 클라이언트 연결 대기
            while (true) {
                // 클라이언트 연결 시 소켓 객체 참조
                // accept() 메소드를 통해 리턴되는 소켓 객체로
                // 클라이언트 소켓의 연결 정보 확인
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
