package pers.os467.selectorClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class Client {

    public static void main(String[] args) {

        SocketChannel socketChannel = null;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String id = null;
        //创建缓冲区
        ByteBuffer bf = ByteBuffer.allocate(200);
        while (true){
            try {
                if (socketChannel == null || !socketChannel.isConnected()){
                    //与对应主机端口建立tcp连接
                    socketChannel = SocketChannel.open(new InetSocketAddress("localhost",8080));
                    //设置该套接字通道非阻塞 write，read方法不阻塞
                    socketChannel.configureBlocking(false);

                    while (id == null || id.length() <= 0){
                        //客户端用户输入本次连接识别id
                        System.out.println("连接id:");
                        id = br.readLine();
                    }

                    //要向服务端发送连接信息
                    ByteBuffer buffer = Charset.forName("utf-8").encode("connect;"+id);
                    //写入通道
                    socketChannel.write(buffer);
                }

                System.out.println("消息模式:读/写(0/1):");
                String mode = br.readLine();
                if (mode.equals("0")){
                    //读取通道内数据
                    int read = socketChannel.read(bf);
                    if (read > 0){
                        //切换到读模式
                        bf.flip();
                        //读取消息
                        System.out.println("接收到消息:");
                        String s = Charset.forName("utf-8").decode(bf).toString();
                        String[] split = s.split("\n");
                        for (String s1 : split) {
                            System.out.println(s1);
                        }
                        //清除缓存区，切换写模式
                        bf.clear();
                    }
                }else {
                    String target = null,msg = null;
                    while (target == null || target.length() <= 0){
                        //用户输入要发送的信息
                        System.out.println("传输目标:");
                        target = br.readLine();
                    }

                    while (msg == null || msg.length() <= 0){
                        System.out.println("传输消息:");
                        msg = br.readLine();
                    }

                    //封装消息对象
                    Message message = new Message(id,target,msg);

                    System.out.println("Client:sending msg...");

                    //要发送的消息信息
                    ByteBuffer buffer = Charset.forName("utf-8").encode(message.toString());
                    //写入通道
                    socketChannel.write(buffer);
                }



            } catch (IOException ioException) {
                //发送连接异常尝试重新连接
                System.out.println("connection failed, retrying...");
                socketChannel = null;
            }

        }



    }

}

class Message {

    private String id;

    private String target;

    private String msg;

    public Message(String id,String target, String msg) {
        this.id = id;
        this.target = target;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "msg;" + id + ";" + target + ";" + msg;
    }
}
