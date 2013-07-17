package com.jackmaney.factorization;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

import org.junit.Test;

public class ExponentListIteratorTest {

	@Test
	public void iteratorTest() {
		Integer[] arr = {2,1,3};
		
		ExponentList e = new ExponentList(arr);
		Iterator<ExponentList> it = e.iterator();
		
		Vector<Vector<Integer>> expected = new Vector<>();
		
		expected.add(new Vector<Integer>(Arrays.asList(1,0,0)));
		expected.add(new Vector<Integer>(Arrays.asList(2,0,0)));
		expected.add(new Vector<Integer>(Arrays.asList(2,1,0)));
		expected.add(new Vector<Integer>(Arrays.asList(2,1,1)));
		expected.add(new Vector<Integer>(Arrays.asList(2,1,2)));
		expected.add(new Vector<Integer>(Arrays.asList(2,1,3)));
		
		int counter = 0;
		
		while(it.hasNext() && counter < 6){
			

			Vector<Integer> a = expected.get(counter);
			Vector<Integer> b = it.next().getExponentList();
			
			assertTrue(a.equals(b));
			counter++;
		}
		
	}

}
