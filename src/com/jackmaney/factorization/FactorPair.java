package com.jackmaney.factorization;

import java.util.Comparator;

public class FactorPair<T extends Multiplicative<T> & Comparable<T>> 
	implements Comparator<FactorPair<T>>
{
	
	private T base;
	private int exponent;
	
	public FactorPair(T t,int e)
	{
		setBase(t);
		setExponent(e);
	}
	
	public FactorPair(T t)
	{
		this(t,1);
	}
	
	public FactorPair() {}
	
	@Override
	public String toString() {
		
		StringBuffer result = new StringBuffer();
		result.append(getBase().toString());
		result.append("^");
		result.append(getExponent());
		
		return result.toString();
	}
	
	public T getBase() {
		return base;
	}
	public void setBase(T base) {
		
		this.base = base;
	}
	public int getExponent() {
		return exponent;
	}
	public void setExponent(int exponent) {
		if(exponent>=0)
		{
			this.exponent = exponent;
		}
		else
		{
			throw new IllegalArgumentException("The exponent value of " + exponent + " must be nonnegative.");
		}
	}
	
	public T product()
	{
		T base = getBase();
		int exponent = getExponent();
		
		if(exponent>0)
		{
			T result = base;
			for(int i=2;i<=exponent;i++)
			{
				result = result.multiply(base);
			}
			return result;
		}
		
		return null;
	}


	
	public int prettyCompareTo(FactorPair<T> o)
	{
		T base1 = getBase();
		T base2 = o.getBase();
		int exp1 = getExponent();
		int exp2 = o.getExponent();
		
		if(base1.compareTo(base2)>0)
		{
			return 1;
		}
		if(base1.compareTo(base2)<0)
		{
			return -1;
		}
		else
		{
			return exp1 <= exp2 ? -1 : 1;
		}
	}

	@Override
	public int compare(FactorPair<T> o1, FactorPair<T> o2) {
		T base1 = o1.getBase();
		T base2 = o2.getBase();
		int exp1 = o1.getExponent();
		int exp2 = o2.getExponent();
		
		if(base1.compareTo(base2)>0)
		{
			return 1;
		}
		if(base1.compareTo(base2)<0)
		{
			return -1;
		}
		else
		{
			return exp1 <= exp2 ? -1 : 1;
		}
	}
	

	
	
}
