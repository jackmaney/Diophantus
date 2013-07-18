package com.jackmaney.factorization.quadratic.imaginary;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Vector;

import org.junit.Test;

import com.jackmaney.Diophantus.element.Element;

public class GetAllIrreduciblesTest {

	private int d = -14;
	private Element e1 = new Element(81,0,d);
	private Element e2 = new Element(3,0,d);
	private Element e3 = new Element(5,-2,d);
	private Element e4 = new Element(5,2,d);
	@Test
	public void getAllIrreduciblesTest() {
		
		Vector<Element> v = e1.getAllIrreducibles();
		
		Collections.sort(v);
		
		Vector<Element> expected = new Vector<>();
		expected.add(e2);
		expected.add(e3);
		expected.add(e4);
		
		assertTrue(expected.equals(v));
		assertTrue(!v.contains(new Element(27,0,d)));
		
	}

}
