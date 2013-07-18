package com.jackmaney.Diophantus;

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
	
	public ExponentList concatenate(ExponentList e){
		Vector<Integer> v = getExponentList();
		v.addAll(e.getExponentList());
		
		return new ExponentList(v);
	}

	@Override
	public int compareTo(ExponentList o) {
		
		if(size() != o.size()){
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
	
	@Override
	public String toString() {
		return getExponentList().toString();
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((exponentList == null) ? 0 : exponentList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExponentList other = (ExponentList) obj;
		if (exponentList == null) {
			if (other.exponentList != null)
				return false;
		} else if (!exponentList.equals(other.exponentList))
			return false;
		return true;
	}
	
	public int firstNonzeroIndex(){
		int result = -1;
		
		for(int i = 0;i < size(); i++){
			if(getExponentList().get(i) > 0){
				result = i;
				break;
			}
		}
		
		return result;
	}
	
	public int size(){
		return getExponentList().size();
	}
}
