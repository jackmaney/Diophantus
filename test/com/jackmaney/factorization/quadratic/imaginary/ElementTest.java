package com.jackmaney.factorization.quadratic.imaginary;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jackmaney.Diophantus.element.Element;

public class ElementTest {

	private Element e1 = new Element(1,-1,-5);
	private Element e2 = new Element(2,1,-5);
	private Element e3 = new Element(6,0,-5);
	
	
	@Test
	public void testEqualsElement() {
		Element e2Clone = new Element(2,1,-5);
		assertTrue(e2.equals(e2Clone));
		assertFalse(e1.equals(e2));
		assertFalse(e2 == e2Clone);
	}

	@Test
	public void testToString() {
		assertTrue("Got: " + e2.toString() + " Expected: 2 + 1 * sqrt(-5)" ,e2.toString().equals("2 + 1 * sqrt(-5)"));
	}

	@Test
	public void testIsInt() {
		assertTrue(e3.isInt());
		assertFalse(e1.isInt());
	}

	@Test
	public void testMultiply() {
		assertTrue(e1.multiply(e2).equals(new Element(7,-1,-5)));
		
	}

	@Test
	public void testAdd() {
		assertTrue(e1.add(e2).equals(new Element(3,0,-5)));
	}

	@Test
	public void testNorm() {
		assertTrue(e1.norm() == 6);
		assertTrue(e2.norm() == 9);
	}

	@Test
	public void testDivisor() {
		assertTrue(e1.divides(e3).equals(new Element(1,1,-5)));
	}

	@Test
	public void testCompare() {
		assertTrue(e1.compare(e1, e2) == -1);
		assertTrue(e3.compare(e3, e1) == 1);
	}
	
	@Test
	public void testCompareTo()
	{
		assertTrue(e1.compareTo(e2) == -1);
		assertTrue(e3.compareTo(e1) == 1);
	}

}
