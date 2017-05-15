package com.shu.lda.service;

import java.io.Serializable;

/**
 * 
 * @author Li Changya
 *
 */

public class Inferencer implements Serializable{	

	private static final long serialVersionUID = 466563090503055129L;
	
	
	public Model trnModel;
	public Dictionary globalDict;
	private LDAOption option;
	
	private Model newModel;
	public int niters = 100;
	

	public boolean init(LDAOption option){
		this.option = option;
		trnModel = new Model();
		
		if (!trnModel.initEstimatedModel(option))
			return false;		
		
		globalDict = trnModel.data.localDict;
		computeTrnTheta();
		computeTrnPhi();
		
		return true;
	}
	
	//从一个规定的数据集推测出一个新的模型
	public Model inference(LDADataset newData){
		
		Model newModel = new Model();
		
		newModel.initNewModel(option, newData, trnModel);		
		this.newModel = newModel;		
		
		//利用吉普斯迭代采样进行推测		
		for (newModel.liter = 1; newModel.liter <= niters; newModel.liter++){
			for (int m = 0; m < newModel.M; ++m){
				for (int n = 0; n < newModel.data.docs[m].length; n++){
					
					int topic = infSampling(m, n);
					newModel.z[m].set(n, topic);
				}
			}
			
		}
		computeNewTheta();
		computeNewPhi();
		newModel.liter--;
		return this.newModel;
	}
	
	public Model inference(String [] strs){
		//读取数据集
		LDADataset dataset = LDADataset.readDataSet(strs, globalDict);
		return inference(dataset);
	}
	
	//根据特定参数获取数据集，从而推测新的模型
	public Model inference(){	
	
		
		newModel = new Model();
		if (!newModel.initNewModel(option, trnModel)) return null;
		
		//吉普斯迭代采样
		for (newModel.liter = 1; newModel.liter <= niters; newModel.liter++){
			for (int m = 0; m < newModel.M; ++m){
				for (int n = 0; n < newModel.data.docs[m].length; n++){
					
					int topic = infSampling(m, n);
					newModel.z[m].set(n, topic);
				}
			}
		}
		
		computeNewTheta();
		computeNewPhi();
		newModel.liter--;
		newModel.saveModel(newModel.dfile + "." + newModel.modelName);		
		
		return newModel;
	}
	
	/*
	 * 为推测进行采样
	 * m: 文档数量
	 * n: 词语数量
	 */
	protected int infSampling(int m, int n){
		
		int topic = newModel.z[m].get(n);
		int _w = newModel.data.docs[m].words[n];
		int w = newModel.data.lid2gid.get(_w);
		newModel.nw[_w][topic] -= 1;
		newModel.nd[m][topic] -= 1;
		newModel.nwsum[topic] -= 1;
		newModel.ndsum[m] -= 1;
		
		double Vbeta = trnModel.V * newModel.beta;
		double Kalpha = trnModel.K * newModel.alpha;
			
		for (int k = 0; k < newModel.K; k++){			
			newModel.p[k] = (trnModel.nw[w][k] + newModel.nw[_w][k] + newModel.beta)/(trnModel.nwsum[k] +  newModel.nwsum[k] + Vbeta) *
					(newModel.nd[m][k] + newModel.alpha)/(newModel.ndsum[m] + Kalpha);
		}
		
		
		for (int k = 1; k < newModel.K; k++){
			newModel.p[k] += newModel.p[k - 1];
		}
		
		
		double u = Math.random() * newModel.p[newModel.K - 1];
		for (topic = 0; topic < newModel.K; topic++){
			if (newModel.p[topic] > u)
				break;
		}
		

		newModel.nw[_w][topic] += 1;
		newModel.nd[m][topic] += 1;
		newModel.nwsum[topic] += 1;
		newModel.ndsum[m] += 1;
		
		return topic;
	}
	
	/*
	 * 计算新的文档-主题
	 */
	protected void computeNewTheta(){
		//每一篇文档
		for (int m = 0; m < newModel.M; m++){
			//文档中每一个主题
			for (int k = 0; k < newModel.K; k++){
				newModel.theta[m][k] = (newModel.nd[m][k] + newModel.alpha) / (newModel.ndsum[m] + newModel.K * newModel.alpha);
			}
		}
	}
	/*
	 * 计算新的主题-词语
	 */
	protected void computeNewPhi(){
		//每一个主题
		for (int k = 0; k < newModel.K; k++){
			//每一个主题下的所有的词语
			for (int _w = 0; _w < newModel.V; _w++){
				Integer id = newModel.data.lid2gid.get(_w);
				
				if (id != null){
					newModel.phi[k][_w] = (trnModel.nw[id][k] + newModel.nw[_w][k] + newModel.beta) / (newModel.nwsum[k] + newModel.nwsum[k] + trnModel.V * newModel.beta);
				}
			}
		}
	}
	
	protected void computeTrnTheta(){
		for (int m = 0; m < trnModel.M; m++){
			for (int k = 0; k < trnModel.K; k++){
				trnModel.theta[m][k] = (trnModel.nd[m][k] + trnModel.alpha) / (trnModel.ndsum[m] + trnModel.K * trnModel.alpha);
			}
		}
	}
	
	protected void computeTrnPhi(){
		for (int k = 0; k < trnModel.K; k++){
			for (int w = 0; w < trnModel.V; w++){
				trnModel.phi[k][w] = (trnModel.nw[w][k] + trnModel.beta) / (trnModel.nwsum[k] + trnModel.V * trnModel.beta);
			}
		}
	}

}


