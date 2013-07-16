package com.jackmaney.factorization;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

public class ExponentListIterator implements Iterator<ExponentList>{

	private ExponentList max = null;
	private ExponentList current = null;
	private boolean reachedMax = false;
	
	public ExponentListIterator(ExponentList m){
		this.max = m;
		
		Vector<Integer> zero = new Vector<>();
		
		for(int i = 0; i < max.getExponentList().size(); i++){
			zero.add(0);
		}
		
		this.current = new ExponentList(zero);
	}
	
	@Override
	public boolean hasNext() {
		
		if(reachedMax || current.compareTo(max) > 0){
			return false;
		}
		
		return true;
	}

	@Override
	public ExponentList next() {
		
		int comparison = current.compareTo(max);

		
		if(comparison > 0){
			throw new NoSuchElementException();
		}
		else if(comparison == 0){
			reachedMax = true;
		}
		else{
			Vector<Integer> v = current.getExponentList();
			
			for(int i = 0; i < v.size(); i++){
				if(v.get(i) < max.getExponentList().get(i)){
					v.set(i, v.get(i) + 1);
					break;
				}
			}
			
			this.current = new ExponentList(v);
			return current;
		}
		
		return null;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
