package com.shu.dao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Description: 源文档实体类
 * User:Lichangya
 * Date:2017/3/25.
 */
@Setter
@Getter
@ToString
public class DocumentDO {

    /**
     * 文档编号
     */
    private int id;

    /**
     * 文档标题
     */
    private String title;
    /**
     * 关键字
     */
    private String keywords;

    /**
     * 摘要
     */
    private String dAbstract;

    /**
     * 内容
     */
    private String content;
    /**
     * 主题
     */
    private String topics;

    /**
     * 存储路径
     */
    private String path;
    /**
     * 文档的类型
     */
    private int type;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 点击量
     */
    private int hits;
}
