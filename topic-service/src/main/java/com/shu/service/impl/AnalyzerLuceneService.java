package com.shu.service.impl;

import com.shu.analyzer.Lucene;
import com.shu.dao.entity.Document;
import com.shu.service.facade.IAnalyzerLuceneService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by pc on 2017/4/11.
 */
@Service
@Slf4j

public class AnalyzerLuceneService implements IAnalyzerLuceneService{
    @Autowired
     private DocumentGetService documentGetService;

//    int id1 = 57;
    Document document = new Document();
    Lucene lucene = new Lucene();


    @Override
    public void luceneToken(){
        ExecutorService pool = Executors.newFixedThreadPool(1000);
        List<Document> documents = documentGetService.getContent();
        for(int i=0;i<documents.size();i++){
            document = documents.get(i);
            AnalyzerRunable analyzerRunable = new AnalyzerRunable();
            analyzerRunable.setDoc(document);
            analyzerRunable.setLucene(lucene);
            pool.submit(analyzerRunable);
        }
        pool.shutdown();
        try {//等待直到所有任务完成
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class AnalyzerRunable implements Runnable{
    @Autowired
    private Document document;
    private Lucene lucene;

    public void setDoc(Document document){
        this.document = document;

    }
    public void setLucene(Lucene lucene){
        this.lucene = lucene;
    }
    @Override
    public void run(){
        try {
            lucene.luceneAnalyzer(document);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        }

//        System.out.println(Thread.currentThread().getName() + ":" );
    }
}