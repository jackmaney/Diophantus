package com.jackmaney.factorization.quadratic.imaginary;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import com.jackmaney.factorization.Multiplicative;
import com.jackmaney.factorization.Power;
import com.jackmaney.factorization.integer.AllFactors;
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
	
	private HashSet<Element> getAllFactors() {
		
		int d = getD();
		int n = norm();
		
		HashSet<Element> result = new HashSet<>();
		
		Vector<Integer> normFactors = AllFactors.find(n);
		
		for (Integer normFactor : normFactors) {
			
			Vector<Element> potentialDivisors = ElementFinder.elementsOfNorm(normFactor, d);
			
			for (Element element : potentialDivisors) {
				Element quotient = element.divides(this);
				if(quotient != null)
				{
					result.add(element);
					result.add(quotient);
				}
			}
		}
		
		return result;
	}
	
	private HashSet<Element> findAllIrreducibles() {
		
		int n = norm();
		int d = getD();
		
		if(n == 0 || n == 1){
			throw new IllegalArgumentException();
		}
		
		HashSet<Element> allFactors = getAllFactors();
		HashSet<Element> result = 
		
		Iterator<Element> it = allFactors.iterator();
		
		//TODO: There's a smarter way: store the structure using a tree where the nodes represent factors of the norm and each node also represents a list of elements having that norm.
		
		
		
		return null;
	}
	
	private Element peelOffIrreducible() {
		int d = getD();
		int n = norm();
		
		if(n == 1 || n == 0)
		{
			throw new IllegalArgumentException();
		}
		
		int smallestNormDivisor = 0;
		
		for(int i = 2; i < Math.floor(Math.sqrt(n)); i++) {
			if(n % i == 0){
				smallestNormDivisor = i;
				break;
			}
		}
		
		if(smallestNormDivisor == 0) { //norm is prime, we're done
			return this;
		}
		
		Vector<Element> possibleIrrs = ElementFinder.elementsOfNorm(smallestNormDivisor, d);
		
		if(possibleIrrs.size() == 0) {
			return this;
		}
		else {
			for (Element element : possibleIrrs) {
				Element quotient = element.divides(this);
				
				if(quotient != null) {
					if(element.getA() < 0){
						return new Element(-1 * element.getA(),-1 * element.getB(),d);
					}
					else{
						return element;
					}
				}
			}
		}
		return null;
	}
	
	
	public boolean isIrreducible()
	{
		int a = getA();
		int b = getB();
		int d = getD();
		int n = norm();
		
		Vector<Integer> normFactors = AllFactors.find(n);
		
		for (Integer normFactor : normFactors) {
			
			Vector<Element> potentialDivisors = ElementFinder.elementsOfNorm(normFactor, d);
			
			for (Element element : potentialDivisors) {
				
				Element quotient = element.divides(this);
				if(quotient != null){
					return false;
				}
			}
		}
		
		return true;
	}
	
	@Override
	public int compare(Element o1, Element o2) {
		
		if(o1.getD()!=o2.getD())
		{
			throw new IllegalArgumentException();
		}
		
		if(o1.norm() < o2.norm()){
			return -1;
		}
		else if(o1.norm() > o2.norm()){
			return 1;
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
