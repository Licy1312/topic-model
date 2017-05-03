package com.shu.analyzer;

import com.shu.BaseTest;
import com.shu.analyzer.facade.IDocumentDeal;
import com.shu.analyzer.service.IndexManager;
import com.shu.analyzer.utils.IndexResult;
import com.shu.analyzer.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Description：
 * User:Lichangya
 * Date:2017/4/26.
 */
@Slf4j
public class DocumentDealTest extends BaseTest {

    @Autowired
    private IDocumentDeal documentDeal;

    @Autowired
    private IndexManager indexManager;

    /**
     * 测试分词
     */
    @Test
    public  void startAnalyzeTest(){
        documentDeal.startAnalyze();
    }

    /**
     * 测试创建索引
     */
    @Test
    public  void indexWriteTest(){
        indexManager.createIndex();
    }


    /**
     * 测试检索索引
     */
    @Test
    public  void indexSearchTest(){
        List<String> indexResultList = indexManager.searchIndex("发展外向型经济");
        log.info("检索结果一共有:{}条相关记录，分别为:{}",indexResultList.size(),indexResultList);
    }

    @Test
    public void timeTest(){
        Date start = new Date();
        try {
            Thread.sleep(10500L);
            Date end = new Date();
            log.info("测试结果：{}", TimeUtils.timeDistance(start,end));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
