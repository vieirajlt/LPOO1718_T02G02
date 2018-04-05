package logic;

import java.io.Serializable;

/**
 * This class is used to represent possible exits openers for the 
 * {@link Hero} on the current Map.
 * 
 * @author João Vieira
 * @author Susana Lima
 * @see Map
 */
public class Unlocker implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -932204565800543432L;
	/**
	 * x and y are the current coordinates relative to the position on {@link Map}.
	 */
	private int x;
	private int y;
	/**
	 * symbol is the char representation of the class on {@link Map}.
	 */
	private char symbol;
	/**
	 * isLever is the flag that indicates if this Unlocker is a lever or a key.
	 */
	private boolean isLever;
	
	/*******************CONSTRUCTORS*******************/
	
	/**
	 * Creates an Unlocker in preferred x and y values and given symbol.
	 * Unlocker type is set by isLever.
	 * 
	 * @param x the new value of x
	 * @param y the new value of y
	 * @param symbol the new value of symbol
	 * @param isLever the new value of isLever
	 */
	public Unlocker(int x, int y, char symbol, boolean isLever)
	{
		this.x = x;
		this.y = y;
		this.symbol = symbol;
		this.isLever = isLever;
	}
	
	/**
	 * Creates an Unlocker in preferred x and y values and predefined symbol 'k'.
	 * Unlocker type is set by isLever.
	 * 
	 * @param x the new value of x
	 * @param y the new value of y
	 * @param isLever the new value of isLever
	 */
	public Unlocker(int x, int y,boolean isLever)
	{
		this(x,y,'k',isLever);
	}
	
	
	/*******************GET FUNCTIONS*******************/
	
	/**
	 * Retrieve the value of this Unlocker x.
	 * 
	 * @return this Unlocker x 
	 */
	public int getX() {
		return x;
	}

	/**
	 * Retrieve the value of this Unlocker y.
	 * 
	 * @return this Unlocker y 
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Retrieve the value of this Unlocker symbol.
	 * 
	 * @return this Unlocker symbol 
	 */
	public char getSymbol() {
		return symbol;
	}
	
	/**
	 * Retrieve the value of this Unlocker isLever.
	 * 
	 * @return this Unlocker isLever 
	 */
	public boolean isLever() {
		return isLever;
	}
	
	/*******************SET FUNCTIONS*******************/
	
	/**
	 * Set the value of this Unlocker x.
	 * 
	 * @param x the new value of x
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 *  Set the value of this Unlocker y.
	 *  
	 * @param y the new value of y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Set the value of this Unlocker symbol.
	 * 
	 * @param symbol the new value of symbol
	 */
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
	/*******************UPDATES MANAGEMENT*******************/
	
	/**
	 * Checks if given x and y values are the same as this Unlocker.
	 * 
	 * @param x the coordinate to be checked
	 * @param y the coordinate to be checked
	 * @return true if x and y values are the same, else false
	 */
	public boolean hasReackedUnlocker(int x, int y)
	{
		return (this.x == x && this.y == y);
	}
	
}
