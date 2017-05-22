package com.shu.service.facade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取所有分词结果，保存到doc.dat
 * Created by pc on 2017/5/16.
 */
public interface IOutputDocIndexService {
    /**
     * 获取所有分词结果，保存到doc.dat
     *
     */
    void outputDocIndex() throws IOException;
}
