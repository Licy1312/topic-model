package com.shu.service;

import com.shu.BaseTest;
import com.shu.dao.entity.Document;
import com.shu.service.impl.DocumentGetService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by pc on 2017/4/12.
 */
@Slf4j
public class DocumentGetServiceTest extends BaseTest{
    @Autowired
    private DocumentGetService documentGetService;

    @Test
    public void contentTest(){
        List<Document> docs = documentGetService.getContent();
        log.info("一共有：{},分别为：｛｝",docs.size(),docs);
    }
}
