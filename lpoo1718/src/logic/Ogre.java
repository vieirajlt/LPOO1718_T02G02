package logic;

import java.util.Random;

import cli.ogreStatusDisplay;

public class Ogre extends Character {
	
	private boolean stunned;
	private int stunCount;
	
	static private ogreStatusDisplay display = new ogreStatusDisplay();

	/*******************CONSTRUCTOR*******************/
	
	public Ogre(int newX, int newY, boolean newMove, boolean MoveClub) {
		super(newX, newY, 'O', true, newMove);
		//club starts at right side of Ogre
		Club club = new Club(newX-1, newY,MoveClub);
		this.setWeapon(club);
		stunned = false;
		stunCount = 0;
	}
	
	public Ogre(int newX, int newY) {
		this(newX,newY,true,true);
	}
	
	/*******************GET FUNCTIONS*******************/
	
	public boolean isStunned() {
		return stunned;
	}

	public int getStunCount() {
		return stunCount;
	}

	/*******************SET FUNCTIONS*******************/
	
	public void setRandomMovement(boolean op) {
		this.setMove(op);
		this.getWeapon().setMove(op);
	}
	
	public void setStunned(boolean stunned) {
		if(this.stunned != stunned) {
			if(stunned)
				display.justStunned(isShowCli());
			this.stunned = stunned;
		}
	}
	
	public void setStunCount(int stunCount) {
		this.stunCount = stunCount;
	}

	/*******************UPDATES MANAGEMENT*******************/
	
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

}
