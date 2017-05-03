package com.shu.service.facade;

import com.shu.dao.entity.DocumentDO;

import java.util.List;

/**
 * Description：文档获取的接口
 * User:wangyiteng
 * Date:2017/4/12.
 */
public interface IDocumentGetService {
    /**
     * 获取所有文档内容
     * @return
     */
    List<DocumentDO> getContent();
}
