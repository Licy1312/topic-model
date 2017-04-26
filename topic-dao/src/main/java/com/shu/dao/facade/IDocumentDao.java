package com.shu.dao.facade;

import com.shu.dao.entity.DocumentDO;

/**
 * Description：操作数据库中的Document的所有接口，方法
 * User:Lichangya
 * Date:2017/3/25.
 */
public interface IDocumentDao {
    /**
     * 通过id获取文档
     * @param id
     * @return
     */
    DocumentDO getDocumentById(int id);

    /**
     * 更新文档
     * @param documentDO
     */
    void updateDocument(DocumentDO documentDO);
}
