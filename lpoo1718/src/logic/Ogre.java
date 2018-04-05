package logic;

import java.util.Random;

import cli.OgreStatusDisplay;

/**
 * This class represents a specific type of {@link Character}.
 * This specific type is one of the Hero's enemies (currently present in the 
 * second level and player created levels).
 * The Ogre has (pseudo)random movement patern.
 * 
 * @author João Vieira
 * @author Susana Lima
 *
 */
public class Ogre extends Character {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 678238776851162692L;
	/**
	 * stunned is the flag that indicates if this Ogre is stunned
	 */
	private boolean stunned;
	/**
	 * stunCount is a counter for the number of remaining stunned rounds
	 */
	private int stunCount;
	
	/**
	 * display is the user friendly information representing this Ogre status.
	 */
	static private OgreStatusDisplay display = new OgreStatusDisplay();

	/*******************CONSTRUCTOR*******************/
	
	/**
	 * Creates an Ogre in preferred x and y values Ogre starts with.
	 * This Ogre can be allowed(or not) to move its {@link Weapon}.
	 * stunned initial value is false and its counter is 0.
	 * 
	 * @param x the location coordinate
	 * @param y the location coordinate
	 * @param moveClub the flag indicating Weapon movement
	 */
	public Ogre(int newX, int newY, boolean MoveClub) {
		super(newX, newY, 'O', true);
		//club starts at right side of Ogre
		Club club = new Club(newX-1, newY,MoveClub);
		this.setWeapon(club);
		stunned = false;
		stunCount = 0;
	}
	
	/*******************GET FUNCTIONS*******************/
	
	/**
	 * Retrieve the value of this Ogre stunned.
	 * 
	 * @return this Ogre stunned 
	 */
	public boolean isStunned() {
		return stunned;
	}

	/**
	 * Retrieve the value of this Ogre stunCount.
	 * 
	 * @return this Ogre stunCount
	 */
	public int getStunCount() {
		return stunCount;
	}

	/*******************SET FUNCTIONS*******************/
	
	/**
	 * Set the value of this Ogre stunned.
	 * 
	 * @param show the new value of stunned
	 */
	public void setStunned(boolean stunned) {
		if(this.stunned != stunned) {
			if(stunned)
				display.justStunned(isShowCli());
			this.stunned = stunned;
		}
	}
	
	/**
	 * Set the value of this Ogre stunCount.
	 * 
	 * @param show the new value of stunCount
	 */
	public void setStunCount(int stunCount) {
		this.stunCount = stunCount;
	}

	/*******************UPDATES MANAGEMENT*******************/
	
	/**
	 * Generates a (pseudo)random char representing a movement direction
	 * in order to update this Ogre position on {@link Map} (x and y values).
	 * 
	 * @return the new movement corresponding char
	 */
	public char getNextMove() {
		char retChar = 'E';
		if (!isMove())
			return retChar;
		Random rand = new Random();

		do {
			int randomNumber = rand.nextInt(4);

			switch(randomNumber) {
			case 0: 
				return 'u';
			case 1:
				return 'd';
			case 2:
				return 'l';
			case 3:
				return 'r';
			default:
				return 'E';
			}
		} while(retChar == 'E');
	}
	
	/**
	 * Takes two char as reference for updating both this Ogre position on 
	 * {@link Map} (x and y values) and its Weapon accordingly to its new
	 * position.
	 * 
	 * @param cOgre the new Ogre movement corresponding char
	 * @param cClub the new Weapon movement corresponding char
	 */
	public void updatePosition(char cOgre, char cClub) {
		if(stunned) {
			super.setSymbol('8');
			++stunCount;
			display.stunned(2-stunCount, isShowCli());
			stunCount %= 2;
			if(stunCount == 0)
				stunned = false;
		} else {
			super.updatePosition(cOgre);
			super.setSymbol('O');
		}
		
		int X = super.getX();
		int Y = super.getY();
		super.getWeapon().setPosition(X, Y);
		super.getWeapon().updatePosition(cClub);
	}

	@Override
	public boolean isMove() {
		return true;
	}

}
