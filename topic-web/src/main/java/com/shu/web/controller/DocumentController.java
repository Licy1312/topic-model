package com.shu.web.controller;

import com.shu.dao.entity.DocumentDO;
import com.shu.service.facade.IDocumentService;
import com.shu.sort.impl.JsTopicModelSimilarImpl;
import com.shu.web.utils.Contains;
import com.shu.web.utils.ListRes;
import com.shu.web.utils.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
    @Autowired
    private JsTopicModelSimilarImpl jsTopicModelSimilar;

    /**
     * 获取某一篇文档
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}")
    @ResponseBody
    public ListRes getDocumentById(@PathVariable("id") int id){
        ArrayList<ResultDto> arrayList = new ArrayList<ResultDto>();
        ListRes listRes = new ListRes();


            ResultDto<DocumentDO>  resultDto = new ResultDto<DocumentDO>();
            DocumentDO documentDO = documentService.getDocumentById(id);
            resultDto.setResult(documentDO);
            arrayList.add(resultDto);
          //获取推荐文章
            int sid = id - 2038;

            double result[] =jsTopicModelSimilar.gainDocSimilarValue(sid);
        Map<String, Double> map = new HashMap<String, Double>();
        for(int i=0;i<result.length;i++){
            map.put(String.valueOf(i),result[i]);
        }
        //将map.entrySet()转换成list
        List<Map.Entry<String, Double>> list = new ArrayList<Map.Entry<String, Double>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            //降序排序
            @Override
            public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
                //return o1.getValue().compareTo(o2.getValue());
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        int count = 0;
        for (Entry<String, Double> mapping : list) {
            if(mapping.getValue()<1&&mapping.getValue()>0){
            count++;
            if(count<11){
                int docmentid = Integer.parseInt(mapping.getKey())+2038;
                ResultDto<DocumentDO>  resultDto1 = new ResultDto<DocumentDO>();
                DocumentDO documentDO1 = documentService.getDocumentById(docmentid);
                resultDto1.setResult(documentDO1);
                arrayList.add(resultDto1);
//            System.out.println(mapping.getKey() + ":" + mapping.getValue());
        }}
        }
//
//        for(int i = 1;i<=5;i++){
//
//        }
        listRes.setCount(0);
        listRes.setResultDtos(arrayList);
        return listRes;
    }

}
