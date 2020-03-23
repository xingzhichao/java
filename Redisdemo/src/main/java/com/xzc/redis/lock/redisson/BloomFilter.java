package com.xzc.redis.lock.redisson;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** bloomFilter */
@RestController
public class BloomFilter {
  @Autowired private Redisson redisson;

  /** */
  @RequestMapping("/bloom")
  public String redlock() throws InterruptedException {
    String name = "bloom-filter";
    RBloomFilter<Object> bloomFilter = redisson.getBloomFilter(name);
    bloomFilter.tryInit(1000, 0.03);

    for (int i = 0; i < 1000; i++) {
      bloomFilter.add("瓜田李下 " + i);
    }

    System.out.println("'瓜田李下 1'是否存在：" + bloomFilter.contains("瓜田李下 " + 1));
    System.out.println("'海贼王'是否存在：" + bloomFilter.contains("海贼王"));
    System.out.println("预计插入数量：" + bloomFilter.getExpectedInsertions());
    System.out.println("容错率：" + bloomFilter.getFalseProbability());
    System.out.println("hash函数的个数：" + bloomFilter.getHashIterations());
    System.out.println("插入对象的个数：" + bloomFilter.count());
    return "success";
  }
}
