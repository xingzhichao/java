package com.xzc.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @ClassName WebSocketConf
 * @Description websocket配置类
 * @Author zhichao.xing
 * @Date 2020/3/11 17:35
 * @Version 1.0
 **/
@Configuration
public class WebSocketConf {

    /**
     * 自动注册使用了@ServerEndpoint注解声明的Websocket endpoint
     * // TODO: 2020/3/19  源码解析
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
