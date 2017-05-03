package com.shu.service.impl;

import com.shu.dao.entity.DocumentDO;
import com.shu.dao.facade.IDocumentDao;
import com.shu.dao.utils.IndexUtil;
import com.shu.service.facade.IDocumentInputService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Description：将文档导入数据库service
 * User:wangyiteng
 * Date:2017/3/25.
 */
@Service
@Slf4j
public class DocumentInputService implements IDocumentInputService{
    @Autowired
    private IDocumentDao documentDao;


    /**
     * 导入文档
     *
     */
    public void inputDocument(String path) {
        DocumentDO doc = new DocumentDO();
        //使用tika提取pdf文本内容
        File root = new File(path);
        File[] fs = root.listFiles();
        String article = new String();
        String title = new String();
        IndexUtil iu = new IndexUtil();
        for (int i = 0; i < fs.length; i++) {
            //获取pdf中的文章文本
            try {
                article = iu.tikaTool(new File(fs[i].toString()));

            } catch (IOException e) {
                e.printStackTrace();
            } catch (TikaException e) {
                e.printStackTrace();
            }
            //获取文章标题
            String[] temp = article.split("(\\n)+");
            title = temp[0];
            int a = 0;
            while (title.getBytes().length == title.length()){
                a++;
                title = temp[a];
            }
            //导入数据库
            doc.setTitle(title);
            doc.setContent(article);
            doc.setType(0);
            doc.setCreateTime(new Date());
            doc.setHits(0);
            documentDao.inputDocument(doc);

        }
    }
}
