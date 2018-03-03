package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logic.Game;
import logic.Hero;

public class TestKeepGameLogic {
	
	TestMap map = new TestMap();
	
	@Test
	//1
	public void  testHeroIsDefeatedByOgre()
	{
		Game game = new Game(map.getMap2());
		assertEquals(game.getMap().getMapPosition(1, 7), game.getMap().getCharacters().get(0).getSymbol());
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('u');
		game.updateGame('r');
		
		assertEquals(((Hero)game.getMap().getCharacters().get(0)).getFatality(), true);
	}
	
	
	
	@Test
	//2
	public void testHeroHasKey() {
		Game game = new Game(map.getMap2(),false);
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
	
}
