package cli;


/**
 * This class contains all the information regarding an {@link Hero} status 
 * that will be displayed in a user friendly way.
 * 
 * @author Jo�o Vieira
 * @author Susana Lima
 * 
 * @see Hero
 */
public class HeroStatusDisplay {

	/**
	 * Displays the information when an {@link Hero} collides with a wall.
	 * 
	 * @param show parameter that specifies if the information will be displayed or not
	 */
	public void wallColliding(boolean show)
	{
		if(show)
			System.out.println("Ouch, try not to faceplant next time!");
	}

	/**
	 * Displays the information when an {@link Hero} acquires an unlocker.
	 * 
	 * @param show parameter that specifies if the information will be displayed or not
	 */
	public void keyColliding(boolean show)
	{
		if(show)
			System.out.println("Key aquired.");
	}

	
	
	/**
	 * Displays the information when an {@link Hero} reaches a lever.
	 * 
	 * @param show parameter that specifies if the information will be displayed or not
	 */
	public void leverColliding(boolean show)
	{
		if(show)
			System.out.println("Lever pushed, time to escape!");
	}

	/**
	 * Displays the information when an {@link Hero} opens a door.
	 * 
	 * @param show parameter that specifies if the information will be displayed or not
	 */
	public void exitOpen(boolean show)
	{
		if(show)
			System.out.println("Exit opened, time to escape!");
	}
}
