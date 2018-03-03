package logic;

import java.util.Random;

public class Suspicious extends Guard {
	
	private boolean isReverse;
	
	public boolean isReverse() {
		return isReverse;
	}

	public void setReverse(boolean isReverse) {
		this.isReverse = isReverse;
	}

	public Suspicious(int x, int y, boolean move)
	{
		super(x, y, move);
		isReverse = false;
	}
	
	public Suspicious(int x, int y)
	{
		this(x, y, true);
	}
	
	public char updateGuard()
	{
		Random rand = new Random();
		int num = rand.nextInt(5);
		if (num == 0)
			isReverse = !isReverse;
		return super.updateGuard(isReverse);
		
	}

}
