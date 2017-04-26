package com.shu.web.controller;

import com.shu.dao.entity.DocumentDO;
import com.shu.service.facade.IDocumentService;
import com.shu.web.utils.Contains;
import com.shu.web.utils.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description：文档控制器类
 * User:Lichangya
 * Date:2017/3/25.
 */
@Slf4j
@Controller
@RequestMapping("/main")
public class DocumentController {
    @Autowired
    private IDocumentService documentService;


    /**
     * 获取某一篇文档
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}")
    @ResponseBody
    public ResultDto<DocumentDO> getDocumentById(@PathVariable("id") int id){

        ResultDto<DocumentDO>  resultDto = new ResultDto<DocumentDO>();
        try{
            DocumentDO documentDO = documentService.getDocumentById(id);
            resultDto.setResult(documentDO);
        }catch (Exception e){
            resultDto = ResultDto.getReturn(Contains.DEFECT_CODE,e.getMessage());
        }
        return resultDto;
    }

}
