package logic;

import cli.GameStatusDisplay;

public class Game {
	private  boolean endGame;
	private static GameStatusDisplay display;
	private Map map;

	
	public boolean isEndGame() {
		return endGame;
	}

	public  void setEndGame(boolean endGame) {
		this.endGame = endGame;
	}

	public GameStatusDisplay getDisplay() {
		return display;
	}

	public void setDisplay(GameStatusDisplay display) {
		Game.display = display;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Game()
	{
		int mapSize = 10;
		map = new Map(mapSize);
		endGame = false;
		display = new GameStatusDisplay();
	}
	
	public Game(char[][] newMapConfig)
	{
		this(newMapConfig,true);
	}
	
	public Game(char[][] newMapConfig,boolean isLever)
	{
		map = new Map(newMapConfig,isLever);
		endGame = false;
		display = new GameStatusDisplay();
	}
	
	
	//GAME MANAGEMENT FUNCTION
	public void updateGame(char command) {
		
		map.updateMap(command);
		map.updateMapDisplay();
		if(((Hero) map.getCharacters().get(0)).hasSteppedGuard()) {
			endGame = true;
			display.guardAwoken();
		} else if(((Hero) map.getCharacters().get(0)).getCaptured()) {
			display.captured();
			endGame = true;
		} else if(((Hero) map.getCharacters().get(0)).getFatality()) {
			display.fatality();
			endGame = true;
		} else if(((Hero) map.getCharacters().get(0)).getEscaped()) {
			if(map.getLevel() != map.getMaxLevel()) {
				display.nextLevel();
				map.getToNextLevel();
			} else {
				display.gameWon();
				endGame = true;
			}
		}
	}

}
