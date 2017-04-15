package com.shu.web.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Description:请求返回对象
 * User:wangyiteng
 * Date:2017/4/12.
 */
@Setter
@Getter
@ToString
public class ListRes {
    private List<ResultDto> resultDtos;
    private int count ;
    /**
     * 默认成功的构造函数
     */
    public ListRes(){
        this.resultDtos = null;
        this.count = 0;
    };
    /**
     * 添加ResProperty
     */
//    public ListRes addResProperty(ListRes listRes,ResProperty resProperty){
//           listRes = listRes + resProperty;
//        return listRes;
//    }
}
