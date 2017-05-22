package com.shu.service.impl;

import com.shu.dao.entity.Topic;
import com.shu.dao.facade.ITopicDao;
import com.shu.service.facade.ITopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 主题操作
 * Created by pc on 2017/5/16.
 */
@Service
@Slf4j
public class TopicService implements ITopicService{
    @Autowired
    ITopicDao iTopicDao;
    /*
     *主题写入数据库
     */
    @Override
    public void insertTopic(){
        int id = 0;
        String content = new String();
        Topic topic = new Topic();
        File file = new File("d:/data/topic/model/model-final.twords");
        BufferedReader reader = null;
        try {
//            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                if(line%11==1){
                    topic.setId(id);
                    id++;
                    topic.setName(tempString);
                }
                else if(line%11==0){
                    topic.setContent(content);
                    iTopicDao.insertTopic(topic);
                    content = "";
                }
                else{
                    content = content + tempString;
                }
                // 显示行号
//                System.out.println("line " + line + ": " + tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
}
