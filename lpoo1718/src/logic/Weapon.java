package logic;

import java.io.Serializable;


/**
 * This class represents a weapon in the in the game.
 * A weapon can be visible or not.
 * Used by some of the characters.
 * 
 * @author João Vieira
 * @author Susana Lima
 *
 * @see Character
 */

public class Weapon implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1994817126402519464L;
	/**
	 * X and Y are the current coordinates relative to the position on {@link Map}.
	 */
	private int X;
	private int Y;
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
	 * move is a flag indicating if this Weapon can move or not.
	 */
	private boolean move;

	/**
	 * visible is a flag indicating if this Weapon is visible or not.
	 */
	private boolean visible;

	/*******************CONSTRUCTORS*******************/

	/**
	 * Creates a Weapon in preferred X and Y values and given symbol.
	 * 
	 * @param newX the new value of X
	 * @param newY the new value of Y
	 * @param newSymbol the new value of symbol
	 */
	public Weapon(int newX, int newY, char newSymbol, boolean newMove) {
		X = newX;
		Y = newY;
		symbol = newSymbol;
		prevX = -1;
		prevY = -1;
		visible = true;
		move = newMove;
	}

	public Weapon(int newX, int newY, char newSymbol) {
		this(newX,newY,newSymbol,true);
	}

	/*******************GET FUNCTIONS*******************/
	/**
	 * Retrieve the value of this Weapon move.
	 * 
	 * @return this Weapon move
	 */
	public boolean isMove() {
		return move;
	}

	/**
	 * Retrieve the value of this Weapon X.
	 * 
	 * @return this Weapon X
	 */
	public int getX() {
		return X;
	}

	/**
	 * Retrieve the value of this Weapon Y.
	 * 
	 * @return this Weapon Y
	 */
	public int getY() {
		return Y;
	}

	/**
	 * Retrieve the value of this Weapon prevX.
	 * 
	 * @return this Weapon prevX
	 */
	public int getPrevX() {
		return prevX;
	}

	/**
	 * Retrieve the value of this Weapon prevY.
	 * 
	 * @return this Weapon prevY
	 */
	public int getPrevY() {
		return prevY;
	}

	/**
	 * Retrieve the value of this Weapon symbol.
	 * 
	 * @return this Weapon symbol 
	 */
	public char getSymbol() {
		return symbol;
	}

	/**
	 * Retrieve the value of this Weapon visible.
	 * 
	 * @return this Weapon visible 
	 */
	public boolean isVisible() {
		return visible;
	}

	/*******************SET FUNCTIONS*******************/

	/**
	 * Retrieve the value of this Weapon symbol.
	 * 
	 * @return this Weapon symbol 
	 */
	public void setSymbol(char newSymbol) {
		symbol = newSymbol;
	}

	/**
	 * Set the value of this Weapon X, updating prevX.
	 * 
	 * @param newX the new value of X
	 */
	public void setX(int newX) {
		prevX = X;
		X = newX;
	}

	/**
	 *  Set the value of this Weapon Y, updating prevY.
	 *  
	 * @param newY the new value of Y
	 */
	public void setY(int newY) {
		prevY = Y;
		Y = newY;
	}

	/**
	 * Set this Weapon position, setting both the values of X and Y.
	 * 
	 * @param newX the new value of X
	 * @param newY the new value of Y
	 */
	public void setPosition(int newX, int newY) {
		setX(newX);
		setY(newY);
	}

	/**
	 * Set this Weapon move value.
	 * 
	 * @param move the new value of this Weapon move
	 */
	public void setMove(boolean move) {
		this.move = move;
	}

	/**
	 * Set this Weapon visible value.
	 * 
	 * @param visible the new value of this Weapon visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/*******************UPDATES MANAGEMENT*******************/

	/**
	 * Set this Weapon position to one unit above the current position.
	 * Updates values of prevX and prevY to the current position values.
	 * Decreases this Weapon Y by a unit (equivalent to moving up).
	 */
	public void moveUp() {
		prevY = Y;
		prevX = X;
		--Y;
	}

	/**
	 * Set this Weapon position to one unit under the current position.
	 * Updates values of prevX and prevY to the current position values.
	 * Increases this Weapon Y by a unit (equivalent to moving down).
	 */
	public void moveDown() {
		prevY = Y;
		prevX = X;
		++Y;
	}

	/**
	 * Set this Weapon position to one unit to the left of the current position.
	 * Updates values of prevX and prevY to the current position values.
	 * Decreases this Weapon X by a unit (equivalent to moving left).
	 */
	public void moveLeft() {
		prevY = Y;
		prevX = X;
		--X;
	}

	/**
	 * Set this Weapon position to one unit to the right of the current position.
	 * Updates values of prevX and prevY to the current position values.
	 * Increases this Weapon X by a unit (equivalent to moving right).
	 */
	public void moveRight() {
		prevY = Y;
		prevX = X;
		++X;
	}

	/**
	 * Set this Weapon X and Y values accordingly to the command given.
	 * Calls upon this Class move*() functions in order to do that update.
	 * 
	 * @param command the char used to update this Weapon position
	 */
	public void updatePosition(char command) {
		if(!move)
		{
			setToPreviousPosition();
			return;
		}
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
			System.out.println("Wrong input\n");
			return;
		}
	}

	/**
	 * Set this Weapon X and Y values to prevX and prevY respectively.
	 * Places Weapon in the previous position.
	 */
	public void setToPreviousPosition() {
		X = prevX;
		Y = prevY;
	}

	/**
	 * Given a specific command, it return the calculated command that can
	 * move this Weapon in the opposite direction
	 * 
	 * @param command the char to be used as reference to move this Weapon 
	 * in the opposite direction
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
	 * Checks if this Weapon and character c 
	 * are in hitting proximity (distance minor or equal to one unit).
	 * 
	 * @param c the Character with which the proximity is checked
	 * @return true if in hitting proximity, else false
	 */
	public boolean isHit(Character c) {
		//if distance to weapon is equal or inferior to 1, get fatality
		if((Math.abs(c.getY()-this.getY()) + Math.abs(c.getX()-this.getX())) <= 1) {
			return true;
		}
		return false;
	}

}
