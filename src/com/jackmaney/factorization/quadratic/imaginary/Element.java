package com.jackmaney.factorization.quadratic.imaginary;

import java.util.Comparator;

import com.jackmaney.factorization.Multiplicative;
import com.jackmaney.factorization.integer.NegativeSquareFreeInteger;

public class Element implements Multiplicative<Element>,Comparator<Element>,Comparable<Element>{
	
	private int a,b;
	private NegativeSquareFreeInteger d;
	
	public Element(int a,int b,int d)
	{
		this.a = a;
		this.b = b;
		this.d = new NegativeSquareFreeInteger(d);
	}
	
	public Element(int a,int b,NegativeSquareFreeInteger d)
	{
		this(a,b,d.intValue());
	}

	/**
	 * Two <code>Element</code> objects are equal precisely when each of the respective real and imaginary parts are equal.
	 * 
	 * @param other
	 * @return {@link Boolean}
	 */
	public boolean equals(Element other) {

		return getA()==other.getA() && getB() == other.getB() && getD() == other.getD();
	}
	
	/**
	 * Returns the stringified version of this {@link Element} object. For example, 
	 * <code>
	 * Element e = new Element(2,3,-6);
	 * System.out.println(e.toString());
	 * </code>
	 * 
	 * results in <code>2 + 3 * sqrt(-6)</code> being printed out.
	 * 
	 * @return {@link String}
	 */
	@Override
	public String toString() {
		return getA() + " + " + getB() + " * sqrt(" + getD() + ")";
	}
	
	
	public static String toString(Element e)
	{
		return e.getA() + " + " + e.getB() + " * sqrt(" + e.getD() + ")";
	}
	
	public boolean isInt()
	{
		return getB() == 0;
	}
	
	public static boolean isInt(Element e)
	{
		return e.getB() == 0;
	}
	
	public Element multiply(Element other)
	{
		if(getD()!=other.getD())
		{
			throw new IllegalArgumentException("Mismatching values of d: " + getD() + " vs " + other.getD());
		}
		int a1=getA();
		int b1=getB();
		int d = getD();
		int a2=other.getA();
		int b2=other.getB();
		
		return new Element(a1 * a2 + d * b1 * b2,a1 * b2 + a2 * b1,d);
	}
	
	public Element add(Element other)
	{
		if(getD()!=other.getD())
		{
			throw new IllegalArgumentException("Mismatching values of d: " + getD() + " vs " + other.getD());
		}
		
		return new Element(getA() + other.getA(),getB() + other.getB(),getD());
	}
	
	public int norm()
	{
		return getA() * getA() - getD() * getB() * getB();
	}
	
	
	public static int norm(Element e)
	{
		return e.norm();
	}
	
	public static int norm(int a,int b,NegativeSquareFreeInteger d)
	{
		return a * a - d.intValue() * b * b;
	}
	
	
	private boolean isInt(double d)
	{
		return d == (int)d;
	}
	
	/**
	 * If <code>this</code> divides <code>other</code> as an element of Z[sqrt(d)], 
	 * then this method returns the divisor (ie the element z of Z[sqrt(d)] such that
	 * <code>this.multiply(z).equals(other)</code>.
	 * 
	 * If <code>this</code> does not divide <code>other</code>, then <code>null</code> is returned.
	 * 
	 * @param other
	 * @return <code>Element</code> (or <code>null</code>)
	 */
	public Element divides (Element other)
	{
		if(getD()!=other.getD())
		{
			throw new IllegalArgumentException("Mismatching values of d: " + getD() + " vs " + other.getD());
		}
		
		double intRatioNorms = norm(other)/norm();
		if(norm() * intRatioNorms != norm(other))
		{
			return null; //If norm(this) doesn't divide norm(other), then we don't have a prayer.
		}
		
		/**
		 * (a1f + b1f sqrt(d))(x + y sqrt(d)) = (a2f + b2f sqrt(d))
		 * 
		 * a1f x + d y b1f = a2f
		 * a1f y + b1f x     = b2f
		 * 
		 * y = (b2f - b1f x)/a1f
		 * 
		 * a1f x + d b1f (b2f - b1f x)/a1f = a2f
		 * a1f^2 x + d b1f b2f - d b1f^2 x = a2f a1f
		 * 
		 * x(a1f^2 - d b1f^2) = a2f a1f - d b1f b2f
		 * 
		 * x=(a2f a1f - d b1f b2f)/(a1f^2 - d b1f^2)
		 * 
		 * y=(b2f - b1f x)/a1f = (1/a1f) (b2f - b1f [(a2f a1f - d b1f b2f)/(a1f^2 - d b1f^2)])
		 * =(1/a1f) [b2f (a1f^2 - d^2 b1f^2) - a1f a2f b1f + d b1f^2 b2f]/(a1f^2 - d b1f^2)]
		 * =(1/a1f) [b2f a1f^2 - d b1f^2 b2f - a1f a2f b1f + d b1f^2 b2f]/(a1f^2 - d b1f^2)
		 * =(1/a1f) (b2f a1f^2 - a1f a2f b1f)/(a1f^2 - d b1f^2)
		 * =(a1f b2f - a2f b1f)/(a1f^2 - d b1f^2)
		 */
		
		float a1f = (float)getA();
		float b1f = (float)getB();
		float df =  (float)getD();
		float a2f = (float)other.getA();
		float b2f = (float)other.getB();
		
		double yf = (a1f * b2f - a2f * b1f)/(a1f * a1f - df * b1f * b1f);
		double xf=(a2f * a1f - df *  b1f * b2f)/(a1f * a1f - df *  b1f * b1f);
		
		if(!(isInt(yf) && isInt(xf)))
		{
			return null;
		}
		
		return new Element((int)xf,(int)yf,(int)df);

	}
	
	@Override
	public int compare(Element o1, Element o2) {
		
		if(o1.getD()!=o2.getD())
		{
			throw new IllegalArgumentException();
		}
		
		if(o1.getA() < o2.getA())
		{
			return -1;
		}
		else if(o1.getA() > o2.getA())
		{
			return 1;
		}
		else
		{
			if(o1.getB() == o2.getB())
			{
				return 0;
			}
			else
			{
				return o1.getB() < o2.getB() ? -1 : 1;
			}
		}
	}

	
	@Override
	public int compareTo(Element o) {
		return compare(this,o);
	}

	
	public int getA() {
		return a;
	}




	public void setA(int a) {
		this.a = a;
	}




	public int getB() {
		return b;
	}




	public void setB(int b) {
		this.b = b;
	}




	public int getD() {
		return d.intValue();
	}



	public void setD(int d) {
		this.d = new NegativeSquareFreeInteger(d);
	}
	
	public NegativeSquareFreeInteger getRawD()
	{
		return d;
	}

	public void setRawD(NegativeSquareFreeInteger d)
	{
		this.d = d;
	}



	
}
