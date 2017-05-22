package com.shu.service;

import com.shu.BaseTest;
import com.shu.service.impl.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * TopicService测试类
 * Created by pc on 2017/5/16.
 */
@Slf4j
public class TopicServiceTest extends BaseTest{
    @Autowired
    TopicService topicService;

    /*
     *写入数据库
     */
    @Test
    public void insertTopic(){
        topicService.insertTopic();
    }
}
