package com.shu.web.controller;

import com.shu.dao.entity.Document;
import com.shu.service.impl.DocumentGetService;
import com.shu.service.impl.DocumentService;
import com.shu.web.utils.Contains;
import com.shu.web.utils.ListRes;
import com.shu.web.utils.ResProperty;
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
    private DocumentService documentService;
    /**
     * 获取所有文档
     * @return
     */
    @RequestMapping("/index")
    @ResponseBody
    public ListRes getContent(){

        ArrayList<ResultDto> arrayList = new ArrayList<ResultDto>();
        ListRes listRes = new ListRes();
        List<Document> documents = documentGetService.getContent();

        for(int i=0;i<documents.size();i++){
            ResultDto<Document>  resultDto = new ResultDto<Document>();
            resultDto.setResult(documents.get(i));
            arrayList.add(resultDto);

        }
        listRes.setCount(documents.size());
        listRes.setResultDtos(arrayList);
//        try{
//            int i=154;
//            Document document = documentService.getDocumentById(i);
//            resultDto.setResult(document);
//            temp.setColumn("1");
//            temp.setPage("1");
//            temp.setResultDto(resultDto);
//
//            resProperty.add(temp);
////            resProperty.setResultDto(resultDto);
////            resProperty.setPage("1");
////            resProperty.setColumn("1");
//        }catch (Exception e){
//            resultDto = ResultDto.getReturn(Contains.DEFECT_CODE,e.getMessage());
//        }
//        try{
//            int j=189;
//            Document document = documentService.getDocumentById(j);
//            resultDto.setResult(document);
//            temp.setColumn("1");
//            temp.setPage("3");
//            temp.setResultDto(resultDto);
//            resProperty.add(temp);
//
//        }catch (Exception e){
//            resultDto = ResultDto.getReturn(Contains.DEFECT_CODE,e.getMessage());
//        }

        return listRes;
    }
}

