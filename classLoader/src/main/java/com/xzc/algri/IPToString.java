package com.xzc.algri;

import java.util.ArrayList;
import java.util.List;

/**
 * https://mp.weixin.qq.com/s?__biz=MzIzMzgxOTQ5NA==&mid=2247486894&idx=1&sn=bf17d404a989c3bb202241326d94647b&chksm=e8fe91a7df8918b1708f0cb759c6f9411d43ac22c257d56cc9191f74dc5eadd666862bb4bd92&scene=0&xtrack=1#rd
 *
 * @ClassName IPToString
 * @Description
 * @Author zhichao.xing
 * @Date 2020/8/28 12:46
 * @Version 1.0
 **/
public class IPToString {
    /**
     * &：全是1则为1，否则为 0
     * |：只要一个为1则为1，否则为0
     * <p>
     * 十进制转成十六进制：
     * Integer.toHexString(int i)
     * 十进制转成八进制
     * Integer.toOctalString(int i)
     * 十进制转成二进制
     * Integer.toBinaryString(int i)
     * 十六进制转成十进制
     * Integer.valueOf("FFFF",16).toString()
     * 八进制转成十进制
     * Integer.valueOf("876",8).toString()
     * 二进制转十进制
     * Integer.valueOf("0101",2).toString()
     */
    public static void main(String[] args) {
        String[] ips4Test = new String[]{"0.0.0.0", "127.0.0.1",
                "192.168.1.1", "255.0.0.255", "255.255.255.255"};
        for (String ip : ips4Test) {
//            test(ip);
        }

        long l = ipToLong("127.0.0.1");
        System.out.println(l);
        System.out.println(ip2Long("127.0.0.1"));
        String ip = getIP(l);
        System.out.println(ip);
    }

    public static void test(String ip) {
        int ipInt = ip2Int(ip);
        String ipString = int2Ip(ipInt);
        System.out.println("用于测试的ip地址: " + ip + ", int表示: " + ipInt + ", 二进制: " + Long.toBinaryString(ipInt)
                + ", 转回String: " + ipString + "，与测试 ip 地址是否相等: " + ip.equals(ipString));
    }

    /**
     * 将 ip 字符串转换为 int 类型的数字
     * <p>
     * 思路就是将 ip 的每一段数字转为 8 位二进制数，并将它们放在结果的适当位置上
     *
     * @param ipString ip字符串，如 127.0.0.1
     * @return ip字符串对应的 int 值
     */
    public static int ip2Int(String ipString) {
        // 取 ip 的各段
        String[] ipSlices = ipString.split("\\.");
        int rs = 0;
        for (int i = 0; i < ipSlices.length; i++) {
            // 将 ip 的每一段解析为 int，并根据位置左移 8 位
            int intSlice = Integer.parseInt(ipSlices[i]) << (8 * (3 - i));
//            System.out.println("<<"+intSlice);
            // 求与
            rs = rs | intSlice;
        }
        return rs;
    }

    /**
     * 将 int 转换为 ip 字符串
     *
     * @param ipInt 用 int 表示的 ip 值
     * @return ip字符串，如 127.0.0.1
     */
    public static String int2Ip(int ipInt) {
        String[] ipString = new String[4];
        for (int i = 0; i < 4; i++) {
            // 每 8 位为一段，这里取当前要处理的最高位的位置
            int pos = i * 8;
            // 取当前处理的 ip 段的值
            int and = ipInt & (255 << pos);
            // 将当前 ip 段转换为 0 ~ 255 的数字，注意这里必须使用无符号右移
            ipString[i] = String.valueOf(and >>> pos);
        }
        return String.join(".", ipString);
    }


    public static long ipToLong(String ip) {
        String[] ipArray = ip.split("\\.");
        List ipNums = new ArrayList();
        for (int i = 0; i < 4; ++i) {
            ipNums.add(Long.valueOf(Long.parseLong(ipArray[i].trim())));
        }
        long ZhongIPNumTotal = ((Long) ipNums.get(0)).longValue() * 256L * 256L * 256L
                + ((Long) ipNums.get(1)).longValue() * 256L * 256L + ((Long) ipNums.get(2)).longValue() * 256L
                + ((Long) ipNums.get(3)).longValue();

        return ZhongIPNumTotal;
    }

    /**
     * 将字符串表示的ip地址转换为long表示.
     *
     * @param ip ip地址
     * @return 以32位整数表示的ip地址
     */
    public static final long ip2Long(final String ip) {
//        if (!RegexpUtils.isExactlyMatches("(\\d{1,3}\\.){3}\\d{1,3}", ip)) {
//            throw new IllegalArgumentException("[" + ip + "]不是有效的ip地址");
//        }
        final String[] ipNums = ip.split("\\.");
        return (Long.parseLong(ipNums[0]) << 24)
                + (Long.parseLong(ipNums[1]) << 16)
                + (Long.parseLong(ipNums[2]) << 8)
                + (Long.parseLong(ipNums[3]));
    }

    public static String getIP(long ipaddr) {
        long y = ipaddr % 256;
        long m = (ipaddr - y) / (256 * 256 * 256);
        long n = (ipaddr - 256 * 256 * 256 * m - y) / (256 * 256);
        long x = (ipaddr - 256 * 256 * 256 * m - 256 * 256 * n - y) / 256;
        return m + "." + n + "." + x + "." + y;
    }
}
