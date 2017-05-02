package com.shu.web.controller;

import com.shu.analyzer.Lucene;
import com.shu.dao.entity.Document;
import com.shu.service.facade.IDocumentService;
import com.shu.service.impl.AnalyzerLuceneService;
import com.shu.service.impl.AnalyzerSearchService;
import com.shu.service.impl.DocumentGetService;
import com.shu.service.impl.DocumentService;
import com.shu.web.utils.ListRes;
import com.shu.web.utils.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

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
            ResultDto<Document>  resultDto = new ResultDto<Document>();
            int temp = Integer.parseInt(res.get(i));
            Document document = documentService.getDocumentById(temp);
            resultDto.setResult(document);
            arrayList.add(resultDto);
        }

        listRes.setCount(res.size());

//        System.out.println(key);
//        for(int i=90;i<120;i++){
//            ResultDto<Document>  resultDto = new ResultDto<Document>();
//            Document document = documentService.getDocumentById(i);
//            resultDto.setResult(document);
//            arrayList.add(resultDto);
//        }
//        listRes.setCount(30);


        listRes.setResultDtos(arrayList);
        return listRes;
    }
}
