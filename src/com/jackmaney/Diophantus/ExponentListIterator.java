package com.jackmaney.Diophantus;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

public class ExponentListIterator implements Iterator<ExponentList>{

	private ExponentList max = null;
	private ExponentList current = null;
	private ExponentList zero = null;
	private boolean reachedMax = false;
	
	public ExponentListIterator(ExponentList m){
		
		for(Integer integer: m.getExponentList()){
			if(integer == 0){
				throw new IllegalArgumentException("Argument cannot have an ExponentList containing a zero");
			}
		}
		
		this.max = m;
		
		Vector<Integer> z = new Vector<>();
		
		for(int i = 0; i < max.size(); i++){
			z.add(0);
		}
		
		this.zero = new ExponentList(z);
		
		this.current = new ExponentList(z);
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
		
		
		int index = current.firstNonzeroIndex();
		
		//used for constructor in return statement
		Vector<Integer> v = current.getExponentList(); 
		
		if(index == -1){
			v = zero.getExponentList();
			v.set(v.size() - 1,1);
			
			//Really dumb edge case.
			if(current.size() == 1 && max.getExponentList().get(0) == 1){
				reachedMax = true;
			}
		}
		else if(current.size() == 1){ 
			//silly edge case...
			
			v.set(0,v.get(0) + 1);
			if(v.get(0) == max.getExponentList().get(0)){
				reachedMax = true;
			}
			
		}
		else{
			//We traverse the exponent list from the right, trying to find
			//the first place before index that has a non-maximal value
			//(if such a place exists).
			
			//Note that if index is all the way to the right, then 
			//we still have rightIndex == -1
			
			int rightIndex = -1;
			
			for(int i = v.size() - 1; i > index; i--){
				if(v.get(i) < max.getExponentList().get(i)){
					rightIndex = i;
					break;
				}
			}
			
			//If we found such a place after index to increment, then do so
			//and zero out everything to the right of rightIndex
			if(rightIndex > -1){
				v.set(rightIndex,v.get(rightIndex) + 1);
				
				for(int i = rightIndex + 1; i < v.size(); i++){
					v.set(i,0);
				}
			}
			else{
				//Otherwise, everything to the right of index is maxed out.
				//Can we increment at index? If so, do so and zero out 
				//everything to the right.
				if(v.get(index) < max.getExponentList().get(index)){
					v.set(index, v.get(index) + 1);
					
					for(int i = index + 1; i < v.size(); i++){
						v.set(i,0);
					}
				}
				else{
					//If we can't increment at index, then can we move
					//to the left?
					if(index > 0){
						//If so, put a 1 to the left and zero everything else out.
						v.set(index - 1,1);
						
						for(int i = index; i < v.size(); i++){
							v.set(i, 0);
						}
					}
					else{
						//If index == 0 and we can't increment at index,
						//then current.equals(max), and something went horribly wrong...
						
						throw new NoSuchElementException();
					}
				}
			}
		}
		
		ExponentList result = v == null ? null : new ExponentList(v);
		
		if(result.equals(max)){
			reachedMax = true;
		}
		
		return result;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
