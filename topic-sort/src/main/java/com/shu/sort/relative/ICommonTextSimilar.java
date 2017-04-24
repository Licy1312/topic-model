package com.shu.sort.relative;

import java.util.Vector;

/**
 * Description：文本的相似度计算
 * User:Lichangya
 * Date:2017/4/12.
 */
public interface ICommonTextSimilar {

    /**
     * 加载词典
     * @param path
     * @return
     */
    Vector<String> loadBaseWords(String path);
    /**
     *求取目标文本在基向量上的余弦值
     * @param baseVector
     * @param targetDoc
     * @return 向量
     */
    double getTextSimilarValue(Vector<String> baseVector, String targetDoc);
}
