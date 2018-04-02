package test;

import static org.junit.Assert.*;

import org.junit.Test;
import logic.Character;
import logic.Guard;
import logic.Weapon;


public class TestOtherFuncions {

	@Test 
	public void testReverseCommandFunctions() {
		Character c = new Character(1,1,'c',false,false);
		assertEquals(c.reverseCommand('r'), 'l');
		assertEquals(c.reverseCommand('l'), 'r');
		assertEquals(c.reverseCommand('u'), 'd');
		assertEquals(c.reverseCommand('d'), 'u');
		assertEquals(c.reverseCommand('e'), 'E');
		Weapon w = new Weapon(1,1,'w',true);
		assertEquals(w.reverseCommand('r'), 'l');
		assertEquals(w.reverseCommand('l'), 'r');
		assertEquals(w.reverseCommand('u'), 'd');
		assertEquals(w.reverseCommand('d'), 'u');
		assertEquals(w.reverseCommand('e'), 'E');
	}
	
	
	@Test 
	public void TestGuardRoute()
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
		
		assertEquals(g.updateGuard(),'l');
		assertEquals(g.updateGuard(true),'r');
		assertEquals(g.updateGuard(false),'l');
	}
		
}
