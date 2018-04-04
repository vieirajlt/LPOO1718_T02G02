package logic;

public class testDrunken extends Drunken{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7527775992906518658L;

	public testDrunken(int x, int y) {
		super(x, y);
	}
	
	@Override
	public boolean isMove() {
		return false;
	}
	
}
