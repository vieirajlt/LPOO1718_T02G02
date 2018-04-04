package logic;

import java.util.Random;

public class Drunken extends Guard{

	/**
	 * 
	 */
	private static final long serialVersionUID = -179875341590099576L;
	private boolean isSleeping;
	private int sleepCount;
	private int sleepMax;

	/*******************CONSTRUCTORS*******************/
	
	public Drunken(int x, int y)
	{
		super(x, y);
		isSleeping = false;
		sleepCount = 0;
		sleepMax = 0;
	}
	
	/*******************GET fUNCTIONS*******************/
	
	public boolean isSleeping() {
		return isSleeping;
	}

	public int getSleepCount() {
		return sleepCount;
	}

	public int getSleepMax() {
		return sleepMax;
	}

	/*******************SET fUNCTIONS*******************/

	public void setSleeping(boolean isSleeping) {
		this.isSleeping = isSleeping;
	}
	
	public void setSleepCount(int sleepCount) {
		this.sleepCount = sleepCount;
	}
	
	public void setSleepMax(int sleepMax) {
		this.sleepMax = sleepMax;
	}
	
	/*******************UPDATES MANAGEMENT*******************/
	
	public char updateGuard() {
		Random rand = new Random();
		int num = rand.nextInt(20);
		char ret = 'E';
		
		if(isSleeping)
			updateSleepingGuard();
		else
			ret = updateAwakenGuard(rand, num, ret);
		updateDrunkenStatus();
		return ret;

	}

	private char updateAwakenGuard(Random rand, int num, char ret) {
		if (num == 0)
		{
			isSleeping = true;
			sleepMax = rand.nextInt(10)+1;
			sleepCount = 1;
		} 
		else {
			isSleeping = false;
			ret = super.updateGuard();
		}
		return ret;
	}

	private void updateSleepingGuard() {
		if (sleepCount < sleepMax)
			sleepCount++;
		else
			isSleeping = false;
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
