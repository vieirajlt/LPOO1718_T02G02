package logic;

public class testSuspicious extends Suspicious{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6383155219245962984L;

	public testSuspicious(int x, int y) {
		super(x, y);
	}
	
	@Override
	public boolean isMove() {
		return false;
	}

}
