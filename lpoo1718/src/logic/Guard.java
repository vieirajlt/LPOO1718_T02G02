package logic;

public class Guard extends Character{
	private char route[];
	private int routeStep;

	//CONSTRUCTORS

	public Guard(int newX, int newY) {
		this(newX, newY, true);
	}
	
	public Guard(int newX, int newY, boolean move) {
		super(newX, newY, 'G', false, move);
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
		if(!isMove())
			return 'E';
		
		char command = route[routeStep++];
		routeStep %= route.length;
		return command;
	}

	public char updateGuard(boolean isReverse) {
		if(!isMove())
			return 'E';
		char command;
		if (isReverse) {
			--routeStep;
			routeStep += route.length;
			routeStep %= route.length;
			command = route[routeStep];
			command = super.reverseCommand(command);
		}
		else {
			command = route[routeStep++];
			routeStep %= route.length;
		}
		return command;
	}
	
	public char[] getRoute()
	{
		return this.route;
	}

	public int getRouteStep() {
		return routeStep;
	}

	public void setRouteStep(int routeStep) {
		this.routeStep = routeStep;
	}
	
}
