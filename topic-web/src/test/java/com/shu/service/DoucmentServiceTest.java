package com.shu.service;

import com.shu.dao.entity.Document;
import com.shu.service.facade.IDocumentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Description：service层测试类
 * User:Lichangya
 * Date:2017/3/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
@Slf4j
public class DoucmentServiceTest {

    @Autowired
    private IDocumentService documentService;

    /**
     * 测试文档获取的方法
     */
    @Test
    public void testGetDocument(){
        int id = 1;
        Document document = documentService.getDocumentById(id);
        log.info("文档的的详细:{}",document);
    }
}

