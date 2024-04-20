package pers.os467.nioServer;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerListener {

    public static void main(String[] args) {

        try {
            //打开服务端套接字通道
            ServerSocketChannel ssc = ServerSocketChannel.open();

            //监听本地端口
            ssc.bind(new InetSocketAddress(8080));

            //设置非阻塞模式，accept不阻塞
            ssc.configureBlocking(false);

            //存储的与客户端套接字处理列表，套接字哈希表
            List<SocketChannel> socketChannelList = new ArrayList<>();
            ConcurrentHashMap<String,SocketChannel> socketChannelMap = new ConcurrentHashMap();

            while (true){

                //处理连接事件
                SocketChannel socketChannel = ssc.accept();
                if (socketChannel != null){
                    System.out.println("connect success!");
                    //与客户端的套接字通道设置非阻塞
                    socketChannel.configureBlocking(false);
                    //建立连接成功，将套接字通道加入处理列表
                    socketChannelList.add(socketChannel);
                }

                //处理连接通道消息
                C: for (SocketChannel channel : socketChannelList) {

                    //创建服务端缓冲区
                    ByteBuffer buffer = ByteBuffer.allocate(100);

                    int read;
                    try {
                        //读取客户端套接字通道信息
                        read = channel.read(buffer);
                    }catch (IOException e){
                        //与客户端发生连接异常，一般为断开连接
                        read = 0;
                        //遍历套接字表，将其信息移除
                        Iterator<Map.Entry<String, SocketChannel>> iterator = socketChannelMap.entrySet().iterator();
                        while (iterator.hasNext()) {
                            Map.Entry<String, SocketChannel> next = iterator.next();
                            if (next.getValue() == channel){
                                //移除套接字表该套接字通道项
                                socketChannelMap.remove(next.getKey());
                                break;
                            }
                        }
                        //将套接字通道移出处理列表
                        Iterator<SocketChannel> iterator1 = socketChannelList.iterator();
                        while (iterator1.hasNext()) {
                            SocketChannel next = iterator1.next();
                            if (next == channel){
                                iterator1.remove();
                                System.out.println("remote client closed...");
                                //打破本次处理循环，避免因为移除元素造成的遍历异常
                                break C;
                            }
                        }
                    }

                    if (read > 0){
                        //缓冲区切换为读模式，读取套接字通道信息
                        buffer.flip();
                        CharBuffer decode = Charset.forName("utf-8").decode(buffer);

                        //解析消息，消息规定以;符号分割
                        String[] split = decode.toString().split(";");

                        if ("connect".equals(split[0])){
                            //该消息为初次连接消息，将用户标识和对应通道放入哈希表，以便服务端识别
                            System.out.println("register-user:"+split[1]);
                            socketChannelMap.put(split[1],channel);
                        }else if ("msg".equals(split[0])){
                            //读取客户端发生的消息消息，服务端为其进行转发，获取到消息中存储的目标用户消息
                            //获取到目标用户套接字通道
                            SocketChannel sc = socketChannelMap.get(split[2]);
                            System.out.println("proxy...");
                            ByteBuffer encode = Charset.forName("utf-8").encode("来自用户"+split[1] + "的消息" + ":" + split[3] + "\n");
                            if (sc == null){
                                //未找到对应用户id的套接字通道
                                System.out.println("target user is offline");
                            }else {
                                //写入目标用户套接字通道
                                sc.write(encode);
                            }

                        }

                        //切换回写模式，以便于下次读取
                        buffer.clear();
                    }

                }


            }



        } catch (IOException ioException) {
            ioException.printStackTrace();
        }


    }

}
