package com.xzc.redis.cluster;

import org.springframework.data.redis.connection.ClusterSlotHashUtil;
import redis.clients.util.JedisClusterCRC16;

public class CRC16 {
  /**
   * 在jdedis 集群与 springdataredis 中提供的算法都一样
   *
   * @param args
   */
  public static void main(String[] args) {
    String str = "name1";
    System.out.println(JedisClusterCRC16.getCRC16(str) % 16384);

    int util = ClusterSlotHashUtil.calculateSlot(str);
    System.out.println(util);
  }
}
