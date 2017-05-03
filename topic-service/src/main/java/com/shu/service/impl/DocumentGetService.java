package com.shu.service.impl;

import com.shu.dao.entity.DocumentDO;
import com.shu.dao.facade.IDocumentDao;
import com.shu.service.facade.IDocumentGetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description：文档获取的方法
 * User:wangyiteng
 * Date:2017/4/12.
 */
@Service
@Slf4j
public class DocumentGetService implements IDocumentGetService{
    @Autowired
    private IDocumentDao documentDao;
    /**
     * 获取所有文档内容
     * @return
     */
    @Override
    public List<DocumentDO> getContent(){
      List<DocumentDO> documentDOs = documentDao.getContent();
      return documentDOs;
    }
}
