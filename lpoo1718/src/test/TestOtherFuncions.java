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
		
		for(int i = 5; i < 11; ++i)
			route[i] = 'l';
		
		for(int i = 1; i < 5; ++i)
			route[i] = 'd';
		
		route[11] = 'd';
		
		for(int i = 19; i <= 23; ++i)
			route[i] = 'u';
		
		for(int i = 12; i < 19; ++i)
			route[i] = 'r';
		
		for (int i = 0; i < route.length; i++)
			assertEquals(g.getRoute()[i],route[i]);
		
		assertEquals(g.updateGuard(),'l');
		assertEquals(g.updateGuard(true),'r');
		assertEquals(g.updateGuard(false),'l');
	}
	
	@Test
	public void TestSomeMapFunctionalities(){
		TestMap testMap = new TestMap();
		Map map = new Map(testMap.getMap2());
		assertEquals(map.searchCharacter('H'), true);
		assertEquals(map.searchCharacter('G'), false);
		assertEquals(map.searchHero(),true);
		assertEquals(map.searchOgre(),true);
		assertEquals(map.searchClub(),true);
		assertEquals(map.searchDoor(),true);
		assertEquals(map.searchKey(),true);
		assertEquals(map.validateMapScheme(),true);
		map = new Map();
		map.initializeMap(testMap.getMap2());
		assertEquals(map.getMapScheme().length, 9);
		assertEquals(map.getMapScheme()[0].length, 9);
		assertEquals(map.getCharacters().size(), 1);
		assertEquals(map.getOgres().size(), 1);
		assertEquals(map.getDoors().size(), 1);
		assertEquals(map.getUnlockers().size(), 1);
	}
	
		
}
