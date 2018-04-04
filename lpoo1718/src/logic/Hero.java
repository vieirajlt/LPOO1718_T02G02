package logic;
import cli.heroStatusDisplay;

public class Hero extends Character {
	/**
	 * 
	 */
	private static final long serialVersionUID = -332143168894865218L;
	private boolean escaped;
	private boolean wallColliding; //for collisions with closed doors and common walls
	private boolean objectColliding; //for collisions with levers/keys
	private boolean exitColliding;
	private boolean exitOpened;
	private boolean captured;
	private boolean fatality;
	private boolean steppedGuard;
	
	static private heroStatusDisplay display = new heroStatusDisplay();
	
	
	/*******************CONSTRUCTORS*******************/
	
	public Hero(int valX, int valY) {
		this(valX,valY,false);
	}
	
	public Hero(int valX, int valY, boolean hasClub) {
		super(valX, valY, 'H', hasClub);
		escaped = false;
		objectColliding = false;
		wallColliding = false;
		captured = false;
		exitOpened = false;
		fatality = false;
		steppedGuard = false;
		if(this.hasWeapon()) {
			Club club = new Club(valX, valY);
			super.setWeapon(club);
			super.setSymbol('A');
			super.getWeapon().setVisible(false);
		}
	}

	/*******************GET FUNCTIONS*******************/
	
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
	
	public boolean hasSteppedGuard() {
		return steppedGuard;
	}
	
	/*******************SET FUNCTIONS*******************/
	
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
	
	public void setSteppedGuard(boolean steppedGuard) {
		this.steppedGuard = steppedGuard;
	}
	
	/*******************HERO MANAGEMENT FUNCTIONS*******************/
	
//	public void updateHero() {
//		
//		if(wallColliding) {
//			display.wallColliding(isShowCli());
//			super.setToPreviousPosition();
//			if(super.hasWeapon())
//				super.getWeapon().setToPreviousPosition();
//			wallColliding = false;
//		} else if(objectColliding) {
//			if(super.getSymbol() == 'K')
//				display.keyColliding(isShowCli());
//			else {
//				display.doorColliding(isShowCli());
//				super.setToPreviousPosition();
//				if(super.hasWeapon())
//					super.getWeapon().setToPreviousPosition();
//				exitOpened = true;
//			}
//			objectColliding = false;
//		} else if(exitColliding) {
//			if(exitOpened) {
//				escaped = true;
//			} else if(super.getSymbol() == 'K') {
//				display.exitOpen(isShowCli());
//				super.setToPreviousPosition();
//				if(super.hasWeapon())
//					super.getWeapon().setToPreviousPosition();
//				exitOpened = true;
//			} else {//is like a closed door
//				display.wallColliding(isShowCli());
//				super.setToPreviousPosition();
//				if(super.hasWeapon())
//					super.getWeapon().setToPreviousPosition();
//			}
//			exitColliding = false;
//		}
//
//	}
	
	
	public void updateHero() {
		if(wallColliding) {
			updateHeroWallColliding();
		} else if(objectColliding) {
			updateHeroObjectColliding();
		} else if(exitColliding) {
			updateHeroExitColliding();
		}
	}

	
	private void updateHeroExitColliding() {
		if(exitOpened) {
			escaped = true;
		} else if(super.getSymbol() == 'K') {
			display.exitOpen(isShowCli());
			setHeroToPreviousPosition();
			exitOpened = true;
		} else {//is like a closed door
			display.wallColliding(isShowCli());
			setHeroToPreviousPosition();
		}
		exitColliding = false;
	}

	
	private void updateHeroObjectColliding() {
		if(super.getSymbol() == 'K')
			display.keyColliding(isShowCli());
		else {
			display.doorColliding(isShowCli());
			setHeroToPreviousPosition();
			exitOpened = true;
		}
		objectColliding = false;
	}

	private void updateHeroWallColliding() {
		display.wallColliding(isShowCli());
		setHeroToPreviousPosition();
		wallColliding = false;
	}
	
	private void setHeroToPreviousPosition() {
		super.setToPreviousPosition();
		setWeaponToPreviousPosition();
	}

	private void setWeaponToPreviousPosition() {
		if(super.hasWeapon())
			super.getWeapon().setToPreviousPosition();
	}

	

	public void updatePosition(char command) {
		super.updatePosition(command);
		
		if(this.hasWeapon()) {
			this.getWeapon().setPosition(super.getX(), super.getY());
		}
	}
	
	
	public void addWeapon()
	{
		if(!this.hasWeapon()) {
			Club club = new Club(this.getX(), this.getY());
			super.setWeapon(club);
			super.setSymbol('A');
			super.getWeapon().setVisible(false);
			this.setHasWeapon(true);
		}
	}

	@Override
	public boolean isMove() {
		return true;
	}

}
