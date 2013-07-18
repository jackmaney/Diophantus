package com.jackmaney.Diophantus;


import com.jackmaney.Diophantus.element.Element;


public class Diophantus {

	public static void main(String[] args) {
		Element e = new Element(6,0,-5);
		
		System.out.println(e.getIrreducibleFactorizations());
		

	}

}
