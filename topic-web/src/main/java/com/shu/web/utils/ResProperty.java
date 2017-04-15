package com.shu.web.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Description:请求返回对象设置属性
 * User:wangyiteng
 * Date:2017/4/12.
 */
@Setter
@Getter
@ToString
public class ResProperty {
    private String page;
    private String column;
    private ResultDto resultDto;
    /**
     * 默认成功的构造函数
     */
    public ResProperty(){
        this.page = "0";
        this.column = "0";
    }
    public ResProperty(String page,String column){
        this.page = page;
        this.column = column;
    }
    /**
     * 返回结果
     * @param page
     * @param column
     * @return ListRes
     */
    public static ResProperty getReturn(String page,String column){
        return new ResProperty(page,column);
    }
}
