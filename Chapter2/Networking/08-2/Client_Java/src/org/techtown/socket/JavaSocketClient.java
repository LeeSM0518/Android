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
