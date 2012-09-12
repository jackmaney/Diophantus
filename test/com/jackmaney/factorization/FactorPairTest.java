package com.jackmaney.factorization;

import static org.junit.Assert.*;

import org.junit.Test;

public class FactorPairTest {


	@Test
	public void testToString() {
		Power<MInteger> fp = new Power<MInteger>(new MInteger(3),4);
		assertTrue("toString() test failed", fp.toString().equals("3^4"));
	}


	@Test
	public void testProduct() {
		Power<MInteger> fp = new Power<MInteger>(new MInteger(3),4);
		assertEquals("product() test failed", fp.product().intValue(), 81, 0); 
	}

}
