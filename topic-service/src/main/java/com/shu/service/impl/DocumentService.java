package com.shu.service.impl;

import com.shu.dao.entity.Document;
import com.shu.dao.facade.IDocumentDao;
import com.shu.service.enums.ExceptionEnum;
import com.shu.service.exceptions.DocumentException;
import com.shu.service.facade.IDocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;

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
    public Document getDocumentById(int id) {
        Document document = documentDao.getDocumentById(id);
        if(document != null){
            return document;
        }else{
         throw  new DocumentException(ExceptionEnum.DOCUMENT_NULL.getDesc());
        }
    }
}
