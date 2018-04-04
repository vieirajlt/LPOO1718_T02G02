package logic;

public class TestOgre extends Ogre{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5660527346620420027L;

	public TestOgre(int newX, int newY) {
		super(newX, newY);
	}
	
	public TestOgre(int newX, int newY, boolean moveClub) {
		super(newX, newY, moveClub);
	}
	
	@Override
	public boolean isMove() {
		return false;
	}

}
