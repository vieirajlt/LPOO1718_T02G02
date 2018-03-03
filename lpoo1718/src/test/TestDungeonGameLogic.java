package test;

import static org.junit.Assert.*;
import org.junit.Test;

import logic.Game;
import logic.Hero;

public class TestDungeonGameLogic {
	TestMap map = new TestMap();
	
	@Test
	//1
	public void testMoveHeroIntoFreeCell() {
		Game game = new Game(map.getMap());
		assertEquals(game.getMap().getMapPosition(1, 1), game.getMap().getCharacters().get(0).getSymbol());
		game.updateGame('d');
		assertEquals(game.getMap().getMapPosition(1, 2), game.getMap().getCharacters().get(0).getSymbol());	
	}

	@Test
	//2
	public void testMoveHeroIntoWall() {
		Game game = new Game(map.getMap());
		assertEquals(game.getMap().getMapPosition(1, 1), game.getMap().getCharacters().get(0).getSymbol());
		game.updateGame('u');
		assertEquals(game.getMap().getMapPosition(1, 1), game.getMap().getCharacters().get(0).getSymbol());	
	}


	@Test
	//3
	public void testHeroIsCapturedByGuard() {
		Game game = new Game(map.getMap());
		assertEquals(game.getMap().getMapPosition(1, 1), game.getMap().getCharacters().get(0).getSymbol());
		game.updateGame('r');
		assertEquals(game.getMap().getMapPosition(2, 1), game.getMap().getCharacters().get(0).getSymbol());	
		assertEquals(true, ((Hero) game.getMap().getCharacters().get(0)).getCaptured());
	}

	@Test
	//4
	public void testMoveHeroIntoClosedDoor() {
		Game game = new Game(map.getMap());
		assertEquals(game.getMap().getMapPosition(1, 1), game.getMap().getCharacters().get(0).getSymbol());
		game.updateGame('d');
		game.updateGame('l');
		assertEquals(game.getMap().getMapPosition(1, 2), game.getMap().getCharacters().get(0).getSymbol());	
	}

	@Test
	//5
	public void testMoveHeroIntoLever() {
		Game game = new Game(map.getMap());
		assertEquals(game.getMap().getMapPosition(1, 1), game.getMap().getCharacters().get(0).getSymbol());
		game.updateGame('d');
		game.updateGame('d');
		assertEquals(game.getMap().getMapPosition(1, 2), game.getMap().getCharacters().get(0).getSymbol());
		int X = game.getMap().getDoors().get(0).getX();
		int Y = game.getMap().getDoors().get(0).getY();
		assertEquals('S', game.getMap().getMapPosition(X, Y));
	}

	@Test
	//6
	public void testMoveHeroIntoOpenDoor() {
		Game game = new Game(map.getMap());
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

