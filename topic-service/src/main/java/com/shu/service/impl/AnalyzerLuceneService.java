package com.shu.service.impl;

import com.shu.analyzer.Lucene;
import com.shu.dao.entity.Document;
import com.shu.service.facade.IAnalyzerLuceneService;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by pc on 2017/4/11.
 */
@Service
@Slf4j

public class AnalyzerLuceneService implements IAnalyzerLuceneService{
    @Autowired
     private DocumentGetService documentGetService;


    Document document = new Document();
    Lucene lucene = new Lucene();
    ArrayList<org.apache.lucene.document.Document> docs ;
    org.apache.lucene.document.Document doc ;
    @Override
    public void luceneToken() throws InterruptedException {
        int threadCount = 1000;
        ExecutorService pool = Executors.newFixedThreadPool(threadCount);
        List<Document> documents = documentGetService.getContent();
        docs = new ArrayList<org.apache.lucene.document.Document>();

        for(int i=0;i<documents.size();i++){
            document = documents.get(i);

            doc = new org.apache.lucene.document.Document();
            AnalyzerRunable analyzerRunable = new AnalyzerRunable(document,lucene,doc,docs);
          pool.submit(analyzerRunable);

        }

        // 释放线程池资源
        pool.shutdown();
        try {//等待直到所有任务完成
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            lucene.indexWrite(docs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public synchronized void addDocument( List<org.apache.lucene.document.Document> docs,org.apache.lucene.document.Document doc){
        docs.add(doc);
    }
}

class AnalyzerRunable implements Runnable{
    org.apache.lucene.document.Document doc;
    private Document document;
    private Lucene lucene;
    ArrayList<org.apache.lucene.document.Document> docs;



    public AnalyzerRunable(Document document, Lucene lucene, org.apache.lucene.document.Document doc, ArrayList<org.apache.lucene.document.Document> docs){
        super();
        this.document = document;
        this.lucene = lucene;
        this.doc = doc;
        this.docs = docs;


    }

    @Override
    public void run(){
        try {

            doc = lucene.luceneAnalyzer(document);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        }
//        doc.add(new Field("text", "13456879", TextField.TYPE_STORED));
        AnalyzerLuceneService analyzerLuceneService = new AnalyzerLuceneService();
        analyzerLuceneService.addDocument(docs,doc);

    }
}
