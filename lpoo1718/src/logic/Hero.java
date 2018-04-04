package logic;
import cli.heroStatusDisplay;

/**
 * This class represents a specific type of {@link Character}.
 * This specific type is the game's hero (good guy), its main purpose is to end the game
 * without being fatally injured or captured by the different levels enemies.
 * 
 * @author João Vieira
 * @author Susana Lima
 *
 */
public class Hero extends Character {
	/**
	 * 
	 */
	private static final long serialVersionUID = -332143168894865218L;
	/**
	 * escaped is a flag indicating if this Hero has escaped throw the exit.
	 */
	private boolean escaped;
	
	/**
	 * wallColliding is a flag indicating if this Hero is colliding with a wall or a closed door.
	 */
	private boolean wallColliding; 
	
	/**
	 * objectColliding is a flag indicating if this Hero is colliding with a lever or a key.
	 */
	private boolean objectColliding; 
	
	/**
	 * exitColliding is a flag indicating if this Hero is colliding with an exit.
	 */
	private boolean exitColliding;
	
	/**
	 * exitOpened is a flag indicating if a door is opened or not.
	 */
	private boolean exitOpened;
	
	/**
	 * captured is a flag indicating if this Hero was captured (by a guard or by an ogre).
	 */
	private boolean captured;
	
	/**
	 * fatality is a flag indicating if this Hero has suffered a fatality, if was hit by a weapon.
	 */
	private boolean fatality;
	
	/**
	 * steppedGuard is a flag indicating if this Hero has stepped on a sleeping guard.
	 */
	private boolean steppedGuard;
	
	
	/**
	 * display is the user friendly information representing this Hero status.
	 */
	static private heroStatusDisplay display = new heroStatusDisplay();
	
	
	/*******************CONSTRUCTORS*******************/
	
	/**
	 * Creates a new Hero in preferred x and y values. 
	 * 
	 * @param valX the new value of x
	 * @param valY the new value of y
	 */
	public Hero(int valX, int valY) {
		this(valX,valY,false);
	}
	
	/**
	 * Creates a new Hero in preferred x and y values with or without a weapon accordingly to the hasClub value.
	 * All this Hero flags are set to false
	 * 
	 * @param valX the new value of x
	 * @param valY the new value of y
	 * @param hasClub true if the Hero to be created is an ArmedHero (has a weapon), false otherwise
	 */
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
	
	/**
	 * Retrieve the value of this Hero escaped.
	 * 
	 * @return this Hero escaped
	 */
	public boolean getEscaped() {
		return escaped;
	}
	
	/**
	 * Retrieve the value of this Hero wallColliding.
	 * 
	 * @return this Hero wallColliding
	 */
	public boolean getWallColliding() {
		return wallColliding;
	}
	
	/**
	 * Retrieve the value of this Hero objectColliding.
	 * 
	 * @return this Hero objectColliding
	 */
	public boolean getObjectColliding() {
		return objectColliding;
	}
	
	/**
	 * Retrieve the value of this Hero exitColliding.
	 * 
	 * @return this Hero exitColliding
	 */
	public boolean getExitColliding() {
		return exitColliding;
	}
	
	/**
	 * Retrieve the value of this Hero exitOpened.
	 * 
	 * @return this Hero exitOpened
	 */
	public boolean getExitOpened() {
		return exitOpened;
	}
	
	
	/**
	 * Retrieve the value of this Hero captured.
	 * 
	 * @return this Hero captured
	 */
	public boolean getCaptured() {
		return captured;
	}
	
	/**
	 * Retrieve the value of this Hero fatality.
	 * 
	 * @return this Hero fatality
	 */
	public boolean getFatality() {
		return fatality;
	}
	
	/**
	 * Retrieve the value of this Hero steppedGuard.
	 * 
	 * @return this Hero steppedGuard
	 */
	public boolean hasSteppedGuard() {
		return steppedGuard;
	}
	
	/*******************SET FUNCTIONS*******************/
	
	/**
	 * Set the value of this Hero escaped.
	 * 
	 * @param newE the new value of escaped
	 */
	public void setEscaped(boolean newE) {
		escaped = newE;
	}
	
	/**
	 * Set the value of this Hero wallColliding.
	 * 
	 * @param newCol the new value of wallColliding
	 */
	public void setWallColliding(boolean newCol) {
		wallColliding = newCol;
	}
	
	/**
	 * Set the value of this Hero objectColliding.
	 * 
	 * @param newCol the new value of objectColliding
	 */
	public void setObjectColliding(boolean newCol) {
		objectColliding = newCol;
	}
	
	/**
	 * Set the value of this Hero exitColliding.
	 * 
	 * @param newCol the new value of exitColliding
	 */
	public void setExitColliding(boolean newCol) {
		exitColliding = newCol;
	}
	
	/**
	 * Set the value of this Hero exitOpened.
	 * 
	 * @param newSet the new value of exitOpened
	 */
	public void setExitOpened(boolean newSet) {
		exitOpened = newSet;
	}
	
	/**
	 * Set the value of this Hero captured.
	 * 
	 * @param newCap the new value of captured
	 */
	public void setCaptured(boolean newCap) {
		captured = newCap;
	}
	
	/**
	 * Set the value of this Hero fatality
	 * 
	 * @param newFat the new value of fatality
	 */
	public void setFatality(boolean newFat) {
		fatality = newFat;
	}
	
	/**
	 * Set the value of this Hero steppedGuard
	 * 
	 * @param steppedGuard the new value of steppeddGuard
	 */
	public void setSteppedGuard(boolean steppedGuard) {
		this.steppedGuard = steppedGuard;
	}
	
	/*******************HERO MANAGEMENT FUNCTIONS*******************/
	
	/**
	 * Updates this Hero position and flags having in consideration its current flags values
	 */
	public void updateHero() {
		if(wallColliding) {
			updateHeroWallColliding();
		} else if(objectColliding) {
			updateHeroObjectColliding();
		} else if(exitColliding) {
			updateHeroExitColliding();
		}
	}

	/**
	 * Update this Hero position and flags if he is colliding with an exit (exitColliding is true).
	 * This Hero position is set to the previous one in the case the exitOpened is false
	 */
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

	/**
	 * Updates this Hero position and flags if he is colliding with a key or a lever (objectColliding is true).
	 * This Hero position is set to the previous one.
	 */
	private void updateHeroObjectColliding() {
		if(super.getSymbol() == 'K')
			display.keyColliding(isShowCli());
		else {
			display.leverColliding(isShowCli());
			setHeroToPreviousPosition();
			exitOpened = true;
		}
		objectColliding = false;
	}

	/**
	 * Updates this Hero position and flags if he is colliding with a wall (wallColliding is true).
	 * This Hero position is set to the previous one.
	 */
	private void updateHeroWallColliding() {
		display.wallColliding(isShowCli());
		setHeroToPreviousPosition();
		wallColliding = false;
	}
	
	/**
	 * Set this Hero to the previous position
	 * Set the Hero's weapon to previous position (if this Hero has a weapon)
	 */
	private void setHeroToPreviousPosition() {
		super.setToPreviousPosition();
		setWeaponToPreviousPosition();
	}

	/**
	 * If this hero has a weapon, set the weapon's position to the previous one
	 */
	private void setWeaponToPreviousPosition() {
		if(super.hasWeapon())
			super.getWeapon().setToPreviousPosition();
	}

	
	/**
	 * Updates this Hero current position accordingly to the given command.
	 * If this Hero has a weapon also updates its position accordingly. 
	 */
	public void updatePosition(char command) {
		super.updatePosition(command);
		
		if(this.hasWeapon()) {
			this.getWeapon().setPosition(super.getX(), super.getY());
		}
	}
	
	/**
	 * Creates and adds to this Hero a new {@link Club}, if this Hero does not already have one.
	 * The new weapon is positioned on heros' position.
	 */
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
