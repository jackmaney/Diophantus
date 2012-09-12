package com.jackmaney.factorization.integer;

import com.jackmaney.factorization.Power;
import com.jackmaney.factorization.Factorization;
import com.jackmaney.factorization.MInteger;


public class NegativeSquareFreeInteger {
	
	private final int value;
	
	public NegativeSquareFreeInteger(int value)
	{
		if(isSquareFree(value))
		{
			this.value=value;
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	
	private boolean isSquareFree(int d)
	{
		if(d<0)
		{
			return isSquareFree(-d);
		}
		else if(d<=1)
		{
			throw new IllegalArgumentException();
		}
			
		
		
		Factorization<MInteger> primes = PrimeFactors.find(new MInteger(d));
		
		for (Power<MInteger> factorPair : primes.getFactorization()) {
			if(factorPair.getExponent()>1)
			{
				return false;
			}
		}
		
		return true;
	}
	
	
	public int getValue() {
		return value;
	}
	
}
