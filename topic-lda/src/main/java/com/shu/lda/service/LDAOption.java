package com.shu.lda.service;

import com.shu.lda.utils.LDAParameterConfigure;

import java.io.Serializable;

/**
 * 对LDA算法中的各种参数进行初始化设置
 * @author lichangya
 * 
 */

public class LDAOption implements Serializable {
	
	private static final long serialVersionUID = 526166310343530738L;
	public static final long BUFFER_SIZE_LONG = 1000000;
	public static final short BUFFER_SIZE_SHORT = 512;
	
	public static final int MODEL_STATUS_UNKNOWN = 0;
	public static final int MODEL_STATUS_EST = 1;
	public static final int MODEL_STATUS_ESTC = 2;
	public static final int MODEL_STATUS_INF = 3;

	private LDAParameterConfigure ldaParameterConfig;
	
	//是否开启训练模式
	public boolean est = false; 
	
	//是否在先前已有的模型基础上继续用新的数据训练模型
	public boolean estc = false;  
	
	//是否使用先前已经训练好的模型进行推断
	public boolean inf = true;  
	
	//数据结果（模型数据）保存的位置
	public String dir = "";  
	
	//训练使用的原始数据文档
	public String dfile = "";  
	
	//选择一个迭代的模型结果进行判断
	public String modelName = "";  
	
	//主题的数量
	public int K = 20;

//	//超参数α的值（平滑系数）
//	public double alpha = ldaParameterConfig.getAlpha();
//
//	//超参数β的值
//	public double beta = ldaParameterConfig.getBeta();
//
//	//迭代的次数
//	public int niters = ldaParameterConfig.getIteration();
//
//	//每迭代200次在本地磁盘上进行保存一次
//	public int savestep =ldaParameterConfig.getSaveIterationStep();
//
//	//对每一个主题，选择前10个概率最大的词进行保存
//	public int twords = ldaParameterConfig.getTopicWords();
    //超参数α的值（平滑系数）
    public double alpha = 0.5;

	//超参数β的值
	public double beta = 0.02;

	//迭代的次数
	public int niters = 1000;

	//每迭代200次在本地磁盘上进行保存一次
	public int savestep = 200;

	//对每一个主题，选择前10个概率最大的词进行保存
	public int twords = 10;
	
	//是否包含输入的原始数据
	public boolean withrawdata = false;
	
	//保存所有的词及其出现的次数
	public String wordMapFileName = "wordmap.txt";  

	public static String chartSet = "utf-8";

	public LDAParameterConfigure getLdaParameterConfig() {
		return ldaParameterConfig;
	}

	public void setLdaParameterConfig(LDAParameterConfigure ldaParameterConfig) {
		this.ldaParameterConfig = ldaParameterConfig;
	}
}
