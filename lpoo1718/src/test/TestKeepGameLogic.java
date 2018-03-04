package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logic.Game;
import logic.Guard;
import logic.Club;
import logic.Drunken;
import logic.Hero;
import logic.Suspicious;

public class TestKeepGameLogic {
	
	TestMap map = new TestMap();
	
	@Test
	//1
	public void  testHeroIsDefeatedByOgre()
	{
		Game game = new Game(map.getMap2());
		game.setShowCli(false);
		assertEquals(((Club)game.getMap().getOgres().get(0).getWeapon()).isAboveKey(),false);
		assertEquals(game.getMap().getMapPosition(1, 7), game.getMap().getCharacters().get(0).getSymbol());
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('r');
		assertEquals(game.getMap().getOgres().get(0).isStunned(),false);
		assertEquals(((Hero)game.getMap().getCharacters().get(0)).getFatality(), true);
	}
	
	
	
	@Test
	//2
	public void testHeroHasKey() {
		Game game = new Game(map.getMap2(),false);
		game.setShowCli(false);
		assertEquals(game.getMap().getMapPosition(1, 7), game.getMap().getCharacters().get(0).getSymbol());
		game.updateGame('r');
		game.updateGame('r');
		game.updateGame('r');
		game.updateGame('r');
		game.updateGame('r');
		game.updateGame('r');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		assertEquals(game.getMap().getMapPosition(7, 1), game.getMap().getCharacters().get(0).getSymbol());
		assertEquals('K',game.getMap().getCharacters().get(0).getSymbol());
	}
	
	@Test 
	//3
	public void testMoveHeroIntoClosedExit() {
		Game game = new Game(map.getMap2(),false);
		game.setShowCli(false);
		assertEquals(game.getMap().getMapPosition(1, 7), game.getMap().getCharacters().get(0).getSymbol());
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('l');
		game.updateGame('l');
		assertEquals(game.getMap().getMapPosition(1, 1), game.getMap().getCharacters().get(0).getSymbol());
		assertEquals(((Hero)game.getMap().getCharacters().get(0)).getExitOpened(),false);
		assertEquals(game.isEndGame(),false);
	}

	@Test
	//4
	public void testMoveHeroIntoExitWithKey() {
		Game game = new Game(map.getMap2(),false);
		game.setShowCli(false);
		assertEquals(game.getMap().getMapPosition(1, 7), game.getMap().getCharacters().get(0).getSymbol());
		game.updateGame('r');
		game.updateGame('r');
		game.updateGame('r');
		game.updateGame('r');
		game.updateGame('r');
		game.updateGame('r');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		assertEquals(game.getMap().getMapPosition(7, 1), game.getMap().getCharacters().get(0).getSymbol());
		assertEquals('K',game.getMap().getCharacters().get(0).getSymbol());
		game.updateGame('l');
		game.updateGame('d');
		game.updateGame('d');
		game.updateGame('l');
		game.updateGame('l');
		game.updateGame('l');
		game.updateGame('l');
		game.updateGame('l');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('l');
		game.updateGame('l');
		assertEquals(((Hero)game.getMap().getCharacters().get(0)).getExitOpened(),true);
	}
	
	@Test
	//5
	public void testMoveHeroIntoOpenExit() {
		Game game = new Game(map.getMap2(),false);
		game.setShowCli(false);
		assertEquals(game.getMap().getMapPosition(1, 7), game.getMap().getCharacters().get(0).getSymbol());
		game.updateGame('r');
		game.updateGame('r');
		game.updateGame('r');
		game.updateGame('r');
		game.updateGame('r');
		game.updateGame('r');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		assertEquals(game.getMap().getMapPosition(7, 1), game.getMap().getCharacters().get(0).getSymbol());
		assertEquals('K',game.getMap().getCharacters().get(0).getSymbol());
		game.updateGame('l');
		game.updateGame('d');
		game.updateGame('d');
		game.updateGame('l');
		game.updateGame('l');
		game.updateGame('l');
		game.updateGame('l');
		game.updateGame('l');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('l');
		game.updateGame('l');
		assertEquals(((Hero)game.getMap().getCharacters().get(0)).getExitOpened(),true);
		assertEquals(game.isEndGame(),true);
	}
	
	@Test(timeout=1000)
	public void testDrunkenGuard() {
		Game game = new Game(map.getMap(),false);
		game.setShowCli(false);
		game.getMap().setGuardType('d');
		
		boolean isSleeping = false;
		boolean hasWoken = false;
		
		Guard g = (Guard) game.getMap().getGuard();
		while(!isSleeping || !hasWoken) {
			game.updateGame('u');
			if(g.getSymbol() == 'g')
				isSleeping = true;
			if(isSleeping && g.getSymbol() == 'G')
				hasWoken = true;
		}
		
		assertEquals(isSleeping && hasWoken,true);
		
	}
	
	@Test(timeout=1000)
	public void testSuspiciousGuard() {
		Game game = new Game(map.getMap(),false);
		game.setShowCli(false);
		game.getMap().setGuardType('s');
		
		boolean hasReversed = false;
		
		Suspicious s = (Suspicious) game.getMap().getGuard();
		
		while(!hasReversed) {
			game.updateGame('u');
			if(s.isReverse()) {
				hasReversed = true;
				assertEquals(s.isReverse(),true);
			}
		}
		
	}
	
}
