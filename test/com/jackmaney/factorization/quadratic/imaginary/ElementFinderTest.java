package com.jackmaney.factorization.quadratic.imaginary;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

public class ElementFinderTest {

	@Test
	public void testElementsOfNormIntInt() {
		Vector<Element> v = ElementFinder.elementsOfNorm(81, -14);
		
		Vector<Element> expected = new Vector<>();
		
		expected.add(new Element(5,2,-14));
		expected.add(new Element(5,-2,-14));
		expected.add(new Element(9,0,-14));
		
		for(int i=0;i<3;i++)
		{
			assertTrue(v.elementAt(i).equals(expected.elementAt(i)));
		}
		
		v=ElementFinder.elementsOfNorm(7, -5);
		assertTrue(v==null);

	}

}
