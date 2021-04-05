package com.xzc.algri.calc;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;

/** */
@Slf4j
public class BitCalc {

  /**
   * 判断一个数 是不是2的乘方
   *
   * <p>思路一：从int temp = 1开始，每次循环比较是否与number相等，不相等就让temp增大一倍(temp = temp*2)，如此循环比较，直到相等为止。
   *
   * <p>这个方法的时间复杂度是O(LogN)
   *
   * <p>思路二：当x&x-1后的结果为0时，该数为2的乘方。否则若结果不为0，该数不是2的乘方。
   *
   * <p>时间复杂度为O(1)，不需要额外空间。 由于计算机中数的形式都为二进制，用二进制的方法来解决二进制问题往往比用十进制的数学方法要高效。
   *
   * @param number
   * @return
   */
  public static boolean isPowerOf2(Integer number) {
    return (number & (number - 1)) == 0;
  }

  /**
   * 那么，求一个正整数转成二进制后，有多少个1？
   *
   * <p>举个例子：
   *
   * <p>18：00010010
   *
   * <p>18-1： 00010001
   *
   * <p>18&18-1： 00010000=16（十进制）
   *
   * <p>与运算后的结果为16，那么：
   *
   * <p>16： 00010000
   *
   * <p>16-1： 00001111
   *
   * <p>16&16-1： 00000000
   *
   * <p>与运算后的结果为0，我们这时候可以发现，18的二进制数中有2个1，进行了两次x&x-1的与运算后为0。
   *
   * <p>也就是说，每执行一次x&x-1，实际上消除了一个二进制数中从最低位开始数的第一个的1
   *
   * @param number
   * @return
   */
  public static int counterOfOne(int number) {
    int count = 0;
    while (number > 0) {
      count++;
      // number=number & number - 1;
      number &= number - 1;
    }
    return count;
  }

  /**
   * 输入参数是一个正整数，输出该整数所对应的二进制数对应的字符串
   *
   * @param N
   * @return
   */
  public static String outBinaryFormat(int N) {
    String S = "";
    while (N > 1) {
      S = N % 2 + S;
      N = N / 2;
      log.info("{},{}", S, N);
    }
    S = N + S;
    return S;
  }

  public static void main(String[] args) {
    //    log.info("{}", isPowerOf2(99));
    //    log.info("{}", counterOfOne(7));
    //    log.info("{}", outBinaryFormat(7));

    //    for (int i = 2; i < 16; i++) {
    ////      i *= 2;
    ////      log.info("{}", i);
    ////    }

    calcBit();
  }

  /**
   * 非运算符
   *
   * <p>长整型：
   *
   * <p>长整型的二进制表示为 00000000 00000000 00000000 00000000 11111111 11111111 11111111 00000000
   *
   * <p>取反为 11111111 11111111 11111111 11111111 00000000 00000000 00000000 11111111
   * 注意计算机中保存的都是补码，首位为1意味着是负数，计算负数的原码为 补码-1然后除符号位按位取反
   *
   * <p>补码-1 11111111 11111111 11111111 11111111 00000000 00000000 00000000 11111110
   *
   * <p>除符号位按位取反 10000000 00000000 00000000 00000000 11111111 11111111 11111111 00000001
   * 即最后的结果为-(4294967040+1)=-4294967041
   *
   * <p>整形：
   *
   * <p>整形的二进制表示： 11111111 11111111 11111111 00000000
   *
   * <p>按位取反为： 00000000 00000000 00000000 11111111
   *
   * <p>这是补码，首位符号位为0 表示正数，正数的原码即为补码 因此最后结果为255
   *
   * <p>作者：TreviD 链接：https://www.jianshu.com/p/1299e92bca7c 来源：简书 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
   */
  public static void calcBit() {
    // 与运算符 - 两个操作数中位都为1，结果才为1，否则结果为0
    int a = 129; // 10000001
    int b = 128; // 10000000
    System.out.println("a 和b 与 结果是：" + (a & b));
    // 或运算符  - 两个位只要有一个为1，那么结果就是1，否则就为0
    int a1 = 129;
    int b1 = 128;
    System.out.println("a 和b 或 结果是：" + (a1 | b1));
    // 非运算符 -  如果位为0，结果是1，如果位为1，结果是0
    int a3 = 2; // 010 101 100 011(-3)
    System.out.println("a3 非的结果是：" + (~a3));
    int a31 = 10;
    System.out.println("a31 非的结果是：" + (~a31));
    // 异或运算符 - 两个操作数的位中，相同则结果为0，不同则结果为1
    int a4 = 15;
    int b4 = 2;
    System.out.println("a 与 b 异或 结果是：" + (a4 ^ b4));

    //      一元运算符
    int a5 = 10;
    int b5 = 10;
    int sum5 = a5 + ++b5;
    System.out.println("a5=" + a5 + ",b5=" + b5 + ",sum5=" + sum5); // 运行结果是： a=10,b=11,sum=21
    int a6 = 10;
    int b6 = 10;
    int sum6 = a6++ + b6;
    System.out.println("a6=" + a6 + ",b6=" + b6 + ",sum6=" + sum6); // a6=11,b6=10,sum6=20

    // 移位运算符
    System.out.println("移位运算符开始");
    System.out.println(Integer.toBinaryString(6297));
    System.out.println(Integer.toBinaryString(-6297));
    // "有符号"右移运算 符  >>
    System.out.println(Integer.toBinaryString(6297 >> 5)); // 使用符号扩展机制，也就是说，如果值为正，则在高位补0
    System.out.println(Integer.toBinaryString(-6297 >> 5)); // 如果值为负，则在高位补1.
    // "无符号"右移运算 符 >>>
    System.out.println(Integer.toBinaryString(6297 >>> 5)); // 采用0扩展机制，也就是说，无论值的正负，都在高位补0.
    System.out.println(Integer.toBinaryString(-6297 >>> 5));
    System.out.println(Integer.toBinaryString(6297 << 5)); // （在低位补0）
    System.out.println(Integer.toBinaryString(-6297 << 5));
    System.out.println("移位运算符结束");

    /** 注：x<<y 相当于 x*2的y次幂 ；x>>y相当于x/2的y次幂 从计算速度上讲，移位运算要比算术运算快。 如果x是负数，那么x>>>3没有什么算术意义，只有逻辑意义。 */

    //
    calcCommon();

    log.info("{}", decimalToBinary(10));
    log.info("{}", binaryToDecimal("1001"));
  }

  private static void calcCommon() {
    int a = 22;
    int b = 5;
    double c = 5;
    System.out.println(b + "+" + c + "=" + (b + c));
    System.out.println(b + "-" + c + "=" + (b - c));
    System.out.println(b + "*" + c + "=" + (b * c));
    System.out.println(a + "/" + b + "=" + (a / b));
    System.out.println(a + "%" + b + "=" + (a % b));
    System.out.println(a + "/" + c + "=" + (a / c));
    System.out.println(a + "%" + c + "=" + (a % c));
  }

  /**
   * @Description: 十进制转换成二进制 ()
   *
   * @param decimalSource
   * @return String
   */
  public static String decimalToBinary(int decimalSource) {
    BigInteger bi = new BigInteger(String.valueOf(decimalSource)); // 转换成BigInteger类型
    return bi.toString(2); // 参数2指定的是转化成X进制，默认10进制
  }

  /**
   * @Description: 二进制转换成十进制
   *
   * @param binarySource
   * @return int
   */
  public static int binaryToDecimal(String binarySource) {
    BigInteger bi = new BigInteger(binarySource, 2); // 转换为BigInteger类型
    return Integer.parseInt(bi.toString()); // 转换成十进制
  }
}
