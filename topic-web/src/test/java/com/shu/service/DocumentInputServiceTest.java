package com.shu.service;

import com.shu.BaseTest;
import com.shu.service.impl.DocumentInputService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by pc on 2017/4/11.
 */
public class DocumentInputServiceTest extends BaseTest {
    @Autowired
    DocumentInputService documentInputService;

    /**
     * 测试文档导入的方法
     */
    @Test
    public void documentInput(){
        String path = "D:/java/bs";
        documentInputService.inputDocument(path);
    }
}
