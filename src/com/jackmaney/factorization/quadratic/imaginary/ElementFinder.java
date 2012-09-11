package com.jackmaney.factorization.quadratic.imaginary;

import java.util.Vector;

import com.jackmaney.factorization.integer.NegativeSquareFreeInteger;

public class ElementFinder {
	
	public static Vector<Element> hasNorm(int norm,NegativeSquareFreeInteger d)
	{
		if(norm <= 1)
		{
			throw new IllegalArgumentException();
		}
		
		Vector<Element> result = new Vector<>();
		
		//Now, we need to find all solutions (if there are any) to a^2 + d * b^2 = norm.
		
		
		
		return null;
	}
}
