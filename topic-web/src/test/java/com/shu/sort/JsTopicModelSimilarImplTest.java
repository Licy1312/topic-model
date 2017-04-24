package com.shu.sort;

import com.shu.BaseTest;
import com.shu.sort.impl.JsTopicModelSimilarImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description：js公式测试类
 * User:Lichangya
 * Date:2017/4/23.
 */
@Slf4j
public class JsTopicModelSimilarImplTest extends BaseTest {
    @Autowired
    private JsTopicModelSimilarImpl jsTopicModelSimilar;

    @Test
    public void testTopicDistribution(){
        double result[] =jsTopicModelSimilar.gainDocSimilarValue();
    }
}
