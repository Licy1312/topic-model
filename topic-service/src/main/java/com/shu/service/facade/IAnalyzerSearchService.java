package com.shu.service.facade;

import java.util.ArrayList;

/**
 * Created by pc on 2017/4/21.
 */
public interface IAnalyzerSearchService {
    /**
     * 使用lucene进行查询
     */
    ArrayList<String> luceneSearch(String str);
}
