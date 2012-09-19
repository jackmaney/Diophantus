package com.jackmaney.factorization.integer;

import static org.junit.Assert.*;

import org.junit.Test;

public class NegativeSquareFreeIntegerTest {

	@Test
	public void testNegativeSquareFreeInteger() {
		int[] testInts = {-2,-6,-12,3};
		boolean[] testOutputs = {true,true,false,false};
		
		
		for(int i=0;i<4;i++)
		{
			
			boolean good = true;
			try {
				new NegativeSquareFreeInteger(testInts[i]);
			} catch (Exception e) {
				good = false;
			}
			
			assertTrue(good == testOutputs[i]);
		}
	}

}
