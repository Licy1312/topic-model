package com.shu.service;

import com.shu.service.impl.AnalyzerLuceneService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Description：service层Analyzer测试类
 * User:Lichangya
 * Date:2017/3/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
@Slf4j
public class AnalyzerLuceneServiceTest {

    @Autowired
    private AnalyzerLuceneService analyzerLuceneService;

    /**
     * 使用lucene进行的方法
     */
    @Test
    public void analyzerLuceneTest(){
        analyzerLuceneService.luceneToken();
    }
}
