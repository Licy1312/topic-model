package com.shu.analyzer.service;

import com.shu.analyzer.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apdplat.word.lucene.ChineseWordAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description：索引管理
 * User:Lichangya
 * Date:2017/4/26.
 */
@Slf4j
@Component
public class IndexManager {

    private static Directory directory;
    private static Analyzer analyzer;
    private static IndexWriterConfig indexWriterConfig;
    private static IndexWriter indexWriter;
    private static DirectoryReader directoryReader;
    private static IndexSearcher indexSearcher;

    @Autowired
    private FilePathDealUtils filePathDealUtils;

    /**
     * 创建索引
     */
    public void createIndex(){
        try {
            Date start = new Date();
            //初始化
            directory = FSDirectory.open(Paths.get(filePathDealUtils.getIndexPath()));
            analyzer = new ChineseWordAnalyzer("BidirectionalMaximumMatching");
            indexWriterConfig = new IndexWriterConfig(analyzer);
            indexWriter = new IndexWriter(directory, indexWriterConfig);

            //处理数据源
            File files = new File(filePathDealUtils.getResourcePath());
            for(File file:files.listFiles()){
                Document doc = new Document();
                doc.add(new Field("name",file.getName(),TextField.TYPE_STORED));
                doc.add(new Field("content", FilesToString.pdfToString(file.getAbsolutePath()), TextField.TYPE_STORED));
                //写入索引
                indexWriter.addDocument(doc);
            }
            Date end = new Date();
            log.info("索引创建完成一共耗时:{}，处理的文档数量:{}", TimeUtils.timeDistance(start,end),files.listFiles().length);
        } catch (Exception e) {
            log.error("创建索引遇到错误:{}",e);
            throw new DocumentAnalyzerException(AnalyzerExceptionEnum.INDEX_WRITE_ERROR);
        }finally {
            try {
                indexWriter.close();
                directory.close();
            } catch (IOException e) {
                log.error("关闭索引流的时候遇到错误:{}",e);
                throw new DocumentAnalyzerException(AnalyzerExceptionEnum.INDEX_WRITE_CLOSE_ERROR);
            }
        }
    }

    /**
     * 搜索索引
     * @param kayWords 搜索词
     */
    public List<String> searchIndex(String kayWords){
        List<String> indexResultList = new ArrayList<String>();
        try {
            directory = FSDirectory.open(Paths.get(filePathDealUtils.getIndexPath()));
            directoryReader = DirectoryReader.open(directory);
            indexSearcher = new IndexSearcher(directoryReader);
            analyzer = new ChineseWordAnalyzer("BidirectionalMaximumMatching");
            QueryParser queryParser = new QueryParser("content", analyzer);
            Query query = queryParser.parse(kayWords);

            Date start = new Date();
            TopDocs topDocs = indexSearcher.search(query,100);
            Date end = new Date();
            log.info("对索引的检索一共耗时:{}", TimeUtils.timeDistance(start,end));
            for(ScoreDoc sd: topDocs.scoreDocs) {
                Document doc = indexSearcher.doc(sd.doc);
                String title = doc.get("name");
                indexResultList.add(title);
            }
        } catch (Exception e) {
            log.error("索引检索过程中出现异常:{}",e);
            throw new DocumentAnalyzerException(AnalyzerExceptionEnum.INDEX_SEARCH_ERROR);
        }
        return indexResultList;
    }

    /**
     * 初始参数
     */
    public void init(){
        try {
            //初始化
            directory = FSDirectory.open(Paths.get(filePathDealUtils.getIndexPath()));
            analyzer = new ChineseWordAnalyzer("BidirectionalMaximumMatching");
        } catch (Exception e) {
            log.error("索引配置初始化异常:{}",e);
            throw new DocumentAnalyzerException(AnalyzerExceptionEnum.INDEX_CONFIG_INIT_ERROR);
        }
    }
}
