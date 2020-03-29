package com.xzc.redis.app.rank;

import com.alibaba.fastjson.JSON;
import com.xzc.redis.WebRedisApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * 排行榜
 * https://mp.weixin.qq.com/s/tLajYRchaOgocA-H1ozgyg
 *
 * @Author xzc
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebRedisApplication.class})// 指定启动
public class TestRankDemo {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public static final String SCORE_RANK = "score_rank";

    /**
     * 批量新增
     */
    @Test
    public void batchAdd() {
        Set<ZSetOperations.TypedTuple<String>> tuples = new HashSet<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            //把带有分数的有序集合的值和分数封装到这个类
            DefaultTypedTuple<String> tuple = new DefaultTypedTuple<>("张三" + i, 1D + i);
            tuples.add(tuple);
        }
        System.out.println("循环时间:" + (System.currentTimeMillis() - start));
        Long num = redisTemplate.opsForZSet().add(SCORE_RANK, tuples);
        Boolean flag = redisTemplate.opsForZSet().add(SCORE_RANK, "李四", 1000009D);
        //同样的value 会更新分数，但是返回值是false （代表没有影响记录数），并且以  value 为键
        //按照分数排序，value 也会排序（应该是ascii码？）
        Boolean flag4 = redisTemplate.opsForZSet().add(SCORE_RANK, "li", 1000008D);
        Boolean flag5 = redisTemplate.opsForZSet().add(SCORE_RANK, "lis", 1000008D);
        Boolean flag3 = redisTemplate.opsForZSet().add(SCORE_RANK, "lisi", 1000008D);
        Boolean flag2 = redisTemplate.opsForZSet().add(SCORE_RANK, "王五", 1000008D);
        System.out.println("批量新增时间:" + (System.currentTimeMillis() - start));
        System.out.println("受影响行数：" + num + ":" + flag3);
    }

    /**
     * 获取排行列表
     */
    @Test
    public void list() {
        // 默认只输出value
        Set<String> range = redisTemplate.opsForZSet().reverseRange(SCORE_RANK, 0, 10);
        System.out.println("获取到的排行列表:" + JSON.toJSONString(range));
        // withScores 会输出value,score
        Set<ZSetOperations.TypedTuple<String>> rangeWithScores = redisTemplate.opsForZSet().reverseRangeWithScores(SCORE_RANK, 0, 10);
        System.out.println("获取到的排行和分数列表:" + JSON.toJSONString(rangeWithScores));

        //正序 下标 从0 开始
        Set<String> range1 = redisTemplate.opsForZSet().range(SCORE_RANK, 100000, 100003);
        System.out.println("正序 下标由小到大:" + JSON.toJSONString(range1));
        Set<ZSetOperations.TypedTuple<String>> typedTuples = redisTemplate.opsForZSet().rangeWithScores(SCORE_RANK, 0, 10);
        System.out.println("正序 下标由小到大 列表:" + JSON.toJSONString(typedTuples));


        Set<ZSetOperations.TypedTuple<String>> typedTuples1 = redisTemplate.opsForZSet().rangeByScoreWithScores(SCORE_RANK, 100000, 1000008);
        System.out.println("正序 分数列表:" + JSON.toJSONString(typedTuples1));


    }

    /**
     * 获取单个的排行
     */
    @Test
    public void find() {
        //从0开始
        Long rankNum = redisTemplate.opsForZSet().reverseRank(SCORE_RANK, "李四");
        System.out.println("李四的个人排名：" + rankNum);

        Double score = redisTemplate.opsForZSet().score(SCORE_RANK, "李四");
        System.out.println("李四的分数:" + score);
    }

    /**
     * 统计两个分数之间的人数
     */
    @Test
    public void count() {
        Long count = redisTemplate.opsForZSet().count(SCORE_RANK, 8001, 9000);
        System.out.println("统计8001-9000之间的人数:" + count);
    }

    /**
     * 获取整个集合的基数(数量大小)
     */
    @Test
    public void zCard() {
        Long aLong = redisTemplate.opsForZSet().zCard(SCORE_RANK);
        System.out.println("集合的基数为：" + aLong);
    }

    /**
     * 使用加法操作分数
     */
    @Test
    public void incrementScore() {
        Double score = redisTemplate.opsForZSet().incrementScore(SCORE_RANK, "李四", 1000);
        System.out.println("李四分数+1000后：" + score);
    }

    /**
     * 通过Range对象筛选再获取 集合排行
     * Set<V> rangeByLex(K key, Range range);
     */
    @Test
    public void rangeByLex() {
        RedisZSetCommands.Range range = RedisZSetCommands.Range.range();
        range.lt("张三99006"); //小于
        range.gt("张三99000"); //大于
        Set<String> score = redisTemplate.opsForZSet().rangeByLex(SCORE_RANK, range);
        System.out.println("rangeByLex：" + score);
        RedisZSetCommands.Limit limit = RedisZSetCommands.Limit.limit();
        //限制返回个数
        limit.count(2);
        //限制从第2个开始截取
        limit.offset(2);
        Set<String> strings = redisTemplate.opsForZSet().rangeByLex(SCORE_RANK, range, limit);
        System.out.println("rangeByLex：" + strings);
    }

    @Test
    public void removeRange() {
        //下标从0开始
        Long strings = redisTemplate.opsForZSet().removeRange(SCORE_RANK, 0, 1);
        System.out.println("removeRange：" + strings);
    }
}
