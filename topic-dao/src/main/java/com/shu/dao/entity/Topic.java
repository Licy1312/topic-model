package com.shu.dao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by pc on 2017/5/16.
 */
@Setter
@Getter
@ToString
public class Topic {
    /**
     * id
     */
    private int id;

    /**
     * 主题编号
     */
    private String name;

    /**
     * 主题内容
     */
    private String content;


}
