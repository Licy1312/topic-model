package com.shu.dao;

import com.shu.BaseTest;
import com.shu.dao.entity.DocIndex;
import com.shu.dao.facade.IDocIndexDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * DocIndexDao测试类
 * Created by pc on 2017/5/16.
 */
@Slf4j
public class DocIndexDaoTest extends BaseTest{
    @Autowired
    IDocIndexDao iDocIndexDao;
    /**
     * 写入主题概率
     */
    @Test
    public void insertprobability(){
        String pro = "0.09 0.0577 0.3895 0.0102 0.0016 5.0E-4 0.0232 0.0404 0.0393 0.1827 0.0684 0.0027 0.0523 0.0081 0.021 5.0E-4 0.0016 0.0092 5.0E-4 5.0E-4 ";
        DocIndex docIndex = new DocIndex();
        docIndex.setProbability(pro);
        docIndex.setDocName("20140630234446821.pdf");
        iDocIndexDao.insertProbability(docIndex);
    }
}
