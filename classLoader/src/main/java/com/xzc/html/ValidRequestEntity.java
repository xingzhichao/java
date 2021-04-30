package com.xzc.html;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * "xyz": "X-zFaRZxNIfk7xJmA73yl9Cd",
 * "areaCodeSet": "110108",
 * "regStatusSet": "存续（在营、开业、在业）",
 * "pageNum": "1",
 * "pageSize": "20",
 * "resultTagList": "地区：北京市,企业状态：存续（在营、开业、在业）",
 * "sortType": "1"
 **/
@Getter
@Setter
@ToString
public class ValidRequestEntity implements Serializable {
    private static final long serialVersionUID = 1538127999849133467L;
    private String xyz = "X-zFaRZxNIfk7xJmA73yl9Cd";
    private String areaCodeSet = "110108";
    private String regStatusSet = "存续（在营、开业、在业）";
    private String pageNum = "1";
    private String pageSize = "20";
    private String resultTagList = "地区：北京市,企业状态：存续（在营、开业、在业）";
    private String sortType = "1";

}
