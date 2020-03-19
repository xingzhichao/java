package com.xzc.websocket.config;

import com.alibaba.fastjson.JSON;
import com.xzc.websocket.entity.SocketMsg;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @ClassName ServerEncoder
 * @Description 配置WebSocket编码器，用于发送请求的时候可以发送Object对象，实则是json数据
 * @Author zhichao.xing
 * @Date 2020/3/19 11:14
 * @Version 1.0
 **/
public class ServerEncoder implements Encoder.Text<SocketMsg> {
    @Override
    public String encode(SocketMsg object) throws EncodeException {
        return JSON.toJSONString(object);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
