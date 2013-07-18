package com.jackmaney.Diophantus.integer;


import java.util.Iterator;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jackmaney.Diophantus.ExponentList;
import com.jackmaney.Diophantus.element.Element;


public class Fiddle {

	public static void main(String[] args) {
		Element e = new Element(242, 18, -14);
		
		System.out.println(e.getIrreducibleFactorizations());
		

	}

}
