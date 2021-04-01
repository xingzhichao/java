package com.xzc.jvm;

import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.objenesis.instantiator.util.UnsafeUtils;
import sun.misc.Unsafe;

/**
 * @ClassName SynchronizeTest
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/3/22 14:00
 * @Version 1.0
 **/
public class SynchronizeTest {
    public static void main(String[] args) {
        Object object=new Object();
        UnsafeUtils.getUnsafe().monitorEnter(object);
        UnsafeUtils.getUnsafe().monitorExit(object);

    }
}
