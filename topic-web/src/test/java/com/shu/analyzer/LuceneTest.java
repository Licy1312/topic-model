package com.shu.analyzer;

import com.shu.dao.entity.Document;
import com.shu.dao.facade.IDocumentDao;
import com.shu.service.facade.IDocumentService;
import com.shu.service.impl.DocumentGetService;
import com.shu.web.utils.ResultDto;
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
import java.util.List;
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
    private DocumentGetService documentGetService;
    /**
     * 创建并保存索引
     *
     */
    @Test
    public void LuceneIndexTest() throws IOException, TikaException {
        List<Document> documents = documentDao.getContent();
        for(int i=0;i<documents.size();i++){
            document = documents.get(i);
            Lucene lucene = new Lucene();
            lucene.luceneAnalyzer(document);
        }
//       for(int i=46;i<51;i++){
//           document = documentDao.getDocumentById(i);
//           Lucene lucene = new Lucene();
//            lucene.luceneAnalyzer(document);
//       }

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
//        for(int i=0;i<res.size();i++){
//            System.out.println(res.get(i));
//        }
    }
}
