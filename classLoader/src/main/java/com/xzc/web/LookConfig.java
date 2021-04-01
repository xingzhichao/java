package com.xzc.web;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName TInspectionGroup
 * @Description 巡查配置
 * @Author zhichao.xing
 * @Date 2019/9/3 14:24
 * @Version 1.0
 **/
@ToString
@Data
public class LookConfig implements Serializable {

    private String id;

    //单位s
    private Long time;

    //巡查长度，单位m
    private BigDecimal length;

    //步长，前后端增量传输数据的最小粒度。
    private int stepLength;
}
