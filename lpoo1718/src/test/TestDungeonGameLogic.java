package test;

import static org.junit.Assert.*;
import org.junit.Test;

import logic.Game;
import logic.Hero;

public class TestDungeonGameLogic {

	char [][] map = {
			{'X','X','X','X','X'},
			{'X','H',' ','G','X'},
			{'I',' ',' ',' ','X'},
			{'I','k',' ',' ','X'},
			{'X','X','X','X','X'}};
	@Test
	public void testMoveHeroIntoFreeCell() {
		Game game = new Game(map);
		assertEquals(game.getMap().getMapPosition(1, 1), game.getMap().getCharacters().get(0).getSymbol());
		game.updateGame('d');
		assertEquals(game.getMap().getMapPosition(1, 2), game.getMap().getCharacters().get(0).getSymbol());	
	}

	@Test
	public void testMoveHeroIntoWall() {
		Game game = new Game(map);
		assertEquals(game.getMap().getMapPosition(1, 1), game.getMap().getCharacters().get(0).getSymbol());
		game.updateGame('u');
		assertEquals(game.getMap().getMapPosition(1, 1), game.getMap().getCharacters().get(0).getSymbol());	
	}


	@Test
	public void testHeroIsCapturedByGuard() {
		Game game = new Game(map);
		assertEquals(game.getMap().getMapPosition(1, 1), game.getMap().getCharacters().get(0).getSymbol());
		game.updateGame('r');
		assertEquals(game.getMap().getMapPosition(2, 1), game.getMap().getCharacters().get(0).getSymbol());	
		assertEquals(true, ((Hero) game.getMap().getCharacters().get(0)).getCaptured());
	}

	@Test
	public void testMoveHeroIntoClosedDoor() {
		Game game = new Game(map);
		assertEquals(game.getMap().getMapPosition(1, 1), game.getMap().getCharacters().get(0).getSymbol());
		game.updateGame('d');
		game.updateGame('l');
		assertEquals(game.getMap().getMapPosition(1, 2), game.getMap().getCharacters().get(0).getSymbol());	
	}

	@Test
	public void testMoveHeroIntoLever() {
		Game game = new Game(map);
		assertEquals(game.getMap().getMapPosition(1, 1), game.getMap().getCharacters().get(0).getSymbol());
		game.updateGame('d');
		game.updateGame('d');
		assertEquals(game.getMap().getMapPosition(1, 2), game.getMap().getCharacters().get(0).getSymbol());
		int X = game.getMap().getDoors().get(0).getX();
		int Y = game.getMap().getDoors().get(0).getY();
		assertEquals('S', game.getMap().getMapPosition(X, Y));
	}

	@Test
	public void testMoveHeroIntoOpenExit() {
		Game game = new Game(map);
		assertEquals(game.getMap().getMapPosition(1, 1), game.getMap().getCharacters().get(0).getSymbol());
		game.updateGame('d');
		game.updateGame('d');
		game.updateGame('l');
		int X = game.getMap().getDoors().get(0).getX();
		int Y = game.getMap().getDoors().get(0).getY();
		assertEquals(game.getMap().getMapPosition(X, Y), game.getMap().getCharacters().get(0).getSymbol());
		assertEquals(game.isEndGame(),true);
	}






}

