package com.shu.analyzer.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;

import java.io.File;

/**
 * Description： 文档处理工具类
 * User:Lichangya
 * Date:2017/4/26.
 */
@Slf4j
public class FilesToString {

    /**
     * tika解析文件为字符串
     * @param path 文件路径
     * @return
     */
    public static String pdfToString(String path){
        String string = null;
        Tika tika = new Tika();
        tika.setMaxStringLength(-1);
        try {
            string = tika.parseToString(new File(path));
        } catch (Exception e) {
            log.error("解析文件:{}遇到错误:{}",path,e);
            throw new DocumentAnalyzerException(AnalyzerExceptionEnum.DOCUMENT_PARSING_ERROR);
        }
        return string;
    }
}
