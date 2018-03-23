package logic;

import java.io.Serializable;

public class Door implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6317099912561121522L;
	private int x;
	private int y;
	
	/*******************CONSTRUCTORS*******************/
	
	public Door(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/*******************GET FUNCTIONS*******************/
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	/*******************SET FUNCTIONS*******************/
}
