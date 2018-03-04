package logic;

import java.util.Random;

public class Drunken extends Guard{

	private boolean isSleeping;
	private int sleepCount;
	private int sleepMax;

	public Drunken(int x, int y, boolean move)
	{
		super(x, y, move);
		isSleeping = false;
		sleepCount = 0;
		sleepMax = 0;
	}
	
	public Drunken(int x, int y)
	{
		this(x, y, true);
	}

	public char updateGuard() {
		Random rand = new Random();
		int num = rand.nextInt(20);
		char ret = 'E';
		
		if(isSleeping)
		{
			if (sleepCount < sleepMax)
				sleepCount++;
			else
				isSleeping = false;
		}
		else if (num == 0)
		{
			isSleeping = true;
			sleepMax = rand.nextInt(10)+1;
			sleepCount = 1;
		} 
		else {
			isSleeping = false;
			ret = super.updateGuard();
		}
		updateDrunkenStatus();
		return ret;

	}

	private void updateDrunkenStatus() {
		if(isSleeping)
			super.setSymbol('g');
		else
			super.setSymbol('G');

	}
	
	
	public boolean isCaptured(Character c) {
		if(isSleeping) {
			//if guard is stepped, he awakes
			if((Math.abs(c.getY()-super.getY()) + Math.abs(c.getX()-super.getX())) < 1) {
				isSleeping = false;
				((Hero)c).setSteppedGuard(true);
				return true;
			}
			return false;
		} else {
			return super.isCaptured(c);
		}
	}


}
