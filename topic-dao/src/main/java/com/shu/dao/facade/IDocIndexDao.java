package com.shu.dao.facade;

import com.shu.dao.entity.DocIndex;
import org.apache.ibatis.annotations.Param;

/**
 * Description：文档索引操作接口
 * User:Lichangya
 * Date:2017/4/24.
 */
public interface IDocIndexDao {

    void insertDocIndex(@Param("bean") DocIndex bean);
}
