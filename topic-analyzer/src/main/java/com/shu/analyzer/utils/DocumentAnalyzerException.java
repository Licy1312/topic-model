package com.shu.analyzer.utils;

import lombok.Getter;
import lombok.Setter;

/**
 * Description：自定义异常类
 * User:Lichangya
 * Date:2017/3/25.
 */
@Getter
@Setter
public class DocumentAnalyzerException extends RuntimeException {

    private String code;

    private String desc;

    public DocumentAnalyzerException(String message){
        super(message);
    }

    public DocumentAnalyzerException(AnalyzerExceptionEnum exceptionEnum){
        super(exceptionEnum.getCode());
        this.code = exceptionEnum.getCode();
        this.desc = exceptionEnum.getDesc();
    }
}
