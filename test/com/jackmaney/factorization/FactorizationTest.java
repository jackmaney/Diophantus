package com.jackmaney.factorization;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import com.jackmaney.Diophantus.Factorization;
import com.jackmaney.Diophantus.MInteger;
import com.jackmaney.Diophantus.Power;

public class FactorizationTest {
	
	private Factorization<MInteger> f;
	
	@Before
	public void setup()
	{
		Vector<Power<MInteger>> v = new Vector<>();
		v.add(new Power<MInteger>(new MInteger(2),4));
		v.add(new Power<MInteger>(new MInteger(3), 5));
		v.add(new Power<MInteger>(new MInteger(5)));
		
		f = new Factorization<>(v);
	}
	
	@Test
	public void testProduct() {
		int n = (int)Math.pow(2, 4);
		n*=(int)Math.pow(3, 5);
		n*=5;
		assertTrue("product() test failed: expected " + n + " got " + f.product().intValue()
				,f.product().intValue() == n);
	}

	@Test
	public void testToString() {
		
		assertTrue("toString() test failed", f.toString().equals("2^4*3^5*5^1"));
		
		
	}

}
