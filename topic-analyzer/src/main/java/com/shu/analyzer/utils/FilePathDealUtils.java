package com.shu.analyzer.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.xml.ws.BindingType;

/**
 * Description：
 * User:Lichangya
 * Date:2017/4/1.
 */
@Component
@Getter
@Setter
public class FilePathDealUtils {
    /**
     * 数据源路径
     */
    private  String resourcePath;

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
