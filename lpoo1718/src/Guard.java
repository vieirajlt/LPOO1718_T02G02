
public class Guard {
	private int X;
	private int Y;
	private char route[];
	private int routeStep;

	public Guard(int newX, int newY) {
		X = newX;
		Y = newY;
		initializeRoute();
	}

	public void initializeRoute() {
		routeStep = 0;
		route = new char[24];

		route[0] = 'l';

		for(int i = 1; i < 5; ++i)
			route[i] = 'd';

		for(int i = 5; i < 11; ++i)
			route[i] = 'l';

		route[11] = 'd';

		for(int i = 12; i < 19; ++i)
			route[i] = 'r';

		for(int i = 19; i <= 23; ++i)
			route[i] = 'u';
	}

	public void updateGuardPosition(Map map) {

		switch(route[routeStep++]) {
		case 'u': case 'U':
			--Y;
			map.setMapPosition(X, Y+1, ' ');
			map.setMapPosition(X, Y, 'G');
			break;
		case 'd': case 'D':
			++Y;
			map.setMapPosition(X, Y-1, ' ');
			map.setMapPosition(X, Y, 'G');
			break;
		case 'l': case 'L':
			--X;
			map.setMapPosition(X+1, Y, ' ');
			map.setMapPosition(X, Y, 'G');
			break;
		case 'r': case 'R':
			++X;
			map.setMapPosition(X-1, Y, ' ');
			map.setMapPosition(X, Y, 'G');
			break;
		default:
			break;
		}
		routeStep %= route.length;
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
