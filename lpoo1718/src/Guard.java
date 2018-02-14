
public class Guard {
	int X;
	int Y;
	
	public Guard(int newX, int newY) {
		X = newX;
		Y = newY;
	}
	
	public boolean isCaptured(Hero hero) {
		//if distance to guard is equal or inferior to 1, get captured
		if((Math.abs(hero.getY()-Y) + Math.abs(hero.getX()-X)) <= 1) {
			return true;
		}
		return false;
	}
	
	public void setX(int newX) {
		X = newX;
	}
	
	public void setY(int newY) {
		Y = newY;
	}
	
	public int getX() {
		return X;
	}
	
	public int getY() {
		return Y;
	}
}
