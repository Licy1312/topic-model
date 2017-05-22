package com.shu.dao;

import com.shu.BaseTest;
import com.shu.dao.facade.IDocIndexDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2017/5/16.
 */
@Slf4j
public class IndexSearchTest extends BaseTest {
    @Autowired
    IDocIndexDao iDocIndexDao;

    @Test
    public void searchIndexDoc(){
        ArrayList<String> list = iDocIndexDao.searchDocIndex();
        log.info("一共有：{},分别为：｛｝",list.size(),list);
    }
}
