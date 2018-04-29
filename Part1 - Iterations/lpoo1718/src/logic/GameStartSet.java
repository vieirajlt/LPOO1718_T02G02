package logic;

import exception.InvalidBoardSizeException;
import exception.InvalidOgreCountException;

/**
 * This class is a class that serves a variable controller for
 * the new game to be created. 
 * 
 * @author João Vieira
 * @author Susana Lima
 * @see Game
 */
public class GameStartSet {

	/**
	 * Predefined variables for limit imposing when setting a Game level
	 */
	private static final int maxOgresCount = 6;
	private static final int maxWidth = 15;
	private static final int maxHeigth = 15;
	
	/**
	 * ogreCount and guardPersonality are the specifications for the new Game
	 * to be set when considering enemy number/type.
	 */
	private int ogresCount;
	private GuardPersonality guardPersonality;
	
	/**
	 * width and height is the size of the to be Game {@link Map}
	 */
	private int width;
	private int height;
	
	/**
	 * isLvlCreation is a flag that indicates if the to be Game is a user created
	 * level or a predefined class used one.
	 */
	private boolean isLvlCreation;
	
	/**
	 * Creates a GameStartSet with specified width and height, being a user created
	 * level.
	 * 
	 * @param w the new value of width 
	 * @param h the new value of height
	 * @throws InvalidBoardSizeException in case of width and/or height are too big
	 * considering previously set parameters.
	 */
	public GameStartSet(int w, int h) throws InvalidBoardSizeException {
		if(!isSizeValid(w, h))
			throw new InvalidBoardSizeException();
		
		setWidth(w);
		setHeigth(h);
		isLvlCreation = true;
	}
	
	/**
	 * Creates a GameStartSet with specified ogresCount and guardPersonality, that 
	 * is not a created level.
	 * 
	 * @param oc the new value of ogresCount
	 * @param gp the new value of guardPersonality
	 * @throws InvalidOgreCountException when oc is not within predefined limits. 
	 */
	public GameStartSet(int oc, GuardPersonality gp) throws InvalidOgreCountException {

		if(!isOgreCountValid(oc))
			throw new InvalidOgreCountException();
		ogresCount = oc;
		guardPersonality = gp;
		isLvlCreation = false;

	}
	
	/**
	 * Creates a Game based on this GameStartSet variables
	 * 
	 * @return the created Game
	 */
	public Game startNewGame() {
		if(isLvlCreation) {
			return new Game();
		} else {
			return new Game(ogresCount, guardPersonality);
		}
	}
	
	/**
	 * Retrieve the value of this GameStartSet ogresCount.
	 * 
	 * @return this GameStartSet ogresCount
	 */
	public int getOgresCount() {
		return ogresCount;
	}
	
	/**
	 * Retrieve the value of this GameStartSet guardPersonality.
	 * 
	 * @return this GameStartSet guardPersonality
	 */
	public GuardPersonality getGuardPersonality() {
		return guardPersonality;
	}
	
	/**
	 * Retrieve the value of this GameStartSet width.
	 * 
	 * @return this GameStartSet width.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Retrieve the value of this GameStartSet height.
	 * 
	 * @return this GameStartSet height
	 */
	public int getHeigth() {
		return height;
	}
	
	/**
	 * Retrieve the value of this GameStartSet isLvlCreation.
	 * 
	 * @return this GameStartSet isLvlCreation
	 */
	public boolean isLvlCreation() {
		return isLvlCreation;
	}
	
	/**
	 * Set the value of this GameStartSet ogresCount.
	 * 
	 * @param ogresCount the new value of ogresCount
	 */
	public void setOgresCount(int ogresCount) {
		this.ogresCount = ogresCount;
	}
	
	/**
	 * Set the value of this GameStartSet guardPersonality.
	 * 
	 * @param guardPersonality the new value of guardPersonality
	 */
	public void setGuardPersonality(GuardPersonality guardPersonality) {
		this.guardPersonality = guardPersonality;
	}

	/**
	 * Set the value of this GameStartSet width.
	 * 
	 * @param width the new value of width
	 */
	public void setWidth(int width) {
		this.width = width;
	}	

	/**
	 * Set the value of this GameStartSet height.
	 * 
	 * @param heigth the new value of height
	 */
	public void setHeigth(int heigth) {
		this.height = heigth;
	}

	/**
	 * Set the value of this GameStartSet isLvlCreation.
	 * 
	 * @param isLvlCreation the new value of isLvlCreation
	 */
	public void setLvlCreation(boolean isLvlCreation) {
		this.isLvlCreation = isLvlCreation;
	}
	
	/**
	 * Checks if oc is a valid parameter for ogresCount.
	 * 
	 * @param oc the value to verify
	 * @return true if valid, else false
	 */
	public boolean isOgreCountValid(int oc) {
		return ((oc > 0) && (oc < maxOgresCount));
	}
	
	/**
	 * Check if w and h are valid parameters for width and height, respectively.
	 * 
	 * @param w the value to verify for width
	 * @param h the value to verify for height
	 * @return true if valid, else false
	 */
	public boolean isSizeValid(int w, int h) {
		return ((w > 0) && (w < maxWidth) && (h > 0) && (h < maxHeigth));
	}
	
}
