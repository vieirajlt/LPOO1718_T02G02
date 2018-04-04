package logic;

import java.io.Serializable;

import cli.CharacterStatusDisplay;

/**
 * This class is an abstract class that will be the base for 
 * every character in game, giving basic movability to the 
 * character. It also associates them with a weapon, if that
 * is the case.
 * 
 * @author João Vieira
 * @author Susana Lima
 * @see Weapon
 */
public abstract class Character implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8571121080640766560L;
	/**
	 * x and y are the current coordinates relative to the position on {@link Map}.
	 */
	private int x;
	private int y;
	/**
	 * prevX and prevY are the coordinates relative to the position on {@link Map}.
	 * This represent the position occupied before the current position.
	 * Useful for collision control.
	 */
	private int prevX;
	private int prevY;

	/**
	 * symbol is the char representation of the class on {@link Map}.
	 */
	private char symbol;

	/**
	 * hasWeapon is the flag of the existence of a weapon in this Character.
	 */
	private boolean hasWeapon;
	/**
	 *	weapon is the {@link Weapon} that this Characer has.
	 */
	private Weapon weapon;
	/**
	 * showCli is the flag indicating if display should be used.
	 */
	private boolean showCli;
	/**
	 * display is the user friendly information representing this Character status.
	 */
	static private CharacterStatusDisplay display = new CharacterStatusDisplay();

	/*******************CONSTRUCTORS*******************/

	/**
	 * Creates a Character in preferred x and y values and given symbol.
	 * Case he has a weapon, set hasWeapon to true.
	 * showCli is predefined as true.
	 * 
	 * @param newX
	 * @param newY
	 * @param newSymbol
	 * @param hasWeapon
	 */
	public Character(int newX, int newY, char newSymbol, boolean hasWeapon) {
		x = newX;
		y = newY;
		symbol = newSymbol;
		prevX = -1;
		prevY = -1;
		this.hasWeapon = hasWeapon;
		setShowCli(true);
	}

	/*******************GET FUNCTIONS*******************/
	
	/**
	 * Retrieve the value of this Character showCli.
	 * 
	 * @return this Character showCli
	 */
	public boolean isShowCli() {
		return showCli;
	}

	/**
	 * Retrieve the value of this Character x.
	 * 
	 * @return this Character x 
	 */
	public int getX() {
		return x;
	}

	/**
	 * Retrieve the value of this Character y.
	 * 
	 * @return this Character y 
	 */
	public int getY() {
		return y;
	}

	/**
	 * Retrieve the value of this Character prevX.
	 * 
	 * @return this Character prevX 
	 */
	public int getPrevX() {
		return prevX;
	}

	/**
	 * Retrieve the value of this Character prevY.
	 * 
	 * @return this Character prevY 
	 */
	public int getPrevY() {
		return prevY;
	}

	/**
	 * Retrieve the value of this Character symbol.
	 * 
	 * @return this Character symbol 
	 */
	public char getSymbol() {
		return symbol;
	}

	/**
	 * Retrieve the value of this Character hasWeapon.
	 * 
	 * @return this Character hasWeapon 
	 */
	public boolean hasWeapon() {
		return hasWeapon;
	}

	
	/**
	 * Retrieve the value of this Character weapon.
	 * 
	 * @return this Character weapon 
	 */
	public Weapon getWeapon() {
		return weapon;
	}
	
	/**
	 * Retrieve information about Character ability to move.
	 * 
	 * @return if Character is able to move
	 */
	public abstract boolean isMove();
	
	/*******************SET FUNCTIONS*******************/
	
	/**
	 * Set the value of this Character showCli.
	 * 
	 * @param show
	 */
	public void setShowCli(boolean show) {
		this.showCli = show;
	}

	/**
	 * Set the value of this Character symbol.
	 * 
	 * @param newSymbol
	 */
	public void setSymbol(char newSymbol) {
		symbol = newSymbol;
	}

	/**
	 * Set the value of this Character x, updating prevX.
	 * 
	 * @param newX
	 */
	public void setX(int newX) {
		setPrevX();
		x = newX;
	}

	/**
	 *  Set the value of this Character y, updating prevY.
	 *  
	 * @param newY
	 */
	public void setY(int newY) {
		setPrevY();
		y = newY;
	}
	
	/**
	 * Set the value of this Character prevX to x.
	 */
	public void setPrevX() {
		this.prevX = x;
	}

	/**
	 * Set the value of this Character prevY to y.
	 */
	public void setPrevY() {
		this.prevY = y;
	}

	/**
	 * Set this Character position, setting both the values of x and y.
	 * 
	 * @param newX
	 * @param newY
	 */
	public void setPosition(int newX, int newY) {
		setX(newX);
		setY(newY);
	}

	/**
	 * Set the value of this Character hasWeapon.
	 * 
	 * @param hasWeapon
	 */
	public void setHasWeapon(boolean hasWeapon) {
		this.hasWeapon = hasWeapon;
	}

	/**
	 * Set the value of this Character weapon.
	 * 
	 * @param weapon
	 */
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	/*******************POSITION COMMANDS*******************/

	/**
	 * Set this Character position to one unit above the current position.
	 * Updates values of prevX and prevY to the current position values.
	 * Decreases this Character y by a unit (equivalent to moving up).
	 */
	public void moveUp() {
		prevY = y;
		prevX = x;
		--y;
	}

	/**
	 * Set this Character position to one unit under the current position.
	 * Updates values of prevX and prevY to the current position values.
	 * Increases this Character y by a unit (equivalent to moving down).
	 */
	public void moveDown() {
		prevY = y;
		prevX = x;
		++y;
	}

	/**
	 * Set this Character position to one unit to the left of the current position.
	 * Updates values of prevX and prevY to the current position values.
	 * Decreases this Character x by a unit (equivalent to moving left).
	 */
	public void moveLeft() {
		prevY = y;
		prevX = x;
		--x;
	}

	/**
	 * Set this Character position to one unit to the right of the current position.
	 * Updates values of prevX and prevY to the current position values.
	 * Increases this Character x by a unit (equivalent to moving right).
	 */
	public void moveRight() {
		prevY = y;
		prevX = x;
		++x;
	}

	/**
	 * Set this Character x and y values accordingly to the command given.
	 * Calls upon this Class move*() functions in order to do that update.
	 * 
	 * @param command
	 */
	public void updatePosition(char command) {
		switch(command) {
		case 'u': case 'U':
			moveUp();
			break;
		case 'd': case 'D':
			moveDown();
			break;
		case 'l': case 'L':
			moveLeft();
			break;
		case 'r': case 'R':
			moveRight();
			break;
		case 'E':
			break;
		default:
			display.wrongCommandInput(showCli);
			return;
		}
	}

	/**
	 * Set this Character x and y values to prevX and prevY respectively.
	 * Places Character in the previous position.
	 */
	public void setToPreviousPosition() {
		x = prevX;
		y = prevY;
	}

	/**
	 * Given a specific command, it return the calculated command that can
	 * move this Character in the opposite direction
	 * 
	 * @param command
	 * @return the calculated opposite command
	 */
	public char reverseCommand(char command) {
		switch(command)
		{
		case 'u': case 'U':
			return 'd';
		case 'd': case 'D':
			return 'u';
		case 'l': case 'L':
			return 'r';
		case 'r': case 'R':
			return 'l';
		default :
			return 'E';
		}
	}

	/**
	 * Checks if this Character and character c (in the possibility of having a weapon)
	 * are in capturing proximity (distance minor or equal to one unit).
	 * 
	 * @param c
	 * @return true if in capturing proximity of weapon, else false
	 */
	public boolean isCaptured(Character c) {
		if(!c.hasWeapon())
			if((Math.abs(c.getY()-this.getY()) + Math.abs(c.getX()-this.getX())) <= 1) {
				return true;
			}
		return false;
	}

}

