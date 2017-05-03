package com.shu.analyzer.facade;

import com.shu.dao.entity.DocumentDO;

import java.util.List;

/**
 * Description：文本处理类
 * User:Lichangya
 * Date:2017/3/25.
 */
public interface IDocumentDeal {
    /**
     * 开始分词
     */
    void startAnalyze();

    /**
     * 模型处理的分词器
     */
    void startLdaAnalyzer();

    /**
     * 根据关键字获取一组相关的文章
     * @param keywords
     * @return
     */
    List<DocumentDO> searchIndexByKeyWords(String keywords);

    /**
     * 所有文档基本信息入库（标题、内容）
     */
    void saveAllDocumentBaseInfo();
}
