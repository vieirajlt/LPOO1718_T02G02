package logic;

import java.util.Random;

public class Drunken extends Guard{

	private boolean isSleeping;
	private int sleepCount;
	private int sleepMax;

	public Drunken(int x, int y)
	{
		super(x, y);
		isSleeping = false;
		sleepCount = 0;
		sleepMax = 0;
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
	
	public boolean isCaptured(Hero hero) {
		if(isSleeping) {
			//if guard is stepped, he awakes
			if((Math.abs(hero.getY()-super.getY()) + Math.abs(hero.getX()-super.getX())) < 1) {
				isSleeping = false;
				hero.setSteppedGuard(true);
				return true;
			}
			return false;
		} else
			return super.isCaptured(hero);
	}


}
