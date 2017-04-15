package com.shu.dao;

import com.shu.BaseTest;
import com.shu.dao.entity.Document;
import com.shu.dao.facade.IDocumentDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Reader;
import java.util.List;

/**
 * Created by pc on 2017/4/2.
 */
@Slf4j
public class DocumentGetDaoTest extends BaseTest{
    @Autowired
    private IDocumentDao documentDao;
    /**
     * 测试获取文档的方法
     */
    @Test
    public void testGetContentTest() {
        List<Document> docs = documentDao.getContent();
        log.info("一共有：{},分别为：｛｝",docs.size(),docs);
        for(Document doc:docs){
            System.out.println(doc.getTitle());
        }
    }

}

