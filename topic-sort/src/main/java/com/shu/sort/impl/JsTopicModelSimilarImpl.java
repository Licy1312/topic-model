package com.shu.sort.impl;

import com.shu.dao.entity.Document;
import com.shu.sort.relative.ICommonTextSimilar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Description：JS距离公式实现主题模型中的主题相似度
 * User:Lichangya
 * Date:2017/4/23.
 */
@Component
@Slf4j
public class JsTopicModelSimilarImpl implements ICommonTextSimilar {

    private String topicDistributefile = "E:/licy/lda/model/topic.txt";



    @Override
    public Vector<String> loadBaseWords(String path) {
        return null;
    }

    @Override
    public double getTextSimilarValue(Vector<String> baseVector, String targetDoc) {
        return 0;
    }

    /**
     * 获取文档与其他文档对应的相似度
     * @return
     */
    public double[] gainDocSimilarValue(){
        Map<Integer,double[]> docDistributeMap = new HashMap<Integer,double[]>();
        File file = new File(topicDistributefile);
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        try {
            inputStreamReader = new InputStreamReader(new FileInputStream(file));
            bufferedReader = new BufferedReader(inputStreamReader);
            String lineText = null;
            int param =0;
            while( null!=(lineText = bufferedReader.readLine()) ){
                param++;
                String [] stringArray = lineText.split(" ");
                double[] values = new double[stringArray.length];
                for(int i=0;i<stringArray.length;i++){
                    values[i] = Double.parseDouble(stringArray[i]);
                }
                docDistributeMap.put(param,values);
            }
            log.info("一共完成:{}篇文档的主题分布采集",docDistributeMap.size());
            bufferedReader.close();
        } catch (Exception e) {
            log.error("读取文档主题分布出现异常:{}",e);
            e.printStackTrace();
        }
        //计算所有文档与第一篇文档之间的主题分布的差异
        double similarValue[] = new double[docDistributeMap.size()];
        double target[] = docDistributeMap.get(5);
        for(int j=0;j<docDistributeMap.size();j++){
            double origin[] = docDistributeMap.get(j+1);
            double v = 0.0;
            for(int i=0;i<target.length;i++){
                v += (target[i] * (Math.log(2 * target[i] / (target[i] + origin[i])))+origin[i]*(Math.log(2*origin[i]/(target[i] + origin[i]))));
            }
            similarValue[j] = v/2;
        }
        log.info("文档主题分布之间的差异为:{}",similarValue);
        return similarValue;
    }
}
