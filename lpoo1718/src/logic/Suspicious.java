package logic;

import java.util.Random;

/**
 * This class represents a specific type of {@link Guard} (enemy).
 * This specific type (pseudo)randomly reverses its route orientation.
 * 
 * @author João Vieira
 * @author Susana Lima
 */
public class Suspicious extends Guard {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7323895084101865303L;
	/**
	 * isReverse is a flag indicating if this Suspicious route is to be reversed.
	 */
	private boolean isReverse;
	
	/*******************CONSTRUCTORS*******************/
	/**
	 * Creates a Suspicious in preferred x and y values Suspicious starts with.
	 * showCli is predefined as true. 
	 * 
	 * @param x new x value
	 * @param y new y value
	 */
	public Suspicious(int x, int y)
	{
		super(x, y);
		isReverse = false;
	}
	
	/*******************GET FUNTIONS*******************/
	/**
	 * Retrieve the value of this Suspicious isReverse.
	 * 
	 * @return this Suspicious isReverse
	 */
	public boolean isReverse() {
		return isReverse;
	}

	/*******************SET FUNTIONS*******************/
	
	/**
	 * Set the value of this Suspicious isReverse.
	 * 
	 * @param isReverse the new value of this Suspicious isReverse
	 */
	public void setReverse(boolean isReverse) {
		this.isReverse = isReverse;
	}
	
	/*******************UPDATE MANAGEMENT*******************/
	
	/**
	 * Updates this Drunken status accordingly to its current state.
	 * num is a random number that determines if this Suspicious will reverse
	 * its route or not.
	 */
	public char updateGuard()
	{
		Random rand = new Random();
		int num = rand.nextInt(5);
		if (num == 0)
			isReverse = !isReverse;
		return super.updateGuard(isReverse);
		
	}

}
