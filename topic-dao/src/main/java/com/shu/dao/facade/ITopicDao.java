package com.shu.dao.facade;

import com.shu.dao.entity.Topic;
import org.apache.ibatis.annotations.Param;

/**
 * Created by pc on 2017/5/16.
 */
public interface ITopicDao {

   /*

    *主题写入数据库
    */
    void insertTopic(@Param("topic") Topic topic);
}
