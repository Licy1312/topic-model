package com.shu.service;

import com.shu.BaseTest;
import com.shu.service.impl.IndexService;
import com.shu.service.impl.OutputDocIndexService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * 索引操作测试类
 * Created by pc on 2017/5/16.
 */
@Slf4j
public class IndexOutputTest extends BaseTest{
    @Autowired
    OutputDocIndexService outputDocIndexService;
    @Autowired
    IndexService indexService;

    /*
     *测试索引写入doc.dat
     */
    @Test
    public void indexOutput(){
        try {
            outputDocIndexService.outputDocIndex();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试索引对应主题概率入库
     */
    @Test
    public void indexProbability(){
        indexService.insertProbability();
    }
}
