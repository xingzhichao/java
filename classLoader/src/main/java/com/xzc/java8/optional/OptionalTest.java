package com.xzc.java8.optional;

import org.junit.Test;

import java.util.Optional;

/**
 * @ClassName OptionalTest
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/2/19 11:53
 * @Version 1.0
 **/
public class OptionalTest {
    /**
     * @return void
     * @Description Java 8 Optional 类
     * Optional着重为解决java的NPE问题是Java8提供的为了解决null安全问题的一个API。
     * 善用Optional可以使我们代码中很多繁琐、丑陋的设计变得十分优雅。
     * @Author xzc
     * @Date 11:59 2021/2/19
     **/
    public static void main(String[] args) {
        littleUse();
    }

    /**
     * @return void
     * @Description 简单实用
     * @Author xzc
     * @Date 11:59 2021/2/19
     **/
    public static void littleUse() {
        // Optional类已经成为Java 8类库的一部分，在Guava中早就有了，可能Oracle是直接拿来使用了
        // Optional用来解决空指针异常，使代码更加严谨，防止因为空指针NullPointerException对代码造成影响
        String msg = "hello";
        Optional<String> optional = Optional.of(msg);
        // 判断是否有值，不为空
        boolean present = optional.isPresent();
        // 如果有值，则返回值，如果等于空则抛异常
        String value = optional.get();
        // 如果为空，返回else指定的值
        String hi = optional.orElse("hi");
        // 如果值不为空，就执行Lambda表达式
        optional.ifPresent(opt -> System.out.println(opt));

//        还有很多不错的使用姿势，比如为空则不打印可以这么写：
        optional.ifPresent(System.out::println);
    }


    /**
     * @return void
     * @Description 使用Optional，我们就可以把下面这样的代码进行改写：
     * @Author xzc
     * @Date 11:59 2021/2/19
     **/
    public static String getName(UserOptional u) {
        if (u == null) {
            return "Unknown";
        }
        return u.getUsername();
    }

    /**
     * @return
     * @Description 不过，千万不要改写成这副样子。
     * @Author xzc
     * @Date 12:54 2021/2/19
     **/
    public static String getName2(UserOptional u) {
        Optional<UserOptional> user = Optional.ofNullable(u);
        if (!user.isPresent()) {
            return "Unknown";
        }
        return user.get().getUsername();
    }

    /**
     * @return java.lang.String
     * @Description 这样才是正确使用Optional的姿势。那么按照这种思路，我们可以安心的进行链式调用，而不是一层层判断了。
     * @Author xzc
     * @Date 12:54 2021/2/19
     **/
    public static String getName3(UserOptional u) {
        return Optional.ofNullable(u)
                .map(user -> user.getUsername())
                .orElse("Unknown");
    }

    /***
     * @Description 看一段 业务应用代码
     * @Author xzc
     * @Date 12:55 2021/2/19
     * @return java.lang.String
     **/
    public static String getChampionName(Competition comp) throws IllegalArgumentException {
        if (comp != null) {
            CompResult result = comp.getCompResult();
            if (result != null) {
                UserOptional champion = result.getUserOptional();
                if (champion != null) {
                    return champion.getUsername();
                }
            }
        }
        throw new IllegalArgumentException("The value of param comp isn't available.");
    }

    /***
     * @Description 让我们看看经过Optional加持过后，这些代码会变成什么样子。
     * @Author xzc
     * @Date 12:55 2021/2/19
     * @return java.lang.String
     **/
    public static String getChampionName2(Competition comp) throws IllegalArgumentException {
        return Optional.ofNullable(comp)
//        如果你想从Optional<T>中返回一个值怎么办？使用 map()
                .map(c -> c.getCompResult())
                .map(r -> r.getUserOptional())
                .map(u -> u.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("The value of param comp isn't available."));
    }

    @Test
    public void test() {
        Competition competition = new Competition();
//        getChampionName(competition);

        competition = null;
//        getChampionName2(competition);


        // 判断null 传入 会如何  ==>   false
        competition = null;
        boolean competition1 = Optional.ofNullable(competition).isPresent();
        System.out.println(competition1);
    }

    @Test
    public void test1() {
        Competition comp = new Competition();
        CompResult compResult = new CompResult();
        UserOptional userOptional = UserOptional.of("xing", 18);
        compResult.setUserOptional(userOptional);
        comp.setCompResult(compResult);
        CompResult compResult1 = Optional.ofNullable(comp)
                .map(c -> c.getCompResult()).get();
        System.out.println(compResult1);
    }
}
