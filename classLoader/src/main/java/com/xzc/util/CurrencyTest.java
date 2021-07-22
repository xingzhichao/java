package com.xzc.util;

import java.util.Currency;
import java.util.Locale;

/**
 * @ClassName CurrencyTest
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/6/30 10:37
 * @Version 1.0
 **/
public class CurrencyTest {

    public static void main(String[] args) {
        //通过Locale获取货币对象
        Currency currency = Currency.getInstance(Locale.CHINA);
        //通过货币代码获取货币对象
        Currency currency1 = Currency.getInstance("CNY");
        //通过Locale和CurrencyCode获取的相同的货币对象
        System.out.println(currency.equals(currency1));
        //获取货币代码
        System.out.println(currency.getCurrencyCode());
        //获取货币小数位数，例如（欧元为2，日元为0，人民币为2）
        System.out.println(currency.getDefaultFractionDigits());
        //获取货币的名称
        System.out.println(currency.getDisplayName());
        //使用指定国家文字获取货币的名称
        System.out.println(currency.getDisplayName(Locale.KOREA));
        //获取货币的数字代码标识
        System.out.println(currency.getNumericCode());
        //获取货币符号
        System.out.println(currency.getSymbol());
        //获取指定国家使用该货币的符号
        System.out.println(currency.getSymbol(Locale.UK));
        //获取所有可使用的货币的集合
        System.out.println(Currency.getAvailableCurrencies());
    }
}
