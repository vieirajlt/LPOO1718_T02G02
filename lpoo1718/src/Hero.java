
public class Hero extends Character{
	boolean escaped;
	boolean wallColliding; //for collisions with closed doors and common walls
	boolean objectColliding; //for collisions with levers/keys
	boolean exitColliding;
	boolean exitOpened;
	boolean captured;
	boolean fatality;
	
	//CONSTRUCTORS
	
	public Hero(int valX, int valY) {
		super(valX, valY, 'H');
		escaped = false;
		objectColliding = false;
		wallColliding = false;
		captured = false;
		exitOpened = false;
		fatality = false;
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
			System.out.println("Ouch, try not to faceplant next time!");
			super.setToPreviousPosition();
			wallColliding = false;
		} else if(objectColliding) {
			if(super.getSymbol() == 'K')
				System.out.println("Key aquired.");
			else {
				System.out.println("Exit opened, time to escape!");
				super.setToPreviousPosition();
				exitOpened = true;
			}
			objectColliding = false;
		} else if(exitColliding) {
			if(exitOpened) {
				escaped = true;
			} else if(super.getSymbol() == 'K') {
				System.out.println("Exit opened, time to escape!");
				super.setToPreviousPosition();
				exitOpened = true;
			} else {//is like a closed door
				System.out.println("Ouch, try not to faceplant next time!");
				super.setToPreviousPosition();
			}
			exitColliding = false;
		}

	}

}
