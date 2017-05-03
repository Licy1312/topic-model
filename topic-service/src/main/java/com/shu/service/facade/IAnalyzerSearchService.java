package com.shu.service.facade;

import com.shu.analyzer.utils.IndexResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2017/4/21.
 */
public interface IAnalyzerSearchService {
    /**
     * 使用lucene进行查询
     */
    ArrayList<String> luceneSearch(String str);

    /**
     * 检索索引
     * @param str
     * @return
     */
    List<String> searchIndex(String str);
}
