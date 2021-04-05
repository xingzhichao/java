package com.xzc.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * @author haixin
 * @time 2019-11-24
 */
public class NIOserver {
    public static ArrayList<SocketChannel> socketlist = new ArrayList<>();
    public static ByteBuffer byteBuffer = ByteBuffer.allocate(512);

    public static void main(String[] args) {

        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.bind(new InetSocketAddress(8080));
            serverSocketChannel.configureBlocking(false);

            while (true) {
                SocketChannel socketaccept = serverSocketChannel.accept();

                if (socketaccept != null) {
                    socketaccept.configureBlocking(false);
                    socketlist.add(socketaccept);
                }

                for (SocketChannel scl : socketlist) {
                    int read = scl.read(byteBuffer);
                    if (read > 0) {
                        byteBuffer.flip();
                        Charset charset = Charset.forName("utf-8");
                        String receiveText = charset.newDecoder().decode(byteBuffer.asReadOnlyBuffer()).toString();
                        System.out.println(Thread.currentThread().getName() + "receiveText: " + receiveText);
                        socketlist.remove(scl);
                    }

                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
