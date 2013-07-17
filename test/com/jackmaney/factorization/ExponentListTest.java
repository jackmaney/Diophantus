package com.jackmaney.factorization;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

public class ExponentListTest {
	
	
	
	@Test
	public void exponentListTest() {
		Integer[] arr = {2,1,3};
		Vector<Integer> v = new Vector<Integer>();
		
		v.add(1);
		v.add(0);
		v.add(3);
		
		ExponentList e1 = new ExponentList(arr);
		ExponentList e2 = new ExponentList(v);
		
		assertTrue(e1.compareTo(e2) == 1);
	}

}
