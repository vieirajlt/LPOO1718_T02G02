package logic;

public class testRookie extends Rookie{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1751977696406617461L;

	public testRookie(int newX, int newY) {
		super(newX, newY);
	}
	
	@Override
	public boolean isMove() {
		return false;
	}

}
