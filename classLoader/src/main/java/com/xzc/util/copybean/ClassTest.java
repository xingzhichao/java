package com.xzc.util.copybean;

import lombok.Data;

import java.util.List;

/**
 * @ClassName Class
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/7/22 10:39
 * @Version 1.0
 **/
@Data
public class ClassTest {
    private People[] member;
    private People teacher;
    private List<People> student;
}
