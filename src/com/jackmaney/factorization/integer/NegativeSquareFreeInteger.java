package com.jackmaney.factorization.integer;

import com.jackmaney.factorization.Power;
import com.jackmaney.factorization.Factorization;
import com.jackmaney.factorization.MInteger;


public class NegativeSquareFreeInteger extends Number {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6975534683932279247L;
	private final int value;
	
	public NegativeSquareFreeInteger(int value)
	{
		if(value < 0 && isSquareFree(value))
		{
			this.value=value;
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	
	private boolean isSquareFree(int d)
	{
		if(d<0)
		{
			return isSquareFree(-d);
		}
		else if(d<=1)
		{
			throw new IllegalArgumentException();
		}
			
		
		
		Factorization<MInteger> primes = Util.getPrimeFactorization(d);
		
		for (Power<MInteger> factorPair : primes.getFactorization()) {
			if(factorPair.getExponent()>1)
			{
				return false;
			}
		}
		
		return true;
	}
	


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return value;
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
		if (!(obj instanceof NegativeSquareFreeInteger)) {
			return false;
		}
		NegativeSquareFreeInteger other = (NegativeSquareFreeInteger) obj;
		if (value != other.value) {
			return false;
		}
		return true;
	}


	@Override
	public int intValue() {
		return value;
	}


	@Override
	public long longValue() {
		return (long)value;
	}


	@Override
	public float floatValue() {
		return (float)value;
	}


	@Override
	public double doubleValue() {
		return (double)value;
	}
	
}
