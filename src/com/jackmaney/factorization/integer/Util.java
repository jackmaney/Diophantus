package com.jackmaney.factorization.integer;

import java.util.Arrays;
import java.util.Vector;

import com.jackmaney.factorization.Power;
import com.jackmaney.factorization.Factorization;
import com.jackmaney.factorization.MInteger;

public class Util {
	
	public static boolean intDivides(int a,int b){
		double da = (double)a;
		double db = (double)b;
		double q = db / da;
		
		return q == (int)q;
	}
	
	public static Vector<Integer> extractMultiples(Vector<Integer> numbers, int divisor)
	{
		Vector<Integer> result = new Vector<>();
		
		if(divisor == 0)
		{
			throw new IllegalArgumentException();
		}
		
		for (Integer number : numbers) {
			double q = (double)number / (double)divisor;
			
			if(q == (int)q){
				result.add(number);
			}
		}
		
		return result;
	}
	
	public static Vector<Integer> findAllFactors (int n)
	{
		if(n<0)
		{
			return findAllFactors(-n);
		}
		else if(n<=1)
		{
			throw new IllegalArgumentException();
		}
		
		Vector<Integer> result = new Vector<>();
		
		for(int i=2;i<n;i++)
		{
			if(n % i ==0)
			{
				result.add(i);
			}
		}
		
		return result;
	}

	public static Factorization<MInteger> getPrimeFactorization(int n)
	{
		if(n<0)
		{
			return getPrimeFactorization(-n);
		}
		
		if(n<=1)
		{
			throw new IllegalArgumentException("Why are you trying to find the prime factors of zero or 1?!");
		}
		
		Vector<Power<MInteger>> result = new Vector<>();
		
		if(n<=3)
		{
			result.addElement(new Power<MInteger>(new MInteger(n)));
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
					result.add(new Power<MInteger>(new MInteger(integer)));
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
					result.add(new Power<MInteger>(new MInteger(integer)));
				}
			}
			
			
		}
		
		
		
		return new Factorization<MInteger>(result);
	}


}
