package com.xzc.java8.Invoice;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName Invoice
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/3/26 10:45
 * @Version 1.0
 **/
@Data
public class Invoice implements Serializable {
    private BigDecimal  total;
    private String unit_price;
    private Object quantity;
}
