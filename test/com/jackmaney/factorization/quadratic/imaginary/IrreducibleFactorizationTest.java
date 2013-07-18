package com.jackmaney.factorization.quadratic.imaginary;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Vector;

import org.junit.Test;

import com.jackmaney.Diophantus.Factorization;
import com.jackmaney.Diophantus.Power;
import com.jackmaney.Diophantus.element.Element;

public class IrreducibleFactorizationTest {

	@Test
	public void test() {
		int d = -14;
		Element e = new Element(81, 0, d);
		
		Element irr1 = new Element(3,0,d);
		Element irr2 = new Element(5,-2,d);
		Element irr3 = new Element(5,2,d);
		
		Power<Element> p1 = new Power<>(irr1,4);
		Power<Element> p2 = new Power<>(irr2);
		Power<Element> p3 = new Power<>(irr3);
		
		Vector<Factorization<Element>> expected = new Vector<>();
		
		
		Vector<Power<Element>> v = new Vector<>();
		v.add(p2);
		v.add(p3);
		
		expected.add(new Factorization<Element>(v));
		
		expected.add(new Factorization<Element>(p1));
		
		Vector<Factorization<Element>> factorizations = e.getIrreducibleFactorizations();
		
		assertTrue(expected.equals(factorizations));
		
		
	}

}
