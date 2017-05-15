package com.shu.lda.facade;

/**
 * Description：模型管理接口
 * User:Lichangya
 * Date:2017/4/27.
 */
public interface IModelManager {

    /**
     * 创建模型
     * @param resourcePath 输入数据源的路径
     */
    void createModel(String resourcePath);

    /**
     * 模型的处理
     */
    void modelDeal();

}
