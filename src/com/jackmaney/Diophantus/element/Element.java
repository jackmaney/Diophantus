package com.jackmaney.Diophantus.element;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import com.jackmaney.Diophantus.ExponentList;
import com.jackmaney.Diophantus.Factorization;
import com.jackmaney.Diophantus.Multiplicative;
import com.jackmaney.Diophantus.integer.NegativeSquareFreeInteger;
import com.jackmaney.Diophantus.integer.Util;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + a;
		result = prime * result + b;
		result = prime * result + ((d == null) ? 0 : d.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Element)) {
			return false;
		}
		Element other = (Element) obj;
		if (a != other.a) {
			return false;
		}
		if (b != other.b) {
			return false;
		}
		if (d == null) {
			if (other.d != null) {
				return false;
			}
		} else if (!d.equals(other.d)) {
			return false;
		}
		return true;
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
		
		
		if(!Util.intDivides(norm(), norm(other)))
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
	
	public Vector<Element> getAllFactors() {
		
		int d = getD();
		int n = norm();
		
		Vector<Element> result = new Vector<>();
		
		Set<Element> resultSet = new HashSet<>();
		
		Vector<Integer> normFactors = Util.findAllFactors(n);
		
		for (Integer normFactor : normFactors) {
			
			Vector<Element> potentialDivisors = ElementFinder.elementsOfNorm(normFactor, d);
			
			for (Element element : potentialDivisors) {
				Element quotient = element.divides(this);
				if(quotient != null)
				{
					resultSet.add(element);
					resultSet.add(quotient);
				}
			}
		}
		
		for (Element element : resultSet) {
			result.add(element);
		}
		
		return result;
	}
	
	public Vector<Element> getAllIrreducibles() {
		
		int n = norm();
		
		if(n == 0 || n == 1){
			throw new IllegalArgumentException();
		}
		
		Vector<Element> result = new Vector<>(); 
		
		Vector<Element> allFactors = getAllFactors();
		
		//TODO: Replace this with a more sensible algorithm
		
		for(int i = 0; i < allFactors.size(); i++){
			
			Element alpha = allFactors.get(i);
			boolean isIrreducible = true;
			
			// Find out the hard way if anything else divides alpha...
			for(int j = 0; j < allFactors.size(); j++){
				
				if(i == j){
					continue;
				}
				
				Element beta = allFactors.get(j);
				
				if(beta.divides(alpha) != null){
					isIrreducible = false;
					break;
				}			
			}
			
			if(isIrreducible){
				result.add(alpha);
			}
		}
		
		return result;
	}
	
	/**
	 * This method returns the maximum power <code>n</code> 
	 * for which <code>e^n</code> divides <code>this</code>. 
	 * In other words, the maximum <code>n</code> such that 
	 * <code>power.divides(this) != null</code>, where 
	 * <code>power</code> is the <code>Element</code> obtained by 
	 * taking the <code>n</code>th power of <code>e</code>.
	 * 
	 * If <code>e</code> doesn't divide <code>this</code>, then 0 is returned.
	 * 
	 * @param e
	 * @return
	 */
	public int maxPowerDivisor(Element e){
		
		int result = 0;
		
		Element pow = e;
		
		while(pow.divides(this) != null){
			result++;
			pow = pow.multiply(e);
		}
		
		return result;
	}
	
	public Vector<Factorization<Element>> getIrreducibleFactorizations(){
		
		Vector<Element> irreducibles = getAllIrreducibles();
		
		Collections.sort(irreducibles);
		
		Vector<Integer> maxPowers = new Vector<>();
		
		for (Element irr : irreducibles) {
			maxPowers.add(maxPowerDivisor(irr));
		}
		
		Vector<Factorization<Element>> result = new Vector<>();
		
		ExponentList exponents = new ExponentList(maxPowers);
		
		Iterator<ExponentList> it = exponents.iterator();
		
		while(it.hasNext()){
			
			ExponentList exp = it.next();
			System.out.println(exp);
			Factorization<Element> factorization = 
					new Factorization<>(irreducibles, exp);
			
			if(factorization.product().equals(this)){
				result.add(factorization);
			}
		}
		
		return result;
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

	@Override
	public Element pow(int n) {
		Element result = null;
		
		if(n<0){
			throw new IllegalArgumentException();
		}
		else if(n == 0){
			result = getIdentity();
		}
		else{
			result = this;
			
			for(int i = 2; i <= n; i++){
				result = result.multiply(this);
			}
		}
		
		return result;
	}

	@Override
	public Element getIdentity() {
		return new Element(1,0,getD());
	}



	
}
