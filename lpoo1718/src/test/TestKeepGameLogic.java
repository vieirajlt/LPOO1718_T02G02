package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logic.Club;
import logic.Game;
import logic.Guard;
import logic.GuardPersonality;
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
		assertEquals(((Club)game.getCurrentMap().getOgres().get(0).getWeapon()).isAboveKey(),false);
		assertEquals(game.getCurrentMap().getMapPosition(1, 7), game.getCurrentMap().getCharacters().get(0).getSymbol());
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('r');
		assertEquals(game.getCurrentMap().getOgres().get(0).isStunned(),false);
		assertEquals(((Hero)game.getCurrentMap().getCharacters().get(0)).getFatality(), true);
	}
	
	
	
	@Test
	//2
	public void testHeroHasKey() {
		Game game = new Game(map.getMap2(),false);
		game.setShowCli(false);
		assertEquals(game.getCurrentMap().getMapPosition(1, 7), game.getCurrentMap().getCharacters().get(0).getSymbol());
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
		assertEquals(game.getCurrentMap().getMapPosition(7, 1), game.getCurrentMap().getCharacters().get(0).getSymbol());
		assertEquals('K',game.getCurrentMap().getCharacters().get(0).getSymbol());
	}
	
	@Test 
	//3
	public void testMoveHeroIntoClosedExit() {
		Game game = new Game(map.getMap2(),false);
		game.setShowCli(false);
		assertEquals(game.getCurrentMap().getMapPosition(1, 7), game.getCurrentMap().getCharacters().get(0).getSymbol());
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('l');
		game.updateGame('l');
		assertEquals(game.getCurrentMap().getMapPosition(1, 1), game.getCurrentMap().getCharacters().get(0).getSymbol());
		assertEquals(((Hero)game.getCurrentMap().getCharacters().get(0)).getExitOpened(),false);
		assertEquals(game.isEndGame(),false);
	}

	@Test
	//4
	public void testMoveHeroIntoExitWithKey() {
		Game game = new Game(map.getMap2(),false);
		game.setShowCli(false);
		assertEquals(game.getCurrentMap().getMapPosition(1, 7), game.getCurrentMap().getCharacters().get(0).getSymbol());
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
		assertEquals(game.getCurrentMap().getMapPosition(7, 1), game.getCurrentMap().getCharacters().get(0).getSymbol());
		assertEquals('K',game.getCurrentMap().getCharacters().get(0).getSymbol());
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
		assertEquals(((Hero)game.getCurrentMap().getCharacters().get(0)).getExitOpened(),true);
	}
	
	@Test
	//5
	public void testMoveHeroIntoOpenExit() {
		Game game = new Game(map.getMap2(),false);
		game.setShowCli(false);
		assertEquals(game.getCurrentMap().getMapPosition(1, 7), game.getCurrentMap().getCharacters().get(0).getSymbol());
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
		assertEquals(game.getCurrentMap().getMapPosition(7, 1), game.getCurrentMap().getCharacters().get(0).getSymbol());
		assertEquals('K',game.getCurrentMap().getCharacters().get(0).getSymbol());
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
		assertEquals(((Hero)game.getCurrentMap().getCharacters().get(0)).getExitOpened(),true);
		game.updateGame('l');
		assertEquals(((Hero)game.getCurrentMap().getCharacters().get(0)).getEscaped(),true);
		assertEquals(game.isEndGame(),true);
	}
	
	@Test(timeout=1000)
	public void testDrunkenGuard() {
		Game game = new Game(map.getMap(),false);
		game.setShowCli(true);
		game.getCurrentMap().setGuardType(GuardPersonality.DRUNKEN, false);
		
		boolean isSleeping = false;
		boolean hasWoken = false;
		
		Guard d = (Guard) game.getCurrentMap().getGuard();
		while(!isSleeping || !hasWoken) {
			game.getCurrentMap().displayMap();
			game.updateGame('u');
			if(d.getSymbol() == 'g')
				isSleeping = true;
			if(isSleeping && d.getSymbol() == 'G')
				hasWoken = true;
		}
		
		assertEquals(isSleeping && hasWoken,true);
	}
	
	@Test(timeout=1000)
	public void testSuspiciousGuard() {
		Game game = new Game(map.getMap(),false);
		game.setShowCli(false);
		game.getCurrentMap().setGuardType(GuardPersonality.SUSPICIOUS, false);
		
		boolean hasReversed = false;
		
		Suspicious s = (Suspicious) game.getCurrentMap().getGuard();
		
		while(!hasReversed) {
			game.updateGame('u');
			if(s.isReverse()) {
				hasReversed = true;
				assertEquals(s.isReverse(),true);
			}
		}
		
	}
	
}
