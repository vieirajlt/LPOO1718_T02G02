package gui;

import logic.Map;

/**
 * This class represents a specific type of {@link GraphicPanel}.
 * This specific type is used to store the information regarding the program's
 * level creation option. 
 * 
 * @author João Vieira
 * @author Susana Lima
 * 
 */

public class MapCreationPanel extends GraphicPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5595519726727665363L;
	/**
	 * newChar is a variable that stores the current object (char) to be added in the program's level creation option.
	 */
	private char newChar = EMPTY;
	
	/**
	 * originalMap is a {@link Map} that stores the information regarding the foundation Map where the level created will be 
	 * built on (usually set with the default map scheme).
	 */
	private Map originalMap;
	
	/**
	 * Creates a new MapCreationPanel with the originalMap set to null.
	 */
	public MapCreationPanel() {
		super();
		this.originalMap = null;
	}
	
	/*******************GET FUNCTIONS*******************/
	
	/**
	 * Retrieve the value of this MapCreationPanel originalMap map[y][x].
	 * 
	 * @param x the location on this MapCreationPanel originalMap map[]
	 * @param y the location on this MapCreationPanel originalMap map
	 * @return the char stored on this MapCreationPanel originalMap map
	 */
	public char getOriginalChar(int x, int y) {
		return originalMap.getMapPosition(x, y);
	}
	

	/**
	 * Retrieve this MapCreationPanel newChar value.
	 * 
	 * @return the value of this MapCreationPanel newChar.
	 */
	public char getNewChar() {
		return newChar;
	}
	
	
	/**
	 * Retrieve this MapCreationPanel originalMap value. 
	 * 
	 * @return this MapCreationPanel originalMap
	 */
	public Map getOriginalMap() {
		return originalMap;
	}
	
	/**
	 * Check if this MapCreationPanel value is HERO or not
	 * 
	 * @return true if this MapCreationPanel newChar value is HERO, false otherwise
	 */
	public boolean isNewCharHero() {
		return (newChar == HERO);
	}
	
	/**
	 * Check if this MapCreationPanel value is LEVER or not
	 * 
	 * @return true if this MapCreationPanel newChar value is LEVER, false otherwise
	 */
	public boolean isNewCharKey() {
		return (newChar == LEVER);
	}

	
	
	/*******************SET FUNCTIONS*******************/
	
	/**
	 * Set the value of this MapCreationPanel newChar.
	 * 
	 * @param newChar this MapCreationPanel new newChar value
	 */
	public void setNewChar(char newChar) {
		this.newChar = newChar;
	}
	

	/**
	 * Set the value of this MapCreationPanel newChar to EMPTY.
	 * Calls the setNewChar function.
	 */
	public void setNewCharEmpty() {
		setNewChar(EMPTY);
	}

	/**
	 * Set the value of this MapCreationPanel newChar to WALL.
	 * Calls the setNewChar function.
	 */
	public void setNewCharToWall() {
		setNewChar(WALL);
	}
	
	/**
	 * Set the value of this MapCreationPanel newChar to DOOR.
	 * Calls the setNewChar function.
	 */
	public void setNewCharToDoor() {
		setNewChar(DOOR);
	}
	
	
	/**
	 * Set the value of this MapCreationPanel newChar to LEVER.
	 * Calls the setNewChar function.
	 */
	public void setNewCharToKey() {
		setNewChar(LEVER);
	}
	
	/**
	 * Set the value of this MapCreationPanel newChar to HERO.
	 * Calls the setNewChar function.
	 */
	public void setNewCharToHero() {
		setNewChar(HERO);
	}
	
	/**
	 * Set the value of this MapCreationPanel newChar to ARMEDHERO.
	 * Calls the setNewChar function.
	 */
	public void setNewCharToArmedHero() {
		setNewChar(ARMEDHERO);
	}
	
	/**
	 * Set the value of this MapCreationPanel newChar to OGRE.
	 * Calls the setNewChar function.
	 */
	public void setNewCharToOgre() {
		setNewChar(OGRE);
	}
	
	/**
	 * Set the value of this MapCreationPanel newChar to CLUB.
	 * Calls the setNewChar function.
	 */
	public void setNewCharToClub() {
		setNewChar(CLUB);
	}

	/**
	 * Set the value of this MapCreationPanel originalMap.
	 * 
	 * @param originalMap the new value of this MapCreationPanel originalMap
	 */
	public void setOriginalMap(Map originalMap) {
		this.originalMap = originalMap;
	}
	
}
