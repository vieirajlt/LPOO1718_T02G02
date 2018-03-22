package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logic.Game;
import logic.Guard;
import logic.Hero;
import logic.Map;
import logic.Ogre;
import logic.Rookie;
import logic.Suspicious;
import logic.Unlocker;
import logic.Weapon;
import logic.Character;
import logic.Club;
import logic.Door;
import logic.Drunken;

public class TestClassConstructors {

	@Test
	//1
	public void testCharacterConstructor() {
		Character t = new Character(1, 2, 'T', true, false);
		assertEquals(t.getX(),1);
		assertEquals(t.getY(),2);
		assertEquals(t.getSymbol(),'T');
		assertEquals(t.hasWeapon(),true);
		assertEquals(t.isMove(),false);
	}

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
		Drunken t = new Drunken(1, 2, true);
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
		Guard t = new Guard(1, 2, true);
		assertEquals(t.getX(),1);
		assertEquals(t.getY(),2);
		assertEquals(t.getSymbol(), 'G');
		assertEquals(t.isMove(),true);
		assertEquals(t.hasWeapon(),false);
		assertEquals(t.getRoute().length,24);
		assertEquals(t.getRouteStep(),0);
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
		assertEquals(t.getEscaped(),false);
		assertEquals(t.getObjectColliding(),false);
		assertEquals(t.getWallColliding(),false);
		assertEquals(t.getCaptured(),false);
		assertEquals(t.getExitOpened(),false);
		assertEquals(t.getFatality(),false);
		assertEquals(t.hasSteppedGuard(),false);
		t = new Hero(1, 2, false);
		assertEquals(t.getSymbol(), 'H');
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
	}
	
	@Test
	//9
	public void testOgreConstructor() {
		Ogre t = new Ogre(1, 2, true, true);
		assertEquals(t.getX(),1);
		assertEquals(t.getY(),2);
		assertEquals(t.getSymbol(), 'O');
		assertEquals(t.isMove(),true);
		assertEquals(t.hasWeapon(),true);
		assertEquals(t.getWeapon().getX(),0);
		assertEquals(t.getWeapon().getY(),2);
		assertEquals(t.getStunCount(),0);
		assertEquals(t.isStunned(),false);
	}
	
	
	@Test
	//10
	public void testRookieConstructor() {
		Rookie t = new Rookie(1, 2, true);
		assertEquals(t.getX(),1);
		assertEquals(t.getY(),2);
		assertEquals(t.getSymbol(), 'G');
		assertEquals(t.isMove(),true);
	}
	
	@Test
	//11
	public void testSuspiciousConstructor() {
		Suspicious t = new Suspicious(1, 2, true);
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
	
}
