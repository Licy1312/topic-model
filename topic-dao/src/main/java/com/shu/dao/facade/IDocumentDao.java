package com.shu.dao.facade;

import com.shu.dao.entity.DocumentDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description：操作数据库中的Document的所有接口，方法
 * User:Lichangya
 * Date:2017/3/25.
 */
public interface IDocumentDao {
    /**
     * 通过id获取文档
     * @param id
     * @return
     */
    DocumentDO getDocumentById(int id);
    /**
     * 通过path获取文档
     * @param path
     * @return
     */
    DocumentDO getDocumentByPath(String path);

    /**
     * 更新文档
     * @param documentDO
     */
    void updateDocument(DocumentDO documentDO);
    /**
     * 导入文档
     * @param documentDO
     */
    void inputDocument(@Param("documentDO")DocumentDO documentDO);
    /**
     * 获取所有文档内容
     * @return
     */

    List<DocumentDO> getContent();
}