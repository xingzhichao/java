package com.xzc.websocket.config;

import com.xzc.websocket.entity.SocketMsg;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * @ClassName ServerDecoder
 * @Description 解码器，它读入Websocket消息，然后输出java对象
 * @Author zhichao.xing
 * @Date 2020/3/19 11:18
 * @Version 1.0
 **/
public class ServerDecoder implements Decoder.Text<SocketMsg> {
    @Override
    public SocketMsg decode(String s) throws DecodeException {
        // Read message...
        return new SocketMsg();
    }

    @Override
    public boolean willDecode(String s) {
        // Determine if the message can be converted into either a
        // MessageA object or a MessageB object...
        // TODO: 2020/3/19 ???
        return false;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
