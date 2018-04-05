package logic;

/**
 * This class represents a {@link Drunken} that is unable to move.
 * For test purposes.
 * 
 * @author João Vieira
 * @author Susana Lima
 * @see Drunken
 */
public class TestDrunken extends Drunken{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7527775992906518658L;

	/**
	 * Creates a TestDrunken in preferred x and y values TestDrunken starts with.
	 * 
	 * @param x the location coordinate
	 * @param y the location coordinate
	 */
	public TestDrunken(int x, int y) {
		super(x, y);
	}
	
	@Override
	public boolean isMove() {
		return false;
	}
	
}
