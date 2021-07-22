package com.xzc.util.copybean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName People
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/7/22 10:38
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class People {
    private Integer id;
    private String name;
    private Integer age;
    private Integer sex;
}
