package com.shu.dao;

import com.shu.dao.entity.DocumentDO;
import com.shu.dao.facade.IDocumentDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Description：IdocumentDao的测试类
 * User:Lichangya
 * Date:2017/3/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
@Slf4j
public class DocumentDODaoTest {
    @Autowired
    private IDocumentDao documentDao;

    /**
     * 测试获取文档的方法
     */
    @Test
    public void testGetDocumentById(){
        int id = 102;
//        DocumentDO documentDO = documentDao.getDocumentById(id);
        DocumentDO documentDO = documentDao.getDocumentByPath("20140701000340232.pdf");
        log.info("文档对象的内容是:{}", documentDO);
    }
}
