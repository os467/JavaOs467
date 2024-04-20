package pers.os467.demoServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Server {

    //全局ByteBuffer
    public static ByteBuffer buffer = ByteBuffer.allocate(100);

    public static void main(String[] args) throws IOException {

        //打开一个服务端套接字通道
        ServerSocketChannel ssc = ServerSocketChannel.open();

        //这一步很关键，开启非阻塞模式
        ssc.configureBlocking(false);

        //绑定服务器监听端口
        ssc.bind(new InetSocketAddress(8080));

        //建立连接集合，之后我们要处理的与客户端通信的连接都放在这里
        List<SocketChannel> channels = new ArrayList<>();

        while (true){

            //建立与客户端的连接,该方法为阻塞方法
            //如果我们之前没开启非阻塞模式，线程就会阻塞在这里
            SocketChannel socketChannel = ssc.accept();
            if (socketChannel != null){
                //运行到这里表明与客户端建立起连接了
                System.out.println("connect success!");
                //添加连接
                channels.add(socketChannel);
            }

            //线程处理接收客户端发送的数据
            for (SocketChannel channel : channels) {

                //接收数据调用read方法
                int read = channel.read(buffer);
                if (read > 0){
                    //切换到读模式，读取buffer信息
                    buffer.flip();
                    System.out.println("reading...");
                    System.out.println(Charset.defaultCharset().decode(buffer));
                    //切换回写模式，以便于下次读取
                    buffer.clear();
                }
            }
        }

    }

}