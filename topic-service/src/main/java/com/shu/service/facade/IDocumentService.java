package com.shu.service.facade;

import com.shu.dao.entity.DocumentDO;

/**
 * Description：文档操作的接口
 * User:Lichangya
 * Date:2017/3/25.
 */
public interface IDocumentService {
    /**
     * 根据id获取文档
     * @param id
     * @return
     */
    DocumentDO getDocumentById(int id);
}
