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

/**
 * Created by pc on 2017/4/11.
 */
@Service
@Slf4j

public class AnalyzerLuceneService implements IAnalyzerLuceneService{
    @Autowired
    private DocumentService documentDao;
//    int id1 = 57;
    Document document = new Document();
//    int id2 = 88,id3=94,id4=105,id5= 189;
//    Document document2 = documentDao.getDocumentById(id2);
//    Document document3 = documentDao.getDocumentById(id3);
//    Document document4 = documentDao.getDocumentById(id4);
//    Document document5 = documentDao.getDocumentById(id5);

    @Override
    public void luceneToken(){
        ExecutorService pool = Executors.newFixedThreadPool(1000);
        for(int i=1;i<=5;i++){
            document = documentDao.getDocumentById(i*100+2);
            AnalyzerRunable analyzerRunable = new AnalyzerRunable();
            analyzerRunable.setDoc(document);
            pool.submit(analyzerRunable);
        }
        pool.shutdown();
    }
}
class AnalyzerRunable implements Runnable{
    @Autowired
    private Document document;
    private Lucene lucene;

    public void setDoc(Document document){
        this.document = document;

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

    }
}