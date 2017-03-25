package com.shu.web.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Description:请求返回对象
 * User:Lichangya
 * Date:2017/3/25.
 */
@Setter
@Getter
@ToString
public class ResultDto<T> {

    private String code;
    private String desc;
    private T result;

    /**
     * 默认成功的构造函数
     */
    public ResultDto(){
        this.code = Contains.SUCCESS_CODE;
        this.desc = Contains.DEAL_SUCCESS;
    }


    public ResultDto(String code,String desc){
        this.code = code;
        this.desc = desc;
    }

    /**
     * 返回结果
     * @param code
     * @param desc
     * @return ResultDto
     */
    public static ResultDto getReturn(String code,String desc){
        return new ResultDto(code,desc);
    }
}
