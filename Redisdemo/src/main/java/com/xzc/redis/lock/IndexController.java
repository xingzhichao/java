package com.xzc.redis.lock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/** 分布式锁 */
@RestController
public class IndexController {

  @Autowired private StringRedisTemplate stringRedisTemplate;

  @Autowired private Redisson redisson;
  /**
   * 1 setnx 保证set 与expire 的院子性
   *
   * <p>2 保证程序出异常也可以删除lock
   *
   * <p>3 保证自己的线程加的锁自己删除
   *
   * <p>TODO: 超时时间到了，业务流还在try中 执行，则还会redis锁过期，还是有优化空间 - 需要给time 续命。
   *
   * @return
   */
  @RequestMapping("/decrProduct")
  public String decrProduct() {
    String lockKey = "Product:001";
    String clientId = UUID.randomUUID().toString();
    try {
      Boolean aBoolean =
          stringRedisTemplate.opsForValue().setIfAbsent(lockKey, clientId, 5, TimeUnit.SECONDS);
      if (!aBoolean) {
        return "error";
      }
      int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
      if (stock > 0) {
        int realStock = stock - 1;
        stringRedisTemplate.opsForValue().set("stock", realStock + "");
      } else {
        System.out.println("扣减库存失败");
      }
    } finally {
      // 此处if与delete 两行也不是原子操作 ，可以使用lua脚本
      if (clientId.equals(stringRedisTemplate.opsForValue().get(lockKey))) {
        stringRedisTemplate.delete(lockKey);
      }
    }

    return "done";
  }

  /**
   * redisson底层大量使用lua脚本
   *
   * @return
   */
  @RequestMapping("/decrProduct1")
  public String decrProduct1() {
    String lockKey = "Product:001";
    RLock lock = redisson.getLock(lockKey);//读写锁，可冲入锁
    try {
      // 加锁，锁续命（看门狗） 默认30秒
      lock.lock();
      int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
      if (stock > 0) {
        int realStock = stock - 1;
        stringRedisTemplate.opsForValue().set("stock", realStock + "");
      } else {
        System.out.println("扣减库存失败");
      }
    } finally {
      lock.unlock();
    }
    return "done";
  }
}
