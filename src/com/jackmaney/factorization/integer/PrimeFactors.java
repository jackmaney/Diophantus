package com.jackmaney.factorization.integer;

import java.util.Arrays;
import java.util.Vector;

import com.jackmaney.factorization.FactorPair;
import com.jackmaney.factorization.Factorization;
import com.jackmaney.factorization.MInteger;

public class PrimeFactors {
	
	public static Factorization<MInteger> find(MInteger n)
	{
		return find(n.intValue());
	}

	public static Factorization<MInteger> find(int n)
	{
		if(n<0)
		{
			return find(-n);
		}
		
		if(n<=1)
		{
			throw new IllegalArgumentException("Why are you trying to find the prime factors of zero or 1?!");
		}
		
		Vector<FactorPair<MInteger>> result = new Vector<>();
		
		if(n<=3)
		{
			result.addElement(new FactorPair<MInteger>(new MInteger(n)));
		}
		else
		{
			
			Vector<Integer> primes = new Vector<>();
			
			
			while(n>1)
			{
				int floorSr = (int)(Math.sqrt((double)n));
				
				boolean nIsPrime = true;
				
				for(int i=2;i<=floorSr;i++)
				{
					if(n % i == 0)
					{
						primes.add(i);
						n/=i;
						nIsPrime=false;
						break;
					}
					
				}
				
				if(nIsPrime)
				{
					primes.add(n);
					n=1;
				}
			}
			
			
			Integer[] primeArr = (Integer[])(primes.toArray(new Integer[primes.size()]));
			Arrays.sort(primeArr);
			
			boolean first = true;
			int currentPrime = 0;
			
			for (Integer integer : primeArr) {
				
				if(first)
				{
					first=false;
					currentPrime = integer;
					result.add(new FactorPair<MInteger>(new MInteger(integer)));
				}
				else if(currentPrime == integer)
				{
					int e=result.lastElement().getExponent();
					e++;
					result.lastElement().setExponent(e);
				}
				else
				{
					currentPrime = integer;
					result.add(new FactorPair<MInteger>(new MInteger(integer)));
				}
			}
			
			
		}
		
		
		
		return new Factorization<MInteger>(result);
	}

}
