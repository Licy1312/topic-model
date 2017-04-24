package com.shu.web.controller;

import com.shu.dao.entity.Document;
import com.shu.service.facade.IDocumentService;
import com.shu.web.utils.Contains;
import com.shu.web.utils.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Description：文档控制器类
 * User:Lichangya
 * Date:2017/3/25.
 */
@Slf4j
@Controller
//@RequestMapping("/main")
public class DocumentController {
    @Autowired
    private IDocumentService documentService;


    /**
     * 获取某一篇文档
     * @param id
     * @return
     */
    @RequestMapping(value = "/main/{id}")
    @ResponseBody
    public ResultDto<Document> getDocumentById(@PathVariable("id") int id){
        ResultDto<Document>  resultDto = new ResultDto<Document>();
        try{
            Document document = documentService.getDocumentById(id);
            resultDto.setResult(document);
        }catch (Exception e){
            resultDto = ResultDto.getReturn(Contains.DEFECT_CODE,e.getMessage());
        }

        return resultDto;
    }
//    @RequestMapping(method = RequestMethod.GET)
//@RequestMapping(value = "/main")
//@ResponseBody
//    public ResultDto<Document> getDocument(){
//        ResultDto<Document>  resultDto = new ResultDto<Document>();
//        try{
//            Document document = documentService.getDocumentById(50);
//            resultDto.setResult(document);
//        }catch (Exception e){
//            resultDto = ResultDto.getReturn(Contains.DEFECT_CODE,e.getMessage());
//        }
//
//        return resultDto;
//    }

}
