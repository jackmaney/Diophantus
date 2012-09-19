package com.jackmaney.factorization.integer;

import java.util.Vector;

public class AllFactors {
	
	
	public static Vector<Integer> find (int n)
	{
		if(n<0)
		{
			return find(-n);
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
}
