package com.shu.lda.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LDAPredictor {

	private Inferencer inferencer;

	//输入模型文件地址初始化
	public LDAPredictor(String dir, String modelName) {
		LDAOption option = new LDAOption();
		
		option.dir = dir;
		option.modelName = modelName;
		option.inf = true;
		inferencer = new Inferencer();
		inferencer.init(option);
	}
	
	//推断新数据
	public Model inference(String data){
		String [] docs = new String[1];
		docs[0] = data;
		return inferencer.inference(docs);
	}

	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		LDAPredictor predictor = new LDAPredictor("d:/lda/model", "model-final");
		
		String input = "长三角 经济 自驾游 指数 经济带 旅游";
		Model model = predictor.inference(input);
		double [] dist = model.theta[0];
		for (double d : dist) {
			System.out.print(d + " ");
		}
		
	}	
	
}
