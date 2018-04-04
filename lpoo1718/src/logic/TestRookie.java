package logic;

public class TestRookie extends Rookie{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1751977696406617461L;

	public TestRookie(int newX, int newY) {
		super(newX, newY);
	}
	
	@Override
	public boolean isMove() {
		return false;
	}

}
