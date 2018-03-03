package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import logic.Game;
import logic.Hero;
import logic.Ogre;
import logic.Weapon;

public class TestOgreRandomMovement {
	
	TestMap map = new TestMap();

	@Test(timeout=1000)
	//Test if random movement setting is moving the ogre
	public void testOgreDirections() {
		boolean down = false, up = false, rigth = false, left = false;
		Game game = new Game(map.getMap2());
		game.setShowCli(false);
		game.getMap().getOgres().getLast().setRandomMovement(true);
		
		int oX, oY, oPx, oPy;
		
		while(!down || !up || !rigth || !left) {
			game.updateGame('d');
			Ogre o = game.getMap().getOgres().get(0);
			oX = o.getX();
			oY = o.getY();
			oPx = o.getPrevX();
			oPy = o.getPrevY();
			
			if(oX == oPx && oY-1 == oPy)
				down = true;
			else if(oX == oPx && oY+1 == oPy)
				up = true;
			else if(oX-1 == oPx && oY == oPy)
				left = true;
			else if(oX+1 == oPx && oY == oPy)
				rigth = true;
			else if(game.isEndGame()) {
				game = new Game(map.getMap2());
				game.setShowCli(false);
				game.getMap().getOgres().getLast().setRandomMovement(true);
				game.updateGame('d');
			} else
				fail("Not moving");

		}
		
		assertEquals(down && up && rigth && left, true);
	}
	
	@Test(timeout=1000)
	//Test if random movement setting is moving the ogre
	public void testWeaponDirections() {
		boolean down = false, up = false, rigth = false, left = false;
		Game game = new Game(map.getMap2());
		game.setShowCli(false);
		game.getMap().getOgres().getLast().setRandomMovement(true);
		int wX, wY, oX, oY;
		
		while(!down || !up || !rigth || !left) {
			game.updateGame('d');
			Weapon w = game.getMap().getOgres().get(0).getWeapon();
			Ogre o = game.getMap().getOgres().get(0);
			wX = w.getX();
			wY = w.getY();
			oX = o.getX();
			oY = o.getY();
			System.out.println(wX);
			System.out.println(wY);
			System.out.println(oX);
			System.out.println(oX);
			
			if(wX == oX && wY-1 == oY)
				down = true;
			else if(wX == oX && wY+1 == oY)
				up = true;
			else if(wX-1 == oX && wY == oY)
				left = true;
			else if(wX+1 == oX && wY == oY)
				rigth = true;
			else if(game.isEndGame()) {
				game = new Game(map.getMap2());
				game.setShowCli(false);
				game.getMap().getOgres().getLast().setRandomMovement(true);
				game.updateGame('d');
			} else
				fail("Not moving");
		}
		
		assertEquals(down && up && rigth && left, true);
	}
	
}
