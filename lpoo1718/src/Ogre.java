import java.util.Random;

public class Ogre extends Character {

	public Ogre(int newX, int newY) {
		super(newX, newY, 'O');

	}

	public boolean isCaptured(Hero hero) {
		//if distance to ogre is equal or inferior to 1, get captured
		if((Math.abs(hero.getY()-super.getY()) + Math.abs(hero.getX()-super.getX())) <= 1) {
			return true;
		}
		return false;
	}

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
}
