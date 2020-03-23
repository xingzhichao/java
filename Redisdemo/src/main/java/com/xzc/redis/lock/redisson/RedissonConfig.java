package com.xzc.redis.lock.redisson;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {
  @Bean
  public Redisson redisson() {
    Config config = new Config();
    // 单机
    config.useSingleServer().setAddress("localhost:6379").setDatabase(0);
    //      config.useMasterSlaveServers();// 主从
    //      config.useSentinelServers();//哨兵
    /*config.useClusterServers()//集群
    .addNodeAddress("redis://192.168.0.61:8001")
    .addNodeAddress("redis://192.168.0.62:8002")
    .addNodeAddress("redis://192.168.0.63:8003")
    .addNodeAddress("redis://192.168.0.61:8004")
    .addNodeAddress("redis://192.168.0.62:8005")
    .addNodeAddress("redis://192.168.0.63:8006");*/
    Redisson redisson = (Redisson) Redisson.create(config);
    return redisson;
  }
}
