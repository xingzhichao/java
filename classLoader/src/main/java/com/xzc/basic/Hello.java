package com.xzc.basic;

/**
 * @ClassName Hello
 * @Description JDK8之前，匿名内部类访问的局部变量为什么必须要用final修饰
 * 答案： 用final修饰实际上就是为了保护数据的一致性。
 * @Author zhichao.xing
 * @Date 2021/4/19 13:59
 * @Version 1.0
 **/
public class Hello {
    /**
     * @return void
     * @Description F:\IdeaProjects\github\StudyByDay\classLoader\src\main\java\com\xzc\basic>javap -c -l Hello$1.class
     * Compiled from "Hello.java"
     * final class com.xzc.basic.Hello$1 extends java.lang.Thread {
     * final java.lang.String val$str;
     * <p>
     * com.xzc.basic.Hello$1(java.lang.String);
     * Code:
     * 0: aload_0
     * 1: aload_1
     * 2: putfield      #1                  // Field val$str:Ljava/lang/String;
     * 5: aload_0
     * 6: invokespecial #2                  // Method java/lang/Thread."<init>":()V
     * 9: return
     * LineNumberTable:
     * line 13: 0
     * LocalVariableTable:
     * Start  Length  Slot  Name   Signature
     * 0      10     0  this   Lcom/xzc/basic/Hello$1;
     * <p>
     * public void run();
     * Code:
     * 0: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
     * 3: aload_0
     * 4: getfield      #1                  // Field val$str:Ljava/lang/String;
     * 7: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     * 10: return
     * LineNumberTable:
     * line 16: 0
     * line 17: 10
     * LocalVariableTable:
     * Start  Length  Slot  Name   Signature
     * 0      11     0  this   Lcom/xzc/basic/Hello$1;
     * }
     * @Author xzc
     * @Date 14:04 2021/4/19
     **/
    public static void main(String[] args) {
        String str = "haha";
        /**
         也就是说匿名内部类之所以可以访问局部变量，
         是因为在底层将这个局部变量的值传入到了匿名内部类中，
         并且以匿名内部类的成员变量的形式存在，
         这个值的传递过程是通过匿名内部类的构造器完成的。
         **/
        new Thread() {
            @Override
            public void run() {
                System.out.println(str);
            }
        }.start();
        /**
         在JDK8中如果我们在匿名内部类中需要访问局部变量，
         那么这个局部变量不需要用final修饰符修饰。
         看似是一种编译机制的改变，
         实际上就是一个语法糖（底层还是帮你加了final）。
         **/
    }
}
