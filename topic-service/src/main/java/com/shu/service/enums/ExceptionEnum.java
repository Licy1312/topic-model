package com.shu.service.enums;

import lombok.Getter;

/**
 * Description：异常枚举说明
 * User:Lichangya
 * Date:2017/3/25.
 */
@Getter
public enum ExceptionEnum {
    DOCUMENT_NULL("DOCUMENT_NULL","不存在对应的文档"),
    DOCUMENT_TOPIC_NULL("DOCUMENT_TOPIC_NULL","文档的主题为分配");
    private String code;
    private String desc;

    ExceptionEnum(String code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public static ExceptionEnum getExceptionDesc(String code){
        for(ExceptionEnum exceptionEnum:values()){
            if(exceptionEnum.getCode().equals(code)){
                return exceptionEnum;
            }
        }
        return null;
    }
}
