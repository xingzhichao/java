package com.xzc.redis.rank;

import com.alibaba.fastjson.JSON;
import com.xzc.redis.WebRedisApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={WebRedisApplication.class})// 指定启动
public class InitData {
  @Autowired private StringRedisTemplate redisTemplate;

  public static final String SCORE_RANK = "score_rank";
  /** 批量新增 */
  @Test
  public void batchAdd() {
    Set<ZSetOperations.TypedTuple<String>> tuples = new HashSet<>();
    long start = System.currentTimeMillis();
    for (int i = 0; i < 100000; i++) {
      DefaultTypedTuple<String> tuple = new DefaultTypedTuple<>("张三" + i, 1D + i);
      tuples.add(tuple);
    }
    System.out.println("循环时间:" + (System.currentTimeMillis() - start));
    Long num = redisTemplate.opsForZSet().add(SCORE_RANK, tuples);
    System.out.println("批量新增时间:" + (System.currentTimeMillis() - start));
    System.out.println("受影响行数：" + num);
  }

  /**
   * 获取排行列表
   */
  @Test
  public void list() {

    Set<String> range = redisTemplate.opsForZSet().reverseRange(SCORE_RANK, 0, 10);
    System.out.println("获取到的排行列表:" + JSON.toJSONString(range));
    Set<ZSetOperations.TypedTuple<String>> rangeWithScores = redisTemplate.opsForZSet().reverseRangeWithScores(SCORE_RANK, 0, 10);
    System.out.println("获取到的排行和分数列表:" + JSON.toJSONString(rangeWithScores));
  }
}
