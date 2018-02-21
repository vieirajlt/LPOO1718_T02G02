package logic;

import java.util.Random;

public class Suspicious extends Guard {
	
	private boolean isReverse;
	
	public Suspicious(int x, int y)
	{
		super(x, y);
		isReverse = false;
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
