package com.xzc.util;

import org.springframework.util.StringUtils;

/**
 * @ClassName LgtdLttdConvert
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/3/23 11:27
 * @Version 1.0
 **/
public class LgtdLttdConvert {
    public static void reatCoor() {

        String data = "116°8′48.0000000″,40°3′45.0000000″;" + "";
        String[] splits = data.split(";");
        for (String split : splits) {
            if (StringUtils.isEmpty(split)) {
                System.out.println("");
                continue;
            }
            String[] split1 = split.split(",");
            String jingdu = split1[0];
            String weidu = split1[1];
            //经度
            String[] lgtdArr = jingdu.replace("°", ";").replace("′", ";").replace("″", ";").replace("\"", "").split(";");
            Double lgtdResult = 0D;
            for (int j = lgtdArr.length; j > 0; j--) {
                double v = Double.parseDouble(lgtdArr[j - 1]);
                if (j == 1) {
                    lgtdResult = v + lgtdResult;
                } else {
                    lgtdResult = (lgtdResult + v) / 60;
                }
            }

            //纬度
            String[] lttdArr = weidu.trim().replace("°", ";").replace("′", ";").replace("″", ";").replace("\"", "").split(";");
            Double lttdResult = 0D;
            for (int j = lttdArr.length; j > 0; j--) {
                double v = Double.parseDouble(lttdArr[j - 1]);
                if (j == 1) {
                    lttdResult = v + lttdResult;
                } else {
                    lttdResult = (lttdResult + v) / 60;
                }
            }
//            System.out.println(lgtdResult + " " + lttdResult);
            System.out.println(String.format("%-20s", lgtdResult) + " " + lttdResult);

        }
    }

    public static void main(String[] args) {
        LgtdLttdConvert.reatCoor();
    }
}
