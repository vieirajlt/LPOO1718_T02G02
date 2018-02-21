package logic;
import cli.heroStatusDisplay;

public class Hero extends Character{
	private boolean escaped;
	private boolean wallColliding; //for collisions with closed doors and common walls
	private boolean objectColliding; //for collisions with levers/keys
	private boolean exitColliding;
	private boolean exitOpened;
	private boolean captured;
	private boolean fatality;
	private heroStatusDisplay display;
	
	
	//CONSTRUCTORS
	
	public Hero(int valX, int valY) {
		super(valX, valY, 'H');
		escaped = false;
		objectColliding = false;
		wallColliding = false;
		captured = false;
		exitOpened = false;
		fatality = false;
		display = new heroStatusDisplay();
	}

	//GET FUNCTIONS
	
	public boolean getEscaped() {
		return escaped;
	}
	
	public boolean getWallColliding() {
		return wallColliding;
	}
	
	public boolean getObjectColliding() {
		return objectColliding;
	}
	
	public boolean getExitColliding() {
		return exitColliding;
	}
	
	public boolean getExitOpened() {
		return exitOpened;
	}
	
	public boolean getCaptured() {
		return captured;
	}
	
	public boolean getFatality() {
		return fatality;
	}
	
	//SET FUNCTIONS
	
	public void setEscaped(boolean newE) {
		escaped = newE;
	}
	
	public void setWallColliding(boolean newCol) {
		wallColliding = newCol;
	}
	
	public void setObjectColliding(boolean newCol) {
		objectColliding = newCol;
	}
	
	public void setExitColliding(boolean newCol) {
		exitColliding = newCol;
	}
	
	public void setExitOpened(boolean newSet) {
		exitOpened = newSet;
	}
	
	public void setCaptured(boolean newCap) {
		captured = newCap;
	}
	
	public void setFatality(boolean newFat) {
		fatality = newFat;
	}
	
	//HERO MANAGEMENT FUNCTION
	
	public void updateHero() {
		
		if(wallColliding) {
			display.wallColliding();
			super.setToPreviousPosition();
			wallColliding = false;
		} else if(objectColliding) {
			if(super.getSymbol() == 'K')
				display.keyColliding();
			else {
				display.doorColliding();
				super.setToPreviousPosition();
				exitOpened = true;
			}
			objectColliding = false;
		} else if(exitColliding) {
			if(exitOpened) {
				escaped = true;
			} else if(super.getSymbol() == 'K') {
				display.exitOpen();
				super.setToPreviousPosition();
				exitOpened = true;
			} else {//is like a closed door
				display.wallColliding();
				super.setToPreviousPosition();
			}
			exitColliding = false;
		}

	}

}
