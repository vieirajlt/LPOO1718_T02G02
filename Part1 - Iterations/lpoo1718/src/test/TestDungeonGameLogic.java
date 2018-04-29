package test;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.Game;
import logic.Hero;
import logic.Guard;

public class TestDungeonGameLogic {
	TestMap map = new TestMap();
	
	@Test
	//1
	public void testMoveHeroIntoFreeCell() {
		Game game = new Game(map.getMap(),false, false, false);
//		assertEquals(game.isShowCli(),false);
//		game.setShowCli(true);
//		assertEquals(game.isShowCli(),true);
		game.setShowCli(false);
		assertEquals(game.getCurrentMap().getMapPosition(1, 1), game.getCurrentMap().getCharacters().get(0).getSymbol());
		game.updateGame('d');
		assertEquals(game.getCurrentMap().getMapPosition(1, 2), game.getCurrentMap().getCharacters().get(0).getSymbol());	
		game.updateGame('e');
		assertEquals(game.getCurrentMap().getMapPosition(1, 2), game.getCurrentMap().getCharacters().get(0).getSymbol());	
	}

	@Test
	//2
	public void testMoveHeroIntoWall() {
		Game game = new Game(map.getMap(),false, false, false);
		game.setShowCli(false);
		assertEquals(game.getCurrentMap().getMapPosition(1, 1), game.getCurrentMap().getCharacters().get(0).getSymbol());
		game.updateGame('u');
		assertEquals(game.getCurrentMap().getMapPosition(1, 1), game.getCurrentMap().getCharacters().get(0).getSymbol());	
	}


	@Test
	//3
	public void testHeroIsCapturedByGuard() {
		Game game = new Game(map.getMap(),false, false, false);
		game.setShowCli(false);
		assertEquals(game.getCurrentMap().getMapPosition(1, 1), game.getCurrentMap().getCharacters().get(0).getSymbol());
		assertEquals(((Guard)game.getCurrentMap().getCharacters().get(1)).updateGuard(), 'E'); //test if guard is in fact not moving
		game.updateGame('r');
		assertEquals(game.getCurrentMap().getMapPosition(2, 1), game.getCurrentMap().getCharacters().get(0).getSymbol());	
		assertEquals(true, ((Hero) game.getCurrentMap().getCharacters().get(0)).getCaptured());
	}

	@Test
	//4
	public void testMoveHeroIntoClosedDoor() {
		Game game = new Game(map.getMap(),false, false, false);
		game.setShowCli(false);
		assertEquals(game.getCurrentMap().getMapPosition(1, 1), game.getCurrentMap().getCharacters().get(0).getSymbol());
		game.updateGame('d');
		game.updateGame('l');
		assertEquals(game.getCurrentMap().getMapPosition(1, 2), game.getCurrentMap().getCharacters().get(0).getSymbol());	
	}

	@Test
	//5
	public void testMoveHeroIntoLever() {
		Game game = new Game(map.getMap(),false, false, true);
		game.setShowCli(false);
		assertEquals(game.getCurrentMap().getMapPosition(1, 1), game.getCurrentMap().getCharacters().get(0).getSymbol());
		//test lever position and symbol
		assertEquals(game.getCurrentMap().getUnlockers().get(0).getX(),1);
		assertEquals(game.getCurrentMap().getUnlockers().get(0).getY(),3);
		assertEquals(game.getCurrentMap().getUnlockers().get(0).getSymbol(),'k');
		game.updateGame('d');
		game.updateGame('d');
		assertEquals(game.getCurrentMap().getMapPosition(1, 2), game.getCurrentMap().getCharacters().get(0).getSymbol());
		int X = game.getCurrentMap().getDoors().get(0).getX();
		int Y = game.getCurrentMap().getDoors().get(0).getY();
		assertEquals('S', game.getCurrentMap().getMapPosition(X, Y));
	}

	@Test
	//6
	public void testMoveHeroIntoOpenDoor() {
		Game game = new Game(map.getMap(),false, false, true);
		game.setShowCli(false);
		assertEquals(game.getCurrentMap().getMapPosition(1, 1), game.getCurrentMap().getCharacters().get(0).getSymbol());
		game.updateGame('d');
		game.updateGame('d');
		game.updateGame('l');
		int X = game.getCurrentMap().getDoors().get(0).getX();
		int Y = game.getCurrentMap().getDoors().get(0).getY();
		assertEquals(game.getCurrentMap().getMapPosition(X, Y), game.getCurrentMap().getCharacters().get(0).getSymbol());
		assertEquals(game.isEndGame(),true);
	}

}

