package com.jackmaney.factorization;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

public class ExponentList implements Comparable<ExponentList>
,Iterable<ExponentList>{
	
	private Vector<Integer> exponentList;
	
	public ExponentList(Vector<Integer> v){
		
		if(v.isEmpty()){
			throw new IllegalArgumentException("ExponentLists cannot be empty!");
		}
		
		for (Integer integer : v) {
			if(integer < 0){
				throw new IllegalArgumentException("ExponentLists cannot have negative entries!");
			}
		}
		
		exponentList = v;
	}
	
	public ExponentList(Integer[] a){
		this(new Vector<Integer>(Arrays.asList(a)));
	}
	
	public Vector<Integer> getExponentList(){
		return exponentList;
	}

	@Override
	public int compareTo(ExponentList o) {
		
		if(getExponentList().size() != o.getExponentList().size()){
			throw new IllegalArgumentException("Size mismatch in compareTo()");
		}
		
		for(int i = 0; i < getExponentList().size(); i++){
			if(getExponentList().get(i) != o.getExponentList().get(i)){
				return getExponentList().get(i).compareTo(o.getExponentList().get(i));
			}
		}
		
		return 0;
	}

	@Override
	public Iterator<ExponentList> iterator() {
		return new ExponentListIterator(this);
	}
}
