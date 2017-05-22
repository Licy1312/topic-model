package com.shu.sort.impl;

import com.shu.dao.entity.DocumentDO;
import com.shu.sort.model.Term;
import com.shu.sort.relative.ICommonTextSimilar;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Description：基于VSM的余弦值夹角计算文本相似度
 * User:Lichangya
 * Date:2017/4/15.
 */
public class VSMTextSimilarImpl implements ICommonTextSimilar {

    //文本集中文档数量
    private static int docsNum = 10;

    //词典中词语在文本集中出现的文档数
    private static Map<String,Integer> baseWordsMap = new HashMap<>();

    //文档id--词项
    private static Map<Integer,Term[]> docMap = new HashMap<>();

    //文档id--向量
    private static Map<Integer,double[]> docVector = new HashMap<>();

    /**
     * 加载词典
     * @param path
     * @return
     */
    @Override
    public Vector<String> loadBaseWords(String path) {
        return null;
    }


    /**
     * 计算文本的余弦值
     * @param baseVector
     * @param targetDoc
     * @return
     */
    @Override
    public double getTextSimilarValue(Vector<String> baseVector, String targetDoc) {
        return 0;
    }

    /**
     * 统计文档词项信息
     * @param baseWords 词典
     * @param doc
     */
    public void getDocVector(Vector<String> baseWords, DocumentDO doc){
        Term[] terms = new Term[baseWords.size()];
        for(int i=0;i<baseWords.size();i++){
            String word = baseWords.get(i);
            if(!baseWordsMap.containsKey(word)){
                baseWordsMap.put(word,0);
            }
            // 1.计算tf
            int wordCount = getWordCount(doc.getContent(),word);
            // 统计df
            if(wordCount>0){
                baseWordsMap.put(word,baseWordsMap.get(word)+1);
            }
            Term term = new Term(word,wordCount);
            terms[i] = term;
        }
        docMap.put(doc.getId(),terms);
    }

    public void calculateDocVector(DocumentDO doc){
        Term[] terms = docMap.get(doc.getId());
        double vector[] = new double[terms.length];
        for(int i=0;i<terms.length;i++){
            //获取df
            int dfCount = baseWordsMap.get(terms[i].getWord());
            //计算权重 tf-idf
            BigDecimal weight = new BigDecimal(Math.log(docsNum/(dfCount+1)));
            terms[i].setDfCount(dfCount);
            terms[i].setWeight(weight.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue());
            vector[i] = terms[i].getWeight();
        }
        docVector.put(doc.getId(),vector);
    }
    /**
     * 文档中每个词语的出现的个数
     * @param content
     * @param word
     * @return
     */
    public int getWordCount(String content, String word) {
        String str = content.replaceAll(word, "");
        return content.length() - str.length();
    }

}