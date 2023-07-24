package org.pegcode.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum GenderEnum {

    FEMALE(0, "女性"),
    MALE(1, "男性"),
    UNKNOW(99,"未知");

    private int code;
    private String info;


    public String getInfoByCode(int code){
        for (GenderEnum value : GenderEnum.values()) {
            if(value.code==code){
                return value.info;
            }
        }
        return "";
    }
}
