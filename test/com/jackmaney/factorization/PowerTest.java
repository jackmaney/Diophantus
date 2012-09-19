package com.jackmaney.factorization;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.jackmaney.factorization.quadratic.imaginary.Element;
import com.jackmaney.factorization.quadratic.imaginary.ElementFinder;

public class PowerTest {

	private Power<MInteger> fp;
	private Power<Element> ep;
	
	@Before
	public void setup()
	{
		fp = new Power<MInteger>(new MInteger(3),4);
		ep = new Power<Element>(new Element(3,5,-10),2);
	}
	
	@Test
	public void testToString() {
		assertTrue("toString() test failed", fp.toString().equals("3^4"));
		
		assertTrue(ep.toString().equals("(3 + 5 * sqrt(-10))^2"));
	}


	@Test
	public void testProduct() {
		assertTrue(fp.product().intValue()==81);
		
		Element product = new Element(-241,30,-10);
		
		assertTrue(ep.product().equals(product));
		
	}

}
