package com.shu.analyzer;

import com.shu.dao.entity.Document;
import com.shu.dao.facade.IDocumentDao;
import com.shu.service.facade.IDocumentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.tika.exception.TikaException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description：Lucene的测试类
 * User:wangyiteng
 * Date:2017/3/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
@Slf4j
public class LuceneTest {
    @Autowired
    private IDocumentDao documentDao;
    private Document document;
    /**
     * 创建并保存索引
     *
     */
    @Test
    public void LuceneIndexTest() throws IOException, TikaException {
//        int id = 102;
//        Document document = documentDao.getDocumentById(id);
//        Lucene lucene = new Lucene();
//        lucene.luceneAnalyzer(document);
        ExecutorService pool = Executors.newFixedThreadPool(1000);
        for(int i=1;i<=5;i++){
            document = documentDao.getDocumentById(i*100+2);
            AnalyzerRunable analyzerRunable = new AnalyzerRunable();
            analyzerRunable.setDoc(document);
            pool.submit(analyzerRunable);
        }
        pool.shutdown();
    }
    /**
     * 查询
     *
     */
    @Test
    public void LuceneSearchTest() throws IOException, ParseException {
        Lucene lucene = new Lucene();
        lucene.luceneSearch("制造业");
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