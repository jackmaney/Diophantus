package com.jackmaney.factorization;

public class MInteger extends Number implements Multiplicative<MInteger>,Comparable<MInteger> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int n;
	
	public MInteger(int n)
	{
		this.n = n;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof MInteger)
		{
			MInteger m = (MInteger)obj;
			return intValue() == m.intValue();
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		Integer m = new Integer(this.n);
		return m.toString();
	}

	@Override
	public MInteger multiply(MInteger t) {
		return new MInteger( intValue() * t.intValue());
	}

	@Override
	public int intValue() {
		return this.n;
	}

	@Override
	public long longValue() {
		return (long)this.n;
	}

	@Override
	public float floatValue() {
		return (float)this.n;
	}

	@Override
	public double doubleValue() {
		return (double)this.n;
	}

	@Override
	public int compareTo(MInteger o) {
		Integer a = new Integer(this.intValue());
		Integer b = new Integer(o.intValue());
		
		return a.compareTo(b);
	}

}
