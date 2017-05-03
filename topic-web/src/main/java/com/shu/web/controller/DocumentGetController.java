package com.shu.web.controller;

import com.shu.dao.entity.DocumentDO;
import com.shu.service.facade.IDocumentService;
import com.shu.service.impl.DocumentGetService;
import com.shu.web.utils.ListRes;
import com.shu.web.utils.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
        List<DocumentDO> documentDOs = documentGetService.getContent();
        for(int i = 0; i< documentDOs.size(); i++){
//           String temp = documentDOs.get(i).getContent().replaceAll("\n","<br>");
//           documentDOs.get(i).setContent(temp);
            ResultDto<DocumentDO>  resultDto = new ResultDto<DocumentDO>();
            resultDto.setResult(documentDOs.get(i));
            arrayList.add(resultDto);

        }
        listRes.setCount(documentDOs.size());
        listRes.setResultDtos(arrayList);
        return listRes;
    }


}

