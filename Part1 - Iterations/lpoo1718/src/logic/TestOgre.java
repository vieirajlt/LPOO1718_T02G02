package logic;

/**
 * This class represents a {@link Ogre} that is unable to move.
 * For test purposes.
 * 
 * @author João Vieira
 * @author Susana Lima
 * @see Ogre
 */
public class TestOgre extends Ogre{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5660527346620420027L;
	
	/**
	 * Creates a TestOgre in preferred x and y values TestOgre starts with.
	 * This TestOgre can be allowed(or not) to move its {@link Weapon}.
	 * 
	 * @param newX the location coordinate
	 * @param newY the location coordinate
	 * @param moveClub the flag indicating Weapon movement
	 */
	public TestOgre(int newX, int newY, boolean moveClub) {
		super(newX, newY, moveClub);
	}
	
	@Override
	public boolean isMove() {
		return false;
	}

}
