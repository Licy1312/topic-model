package com.shu.lda.utils;

import lombok.Getter;
import lombok.Setter;

/**
 * Description：LDA主题模型的参数配置
 * User:Lichangya
 * Date:2017/4/27.
 */
@Setter
@Getter
public class LDAParameterConfigure {

    /**
     * 主题数量
     */
    private int topicK;

    /**
     * 超参数α的值
     */
    private double alpha;

    /**
     * 超参数β的值
     */
    private double beta;

    /**
     * 迭代的次数
     */
    private int iteration ;

    /**
     * 每次保存结果的迭代次数
     */
    private int saveIterationStep;

    /**
     * 对每一个主题，主题说明词的数量
     */
    private int topicWords;

}
