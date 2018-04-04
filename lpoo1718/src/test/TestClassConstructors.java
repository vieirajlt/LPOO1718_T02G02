package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exception.InvalidBoardSizeException;
import exception.InvalidOgreCountException;
import logic.Club;
import logic.Door;
import logic.Drunken;
import logic.Game;
import logic.GameStartSet;
import logic.Guard;
import logic.GuardPersonality;
import logic.Hero;
import logic.Map;
import logic.Ogre;
import logic.Rookie;
import logic.Suspicious;
import logic.Unlocker;
import logic.Weapon;
import logic.testRookie;
import logic.testOgre;

public class TestClassConstructors {

	/*@Test
	//1
	public void testCharacterConstructor() {
		Character t = new Character(1, 2, 'T', true);
		assertEquals(t.getX(),1);
		assertEquals(t.getY(),2);
		assertEquals(t.getSymbol(),'T');
		assertEquals(t.hasWeapon(),true);
		assertEquals(t.isShowCli(),true);
		t.setPosition(2, 3);
		assertEquals(t.getX(),2);
		assertEquals(t.getY(),3);
		
	}*/

	@Test
	//2
	public void testClubConstructor() {
		Club t = new Club(1, 2, false);
		assertEquals(t.getX(),1);
		assertEquals(t.getY(),2);
		assertEquals(t.isMove(),false);
		assertEquals(t.isAboveKey(),false);
	}

	@Test
	//3
	public void testDoorConstructor() {
		Door t = new Door(1, 2);
		assertEquals(t.getX(),1);
		assertEquals(t.getY(),2);
	}

	@Test
	//4
	public void testDrunkenConstructor() {
		Drunken t = new Drunken(1, 2);
		assertEquals(t.getX(),1);
		assertEquals(t.getY(),2);
		assertEquals(t.getSymbol(), 'G');
		assertEquals(t.isMove(),true);
		assertEquals(t.isSleeping(),false);
		assertEquals(t.getSleepCount(),0);
		assertEquals(t.getSleepMax(),0);
		assertEquals(t.getRoute().length,24);
		assertEquals(t.getRouteStep(),0);
	}

	@Test
	//5
	public void testGameConstructor() {
		Game t = new Game();
		assertEquals(t.isEndGame(),false);
		assertEquals(t.isShowCli(),true);
		assertEquals(t.getCurrentMap().getMapScheme().length, 10);
		assertEquals(t.getCurrentMap().getMapScheme()[0].length, 10);
	}
	
	@Test
	//6
	public void testGuardConstructor() {
		Guard t = new Guard(1, 2);
		assertEquals(t.getX(),1);
		assertEquals(t.getY(),2);
		assertEquals(t.getSymbol(), 'G');
		assertEquals(t.isMove(),true);
		assertEquals(t.hasWeapon(),false);
		assertEquals(t.getRoute().length,24);
		assertEquals(t.getRouteStep(),0);
		testRookie g = new testRookie(1,2);
		assertEquals(g.updateGuard(false), 'E');
	}
	
	@Test
	//7
	public void testHeroConstructor() {
		Hero t = new Hero(1, 2, true);
		assertEquals(t.getX(),1);
		assertEquals(t.getY(),2);
		assertEquals(t.getSymbol(), 'A');
		assertEquals(t.isMove(),true);
		assertEquals(t.hasWeapon(),true); 
		assertEquals(t.getWeapon().isVisible(),false); 
		assertEquals(t.getEscaped(),false);
		assertEquals(t.getObjectColliding(),false);
		assertEquals(t.getWallColliding(),false);
		assertEquals(t.getCaptured(),false);
		assertEquals(t.getExitOpened(),false);
		assertEquals(t.getFatality(),false);
		assertEquals(t.hasSteppedGuard(),false);
		t = new Hero(1, 2, false);
		assertEquals(t.getSymbol(), 'H');
		t.addWeapon();
		assertEquals(t.hasWeapon(),true);
	}
	
	
	@Test
	//8
	public void testMapConstructor() {
		Map t = new Map(25, 25, true);
		assertEquals(t.getMapScheme().length, 25);
		assertEquals(t.getMapScheme()[0].length, 25);
		assertEquals(t.getCharacters().size(), 0);
		assertEquals(t.getOgres().size(), 0);
		assertEquals(t.getDoors().size(), 0);
		assertEquals(t.getUnlockers().size(), 0);
		t = new Map(GuardPersonality.DRUNKEN,1);
		assertEquals(t.getGuardPersonality(),GuardPersonality.DRUNKEN);
		assertEquals(t.getLevel(),1);
		assertEquals(t.getMapHeight(),10);
		assertEquals(t.getMapWidth(),10);
		t = new Map(5,2);
		assertEquals(t.getLevel(),2);
		assertEquals(t.getMapHeight(),10);
		assertEquals(t.getMapWidth(),10);
		t = new Map(1);
		assertEquals(t.getCharacters().size(),2);
		t.setLevel(2);
		assertEquals(t.getCharacters().size(),1);
		t= new Map(9,9,true);
		TestMap map = new TestMap();
		String res = "";
		for (int i = 0 ; i < map.getMap3().length; i++)
			res += String.valueOf(map.getMap3()[i]) + "\n";
		assertEquals(t.toString(), res);
	}
	
	@Test
	//9
	public void testOgreConstructor() {
		Ogre t1 = new Ogre(1, 2, true);
		assertEquals(t1.getX(),1);
		assertEquals(t1.getY(),2);
		assertEquals(t1.getSymbol(), 'O');
		assertEquals(t1.isMove(),true);
		assertEquals(t1.hasWeapon(),true);
		assertEquals(t1.getWeapon().getX(),0);
		assertEquals(t1.getWeapon().getY(),2);
		assertEquals(t1.getStunCount(),0);
		assertEquals(t1.isStunned(),false);
		testOgre t2 = new testOgre(1, 2, true);
		assertEquals(t2.isMove(),false);
	}
	
	
	@Test
	//10
	public void testRookieConstructor() {
		Rookie t = new Rookie(1, 2);
		assertEquals(t.getX(),1);
		assertEquals(t.getY(),2);
		assertEquals(t.getSymbol(), 'G');
		assertEquals(t.isMove(),true);
	}
	
	@Test
	//11
	public void testSuspiciousConstructor() {
		Suspicious t = new Suspicious(1, 2);
		assertEquals(t.getX(),1);
		assertEquals(t.getY(),2);
		assertEquals(t.getSymbol(), 'G');
		assertEquals(t.isMove(),true);
		assertEquals(t.isReverse(),false);
	}
	
	@Test
	//12
	public void testUnlockerConstructor() {
		Unlocker t = new Unlocker(1, 2, '+', true);
		assertEquals(t.getX(),1);
		assertEquals(t.getY(),2);
		assertEquals(t.getSymbol(), '+');
		assertEquals(t.isLever(),true);
		assertEquals(t.hasReackedUnlocker(1, 2),true);
	}
	
	@Test
	//12
	public void testWeaponConstructor() {
		Weapon t = new Weapon(1, 2, '+', false);
		assertEquals(t.getX(),1);
		assertEquals(t.getY(),2);
		assertEquals(t.getSymbol(), '+');
		assertEquals(t.isMove(),false);
		assertEquals(t.isVisible(),true);
	}
	
	@Test
	//13
	public void testGameStartSetConstructor() throws InvalidBoardSizeException, InvalidOgreCountException {
		GameStartSet gss = new GameStartSet(10,10);
		assertEquals(gss.getHeigth(),10);
		assertEquals(gss.getWidth(),10);
		assertEquals(gss.isLvlCreation(),true);
		assertEquals(gss.isSizeValid(10, 10),true);	
		gss.setOgresCount(5);
		assertEquals(gss.isOgreCountValid(gss.getOgresCount()),true);
		gss.setGuardPersonality(GuardPersonality.ROOKIE);
		assertEquals(gss.getGuardPersonality(),GuardPersonality.ROOKIE);
		gss = new GameStartSet(5,GuardPersonality.DRUNKEN);
		assertEquals(gss.getOgresCount(),5);
		
	}
	
}
