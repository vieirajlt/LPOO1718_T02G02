package logic;

import java.util.Random;

/**
 * This class represents a specific type of {@link Guard} (enemy).
 * This specific type (pseudo)randomly "fall asleep", stopping its
 * movement for a (pseudo)randomly amount of {@link Game} updates.
 * 
 * @author João Vieira
 * @author Susana Lima
 */
public class Drunken extends Guard{

	/**
	 * 
	 */
	private static final long serialVersionUID = -179875341590099576L;
	/**
	 * isSleeping is a flag indicating if this Drunken "sleeping"
	 */
	private boolean isSleeping;
	/**
	 * sleepCount is the controller of how many rounds will this Character
	 * remain sleeping.
	 */
	private int sleepCount;

	/*******************CONSTRUCTORS*******************/
	
	/**
	 * Creates a Drunken in preferred x and y values Drunken starts with.
	 * showCli is predefined as true.
	 * 
	 * @param x the new value of x
	 * @param y the new value of y
	 */
	public Drunken(int x, int y)
	{
		super(x, y);
		isSleeping = false;
		sleepCount = 0;
	}
	
	/*******************GET fUNCTIONS*******************/
	
	/**
	 * Retrieve the value of this Drunken isSleeping.
	 * 
	 * @return this Drunken isSleeping
	 */
	public boolean isSleeping() {
		return isSleeping;
	}

	/**
	 * Retrieve the value of this Drunken sleepCount.
	 * 
	 * @return this Drunken sleepCount
	 */
	public int getSleepCount() {
		return sleepCount;
	}

	/*******************SET fUNCTIONS*******************/

	/**
	 * Set the value of this Drunken isSleeping.
	 * 
	 * @param isSleeping the new value of isSleeping
	 */
	public void setSleeping(boolean isSleeping) {
		this.isSleeping = isSleeping;
	}
	
	/**
	 * Set the value of this Drunken sleepCount.
	 * 
	 * @param sleepCount the new value of sleepCount
	 */
	public void setSleepCount(int sleepCount) {
		this.sleepCount = sleepCount;
	}
	
	/*******************UPDATES MANAGEMENT*******************/
	
	/**
	 * Updates this Drunken status accordingly to it current state.
	 */
	public char updateGuard() {
		Random rand = new Random();
		int num = rand.nextInt(20);
		char ret = 'E';
		
		if(isSleeping)
			updateSleepingGuard();
		else
			ret = updateAwakenGuard(num, ret);
		updateDrunkenStatus();
		return ret;

	}

	/**
	 * num is a random number that determines if this Drunken will start
	 * it's sleep or not. Case it starts, another random is calculated in
	 * order to get the number of sleeping rounds. Else, it's position
	 * will be updated following this Drunken path.
	 * 
	 * @param num the value to determine if sleeping or not
	 * @param ret the char used for updating its position
	 * @return ret
	 */
	private char updateAwakenGuard(int num, char ret) {
		Random rand = new Random();
		if (num == 0)
		{
			isSleeping = true;
			sleepCount = rand.nextInt(10)+1;
		} 
		else {
			isSleeping = false;
			ret = super.updateGuard();
		}
		return ret;
	}

	/**
	 * sleepCount will be decremented and isSleeping will be updated if needed.
	 */
	private void updateSleepingGuard() {
		if (sleepCount > 0)
			sleepCount--;
		else
			isSleeping = false;
	}

	/**
	 * Set this Drunken symbol accordingly with isSleeping.
	 */
	private void updateDrunkenStatus() {
		if(isSleeping)
			super.setSymbol('g');
		else
			super.setSymbol('G');

	}
	
	/**
	 * Checks if this Drunken and character c are in capturing proximity.
	 */
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
