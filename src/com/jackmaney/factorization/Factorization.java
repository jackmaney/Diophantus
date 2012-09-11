package com.jackmaney.factorization;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Vector;

public class Factorization<T extends Multiplicative<T> & Comparable<T>>{

	private Vector<FactorPair<T>> factorization;
	
	public Factorization(Vector<FactorPair<T>> f)
	{
		Vector<FactorPair<T>> result = new Vector<>();
		
		Hashtable<T, Integer> hash = new Hashtable<>();
		
		for (FactorPair<T> factorPair : f) 
		{
			
			if(factorPair.getExponent() == 0)
			{
				continue;
			}
			
			if(hash.containsKey(factorPair.getBase()))
			{
				hash.put(factorPair.getBase(), hash.get(factorPair.getBase()) + factorPair.getExponent());
			}
			else
			{
				hash.put(factorPair.getBase(),factorPair.getExponent());
			}
			
		}
		
		for (T b : hash.keySet()) {
			result.add(new FactorPair<T>(b,hash.get(b)));
		}
		
		this.factorization = result;
	}
	
	public T product()
	{
		T result = factorization.elementAt(0).product();
		
		for (int i=1;i<factorization.size();i++)
		{
			result = result.multiply(factorization.elementAt(i).product());
		}
		
		return result;
	}

	@Override
	public String toString() {
		
		StringBuffer result = new StringBuffer();
		
		Vector<FactorPair<T>> v = factorization;
		Collections.sort(v,new FactorPair<T>());
		
		
		result.append(v.elementAt(0).toString());
		
		for(int i=1;i<v.size();i++)
		{
			result.append("*");
			result.append(v.elementAt(i).toString());
		}
		
		
		return result.toString();
	}
	
	
	public Vector<FactorPair<T>> getFactorization() {
		return factorization;
	}

}
