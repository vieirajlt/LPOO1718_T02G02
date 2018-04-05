package logic;

/**
 * This class represents a {@link Rookie} that is unable to move.
 * For test purposes.
 * 
 * @author João Vieira
 * @author Susana Lima
 * @see Rookie
 */
public class TestRookie extends Rookie{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1751977696406617461L;

	/**
	 * Creates a TestRookie in preferred x and y values TestRookie starts with.
	 * 
	 * @param x the location coordinate
	 * @param y the location coordinate
	 */
	public TestRookie(int newX, int newY) {
		super(newX, newY);
	}
	
	@Override
	public boolean isMove() {
		return false;
	}

}
