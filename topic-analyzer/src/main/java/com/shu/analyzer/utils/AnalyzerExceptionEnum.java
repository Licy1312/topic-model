package com.shu.analyzer.utils;

import lombok.Getter;

/**
 * Description：异常枚举说明
 * User:Lichangya
 * Date:2017/3/25.
 */
@Getter
public enum AnalyzerExceptionEnum {

    DOCUMENT_PARSING_ERROR("DOCUMENT_PARSING_ERROR","文档解析错误"),

    DOCUMENT_NULL_ERROR("DOCUMENT_NULL_ERROR","文档集数量为空"),

    INDEX_WRITE_ERROR("INDEX_WRITE_ERROR","文档创建索引异常"),

    INDEX_CLOSE_FLOW_ERROR("INDEX_CLOSE_FLOW_ERROR","索引写操作流关闭异常"),

    INDEX_WRITE_CLOSE_ERROR("INDEX_WRITE_CLOSE_ERROR","关闭索引写操作流异常"),

    INDEX_CONFIG_INIT_ERROR("INDEX_CONFIG_INIT_ERROR","索引配置初始化异常"),

    INDEX_SEARCH_ERROR("INDEX_SEARCH_ERROR","索引检索过程中异常"),

    ANALYZER_DOCUMENT_ERROR("ANALYZER_DOCUMENT_ERROR","文档分词异常");

    private String code;
    private String desc;

    AnalyzerExceptionEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static AnalyzerExceptionEnum getExceptionDesc(String code){
        for(AnalyzerExceptionEnum exceptionEnum:values()){
            if(exceptionEnum.getCode().equals(code)){
                return exceptionEnum;
            }
        }
        return null;
    }
}
