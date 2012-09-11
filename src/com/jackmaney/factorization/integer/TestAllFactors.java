package com.jackmaney.factorization.integer;

import java.util.Vector;

public class TestAllFactors {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Vector<Integer> v = AllFactors.find(8675310);
		
		for (Integer integer : v) {
			System.out.println(integer);
		}

	}

}
