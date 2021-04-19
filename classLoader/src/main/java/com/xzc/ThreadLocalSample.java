package com.xzc;

public class ThreadLocalSample {
//    ThreadLocal<Long> longLocal = new ThreadLocal<>();
//    ThreadLocal<String> stringLocal = new ThreadLocal<>();

    //重写方法
    ThreadLocal<Long> longLocal = new ThreadLocal<Long>(){
        protected Long initialValue() {
            return Thread.currentThread().getId();
        };
    };
    ThreadLocal<String> stringLocal = new ThreadLocal<String>(){;
        protected String initialValue() {
            return Thread.currentThread().getName();
        };
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
        System.out.println(test.getLong());
        System.out.println(test.getString());


        Thread thread1 = new Thread() {
            public void run() {
                test.set();
                System.out.println(test.getLong());
                System.out.println(test.getString());
            }

            ;
        };
        thread1.start();
        thread1.join();

        System.out.println(test.getLong());
        System.out.println(test.getString());
    }
}
