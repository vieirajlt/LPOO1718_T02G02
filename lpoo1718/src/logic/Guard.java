package logic;

/**
 * This class represents a specific type of {@link Character}.
 * This specific type is one of the Hero's enemies (currently present in the first level).
 * The Guard has a predefined route that specifies its movements.
 * 
 * @author João Vieira
 * @author Susana Lima
 */
public class Guard extends Character{
	
	private static final long serialVersionUID = -6482594357189490814L;
	
	/**
	 * Represents the array that stores the Guard's movements.
	 */
	private char route[];
	
	/**
	 * Represents the current index of this Guard route array (updated everytime the Guard moves)
	 */
	private int routeStep;

	/*******************CONSTRUCTORS*******************/
	/**
	 * Creates a Guard in preferred x and y values. Initializes the Guard's route.
	 * 
	 * @param newX the new value of x
	 * @param newY the new value of y
	 */
	public Guard(int newX, int newY) {
		super(newX, newY, 'G', false);
		initializeRoute();
	}
	
	/*******************GET FUNTIONS*******************/
	/**
	 * Retrieve this Guard route.
	 * 
	 * @return array containing this Guard route
	 */
	public char[] getRoute()
	{
		return this.route;
	}
	
	/**
	 * Retrieve this Guard routeStep value.
	 * 
	 * @return this Guard routeStep
	 */
	public int getRouteStep() {
		return routeStep;
	}
	
	/*******************SET FUNCTIONS*******************/
	
	/**
	 * Set the value of this Guard routeStep.
	 * 
	 * @param routeStep this Guard new routeStep value
	 */
	public void setRouteStep(int routeStep) {
		this.routeStep = routeStep;
	}

	/**
	 * Set this Guard route to the one given.
	 * 
	 * @param route this Guard's new route
	 */
	public void setRoute(char[] route) {
		this.route = route;
	}

	/*******************UPDATES MANAGEMENT*******************/

	/**
	 * Initialize this Guard route (to a predefined route)
	 */
	public void initializeRoute() {
		routeStep = 0;
		route = new char[24];

		route[0] = 'l';


		for(int i = 5; i < 11; ++i)
			route[i] = 'l';
		
		for(int i = 1; i < 5; ++i)
			route[i] = 'd';

		route[11] = 'd';

		for(int i = 19; i <= 23; ++i)
			route[i] = 'u';
		
		for(int i = 12; i < 19; ++i)
			route[i] = 'r';
	}

	/**
	 * If the Guard can move retrieves the next value of its route. 
	 * 
	 * @return the next value of this Guard route
	 */
	public char updateGuard() {
		if(!isMove())
			return 'E';
		
		char command = route[routeStep++];
		routeStep %= route.length;
		return command;
	}

	/**
	 * If the Guard can move retrieves the next value of its route considering the route's orientation.
	 * 
	 * @param isReverse true if the route is to be done in the reverse sense, false otherwise
	 * 
	 * @return the next value of this Guard route
	 */
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

	//TODO
	@Override
	public boolean isMove() {
		return true;
	}
	
}
