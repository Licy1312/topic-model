package com.shu.analyzer.impl;

import com.shu.analyzer.facade.IDocumentDeal;
import com.shu.analyzer.utils.FilePathDealUtils;
import com.shu.dao.entity.Document;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description：文本处理实现类
 * User:Lichangya
 * Date:2017/3/25.
 */
@Slf4j
@Component
public class DocumentDeal implements IDocumentDeal {

    @Autowired
    private FilePathDealUtils filePathDealUtils;

    /**
     * 开始分词
     */
    @Override
    public void startAnalyze() {

    }
    /**
     * 模型处理的分词器
     */
    @Override
    public void startLdaAnalyzer() {

    }

    /**
     * 根据关键字获取一组相关的文章
     * @param keywords
     * @return
     */
    @Override
    public List<Document> searchIndexByKeyWords(String keywords) {
        return null;
    }

    /**
     * 保存所有文档基本信息入库（标题、内容）
     */
    @Override
    public void saveAllDocumentBaseInfo() {
            //1.利用线程池,对文件夹中的所有文件也不分词、存储数据库
    }
}
