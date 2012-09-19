package com.jackmaney.factorization.quadratic.imaginary;

import java.util.Vector;

import com.jackmaney.factorization.integer.NegativeSquareFreeInteger;

public class ElementFinder {

	/**
	 * Given a positive integer <code>n</code> and a {@link NegativeSquareFreeInteger} <code>d</code>,
	 * this method returns a vector of the elements of the form <code>a + b * sqrt(d)</code> where
	 * {@link Element}.<code>norm(a,b,d) == n</code> and <code>a >= 0</code>, <em>if</em> any such elements exist.
	 * Otherwise, <code>null</code> is returned.
	 * 
	 * @param n
	 * @param d
	 * @return {@link Vector}<{@link Element}>
	 */
	public static Vector<Element> elementsOfNorm(int n,NegativeSquareFreeInteger d)
	{
		if(n<0)
		{
			throw new IllegalArgumentException();
		}
		
		Vector<Element> result = new Vector<>();
		
		if(n==0)
		{
			result.add(new Element(0, 0, d));
			return result;
		}
		
		if(n==1)
		{
			result.add(new Element(1,0,d));
			return result;
		}
		
		/*
		 * Now, at this point, we're trying to find integer solutions to the equation
		 * 
		 * a^2 + d * b^2 = n
		 * 
		 * with n > 1.
		 * 
		 * Note that we really only need to find positive integer solutions, 
		 * since the norms of a + b * sqrt(d), a - b * sqrt(d), - a + b * sqrt(d), 
		 * and -a - b * sqrt(d) are all the same. 
		 * 
		 * However, since we don't care about unit multiples (ie multiples of plus or minus 1), 
		 * we'll only return conjugate pairs of solutions having a>0.
		 * 
		 * So, we proceed by starting with (a,b) pairs of (0,0), (0,1), (0,2), etc, stopping when 
		 * the norm is too big. Then we start over with (1,0), (1,1), (1,2), etc, again, stopping
		 * when the norm is too big. 
		 * 
		 * In particular:
		 * 
		 * 1. Start with a = b = 0.
		 * 2. Check to see if a^2 = n.
		 * 3. If so, add a + 0 * sqrt(d) on to result and we're done (since we increment b before a).
		 * 4. If not, increment b until the norm is at least as big as n.
		 * 5. If the norm equals n, tack on a + b * sqrt(d) and its conjugate to our list of results.
		 * 6. Reset b to 0, increment a, and go back to step 2.
		 * 
		 * 
		 */
		
		int a = 0;
		int b = 0;
		
		Integer N = n;
		
		
		while(a * a <= n)
		{
			if(a * a == n)
			{
				result.add(new Element(a,0,d));
				break;
			}
			
			while(Element.norm(a,b,d)<n)
			{
				b++;
			}
			
			Integer Norm = Element.norm(a,b,d);
			
			if(Norm.equals(N))
			{
				result.add(new Element(a,b,d));
				result.add(new Element(a,-b,d));
			}
			
			b=0;
			a++;
		}
		
		return result.size() > 0 ? result : null;
	}
	
	/**
	 * An alternative way to call the <code>elementsOfNorm()</code> method
	 * without having to explicitly wrap </code>d</code> into a {@link NegativeSquareFreeInteger}.
	 * 
	 * @param n
	 * @param d
	 * @return {@link Vector}<{@link Element}>
	 */
	public static Vector<Element> elementsOfNorm(int n,int d)
	{
		return elementsOfNorm(n, new NegativeSquareFreeInteger(d));
	}

}
