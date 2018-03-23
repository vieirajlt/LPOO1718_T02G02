package logic;

import java.io.Serializable;
import java.util.ArrayList;

import cli.GameStatusDisplay;
import exception.InvalidSelectedLevelException;

public class Game implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 476316704894951892L;
	private boolean endGame;
	private boolean showCli;
	private int level;
	private ArrayList<Map> levels;
	
	private static GameStatusDisplay display = new GameStatusDisplay();

	/*******************CONSTRUCTORS*******************/
	
	public Game()
	{
		level = 0;
		initializeLevels();
		endGame = false;
		setShowCli(true);
	}
	
	public Game(int width, int height, boolean isDefaultMap)
	{
		level = 0;
		initializeLevels();
		endGame = false;
		setShowCli(true);
	}
	
	public Game(char[][] newMapConfig)
	{
		this(newMapConfig,true);
	}
	
	public Game(char[][] newMapConfig,boolean isLever)
	{
		level = 0;
		levels = new ArrayList<Map>();
		levels.add(new Map(newMapConfig,isLever));
		endGame = false;
	}
	
	public Game(int ogreNumber, GuardPersonality gp)
	{
		level = 0;
		initializeLevelsModified(ogreNumber, gp);
		endGame = false;
		setShowCli(true);
	}
	
	private void initializeLevels() {
		levels = new ArrayList<Map>();
		levels.add(new Map(1));
		levels.add(new Map(2));
	}
	
	private void initializeLevelsModified(int ogreNumber, GuardPersonality gp) {
		levels = new ArrayList<Map>();
		levels.add(new Map(gp, 1));
		levels.add(new Map(ogreNumber, 2));
	}
	
	public void addLevel(Map map) {
		levels.add(map);
	}
	
	public void addLevels(ArrayList<Map> lvls) {
		levels.addAll(lvls);
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
	
	public Map getCurrentMap() {
		return levels.get(level);
	}
	
	public int getLevel() {
		return level;
	}

	public int getNumberLevels() {
		return levels.size();
	}
	
	public void setToLastLevel() {
		this.level = levels.size()-1;
	}

	public void setLevel(int level) throws InvalidSelectedLevelException{
		if(level >= levels.size())
			throw new InvalidSelectedLevelException();
		this.level = level;
	}

	/*******************SET FUNCTIONS*******************/
	
	public void setShowCli(boolean showCli) {
		this.showCli = showCli;
		for(int i = 0; i < levels.size(); ++i) {
			levels.get(i).setShowCli(showCli);
		}
	}

	public  void setEndGame(boolean endGame) {
		this.endGame = endGame;
	}

	public void setDisplay(GameStatusDisplay display) {
		Game.display = display;
	}

	public int setCurrentMap(Map map) {
		levels.set(level, map);
		return level;
	}
	
	/*******************TO STRING*******************/
	
	public String toString(int level)
	{
		return levels.get(level).toString();
	}
	
	public String toString()
	{
		return levels.get(level).toString();
	}
	
	/*******************GAME MANAGEMENT FUNCTION*******************/
	public void updateGame(char command) {
		
		levels.get(level).updateMap(command);
		levels.get(level).updateMapDisplay();
		if(((Hero) levels.get(level).getCharacters().get(0)).hasSteppedGuard()) {
			endGame = true;
			display.guardAwoken(showCli);
		} else if(((Hero) levels.get(level).getCharacters().get(0)).getCaptured()) {
			display.captured(showCli);
			endGame = true;
		} else if(((Hero) levels.get(level).getCharacters().get(0)).getFatality()) { 
			display.fatality(showCli);
			endGame = true;
		} else if(((Hero) levels.get(level).getCharacters().get(0)).getEscaped()) {
			if(++level < levels.size()) {
				display.nextLevel(showCli);
			} else {
				--level;
				display.gameWon(showCli);
				endGame = true;
			}
		}
	}
	
}
