package com.shu.lda.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

/**
 * 主要起着分类的作用
 * @author lichangya
 *
 */
public class LDAClassifier {

	public static void main(String args[]) throws IOException {
		//确定相应的模型和参数
		LDAOption option = new LDAOption();
		option.dir = "d:/lda/model";
		option.est = false; 
		option.estc = false;
		option.inf = true;
		option.modelName = "model-final";

		Inferencer inferencer = new Inferencer();
		inferencer.init(option);

		
		String car = "";
		BufferedReader br = new BufferedReader(new FileReader("d:/lda/doc.txt"));
		
		Map<String, Integer> groupMap = new HashMap<String, Integer>();
		int lineCount = 0;
		while((car = br.readLine()) != null){
			if(car.contains(":")){
				
				String [] newData = new String[1];
				newData[0] = car;
				
				Model newModel = inferencer.inference(newData);
				Map<String, Double> scoreMap = new HashMap<String, Double>();
				int count = 0;
				for (int j = 0; j < newModel.theta[0].length; j++) {
					scoreMap.put("Group" + count++, LDAUtils.baoliu(newModel.theta[0][j], 5));
				}

				List<Entry<String, Double>> list = new ArrayList<Entry<String, Double>>(scoreMap.entrySet());
				Collections.sort(list , SortMapByValueComparator);
				
				double pre = 0.0;

				List<Entry<String, Double>> sublist = new ArrayList<Entry<String, Double>>();
				for (Entry<String, Double> entry : list) {
					if(entry.getValue() != pre){
						sublist.add(entry);
						pre = entry.getValue();
					}else{
						break;
					}
				}
				
				for (int i = 0; i < sublist.size() - 1; i++) {  
					Entry<String, Double> s = sublist.get(i);
					System.out.print(s + " ");
					
					
					Integer isContains = groupMap.containsKey(s.getKey()) ? groupMap.get(s.getKey()) + 1 : 1;
					groupMap.put(s.getKey(), isContains);
				}

			
				System.out.println(lineCount);
				if(lineCount++ == 10000){
					break;
				}
			}
		}
		
		for (String string : groupMap.keySet()) {
			System.out.print(groupMap.get(string) + " ");
		}
		
		
		br.close();
		
	}
	
	
	public static Comparator<Entry<String, Double>> SortMapByValueComparator = new Comparator<Entry<String,Double>>() {
		public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
			if(o2.getValue() - o1.getValue() > 0){
				return 1;
			}else if(o2.getValue() - o1.getValue() < 0){
				return -1;
			}else {
				return 0;
			}
		}
	};
}
