package com.shu.analyzer.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Description：文件路径配置
 * User:Lichangya
 * Date:2017/4/1.
 */
@Setter
@Getter
@Component
public class FilePathDealUtils {
    /**
     * 数据源路径
     */
    public  String resourcePath;

    /**
     * 分词后路径
     */
    private  String analyzedPath;

    /**
     * 所有文本的词库地址
     */
    private String wordsPath;

    /**
     * 索引路径
     */
    private  String indexPath;

    /**
     * 模型路径
     */
    private  String modelPath;

}
