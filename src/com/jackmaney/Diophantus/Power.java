package com.jackmaney.Diophantus;

import java.util.Comparator;
import java.util.regex.Pattern;

/**
 * 
 * @author Jack Maney
 * 
 * <p>Given an object </code>o</code> of type </code>T</code> that implements the <code>Multiplicative</code> interface, 
 * this class represents a (positive integer) power of <code>o</code>.</p>
 * 
 * <p>In order for sorting within <code>Factorization</code> objects, it's also necessary
 * for T to implement <code>Comparable<T></code>.</p>
 *
 * @param <T>
 */
public class Power<T extends Multiplicative<T> & Comparable<T>> 
	implements Comparator<Power<T>>
{

	/**
	 * An element of type <code>T</code>, representing the base.
	 */
	private T base;
	
	/**
	 * A non-negative integer, representing the exponent.
	 */
	private int exponent;
	
	/**
	 * Constructor for a <code>Power<T></code> object, requiring an object
	 * <code>t</code> of type <code>T</code> and an exponent <code>e</code>.
	 * @param t
	 * @param e
	 */
	public Power(T t,int e)
	{
		setBase(t);
		setExponent(e);
	}
	
	/**
	 * If no exponent is provided to the constructor, then a default exponent of 1 is used.
	 * @param t
	 */
	public Power(T t)
	{
		this(t,1);
	}
	
	/**
	 * And we have the void constructor. This is used 
	 */
	public Power() {}
	
	/**
	 * Takes the result of the <code>toString()</code> method of our <code>base</code>, 
	 * tacks on a "^", and then tacks on the <code>exponent</code>. 
	 * The base is wrapped around parentheses if <code>base instanceof {@link MInteger}</code>.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		
		StringBuffer result = new StringBuffer();
		
		if(Pattern.matches("^[1-9]\\d*$", getBase().toString()) )
		{
			result.append(getBase().toString());
		}
		else
		{
			result.append("(").append(getBase().toString()).append(")");
		}
		
		if(getExponent() > 1){
			result.append("^").append(getExponent());
		}
		
		
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
	
	/**
	 * Uses the <code>multiply()</code> method provided by objects of type <code>T</code> to 
	 * multiply <code>base</code> times itself a total of <code>exponent - 1</code> times.
	 * 
	 * @return T
	 */
	public T product()
	{
		return getBase().pow(exponent);
	}

	/**
	 * Comparison of two <code>Power<T></code> objects is done lexicographically 
	 * by first comparing the bases (via <code>Comparable<T></code>), and then
	 * comparing the exponents. 
	 * 
	 * 
	 * @param o1	The first <code>Power<T></code> object to be compared.
	 * @param o2	The second <code>Power<T></code> object to be compared.
	 * 
	 * @return int
	 */
	@Override
	public int compare(Power<T> o1, Power<T> o2) {
		T base1 = o1.getBase();
		T base2 = o2.getBase();
		int exp1 = o1.getExponent();
		int exp2 = o2.getExponent();
		
		if(base1.compareTo(base2) != 0){
			return base1.compareTo(base2);
		}
		else if(exp1 == exp2){
			return 0;
		}
		else{
			return exp1 <= exp2 ? -1 : 1;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((base == null) ? 0 : base.hashCode());
		result = prime * result + exponent;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Power<?> other = (Power<?>) obj;
		if (base == null) {
			if (other.getBase() != null)
				return false;
		} else if (!base.equals(other.base))
			return false;
		if (exponent != other.exponent)
			return false;
		return true;
	}
	

	
	
}
