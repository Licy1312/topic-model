package com.shu.dao.facade;

import com.shu.dao.entity.DocIndex;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：文档索引操作接口
 * User:Lichangya
 * Date:2017/4/24.
 */
public interface IDocIndexDao {

    void insertDocIndex(@Param("bean") DocIndex bean);

    ArrayList<String> searchDocIndex();

    void insertProbability(@Param("DocIndex")DocIndex docIndex);
}
