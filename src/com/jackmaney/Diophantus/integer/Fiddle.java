package com.jackmaney.Diophantus.integer;


import java.util.Iterator;
import java.util.Vector;

import com.jackmaney.Diophantus.ExponentList;
import com.jackmaney.Diophantus.element.Element;


public class Fiddle {

	public static void main(String[] args) {
		//Element e = new Element(81, 0, -14);
		
		//System.out.println(e.getIrreducibleFactorizations());
		
		Vector<Integer> v = new Vector<>();
		v.add(2);
		v.add(1);
		v.add(3);
		
		ExponentList e = new ExponentList(v);
		
		
		
		Iterator<ExponentList> it = e.iterator();
		
		while(it.hasNext()){
			System.out.println(it.next());
		}
		

	}

}
