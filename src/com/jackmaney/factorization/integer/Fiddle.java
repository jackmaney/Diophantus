package com.jackmaney.factorization.integer;

import java.util.Collections;
import java.util.Vector;

import com.jackmaney.factorization.quadratic.imaginary.Element;


public class Fiddle {

	public static void main(String[] args) {
		Element e1 = new Element(6, 0, -5);
		Element e2 = new Element(2,0,-5);
		Element e3 = new Element(3,0,-5);
		Element e4 = new Element(1, 1, -5);
		Element e5 = new Element(1, -1, -5);
		
		Vector<Element> factors = e1.getAllFactors();
		
		System.out.println(factors);
		System.out.println(factors.size());
		
		
		Collections.sort(factors);
		
		Vector<Element> expected = new Vector<>();
		expected.add(e2);
		expected.add(e5);
		expected.add(e4);
		expected.add(e3);
		

	}

}
