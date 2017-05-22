package com.shu.service.impl;

import com.shu.dao.facade.IDocIndexDao;
import com.shu.service.facade.IOutputDocIndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;

/**
 * 获取索引，写入doc.dat文件
 * Created by pc on 2017/5/16.
 */
@Service
@Slf4j
public class OutputDocIndexService implements IOutputDocIndexService {
    @Autowired
    IDocIndexDao iDocIndexDao;

    /* 写入方法
    *
     */
    @Override
    public void outputDocIndex() throws IOException {
        ArrayList<String> arrayList = iDocIndexDao.searchDocIndex();
        File file = new File("d:/data/topic/model/doc.dat");
        if (!file.exists()) {
            file.createNewFile();
        }
        try {

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file));
            BufferedWriter writer = new BufferedWriter(outputStreamWriter);
            String size = String.valueOf(arrayList.size());
            writer.write(size);
            writer.newLine();
            writer.flush();
            for(int i=0;i<arrayList.size();i++){
                writer.write(arrayList.get(i));
                writer.newLine();
                writer.flush();
            }
            outputStreamWriter.close();
            writer.close();
            log.debug("Write File complete!");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
