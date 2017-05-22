package com.shu.analyzer.impl;

import com.shu.analyzer.facade.IDocumentDeal;
import com.shu.analyzer.utils.*;
import com.shu.dao.entity.DocIndex;
import com.shu.dao.entity.DocumentDO;
import com.shu.dao.facade.IDocIndexDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apdplat.word.lucene.ChineseWordAnalyzer;


import java.io.File;

import java.util.Date;
import java.util.List;

/**
 * Description：文本处理实现类
 * User:Lichangya
 * Date:2017/3/25.
 */
@Slf4j
@Component
public class DocumentDeal implements IDocumentDeal {

    /**
     * 文件路径工具类
     */
    @Autowired
    private FilePathDealUtils filePathDealUtils;

    /**
     * 文件分词入库控制器
     */
    @Autowired
    private IDocIndexDao iDocIndexDao;

    /**
     * 开始分词
     */
    @Override
    public void startAnalyze() {
        //获取数据源
        File files = new File(filePathDealUtils.getResourcePath());
        Date startTime = new Date();
        log.info("分词开始时间:{}",startTime);
        for(File file:files.listFiles()){
            Analyzer analyzer = new ChineseWordAnalyzer("BidirectionalMaximumMatching");
//            Analyzer analyzer2 = new IKAnalyzer();
            TokenStream stream = analyzer.tokenStream("text", FilesToString.pdfToString(file.getAbsolutePath()));
            CharTermAttribute cta = stream.addAttribute(CharTermAttribute.class);
            try {
                // 准备消费
                stream.reset();
                StringBuffer stringBuffer = new StringBuffer();
                while (stream.incrementToken()) {
                    stringBuffer.append(cta + " ") ;
                }
                DocIndex docIndex = new DocIndex();
                docIndex.setId(System.currentTimeMillis());
                docIndex.setDocId(System.currentTimeMillis());
                docIndex.setDocName(file.getName());
                docIndex.setDocAnalyzer( stringBuffer.toString().trim());
                iDocIndexDao.insertDocIndex(docIndex);
                // 消费完毕
                stream.close();
            } catch (Exception e) {
                log.error("分词出现异常:{}",e);
                throw  new DocumentAnalyzerException(AnalyzerExceptionEnum.ANALYZER_DOCUMENT_ERROR);
            }
        }
        Date endTime = new Date();
        log.info("分词结束一共耗时:{}，处理的文档数量:{}", TimeUtils.timeDistance(startTime,endTime),files.listFiles().length);
    }

    /**
     * 模型处理的分词器
     */
    @Override
    public void startLdaAnalyzer() {

    }

    /**
     * 根据关键字获取一组相关的文章
     * @param keywords
     * @return
     */
    @Override
    public List<DocumentDO> searchIndexByKeyWords(String keywords) {
        return null;
    }

    /**
     * 保存所有文档基本信息入库（标题、内容）
     */
    @Override
    public void saveAllDocumentBaseInfo() {
            //1.利用线程池,对文件夹中的所有文件也不分词、存储数据库
    }

}
