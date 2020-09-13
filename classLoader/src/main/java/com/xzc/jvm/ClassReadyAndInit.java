package com.xzc.jvm;

public class ClassReadyAndInit {
    public static void main(String[] args) {
//        Singleton singleton=Singleton.getSingleton();
        System.out.println("通过单例对象访问numA："+Singleton.numA);
        System.out.println("通过单例对象访问numB："+Singleton.numB);
    }
}
class Singleton {
    public static int numA;
//    public static int numB=0;
    public static Singleton singleton=new Singleton();
    private Singleton(){
        numA++;
        numB++;
        System.out.println("numA:"+numA);
        System.out.println("numB:"+numB);
    }
    public static int numB=0;

    public static Singleton getSingleton(){
        return singleton;
    }
}
