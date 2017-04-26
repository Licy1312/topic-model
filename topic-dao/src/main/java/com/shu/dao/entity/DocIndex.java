package com.shu.dao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Description：
 * User:Lichangya
 * Date:2017/4/24.
 */
@Setter
@Getter
@ToString
public class DocIndex {
    /**
     * id
     */
    private Long id;

    /**
     * 文档编号
     */
    private Long docId;

    /**
     * 文档标题
     */
    private String docName;

    /**
     * 分词后的词语
     */
    private String docAnalyzer;

    /**
     * 创建时间
     */
    private Date createTime;

}
