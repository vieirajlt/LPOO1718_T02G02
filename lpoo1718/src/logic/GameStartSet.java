package logic;

import exception.InvalidBoardSizeException;
import exception.InvalidOgreCountException;

/**
 * This class is a class that serves a variable controler for
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
	 * Creates s GameStartSet with specified width and height, being a user created
	 * level.
	 * 
	 * @param w
	 * @param h
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
	
	public GameStartSet() throws InvalidOgreCountException {
		this(0, GuardPersonality.ROOKIE);
	}
	
	public GameStartSet(int oc, GuardPersonality gp) throws InvalidOgreCountException {

		if(!isOgreCountValid(oc))
			throw new InvalidOgreCountException();
		ogresCount = oc;
		guardPersonality = gp;
		isLvlCreation = false;

	}
	
	public Game startNewGame() {
		if(isLvlCreation) {
			return new Game();
		} else {
			return new Game(ogresCount, guardPersonality);
		}
	}
	
	public int getOgresCount() {
		return ogresCount;
	}
	public void setOgresCount(int ogresCount) {
		this.ogresCount = ogresCount;
	}
	public GuardPersonality getGuardPersonality() {
		return guardPersonality;
	}
	public void setGuardPersonality(GuardPersonality guardPersonality) {
		this.guardPersonality = guardPersonality;
	}
	
	public boolean isOgreCountValid(int oc) {
		return ((oc > 0) && (oc < maxOgresCount));
	}
	
	public boolean isSizeValid(int w, int h) {
		return ((w > 0) && (w < maxWidth) && (h > 0) && (h < maxHeigth));
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeigth() {
		return height;
	}

	public void setHeigth(int heigth) {
		this.height = heigth;
	}

	public boolean isLvlCreation() {
		return isLvlCreation;
	}

	public void setLvlCreation(boolean isLvlCreation) {
		this.isLvlCreation = isLvlCreation;
	}
	
}
