package gui;

public class MapCreationPanel extends GraphicPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5595519726727665363L;
	private char newChar = EMPTY;
	
	public MapCreationPanel() {
		super();
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
	
	public void setNewCharToOgre() {
		setNewChar(OGRE);
	}
	
	public void setNewCharToClub() {
		setNewChar(CLUB);
	}

	public boolean isNewCharHero() {
		return (newChar == HERO);
	}
	
}
