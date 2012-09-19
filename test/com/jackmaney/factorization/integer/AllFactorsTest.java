package com.jackmaney.factorization.integer;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

public class AllFactorsTest {

	@Test
	public void testFind() {
		int n = 8675310;
		
		Vector<Integer> v = new Vector<>();
		
		v.add(2);
		v.add(3);
		v.add(5);
		v.add(6);
		v.add(7);
		v.add(10);
		v.add(14);
		v.add(15);
		v.add(21);
		v.add(30);
		v.add(35);
		v.add(42);
		v.add(70);
		v.add(105);
		v.add(109);
		v.add(210);
		v.add(218);
		v.add(327);
		v.add(379);
		v.add(545);
		v.add(654);
		v.add(758);
		v.add(763);
		v.add(1090);
		v.add(1137);
		v.add(1526);
		v.add(1635);
		v.add(1895);
		v.add(2274);
		v.add(2289);
		v.add(2653);
		v.add(3270);
		v.add(3790);
		v.add(3815);
		v.add(4578);
		v.add(5306);
		v.add(5685);
		v.add(7630);
		v.add(7959);
		v.add(11370);
		v.add(11445);
		v.add(13265);
		v.add(15918);
		v.add(22890);
		v.add(26530);
		v.add(39795);
		v.add(41311);
		v.add(79590);
		v.add(82622);
		v.add(123933);
		v.add(206555);
		v.add(247866);
		v.add(289177);
		v.add(413110);
		v.add(578354);
		v.add(619665);
		v.add(867531);
		v.add(1239330);
		v.add(1445885);
		v.add(1735062);
		v.add(2891770);
		v.add(4337655);
		
		assertTrue(v.equals(AllFactors.find(n)));
	}

}
