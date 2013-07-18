package com.jackmaney.factorization.quadratic.imaginary;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

import com.jackmaney.Diophantus.element.Element;
import com.jackmaney.Diophantus.element.ElementFinder;
import com.jackmaney.Diophantus.integer.NegativeSquareFreeInteger;

public class ElementFinderTest {

	@Test
	public void testElementsOfNormInt() {
		Vector<Element> v = ElementFinder.elementsOfNorm(81, -14);
		
		assertTrue(v.size() == 3);
		
		Vector<Element> expected = new Vector<>();
		
		expected.add(new Element(5,2,-14));
		expected.add(new Element(5,-2,-14));
		expected.add(new Element(9,0,-14));
		
		for(int i=0;i<3;i++)
		{
			assertTrue(v.elementAt(i).equals(expected.elementAt(i)));
		}
		
		v=ElementFinder.elementsOfNorm(7, -5);
		assertTrue(v.isEmpty());

	}
	
	@Test
	public void testElementsOfNormNNInt() {
		Vector<Element> v = ElementFinder.elementsOfNorm(81, new NegativeSquareFreeInteger(-14));
		
		assertTrue(v.size() == 3);
		
		Vector<Element> expected = new Vector<>();
		
		expected.add(new Element(5,2,-14));
		expected.add(new Element(5,-2,-14));
		expected.add(new Element(9,0,-14));
		
		for(int i=0;i<3;i++)
		{
			assertTrue(v.elementAt(i).equals(expected.elementAt(i)));
		}
		
		v=ElementFinder.elementsOfNorm(7, new NegativeSquareFreeInteger(-5));
		assertTrue(v.isEmpty());

	}

}
