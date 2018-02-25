package logic;
import java.util.LinkedList;
import java.util.Random;

import cli.ogreStatusDisplay;

public class Ogre extends Character {
	
	private boolean stunned;
	private int stunCount;
	
	private ogreStatusDisplay display;

	//CONSTRUCTOR
	
	public Ogre(int newX, int newY) {
		super(newX, newY, 'O', true);
		//club starts at right side of Ogre
		Club club = new Club(newX-1, newY);
		this.setWeapon(club);
		stunned = false;
		stunCount = 0;
		display = new ogreStatusDisplay();
	}
	
	//GET FUNCTIONS

	//SET FUNCTIONS

	//POSITIONS RELATED FUNCTIONS
	
	public char getNextMove() {
		char retChar = 'E';
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
			display.stunned(2-stunCount);
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
	
	
	public boolean isStunned() {
		return stunned;
	}

	public void setStunned(boolean stunned) {
		if(this.stunned != stunned) {
			if(stunned)
				display.justStunned();
			this.stunned = stunned;
		}
	}

	public int getStunCount() {
		return stunCount;
	}

	public void setStunCount(int stunCount) {
		this.stunCount = stunCount;
	}
	
	
	public boolean checkOgreinPreviousPosition(LinkedList<Character> ogres)
	{
		for (int i = 0; i < ogres.size(); i++)
		{
			if ((super.getPrevX() == ogres.get(i).getX() && super.getPrevY() == ogres.get(i).getY())
					|| (super.getPrevX() == ogres.get(i).getWeapon().getX() && super.getPrevY() == ogres.get(i).getWeapon().getY()))
				return true;
		}
		return false;
	}

	//HERO RELATED FUNCTIONS

}
