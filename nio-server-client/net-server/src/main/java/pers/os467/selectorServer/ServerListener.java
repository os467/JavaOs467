package pers.os467.selectorServer;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ServerListener {

    private static ConcurrentHashMap<String,SocketChannel> socketChannelMap;

    public static void main(String[] args) {
        try {
            //打开服务端套接字通道
            ServerSocketChannel ssc = ServerSocketChannel.open();
            //监听本地端口
            ssc.bind(new InetSocketAddress(8080));
            //设置非阻塞模式，accept不阻塞
            ssc.configureBlocking(false);
            Selector selector = Selector.open();
            SelectionKey selectionKey = ssc.register(selector, 0, null);
            selectionKey.interestOps(SelectionKey.OP_ACCEPT);
            socketChannelMap = new ConcurrentHashMap();
            while (true){
                //阻塞直到有事件发生
                selector.select();
                //遍获取事件集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    //处理完成该事件，移除事件
                    iterator.remove();
                    if (key.isAcceptable()){
                        //处理连接事件
                        //获取发生事件的通道
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                        //创建连接
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        //通道必须为非阻塞才能注册
                        socketChannel.configureBlocking(false);
                        SelectionKey register = socketChannel.register(selector, 0, ByteBuffer.allocate(200));
                        //关注读事件
                        register.interestOps(SelectionKey.OP_READ);
                        System.out.println("connect success");
                    }else if (key.isReadable()){
                        //处理读事件
                        SelectableChannel channel = key.channel();
                        if (channel instanceof SocketChannel){
                            SocketChannel sc = (SocketChannel) channel;
                            //拿到附带缓冲区
                            ByteBuffer buffer = (ByteBuffer) key.attachment();
                            int read = 0;
                            try {
                                 read = sc.read(buffer);
                            }catch (IOException e){
                                disconnect(key);
                            }
                            if (read == -1){
                                disconnect(key);
                            }
                            if (read > 0){
                                //处理正常消息
                                buffer.flip();
                                CharBuffer decode = Charset.forName("utf-8").decode(buffer);
                                //解析消息，消息规定以;符号分割
                                String[] split = decode.toString().split(";");
                                if ("connect".equals(split[0])){
                                    //该消息为初次连接消息，将用户标识和对应通道放入哈希表，以便服务端识别
                                    System.out.println("register-user:"+split[1]);
                                    socketChannelMap.put(split[1],sc);
                                }else if ("msg".equals(split[0])){
                                    //读取客户端发生的消息消息，服务端为其进行转发，获取到消息中存储的目标用户消息
                                    //获取到目标用户套接字通道
                                    SocketChannel targetSc = socketChannelMap.get(split[2]);
                                    System.out.println("proxy...");
                                    ByteBuffer encode = Charset.forName("utf-8").encode("来自用户"+split[1] + "的消息" + ":" + split[3] + "\n");
                                    if (targetSc == null){
                                        //未找到对应用户id的套接字通道
                                        System.out.println("target user is offline");
                                    }else {
                                        //写入目标用户套接字通道
                                        targetSc.write(encode);
                                    }
                                }
                                buffer.clear();
                            }
                        }
                    }
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static void disconnect(SelectionKey key) {
        //处理连接断开事件
        SocketChannel channel = (SocketChannel) key.channel();
        for (String s : socketChannelMap.keySet()) {
            SocketChannel socketChannel = socketChannelMap.get(s);
            if (socketChannel == channel){
                socketChannelMap.remove(s);
                break;
            }
        }
        key.cancel();
        System.out.println("client connect closed");
    }

}
