package org.pegcode.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExceptionEnum {

    SUCCESS("200", "执行成功"),
    ERROR("201", "未知异常"),
    BUSINESS_ERROR("202", "业务异常"),
    FAIL("999", "执行失败");


    public String code;

    public String msg;
}
