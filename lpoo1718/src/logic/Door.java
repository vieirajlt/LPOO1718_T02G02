package logic;

import java.io.Serializable;

/**
 * @author João Vieira
 * @author Susana Lima
 */
public class Door implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6317099912561121522L;
	private int x;
	private int y;
	
	/*******************CONSTRUCTORS*******************/
	
	/**
	 * Creates a Door in preferred x and y values.
	 * 
	 * @param x
	 * @param y
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
