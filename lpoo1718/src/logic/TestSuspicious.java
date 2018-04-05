package logic;

/**
 * This class represents a {@link Suspicious} that is unable to move.
 * For test purposes.
 * 
 * @author João Vieira
 * @author Susana Lima
 * @see Suspicious
 */
public class TestSuspicious extends Suspicious{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6383155219245962984L;

	/**
	 * Creates a TestSuspicious in preferred x and y values TestSuspicious starts with.
	 * 
	 * @param x the location coordinate
	 * @param y the location coordinate
	 */
	public TestSuspicious(int x, int y) {
		super(x, y);
	}
	
	@Override
	public boolean isMove() {
		return false;
	}

}
