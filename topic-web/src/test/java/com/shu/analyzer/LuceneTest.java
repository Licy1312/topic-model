package com.shu.analyzer;

import com.shu.dao.entity.DocumentDO;
import com.shu.dao.facade.IDocumentDao;
import com.shu.service.impl.DocumentGetService;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.tika.exception.TikaException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;

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
    private DocumentDO documentDO;
    private DocumentGetService documentGetService;
    /**
     * 创建并保存索引
     *
     */
    @Test
    public void LuceneIndexTest() throws IOException, TikaException {
//        List<DocumentDO> documents = documentDao.getContent();
//        for(int i=0;i<documents.size();i++){
//            documentDO = documents.get(i);
//            Lucene lucene = new Lucene();
//            lucene.luceneAnalyzer(documentDO);
//        }

        ArrayList<org.apache.lucene.document.Document> docs = new ArrayList<org.apache.lucene.document.Document>();
        org.apache.lucene.document.Document doc = new org.apache.lucene.document.Document();
       for(int i=1050;i<1052;i++){
           documentDO = documentDao.getDocumentById(i);
           Lucene lucene = new Lucene();
            doc = lucene.luceneAnalyzer(documentDO);
           docs.add(doc);
       }

    }
    /**
     * 查询
     *
     */
    @Test
    public void LuceneSearchTest() throws IOException, ParseException {
        Lucene lucene = new Lucene();
        ArrayList<String> res =lucene.luceneSearch("上海");

        System.out.println("结果是："+res.size());
        for(int i=0;i<10;i++){
            System.out.println(res.get(i));
        }
    }
}
