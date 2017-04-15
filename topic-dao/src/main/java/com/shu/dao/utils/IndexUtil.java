package com.shu.dao.utils;

/**
 * Description:tika工具类
 * User:wangyiteng
 * Date:2017/3/31
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;


public class IndexUtil {


    public String tikaTool(File f) throws IOException, TikaException {
        Tika tika = new Tika();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.AUTHOR, "空号");//重新设置文档的媒体内容
        metadata.set(Metadata.RESOURCE_NAME_KEY, f.getName());
        String str = tika.parseToString(new FileInputStream(f),metadata);
//        for(String name:metadata.names()) {
//            System.out.println(name+":"+metadata.get(name));
//        }
        return str;
    }

}

