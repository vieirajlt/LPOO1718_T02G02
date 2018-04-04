package logic;

import java.io.Serializable;
import java.util.ArrayList;

import cli.GameStatusDisplay;
import exception.InvalidSelectedLevelException;

/**
 * This class is responsible for the game loop. It has status flags
 * that coordinate the game operations.
 * 
 * @author João Vieira
 * @author Susana Lima
 */
public class Game implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 476316704894951892L;
	/**
	 * endGame is a flag that indicates if it's the end of this game
	 */
	private boolean endGame;
	/**
	 * showCli is the flag indicating if display should be used.
	 */
	private boolean showCli;
	/**
	 * level is the indicator of the level being played or set to be played
	 */
	private int level;
	/**
	 * levels is a container stores all levels that can be played in the current
	 * game session.
	 */
	private ArrayList<Map> levels;
	
	/**
	 * display is the user friendly information representing this Game status.
	 */
	private static GameStatusDisplay display = new GameStatusDisplay();

	/*******************CONSTRUCTORS*******************/
	
	/**
	 * Creates a Game set to the first level. The levels are the two
	 * predefined levels used in class. showCli is predefined as true.
	 */
	public Game()
	{
		level = 0;
		initializeLevels();
		endGame = false;
		setShowCli(true);
	}
	
	/**
	 * Creates a Game set to the first level. The levels are the two
	 * predefined levels used in class except that the guard type/personality
	 * can be changed and the number of ogres can too. showCli is predefined 
	 * as true.
	 * 
	 * @param ogreNumber the number of ogres
	 * @param gp the guard personality/type
	 */
	public Game(int ogreNumber, GuardPersonality gp)
	{
		level = 0;
		initializeLevelsModified(ogreNumber, gp);
		endGame = false;
		setShowCli(true);
	}
	
	/**
	 * Creates a Game set to the first and only level. defined on newMapConfig.
	 * The level can also have or not movable enemies, movable weapons and a key
	 * or a lever (movableChar, movableWeapon and isLever, respectively).
	 * 
	 * @param newMapConfig the variable use to create the map
	 * @param movableChar the indicator of enemies movement
	 * @param movableWeapon the indicator of enemies' weapons movement
	 * @param isLever the indicator if newly made level will have a key
	 */
	public Game(char[][] newMapConfig, boolean movableChar, boolean movableWeapon, boolean isLever) {
		level = 0;
		levels = new ArrayList<Map>();
		levels.add(new Map(newMapConfig,movableChar, movableWeapon, isLever));
		endGame = false;
	}

	/**
	 * Initializes class used predefined maps.
	 */
	private void initializeLevels() {
		levels = new ArrayList<Map>();
		levels.add(new Map(1));
		levels.add(new Map(2));
	}
	
	/**
	 * Initializes class used predefined maps with the variant of guard personality
	 * and number of ogres.
	 * 
	 * @param ogreNumber the number of ogres
	 * @param gp the guard personality/type
	 */
	private void initializeLevelsModified(int ogreNumber, GuardPersonality gp) {
		levels = new ArrayList<Map>();
		levels.add(new Map(gp, 1));
		levels.add(new Map(ogreNumber, 2));
	}
	
	/**
	 * Adds level to this Game.
	 * 
	 * @param map the level's map to be added
	 */
	public void addLevel(Map map) {
		levels.add(map);
	}
	
	/**
	 * Adds various levels to this Game.
	 * 
	 * @param lvls the level's array of map to be added
	 */
	public void addLevels(ArrayList<Map> lvls) {
		levels.addAll(lvls);
	}
	
	/*******************GET FUNTIONS*******************/
	
	/**
	 * Retrieve the value of this Game showCli.
	 * 
	 * @return this Game showCli
	 */
	public boolean isShowCli()
	{
		return this.showCli;
	}
	
	/**
	 * Retrieve the value of this Game endGame.
	 * 
	 * @return this Game endGame
	 */
	public boolean isEndGame() {
		return endGame;
	}
	
	/**
	 * Retrieve the value of this Game display.
	 * 
	 * @return this Game display
	 */
	public GameStatusDisplay getDisplay() {
		return display;
	}
	
	/**
	 * Retrieve the value of this Game current level
	 * 
	 * @return this Game levels.get(level)
	 */
	public Map getCurrentMap() {
		return levels.get(level);
	}
	
	/**
	 * Retrieve the value of this Game level indicator
	 * 
	 * @return this Game level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Retrieve the value of this Game number of levels
	 * 
	 * @return this Game levels.size()
	 */
	public int getNumberLevels() {
		return levels.size();
	}
	
	/*******************SET FUNCTIONS*******************/
	
	/**
	 * Set the value of this Game level to biggest value possible.
	 */
	public void setToLastLevel() {
		this.level = levels.size()-1;
	}

	/**
	 * Set the value of this Game level.
	 * 
	 * @param level the selected level
	 * @throws InvalidSelectedLevelException
	 */
	public void setLevel(int level) throws InvalidSelectedLevelException{
		if(level >= levels.size())
			throw new InvalidSelectedLevelException();
		this.level = level;
	}

	/**
	 * Set the value of this Game showCli and its {@link Map} showCli.
	 * 
	 * @param showCli the new value of showCli
	 */
	public void setShowCli(boolean showCli) {
		this.showCli = showCli;
		for(int i = 0; i < levels.size(); ++i) {
			levels.get(i).setShowCli(showCli);
		}
	}

	/**
	 * Set the value of this Game endGame.
	 * 
	 * @param endGame the new value of endGame
	 */
	public  void setEndGame(boolean endGame) {
		this.endGame = endGame;
	}

	/**
	 * Set the value of this Game current map, indicated by level.
	 * 
	 * @param map the new value of the current map
	 * @return this Game level
	 */
	public int setCurrentMap(Map map) {
		levels.set(level, map);
		return level;
	}
	
	/*******************TO STRING*******************/
	
	/**
	 *  Retrieve the value of this Game specified level has a {@link String}
	 * 
	 * @param level the map to be returned
	 * @return current level {@link Map}
	 */
	public String toString(int level)
	{
		return levels.get(level).toString();
	}
	
	/**
	 * Retrieve the value of this Game current level has a {@link String}
	 */
	public String toString()
	{
		return levels.get(level).toString();
	}
	
	/*******************GAME MANAGEMENT FUNCTION*******************/
	
	/**
	 * Updates this Game status bassed on a char that acts as a position
	 * modifier for the {@link Hero}.
	 * 
	 * @param command the value used to update {@link Hero} position
	 */
	public void updateGame(char command) {
		
		ArrayList<String> log = new ArrayList<String>();
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
