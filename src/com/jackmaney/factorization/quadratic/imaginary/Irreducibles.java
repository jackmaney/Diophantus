package com.jackmaney.factorization.quadratic.imaginary;

import java.util.Vector;

import com.jackmaney.factorization.integer.AllFactors;

public class Irreducibles {
	
	public static Vector<Element> find(Element e)
	{
		int norm = e.norm();
		Vector<Integer> normFactors = AllFactors.find(norm);
		
		Vector<Element> result = new Vector<Element>();
		
		//If the norm is prime, then e is irreducible automatically!
		if(normFactors.size() == 0) 
		{
			result.add(e);
		}
		else //otherwise, waltz through the (integer) factors of norm...
		{
			for (Integer factor: normFactors) {
				
			}
		}
		
		
		
		return result;
	}
}
