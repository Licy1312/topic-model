package com.shu.sort.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Description：每一篇文档中的词项
 * User:Lichangya
 * Date:2017/4/15.
 */
@Getter
@Setter
public class Term {
    /**
     * 词语内容
     */
    private String word;

    /**
     * 词语在文档的频率
     */
    private int tfCount;

    /**
     * 词语出现在文本集中的文档数量
     */
    private int dfCount;

    /**
     * 词语在文档中的权重
     */
    private double weight;

    public Term(String word,int tfCount){
        this.word = word;
        this.tfCount = tfCount;
    }
}
