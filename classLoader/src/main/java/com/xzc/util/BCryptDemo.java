package com.xzc.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * @ClassName BCryptDemo
 * @Description BCrypt.hashpw(password, BCrypt.gensalt ())
 * 是核心。通过调用BCrypt类的静态方法hashpw对password进行加密。第二个参数就是我们平时所说的加盐。
 * BCrypt.checkpw(candidate, hashed)
 * 该方法就是对用户后来输入的密码进行比较。如果能够匹配，返回true。
 * @Author zhichao.xing
 * @Date 2021/8/21 15:07
 * @Version 1.0
 **/
public class BCryptDemo {
    public static void main(String[] args) {
        String password = "testpassword";
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(hashed);
//        $2a$10$NLSzN91l4g2wS52Tel3JFeIQ9GV8O66.L5w6ynP6LG.Zf8zr0sVEK
//        $2a$10$sJNQQ6PXRunB3vi20kYXXOazQk.jjGl6mX/MC02NtNU4piFR9Ohu6
//        String candidate = "testpassword";
        String candidate = "wrongtestpassword";
        if (BCrypt.checkpw(candidate, hashed)) {
            System.out.println("It matches");
        } else {
            System.out.println("It does not match");
        }
    }

}
