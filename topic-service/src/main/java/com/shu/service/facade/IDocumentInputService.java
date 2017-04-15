package com.shu.service.facade;

import com.shu.dao.entity.Document;

/**
 * Description：导入文档接口
 * User:wangyiteng
 * Date:2017/3/31
 */
public interface IDocumentInputService {
    /**
     * 导入文档
     *
     */
    void inputDocument(String path);
}
