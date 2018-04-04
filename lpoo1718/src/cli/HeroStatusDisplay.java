package cli;


/**
 * This class contains all the information regarding an hero status 
 * that will be displayed in a user friendly way.
 * 
 * @author Jo�o Vieira
 * @author Susana Lima
 * 
 * @see Hero
 */
public class HeroStatusDisplay {

	/**
	 * Displays the information when a hero collides with a wall.
	 * 
	 * @param show parameter that specifies if the information will be displayed or not
	 */
	public void wallColliding(boolean show)
	{
		if(show)
			System.out.println("Ouch, try not to faceplant next time!");
	}

	/**
	 * Displays the information when a hero acquires an unlocker.
	 * 
	 * @param show parameter that specifies if the information will be displayed or not
	 */
	public void keyColliding(boolean show)
	{
		if(show)
			System.out.println("Key aquired.");
	}

	
	//TODO
	
	/**
	 * Displays the information when a hero reaches a lever.
	 * 
	 * @param show parameter that specifies if the information will be displayed or not
	 */
	public void leverColliding(boolean show)
	{
		if(show)
			System.out.println("Lever pushed, time to escape!");
	}

	/**
	 * Displays the information when a hero opens a door.
	 * 
	 * @param show parameter that specifies if the information will be displayed or not
	 */
	public void exitOpen(boolean show)
	{
		if(show)
			System.out.println("Exit opened, time to escape!");
	}
}
