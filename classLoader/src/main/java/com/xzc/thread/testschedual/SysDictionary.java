package com.xzc.thread.testschedual;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典表(SysDictionary)实体类
 *
 * @author makejava
 * @since 2020-11-28 11:52:23
 */

public class SysDictionary implements Serializable {

    private static final long serialVersionUID = 970988356768306157L;


    /**
     * 字典编码
     */

    private String dictionaryValue;

    /**
     * 字典名
     */
    private String dictionaryName;

    /**
     * 创建人
     */

    private String creater;

    /**
     * 创建时间
     */

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")

    private Date createTime;

    /**
     * 更新人
     */

    private String updater;

    /**
     * 更新时间
     */

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")

    private Date updateTime;

    /**
     * 是否删除（0=未删除，正常，1=已删除）
     */

    private Integer isDeleted;

    /**
     * 备注
     */

    private String nt;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDictionaryValue() {
        return dictionaryValue;
    }

    public void setDictionaryValue(String dictionaryValue) {
        this.dictionaryValue = dictionaryValue;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getNt() {
        return nt;
    }

    public void setNt(String nt) {
        this.nt = nt;
    }

    @Override
    public String toString() {
        return "SysDictionary{" +
                "dictionaryValue='" + dictionaryValue + '\'' +
                ", dictionaryName='" + dictionaryName + '\'' +
                ", creater='" + creater + '\'' +
                ", createTime=" + createTime +
                ", updater='" + updater + '\'' +
                ", updateTime=" + updateTime +
                ", isDeleted=" + isDeleted +
                ", nt='" + nt + '\'' +
                '}';
    }
}