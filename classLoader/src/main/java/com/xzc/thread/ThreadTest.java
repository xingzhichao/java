package com.xzc.thread;

/**
 * @ClassName ThreadTest
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/2/26 16:25
 * @Version 1.0
 **/
public class ThreadTest {
    public static void main(String[] args) {


        for(int i=0 ; i <300 ;i++){
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "正在执行任务");
                    while (true){

                    }

                }
            });
            thread.start();
        }
    }
}
