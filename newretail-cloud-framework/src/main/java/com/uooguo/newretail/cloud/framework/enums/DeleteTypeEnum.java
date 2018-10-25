package com.uooguo.newretail.cloud.framework.enums;

/**
 * 删除状态枚举
 *
 * jiangskui
 */
public enum DeleteTypeEnum {

    /**
     * 未删除
     */
    DELETE_FALSE(0, "未删除"),

    /**
     * 已删除
     */
    DELETE_TRUE(1, "已删除");

    private Integer code;
    private String value;

    DeleteTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

}
