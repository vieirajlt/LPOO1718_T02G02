package logic;

import java.util.Random;

public class Suspicious extends Guard {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7323895084101865303L;
	private boolean isReverse;
	
	/*******************CONSTRUCTORS*******************/
	
	public Suspicious(int x, int y, boolean move)
	{
		super(x, y, move);
		isReverse = false;
	}
	
	public Suspicious(int x, int y)
	{
		this(x, y, true);
	}
	
	/*******************GET FUNTIONS*******************/
	
	public boolean isReverse() {
		return isReverse;
	}

	/*******************SET FUNTIONS*******************/
	
	public void setReverse(boolean isReverse) {
		this.isReverse = isReverse;
	}
	
	/*******************UPDATE MANAGEMENT*******************/
	
	public char updateGuard()
	{
		Random rand = new Random();
		int num = rand.nextInt(5);
		if (num == 0)
			isReverse = !isReverse;
		return super.updateGuard(isReverse);
		
	}

}
