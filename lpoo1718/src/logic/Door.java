package logic;

import java.io.Serializable;

/**
 * This class represents a possible exit on the {@link Map}
 * where the {@link Hero} can go in order to complete the 
 * current level
 * 
 * @author João Vieira
 * @author Susana Lima
 */
public class Door implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6317099912561121522L;
	/**
	 * x and y are the current coordinates relative to the position on {@link Map}.
	 */
	private int x;
	private int y;
	
	/*******************CONSTRUCTORS*******************/
	
	/**
	 * Creates a Door in preferred x and y values.
	 * 
	 * @param x the new value of x
	 * @param y the new value of y
	 */
	public Door(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/*******************GET FUNCTIONS*******************/
	
	/**
	 * Retrieve the value of this Door x.
	 * 
	 * @return this Door x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Retrieve the value of this Door y.
	 * 
	 * @return this Door y
	 */
	public int getY() {
		return y;
	}
	
	/*******************SET FUNCTIONS*******************/
}
