package com.jackmaney.factorization.quadratic.imaginary;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Vector;

import org.junit.Test;

import com.jackmaney.Diophantus.element.Element;

public class GetAllFactorsTest {
	
	private Element e1 = new Element(6, 0, -5);
	private Element e2 = new Element(2,0,-5);
	private Element e3 = new Element(3,0,-5);
	private Element e4 = new Element(1, 1, -5);
	private Element e5 = new Element(1, -1, -5);

	@Test
	public void testAllFactors() {
		Vector<Element> factors = e1.getAllFactors();
		Collections.sort(factors);
		
		Vector<Element> expected = new Vector<>();
		expected.add(e2);
		expected.add(e5);
		expected.add(e4);
		expected.add(e3);
		
		assertTrue(factors.equals(expected));
	}

}
