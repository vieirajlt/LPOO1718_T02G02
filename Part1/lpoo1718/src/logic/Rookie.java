package logic;


/**
 * This class represents a specific type of {@link Guard} (enemy).
 * This specific type does the Guard route without any interruptions, always forward.
 * 
 * @author João Vieira
 * @author Susana Lima
 */
public class Rookie extends Guard{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7714088494372915061L;

	/*******************CONSTRUCTORS*******************/
	/**
	 * Creates a Rookie in preferred x and y values Rookie starts with.
	 * showCli is predefined as true. 
	 * 
	 * @param x new x value
	 * @param y new y value
	 */
	public Rookie(int x, int y)
	{
		super(x, y);
	}
	
	/*******************GET FUNCTIONS*******************/
	
	/*******************SET FUNTIONS*******************/

}
