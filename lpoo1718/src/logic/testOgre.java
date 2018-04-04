package logic;

public class testOgre extends Ogre{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5660527346620420027L;

	public testOgre(int newX, int newY) {
		super(newX, newY);
	}
	
	public testOgre(int newX, int newY, boolean moveClub) {
		super(newX, newY, moveClub);
	}
	
	@Override
	public boolean isMove() {
		return false;
	}

}
