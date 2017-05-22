package com.shu.dao;

import com.shu.BaseTest;
import com.shu.dao.entity.Topic;
import com.shu.dao.facade.ITopicDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * topic 表数据库操作测试
 * Created by pc on 2017/5/16.
 */
@Slf4j
public class TopicDaoTest extends BaseTest {
    @Autowired
    ITopicDao iTopicDao;



    /*
     *插入测试
     */
    @Test
    public void inserttopic(){
        Topic topic = new Topic();
        topic.setId(3);
        topic.setName("Topic 0th:");
        String temp = "\t创新 0.040386812976285186\n" +
                "\t科技 0.03476163071913278\n" +
                "\t人才 0.018060701578718003\n" +
                "\t研发 0.011911666428438328\n" +
                "\t机构 0.010552143443893734\n" +
                "\t知识产权 0.009753891416271221\n" +
                "\t培训 0.009666582600750009\n" +
                "\t高校 0.009529383033502389\n" +
                "\t研究 0.009479492281775982\n" +
                "\t知识 0.009067893580033123";
//        temp = temp.replaceAll("\t|\n"," ");

        topic.setContent(temp);
        iTopicDao.insertTopic(topic);
     }
}
