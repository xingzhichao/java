package com.xzc;

/**
 * @Description 原来是因为web项目中，
 * Tomcat这类容器都会维护一个线程池，
 * @ref org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat#maxThreads default 200
 * 也就是说web项目他的线程是可以得到复用的，
 * 问题就出在这！
 * 因为ThreadLocal是一个特殊的Map,
 * 他的key是当前线程，value是我们自己设置的值。
 * 所以我在方法中没有调用remove，导致被复用的线程又拿到了旧的值，才导致错误的发生.（也会导致内存泄漏）
 * todo 多数据源的实战
 * @Author xzc
 * @Date 12:42 2021/4/21
 * @return
 **/
public class ThreadLocalSample {
//    ThreadLocal<Long> longLocal = new ThreadLocal<>();
//    ThreadLocal<String> stringLocal = new ThreadLocal<>();

    //重写方法
    ThreadLocal<Long> longLocal = new ThreadLocal<Long>() {
        protected Long initialValue() {
            return Thread.currentThread().getId();
        }

        ;
    };
    ThreadLocal<String> stringLocal = new ThreadLocal<String>() {
        protected String initialValue() {
            return Thread.currentThread().getName();
        }

        ;
    };

    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final ThreadLocalSample test = new ThreadLocalSample();
        // 不set 会报NPE
//        test.set();
//        System.out.println(test.getLong());
//        System.out.println(test.getString());


        Thread thread1 = new Thread("threadname") {
            @Override
            public void run() {
//                test.set();
                System.out.println(test.getLong());
                System.out.println(test.getString());
            }

            ;
        };
        thread1.start();
        thread1.join();

        System.out.println("main方法：" + test.getLong());
        System.out.println("main方法：" + test.getString());
    }
}
