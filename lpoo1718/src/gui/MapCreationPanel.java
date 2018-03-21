package gui;

import logic.Map;

public class MapCreationPanel extends GraphicPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5595519726727665363L;
	private char newChar = EMPTY;
	private Map originalMap;
	
	public char getOriginalChar(int x, int y) {
		return originalMap.getMapPosition(x, y);
	}
	
	public MapCreationPanel() {
		super();
		this.originalMap = null;
	}
	
	public char getNewChar() {
		return newChar;
	}
	
	public void setNewChar(char newChar) {
		this.newChar = newChar;
	}
	

	public void setNewCharEmpty() {
		setNewChar(EMPTY);
	}

	public void setNewCharToWall() {
		setNewChar(WALL);
	}
	
	public void setNewCharToDoor() {
		setNewChar(DOOR);
	}
	
	public void setNewCharToKey() {
		setNewChar(LEVER);
	}
	
	public void setNewCharToHero() {
		setNewChar(HERO);
	}
	
	public void setNewCharToArmedHero() {
		setNewChar(ARMEDHERO);
	}
	
	public void setNewCharToOgre() {
		setNewChar(OGRE);
	}
	
	public void setNewCharToClub() {
		setNewChar(CLUB);
	}

	public boolean isNewCharHero() {
		return (newChar == HERO);
	}
	
	public boolean isNewCharKey() {
		return (newChar == LEVER);
	}

	public Map getOriginalMap() {
		return originalMap;
	}

	public void setOriginalMap(Map originalMap) {
		this.originalMap = originalMap;
	}
	
}
