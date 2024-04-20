package pers.os467.demoClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        int i = 0;
        //建立连接
        SocketChannel socketChannel = null;
        while (true) {
            try {
                if (socketChannel == null || !socketChannel.isConnected()) {
                    System.out.println("connecting...");
                    socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 8080));
                }
                //发送消息
                String message = "第"+(i++)+"条消息, Hello Server!";
                ByteBuffer buffer = Charset.defaultCharset().encode(message);
                System.out.println("sending...");
                socketChannel.write(buffer);
                //保持建立通讯，每隔4秒发送一次消息
                Thread.sleep(4000);
            } catch (IOException e) {
                System.out.println("connection failed, retrying...");
                socketChannel = null;
            }
        }
    }
}