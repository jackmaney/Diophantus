package com.jackmaney.factorization.integer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.jackmaney.factorization.Factorization;
import com.jackmaney.factorization.MInteger;

public class PrimeFactorsTest {

	private int n;
	Factorization<MInteger> f;
	
	@Before
	public void setup()
	{
		n=2;
		
		for(int i=1; i<=17; i++)
		{
			n*=2;
		}
		
		n*=37;
		
		f=PrimeFactors.find(n);
	}
	
	@Test
	public void testClass() {
		assertTrue(f instanceof Factorization<?>);
	}
	
	@Test
	public void testSize()
	{
		assertTrue(f.getFactorization().size() == 2);
	}
	
	@Test
	public void testProduct()
	{
		assertTrue(f.product().intValue() == n);
	}
	
	@Test
	public void testToString()
	{
		assertTrue(f.toString().equals("2^18*37^1"));
	}

}
