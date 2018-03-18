package logic;

import cli.GameStatusDisplay;

public class Game {
	private boolean endGame;
	private boolean showCli;
	private Map map;
	
	private static GameStatusDisplay display = new GameStatusDisplay();

	/*******************CONSTRUCTORS*******************/
	
	public Game()
	{
		int mapSize = 10;
		map = new Map(mapSize);
		endGame = false;
		setShowCli(true);
	}
	
	public Game(int width, int height, boolean isDefaultMap)
	{
		map = new Map(width, height, isDefaultMap);
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
	
	public Game(int ogreNumber, GuardPersonality gp)
	{
		char guardPersonality;
		switch(gp) {
		case DRUNKEN:
			guardPersonality = 'd';
			break;
		case ROOKIE:
			guardPersonality = 'r';
			break;
		case SUSPICIOUS:
			guardPersonality = 's';
			break;
		default:
			guardPersonality = 'e';
			break;
		
		}
		
		int mapSize = 10;
		map = new Map(mapSize,ogreNumber, guardPersonality);
		endGame = false;
		setShowCli(true);
	}
	
	/*******************GET FUNTIONS*******************/
	
	public boolean isShowCli()
	{
		return this.showCli;
	}
	
	public boolean isEndGame() {
		return endGame;
	}
	
	public GameStatusDisplay getDisplay() {
		return display;
	}
	
	public Map getMap() {
		return map;
	}
	
	/*******************SET FUNCTIONS*******************/
	
	public void setShowCli(boolean showCli) {
		this.showCli = showCli;
		map.setShowCli(showCli);
	}

	public  void setEndGame(boolean endGame) {
		this.endGame = endGame;
	}

	public void setDisplay(GameStatusDisplay display) {
		Game.display = display;
	}

	public void setMap(Map map) {
		this.map = map;
	}
	
	/*******************TO STRING*******************/
	
	public String toString()
	{
		String res = "";
		for (int i = 0 ; i < map.getMapScheme().length; i++)
		{
			res += String.valueOf(map.getMapScheme()[i]) + "\n";
		}
		return res;
	}
	
	/*******************GAME MANAGEMENT FUNCTION*******************/
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
