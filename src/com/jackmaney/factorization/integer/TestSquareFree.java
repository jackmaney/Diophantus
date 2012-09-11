package com.jackmaney.factorization.integer;


public class TestSquareFree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] arr = {-5,-12,-6};
		
		for (int i : arr) {
			
			NegativeSquareFreeInteger s;
			
			try {
				s=new NegativeSquareFreeInteger(i);
				System.out.println(s.getValue() + " is square free!");
			} catch (Exception e) {
				System.out.println(i + " is not square free (or positive).");
			}
		}

	}

}
