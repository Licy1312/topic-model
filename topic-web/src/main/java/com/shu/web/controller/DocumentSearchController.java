package com.shu.web.controller;

import com.shu.dao.entity.DocumentDO;
import com.shu.service.impl.AnalyzerSearchService;
import com.shu.service.impl.DocumentService;
import com.shu.web.utils.ListRes;
import com.shu.web.utils.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2017/4/18.
 */
@Slf4j
@Controller
public class DocumentSearchController {

    @Autowired
    private AnalyzerSearchService analyzerSearchService;




    /**
     * 获取查询结果
     * @return
     */
    @RequestMapping(value = "/index/search/{str}")
    @ResponseBody
//    public ListRes getSearch(@RequestParam("str") String str){
    public ListRes getSearch(@PathVariable("str") String str) throws IOException, ParseException {
        String key = new String(str.getBytes("ISO-8859-1"), "utf8");
        ArrayList<ResultDto> arrayList = new ArrayList<ResultDto>();
        ListRes listRes = new ListRes();



        DocumentService documentService = new DocumentService();
        ArrayList<String> res = analyzerSearchService.luceneSearch(str);

        for(int i=0;i<res.size();i++){
            ResultDto<DocumentDO>  resultDto = new ResultDto<DocumentDO>();
            int temp = Integer.parseInt(res.get(i));
            DocumentDO documentDO = documentService.getDocumentById(temp);
            resultDto.setResult(documentDO);
            arrayList.add(resultDto);
        }

        listRes.setCount(res.size());

//        System.out.println(key);
//        for(int i=90;i<120;i++){
//            ResultDto<DocumentDO>  resultDto = new ResultDto<DocumentDO>();
//            DocumentDO document = documentService.getDocumentById(i);
//            resultDto.setResult(document);
//            arrayList.add(resultDto);
//        }
//        listRes.setCount(30);


        listRes.setResultDtos(arrayList);
        return listRes;
    }

    /**
     * 获取查询结果
     * @return
     */
    @RequestMapping(value = "/index/search2/{str}")
    @ResponseBody
    public ListRes gainSearch(@PathVariable("str") String str) throws IOException, ParseException {
        String key = new String(str.getBytes("ISO-8859-1"), "utf8");
        ArrayList<ResultDto> arrayList = new ArrayList<ResultDto>();
        ListRes listRes = new ListRes();

        DocumentService documentService = new DocumentService();
       List<String> list = analyzerSearchService.searchIndex(str);

        for(int i=0;i<list.size();i++){
            ResultDto<DocumentDO>  resultDto = new ResultDto<DocumentDO>();
            DocumentDO documentDO = new DocumentDO();
            documentDO.setTitle(list.get(i));
            resultDto.setResult(documentDO);
            arrayList.add(resultDto);
        }
        listRes.setCount(list.size());
        listRes.setResultDtos(arrayList);
        return listRes;
    }
}
