package logic;
import java.util.Random;

public class Ogre extends Character {
	private Character club;

	//CONSTRUCTOR
	
	public Ogre(int newX, int newY) {
		super(newX, newY, 'O');
		//club starts at rigth side of Ogre
		club = new Club(newX-1, newY);
	}
	
	//GET FUNCTIONS
	
	public Character getClub() {
		return club;
	}

	//SET FUNCTIONS
	
	public void setClub(Character newClub) {
		club = newClub;
	}

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
		super.updatePosition(cOgre);
		int X = super.getX();
		int Y = super.getY();
		club.setX(X);
		club.setY(Y);
		club.updatePosition(cClub);
	}

	//HERO RELATED FUNCTIONS
	
	public boolean isCaptured(Hero hero) {
		//if distance to ogre is equal or inferior to 1, get captured
		if((Math.abs(hero.getY()-super.getY()) + Math.abs(hero.getX()-super.getX())) <= 1) {
			return true;
		}
		return false;
	}

	public boolean isHit(Hero hero) {
		//if distance to club is equal or inferior to 1, get fatality
		if((Math.abs(hero.getY()-club.getY()) + Math.abs(hero.getX()-club.getX())) <= 1) {
			return true;
		}
		return false;
	}

}
