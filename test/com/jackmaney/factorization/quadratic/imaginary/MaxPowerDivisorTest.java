package com.jackmaney.factorization.quadratic.imaginary;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jackmaney.Diophantus.element.Element;

public class MaxPowerDivisorTest {

	private int d = -14;
	private Element e = new Element(81,0,d);
	private Element e1 = new Element(3,0,d);
	private Element e2 = new Element(5,2,d);
	private Element e3 = new Element(1,1,d);
	
	@Test
	public void maxPowerDivisorTest() {
		assertTrue(e.maxPowerDivisor(e1) == 4);
		assertTrue(e.maxPowerDivisor(e2) == 1);
		assertTrue(e.maxPowerDivisor(e3) == 0);
	}

}
