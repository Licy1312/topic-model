package com.shu.lda;

import com.shu.BaseTest;
import com.shu.lda.service.LDA;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by pc on 2017/5/15.
 */
@Slf4j
public class LDATest extends BaseTest{
    static LDA ldaModel = null;

    @Test
    public void ldaTest(){
        ldaModel = new LDA();
        ldaModel.run();
    }

}
