package test;

import static org.junit.Assert.*;


import org.junit.Test;
import logic.Character;
import logic.Guard;
import logic.Map;
import logic.Weapon;


public class TestOtherFuncions {

	@Test 
	public void testReverseCommandFunctions() {
		Character c = new Character(1,1,'c',false,false);
		assertEquals(c.revCommand('r'), 'l');
		assertEquals(c.revCommand('l'), 'r');
		assertEquals(c.revCommand('u'), 'd');
		assertEquals(c.revCommand('d'), 'u');
		assertEquals(c.revCommand('e'), 'E');

		
		Weapon w = new Weapon(1,1,'w',true);
		assertEquals(w.revCommand('r'), 'l');
		assertEquals(w.revCommand('l'), 'r');
		assertEquals(w.revCommand('u'), 'd');
		assertEquals(w.revCommand('d'), 'u');
		assertEquals(w.revCommand('e'), 'E');
	}
	
	@Test
	public void TestMapInitialization()
	{
		Map m = new Map(10);
		assertEquals(m.getCharacters().size(),2);
		m.setLevel(2);
		assertEquals(m.getCharacters().size(),1);
	}
	
	@Test 
	public void TestGuardInitializeRoute()
	{
		Guard g = new Guard(8,1);
		char[] route;
		route = new char[24];
		route[0] = 'l';
		for(int i = 1; i < 5; ++i)
			route[i] = 'd';
		for(int i = 5; i < 11; ++i)
			route[i] = 'l';
		route[11] = 'd';
		for(int i = 12; i < 19; ++i)
			route[i] = 'r';
		for(int i = 19; i <= 23; ++i)
			route[i] = 'u';
		for (int i = 0; i < route.length; i++)
			assertEquals(g.getRoute()[i],route[i]);
	}
	
	
//	public void WhateverImTesting() throws Exception {
//
//		//Prepare to redirect output
//		OutputStream os = new ByteArrayOutputStream();
//		PrintStream ps = new PrintStream(os);
//		System.setOut(ps);
//
//		System.out.print("Hello World");
//		assertEquals("Hello World", os.toString());
//
//		System.out.println("Hello World");
//	
//		PrintStream originalOut = System.out;
//		System.setOut(originalOut);
//
//		}

	
}
