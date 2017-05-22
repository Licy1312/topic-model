package com.shu.service.impl;

import com.shu.dao.entity.DocIndex;
import com.shu.dao.facade.IDocIndexDao;
import com.shu.service.facade.IIndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 索引操作
 * Created by pc on 2017/5/17.
 */
@Service
@Slf4j
public class IndexService implements IIndexService {
    @Autowired
    IDocIndexDao iDocIndexDao;
    /**
     * 写入主题概率
     */
    @Override
    public void insertProbability(){
        DocIndex docIndex = new DocIndex();
        //获取所有文件名
        File alldoc = new File("d:/data/topic/resource/");
        File[] docname = alldoc.listFiles();
        //获取主题概率
        File file = new File("d:/data/topic/model/model-final.theta");
        BufferedReader reader = null;
        ArrayList<String> topicp = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
              topicp.add(tempString);
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
        for(int i=0;i<topicp.size();i++){
            docIndex.setDocName(docname[i].getName());
            docIndex.setProbability(topicp.get(i));
//            System.out.println(docIndex.getDocName()+"===="+docIndex.getProbability());
            iDocIndexDao.insertProbability(docIndex);
        }

//        System.out.println(docname.length+"==========="+topicp.size());
//        for(int i=0;i<docname.length;i++){
//            docIndex.setDocName(docname[i].getName());
////            docIndex.setProbability(topicp.get(i));
////            System.out.println("line "+i+":"+docIndex.getProbability());
//        }

    }
}
