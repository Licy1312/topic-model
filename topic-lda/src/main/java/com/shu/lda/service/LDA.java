package com.shu.lda.service;

/**
 * 程序的主要入口
 * @author Li Changya
 * 
 */
public class LDA {

	public void run() {
		System.out.println("-----开始建立模型-----");
		LDAOption option = new LDAOption();
		//对LDA运行时的一些参数进行赋值
		option.dir = "D:/data/topic/model";
//		option.dir = this.getClass().getResource("/model").getPath();
		option.dfile = "doc.dat";
		option.est = true;  
		option.inf = false;
		option.modelName = "model-final";
		
		Estimator estimator = new Estimator();
		estimator.init(option);
		estimator.estimate();
		System.out.println("-----建立模型结束-----");
	}

//	public static void main(String[] args) {
//		new LDA().run();
//	}
//	
}
