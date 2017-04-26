package com.shu.service.impl;

import com.shu.dao.entity.DocumentDO;
import com.shu.dao.facade.IDocumentDao;
import com.shu.service.enums.ExceptionEnum;
import com.shu.service.exceptions.DocumentException;
import com.shu.service.facade.IDocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description：
 * User:Lichangya
 * Date:2017/3/25.
 */
@Service
@Slf4j
public class DocumentService implements IDocumentService {

    @Autowired
    private IDocumentDao documentDao;

    /**
     * 根据id获取文档
     * @param id
     * @return
     */
    @Override
    public DocumentDO getDocumentById(int id) {
        DocumentDO documentDO = documentDao.getDocumentById(id);
        if(documentDO != null){
            return documentDO;
        }else{
         throw  new DocumentException(ExceptionEnum.DOCUMENT_NULL.getDesc());
        }
    }
}
