
public class Guard extends Character{
	private char route[];
	private int routeStep;

	//CONSTRUCTORS
	
	public Guard(int newX, int newY) {
		super(newX, newY, 'G');
		initializeRoute();
	}

	//POSITIONS RELATED FUNCTIONS
	
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

	public char updateGuard() {
		char command = route[routeStep++];
		routeStep %= route.length;
		return command;
	}

	//HERO RELATED FUNCTIONS
	
	public boolean isCaptured(Hero hero) {
		//if distance to guard is equal or inferior to 1, get captured
		if((Math.abs(hero.getY()-super.getY()) + Math.abs(hero.getX()-super.getX())) <= 1) {
			return true;
		}
		return false;
	}

}
