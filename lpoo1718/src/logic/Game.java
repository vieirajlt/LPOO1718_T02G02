package logic;

import cli.GameStatusDisplay;

public class Game {
	private boolean endGame;
	private boolean showCli;
	private Map map;
	
	private static GameStatusDisplay display = new GameStatusDisplay();

	public Game()
	{
		int mapSize = 10;
		map = new Map(mapSize);
		endGame = false;
		setShowCli(true);
	}
	
	public Game(char[][] newMapConfig)
	{
		this(newMapConfig,true);
	}
	
	public Game(char[][] newMapConfig,boolean isLever)
	{
		map = new Map(newMapConfig,isLever);
		endGame = false;
	}
	
	public boolean isShowCli()
	{
		return this.showCli;
	}
	
	
	public void setShowCli(boolean showCli) {
		this.showCli = showCli;
		map.setShowCli(showCli);
	}

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
	
	
	//GAME MANAGEMENT FUNCTION
	public void updateGame(char command) {
		
		map.updateMap(command);
		map.updateMapDisplay();
		if(((Hero) map.getCharacters().get(0)).hasSteppedGuard()) {
			endGame = true;
			display.guardAwoken(showCli);
		} else if(((Hero) map.getCharacters().get(0)).getCaptured()) {
			display.captured(showCli);
			endGame = true;
		} else if(((Hero) map.getCharacters().get(0)).getFatality()) { 
			display.fatality(showCli);
			endGame = true;
		} else if(((Hero) map.getCharacters().get(0)).getEscaped()) {
			if(map.getLevel() != map.getMaxLevel()) {
				display.nextLevel(showCli);
				map.getToNextLevel();
			} else {
				display.gameWon(showCli);
				endGame = true;
			}
		}
	}

}
