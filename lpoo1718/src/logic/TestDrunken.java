package logic;

public class TestDrunken extends Drunken{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7527775992906518658L;

	public TestDrunken(int x, int y) {
		super(x, y);
	}
	
	@Override
	public boolean isMove() {
		return false;
	}
	
}
