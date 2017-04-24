package com.shu.web.controller;

import com.shu.dao.entity.Document;
import com.shu.service.facade.IDocumentService;
import com.shu.service.impl.DocumentGetService;
import com.shu.service.impl.DocumentService;
import com.shu.web.utils.Contains;
import com.shu.web.utils.ListRes;
import com.shu.web.utils.ResProperty;
import com.shu.web.utils.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Description：文档获取类
 * User:wangyiteng
 * Date:2017/4/14.
 */
@Slf4j
@Controller

public class DocumentGetController {
    @Autowired
   private DocumentGetService documentGetService;
    private IDocumentService documentService;


    /**
     * 获取所有文档
     * @return
     */
    @RequestMapping(value ="/index")
    @ResponseBody
    public ListRes getContent(){

        ArrayList<ResultDto> arrayList = new ArrayList<ResultDto>();
        ListRes listRes = new ListRes();
        List<Document> documents = documentGetService.getContent();
        for(int i=0;i<documents.size();i++){
//           String temp = documents.get(i).getContent().replaceAll("\n","<br>");
//           documents.get(i).setContent(temp);
            ResultDto<Document>  resultDto = new ResultDto<Document>();
            resultDto.setResult(documents.get(i));
            arrayList.add(resultDto);

        }
        listRes.setCount(documents.size());
        listRes.setResultDtos(arrayList);
        return listRes;
    }


}

