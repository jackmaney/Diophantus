package com.jackmaney.factorization;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Vector;

/**
 * Given an object <code>o</code> of type <code>T</code>, this class represents a 
 * factorization of <code>o</code> into a product of <code>Power<T></code> objects
 * (each having a distinct <code>base</code>).
 * 
 * @author Jack Maney
 *
 * @param <T>	A type of object that implements <code>Multiplicative<T></code> and <code>Comparable<T></code>
 */
public class Factorization<T extends Multiplicative<T> & Comparable<T>>{

	/**
	 * Conceptually, a factorization can be represented as a list of factors,
	 * or more concisely, as a <code>Vector</code> of <code>Power<T></code> elements.
	 */
	private Vector<Power<T>> factorization;
	
	private Vector<Power<T>> consolidate(Vector<Power<T>> v){
		
		Vector<Power<T>> result = new Vector<>();
		
		Hashtable<T, Integer> hash = new Hashtable<>();
		
		for (Power<T> factorPair : v) 
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
			result.add(new Power<T>(b,hash.get(b)));
		}
		
		return result;
	}
	
	private Vector<Power<T>> consolidateArray (T[] arr) {
		
		Vector<Power<T>> argsToPass = new Vector<>();
		
		for (T t : arr) {
			argsToPass.addElement(new Power<T>(t));	
		}
		
		return consolidate(argsToPass);
	}
	
	/**
	 * <p>This constructor reshapes the argument to consolidate powers of any <code>Power<T></code> 
	 * objects that have the same base.</p> 
	 * 
	 * <p>As a simple example using integers, if <code>3^4</code>
	 * (ie 3 to the fourth power) and <code>3^3</code> were each power elements, then 
	 * they'd be combined into <code>3^7</code> in our factorization.</p>  
	 * 
	 * 
	 * @param f		A <code>Vector</code> of <code>Power<T></code> objects, conceptually representing the factorization.
	 */
	public Factorization(Vector<Power<T>> f)
	{	
		this.factorization = consolidate(f);
	}
	
	/**
	 * A similar constructor for an array of <code>T</code> values. 
	 * In this case, the exponents for the <code>Power</code> objects are all considered to be 1.
	 * 
	 * @param arr
	 */
	public Factorization(T[] arr) {
		this.factorization = consolidateArray(arr);
	}
	
	
	
	/**
	 * This method multiplies--via the <code>multiply()</code> method provided 
	 * by <code>Multiplicative<T></code>--the objects provided by the
	 * <code>product()</code> method of <code>Power<T></code>.
	 * 
	 * 
	 * @return <T>
	 */
	public T product()
	{
		T result = factorization.elementAt(0).product();
		
		for (int i=1;i<factorization.size();i++)
		{
			result = result.multiply(factorization.elementAt(i).product());
		}
		
		return result;
	}
	
	/**
	 * This method joins the strings provided by the <code>toString()</code> method of 
	 * <code>Power<T></code> via " * ".
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		
		StringBuffer result = new StringBuffer();
		
		Vector<Power<T>> v = factorization;
		Collections.sort(v,new Power<T>());
		
		
		result.append(v.elementAt(0).toString());
		
		for(int i=1;i<v.size();i++)
		{
			result.append("*");
			result.append(v.elementAt(i).toString());
		}
		
		
		return result.toString();
	}
	
	
	public Vector<Power<T>> getFactorization() {
		return factorization;
	}

}
