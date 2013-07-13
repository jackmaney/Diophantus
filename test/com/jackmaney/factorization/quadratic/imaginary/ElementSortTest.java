package com.jackmaney.factorization.quadratic.imaginary;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class ElementSortTest {
	
	private Element e1 = new Element(1, 1, -5);
	private Element e2 = new Element(1, -1, -5);
	private Element e3 = new Element(2,0,-5);
	private Element e4 = new Element(3,0,-5);
	
	

	@Test
	public void testCompare() {
		assertTrue(e1.compareTo(e2) == 1);
		assertTrue(e2.compareTo(e3) == 1);
		assertTrue(e3.compareTo(e4) == -1);;
	}
	
	@Test
	public void testSortArray() {
		Element[] arr = {e1,e2,e3,e4};
		Element[] expected = {e3, e2,e1, e4};
		
		Arrays.sort(arr);
		
		for(int i=0; i<4; i++){
			assertTrue(arr[i].equals(expected[i]));
		}
		
	}

}
