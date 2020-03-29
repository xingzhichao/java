package com.xzc.redis.app.redPacket;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** 基于Redis实现类似微信抢红包 */
public class GrabRedPacket {

  public static void main(String[] args) {
    final String packetId = UUID.randomUUID().toString();
    // 红包金额(以分为单位，无精度损失问题)
    int total = 2000;
    // 红包数量
    int count = 7;
    // 拆红包
    int[] packets = SplitRedPacket(total, count);
    // 存红包
    SaveRedPacket(packetId, packets);

    // 抢红包
    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    for (int i = 0; i < 20; i++) {
      cachedThreadPool.execute(
          new Runnable() {
            public void run() {
              GrabRedPacket(packetId);
            }
          });
    }
  }

  /**
   * 抢红包
   *
   * @param packetId
   */
  public static void GrabRedPacket(String packetId) {
    RedisClient redisClient = RedisClient.create("redis://127.0.0.1:6379/0");
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    RedisCommands<String, String> syncCommands = connection.sync();
    String res = syncCommands.lpop("Packet:" + packetId);
    System.out.println("抢到红包：" + res);
    connection.close();
    redisClient.shutdown();
  }

  /**
   * 存红包 采用redis的list结构
   *
   * @param packetId
   * @param packets
   */
  public static void SaveRedPacket(String packetId, int[] packets) {
    RedisClient redisClient = RedisClient.create("redis://127.0.0.1:6379/0");
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    RedisCommands<String, String> syncCommands = connection.sync();
    for (int packet : packets) {
      syncCommands.lpush("Packet:" + packetId, packet + "");
    }
    connection.close();
    redisClient.shutdown();
  }
  /**
   * 拆红包 1、红包金额要被全部拆分完 2、红包金额不能差的太离谱
   *
   * @param total
   * @param count
   * @return
   */
  public static int[] SplitRedPacket(int total, int count) {
    int use = 0;
    int[] array = new int[count];
    Random random = new Random();
    for (int i = 0; i < count; i++) {
      if (i == count - 1) array[i] = total - use;
      else {
        int avg = (total - use) * 2 / (count - i); // 2 红包随机金额浮动系数
        array[i] = 1 + random.nextInt(avg - 1);
      }
      use = use + array[i];
    }
    return array;
  }
}
