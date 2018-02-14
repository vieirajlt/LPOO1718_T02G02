
public class Hero {
	private int X;
	private int Y;
	boolean escaped;
	
	public Hero(int valX, int valY) {
		X = valX;
		Y = valY;
		escaped = false;
	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

	public void setX(int newX) {
		X = newX;
	}

	public void setY(int newY) {
		Y = newY;
	}

	public boolean isColliding(Map map) {
		if((map.getMapPosition(X, Y) == 'X') || (map.getMapPosition(X, Y) == 'I'))
			return true;
		else if(map.getMapPosition(X, Y) == 'S') {
			escaped = true;
		}
		return false;
	}

	public boolean isLeverReached(Map map) {
		if(map.getMapPosition(X, Y) == 'k')
			return true;
		return false;
	}
	
	public void updateHeroPosition(Map map, char command) {

		switch(command) {
		case 'u': case 'U':
			--Y;
			if(isColliding(map)) {
				System.out.println("Ouch, try not to faceplant next time!");
				++Y;
			} else if(isLeverReached(map)) {
				System.out.println("Exit opened, time to escape!");
				++Y;
				map.leverReached();
			} else {
				map.setMapPosition(X, Y+1, ' ');
				map.setMapPosition(X, Y, 'H');
			}
			break;
		case 'd': case 'D':
			++Y;
			if(isColliding(map)) {
				System.out.println("Ouch, try not to faceplant next time!");
				--Y;
			} else if(isLeverReached(map)) {
				System.out.println("Exit opened, time to escape!");
				--Y;
				map.leverReached();
			} else {
				map.setMapPosition(X, Y-1, ' ');
				map.setMapPosition(X, Y, 'H');
			}
			break;
		case 'l': case 'L':
			--X;
			if(isColliding(map)) {
				System.out.println("Ouch, try not to faceplant next time!");
				++X;
			} else if(isLeverReached(map)) {
				System.out.println("Exit opened, time to escape!");
				++X;
				map.leverReached();
			} else {
				map.setMapPosition(X+1, Y, ' ');
				map.setMapPosition(X, Y, 'H');
			}
			break;
		case 'r': case 'R':
			++X;
			if(isColliding(map)) {
				System.out.println("Ouch, try not to faceplant next time!");
				--X;
			} else if(isLeverReached(map)) {
				System.out.println("Exit opened, time to escape!");
				--X;
				map.leverReached();
			} else {
				map.setMapPosition(X-1, Y, ' ');
				map.setMapPosition(X, Y, 'H');
			}
			break;
		default:
			System.out.println("Wrong input\n");
			break;
		}

	}

}
