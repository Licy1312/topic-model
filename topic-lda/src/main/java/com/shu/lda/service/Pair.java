
package com.shu.lda.service;


public class Pair implements Comparable<Pair> {
	public Object first;
	@SuppressWarnings("rawtypes")
	public Comparable second;
	public static boolean naturalOrder = false;
	
	@SuppressWarnings("rawtypes")
	public Pair(Object k, Comparable v){
		first = k;
		second = v;		
	}
	
	@SuppressWarnings("rawtypes")
	public Pair(Object k, Comparable v, boolean naturalOrder){
		first = k;
		second = v;
		Pair.naturalOrder = naturalOrder; 
	}
	
	@SuppressWarnings("unchecked")
	public int compareTo(Pair p){
		if (naturalOrder)
			return this.second.compareTo(p.second);
		else return -this.second.compareTo(p.second);
	}
}

