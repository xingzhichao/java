package com.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer {
    //通道管理器  
    private Selector selector;

    /**
     * 获得一个ServerSocket通道，并对该通道做一些初始化的工作
     *
     * @param port 绑定的端口号
     * @throws IOException
     */
    public void initServer(int port) throws IOException {
        // 获得一个ServerSocket通道
        // serverSocket
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        /**
         *必须配置为非阻塞才能往selector上注册，否则会报错，selector模式本身就是非阻塞模式
         * java.nio.channels.IllegalBlockingModeException
         */
        serverChannel.configureBlocking(true);
        // 将该通道对应的ServerSocket绑定到port端口  
        serverChannel.socket().bind(new InetSocketAddress(port));
        // 获得一个通道管理器  IO读写线程
        this.selector = Selector.open();
        //将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT连接事件,注册该事件后，
        //当该事件到达时, selector.select()会返回，
        //如果该事件没到达, selector.select()会一直阻塞。
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    /**
     * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
     *
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public void listen() throws IOException {
        System.out.println("服务端启动成功！");
        // 轮询访问selector  
        while (true) {
            //当注册的事件到达时，方法返回；否则,该方法会一直阻塞   // 轮询监听channel里的key
            int select = selector.select();
//            System.out.println("此次轮训返回的事件数量：" + select);
            // 获得selector中选中的项的迭代器，选中的项为注册的事件  
            Iterator ite = this.selector.selectedKeys().iterator();
            while (ite.hasNext()) {
                SelectionKey key = (SelectionKey) ite.next();
                // 删除已选的key,以防重复处理  
                ite.remove();
                // 客户端请求连接事件  
                if (key.isAcceptable()) {
                    accept(key);
                    // 获得了可读的事件  
                } else if (key.isReadable()) {
                    read(key);
                } else if (key.isWritable()) {
                    write(key);
                }
            }
        }
    }

    public void write(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        System.out.println("write事件");
        // TODO : NIO事件触发是水平触发
        // 使用Java的NIO编程的时候，在没有数据可以往外写的时候要取消写事件，
        // 在有数据往外写的时候再注册写事件
        key.interestOps(SelectionKey.OP_READ);
        //sc.close();
    }


    public void accept(SelectionKey key) throws IOException {
        // 服务器收到客户端的连接请求注册的channel
        ServerSocketChannel ssc = (ServerSocketChannel) key
                .channel();
        //获得和客户端连接的通道
        //NIO非阻塞体现：此处accept方法是阻塞的，但是这里因为是发生了连接事件，所以这个方法会马上执行完，不会阻塞
        //处理完连接请求不会继续等待客户端的数据发送
        SocketChannel channel = ssc.accept();
        // 设置成非阻塞
        channel.configureBlocking(false);

        //在这里可以给客户端发送信息哦
        String s = new String(("hello client").getBytes(), "utf-8");
        channel.write(ByteBuffer.wrap(s.getBytes()));
        //在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限。
        channel.register(this.selector, SelectionKey.OP_READ);
    }

    /**
     * 处理读取客户端发来的信息 的事件
     *
     * @param key
     * @throws IOException
     */
    public void read(SelectionKey key) throws IOException {
        // 服务器可读取消息:得到事件发生的Socket通道  
        SocketChannel channel = (SocketChannel) key.channel();
        // 创建读取的缓冲区  
        ByteBuffer buffer = ByteBuffer.allocate(100);
        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data).trim();
        System.out.println("服务端收到信息：" + msg);
//        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
//        channel.write(outBuffer);// 将消息回送给客户端
//        key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
    }

    /**
     * 启动服务端测试
     *
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        NIOServer server = new NIOServer();
        server.initServer(8000);
        server.listen();
    }

}  